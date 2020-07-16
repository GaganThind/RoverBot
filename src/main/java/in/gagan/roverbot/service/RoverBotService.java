package in.gagan.roverbot.service;

import java.io.File;
import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.springframework.stereotype.Service;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import in.gagan.roverbot.constant.ApplicationConstants;
import in.gagan.roverbot.model.Move;
import in.gagan.roverbot.model.Position;
import in.gagan.roverbot.model.RoverBotInput;

/**
 * Rover bot service class to handle the movement and provide final position
 * 
 * @author thindgagan
 *
 */
@Service
public class RoverBotService {
	
	/**
	 * Move the rover bot based on provided inputs
	 * 
	 * @param roverBotInput
	 * @return Position
	 */
	public Position moveRoverBot(RoverBotInput roverBotInput) {
		Position initialPosition = roverBotInput.getPosition();
		char currentDirection = initialPosition.getDirection();
		int currentPositionX = initialPosition.getX();
		int currentPositionY = initialPosition.getY();

		for (Move move : roverBotInput.getMoves()) {

			// Change current direction
			currentDirection = currentDirectionOpt(move.getRotationAngle(), currentDirection);

			// Move rover on the x-axis
			currentPositionX = moveRoverOnXAxis(currentDirection, currentPositionX, move.getMoveSteps());

			// Move rover on the y-axis
			currentPositionY = moveRoverOnYAxis(currentDirection, currentPositionY, move.getMoveSteps());

			System.out.println("Current Direction: " + currentDirection + " X: " + currentPositionX + " Y: " + currentPositionY);

		}

		return new Position(currentDirection, currentPositionX, currentPositionY);
	}

	/**
	 * Fetch the rover bot position saved in XML
	 * 
	 * @return Position
	 * @throws ParserConfigurationException
	 * @throws IOException
	 * @throws SAXException
	 * @throws Exception
	 */
	public Position roverBotPosition() throws ParserConfigurationException, SAXException, IOException {
		File xmlFile = new File(ApplicationConstants.ROVER_BOT_OUTPUT_XML_NAME);
		DocumentBuilder docBuilder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
		
		Document doc = null;
		try {
			doc = docBuilder.parse(xmlFile);
		} catch(IOException e) {
			throw new IOException("Kindly call the first API to generate the output file.");
		}

		doc.getDocumentElement().normalize();

		Position position = null;
		String direction = null;
		String positionX = null;
		String positionY = null;
		NodeList nodeList = doc.getElementsByTagName(ApplicationConstants.POSITION);

		Node node = nodeList.item(0);

		if (node.getNodeType() == Node.ELEMENT_NODE) {

			Element eElement = (Element) node;

			direction = eElement.getElementsByTagName(ApplicationConstants.DIRECTION).item(0).getTextContent();
			positionX = eElement.getElementsByTagName(ApplicationConstants.X).item(0).getTextContent();
			positionY = eElement.getElementsByTagName(ApplicationConstants.Y).item(0).getTextContent();
			position = new Position(direction.charAt(0), Integer.valueOf(positionX), Integer.valueOf(positionY));
		}

		return position;
	}

	/**
	 * This method returns the direction after applying the rotation
	 * 
	 * @param rotationAngle
	 * @param currentDirection
	 * @return
	 */
	private char currentDirectionOpt(int rotationAngle, char currentDirection) {
		if (rotationAngle == 90 || rotationAngle == -270) {
			if (currentDirection == ApplicationConstants.NORTH) {
				currentDirection = ApplicationConstants.EAST;
			} else if (currentDirection == ApplicationConstants.SOUTH) {
				currentDirection = ApplicationConstants.WEST;
			} else if (currentDirection == ApplicationConstants.EAST) {
				currentDirection = ApplicationConstants.SOUTH;
			} else if (currentDirection == ApplicationConstants.WEST) {
				currentDirection = ApplicationConstants.NORTH;
			}
		} else if (rotationAngle == 180 || rotationAngle == -180) {
			if (currentDirection == ApplicationConstants.NORTH) {
				currentDirection = ApplicationConstants.SOUTH;
			} else if (currentDirection == ApplicationConstants.SOUTH) {
				currentDirection = ApplicationConstants.NORTH;
			} else if (currentDirection == ApplicationConstants.EAST) {
				currentDirection = ApplicationConstants.WEST;
			} else if (currentDirection == ApplicationConstants.WEST) {
				currentDirection = ApplicationConstants.EAST;
			}
		} else if (rotationAngle == 270 || rotationAngle == -90) {
			if (currentDirection == ApplicationConstants.NORTH) {
				currentDirection = ApplicationConstants.WEST;
			} else if (currentDirection == ApplicationConstants.SOUTH) {
				currentDirection = ApplicationConstants.EAST;
			} else if (currentDirection == ApplicationConstants.EAST) {
				currentDirection = ApplicationConstants.NORTH;
			} else if (currentDirection == ApplicationConstants.WEST) {
				currentDirection = ApplicationConstants.SOUTH;
			}
		}

		return currentDirection;
	}

	/**
	 * This method provides the x coordinate for the rover bot 
	 * 
	 * @param currentDirection
	 * @param currentPositionX
	 * @param steps
	 * @return
	 */
	private int moveRoverOnXAxis(char currentDirection, int currentPositionX, int steps) {
		if (currentDirection == ApplicationConstants.EAST) {
			currentPositionX += steps;
		} else if (currentDirection == ApplicationConstants.WEST) {
			currentPositionX -= steps;
		}

		return currentPositionX;
	}

	/**
	 * This method provides the y coordinate for the rover bot
	 * 
	 * @param currentDirection
	 * @param currentPositionY
	 * @param steps
	 * @return
	 */
	private int moveRoverOnYAxis(char currentDirection, int currentPositionY, int steps) {
		if (currentDirection == ApplicationConstants.NORTH) {
			currentPositionY += steps;
		} else if (currentDirection == ApplicationConstants.SOUTH) {
			currentPositionY -= steps;
		}

		return currentPositionY;
	}
	
}
