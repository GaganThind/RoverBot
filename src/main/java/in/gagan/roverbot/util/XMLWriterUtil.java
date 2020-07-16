package in.gagan.roverbot.util;

import java.io.File;
import java.util.List;
import java.util.Map;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.json.simple.JSONObject;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import in.gagan.roverbot.constant.ApplicationConstants;
import in.gagan.roverbot.model.Position;

/**
 * This class provides methods to write data to XML file
 * 
 * @author thindgagan
 *
 */
public class XMLWriterUtil {
	
	/**
	 * Method to write XML from position obj only
	 * 
	 * @param position
	 * @param path
	 * @throws ParserConfigurationException
	 * @throws TransformerException
	 */
	public static void writeToOutputXMLFile(Position position) throws ParserConfigurationException, TransformerException {
		
		final String path = ApplicationConstants.ROVER_BOT_OUTPUT_XML_NAME;
		
		DocumentBuilder docBuilder = DocumentBuilderFactory.newInstance().newDocumentBuilder();

		// Root elements
		Document doc = docBuilder.newDocument();
		Element rootElement = doc.createElement(ApplicationConstants.ROVER_BOT);
		doc.appendChild(rootElement);

		// Position elements
		Element positionElement = doc.createElement(ApplicationConstants.POSITION);
		rootElement.appendChild(positionElement);

		// Direction elements
		Element direction = doc.createElement(ApplicationConstants.DIRECTION);
		direction.appendChild(doc.createTextNode(String.valueOf(position.getDirection())));
		positionElement.appendChild(direction);

		// X-axis elements
		Element positionX = doc.createElement(ApplicationConstants.X);
		positionX.appendChild(doc.createTextNode(String.valueOf(position.getX())));
		positionElement.appendChild(positionX);

		// Y-axis elements
		Element positionY = doc.createElement(ApplicationConstants.Y);
		positionY.appendChild(doc.createTextNode(String.valueOf(position.getY())));
		positionElement.appendChild(positionY);
		
		// write the content into XML file
		writeXML(doc, path);
	}
	
	/**
	 * Write the input file to XML
	 * 
	 * @param jsonObj
	 * @throws ParserConfigurationException
	 * @throws TransformerException 
	 */
	public static void writeToInputXMLFile(JSONObject jsonObj) throws ParserConfigurationException, TransformerException {
		
		final String path = ApplicationConstants.ROVER_BOT_INPUT_XML_NAME;
		
		Map<?, ?> positionMap = (Map<?, ?>) jsonObj.get(ApplicationConstants.POSITION);
		char direction = ((String) positionMap.get(ApplicationConstants.DIRECTION)).charAt(0);
		int positionX = (int) positionMap.get(ApplicationConstants.X);
		int positionY = (int) positionMap.get(ApplicationConstants.Y);
		
		DocumentBuilder docBuilder = DocumentBuilderFactory.newInstance().newDocumentBuilder();

		// Root elements
		Document doc = docBuilder.newDocument();
		Element rootElement = doc.createElement(ApplicationConstants.ROVER_BOT);
		doc.appendChild(rootElement);

		// Position elements
		Element positionElement = doc.createElement(ApplicationConstants.POSITION);
		rootElement.appendChild(positionElement);

		// Direction elements
		Element directionElement = doc.createElement(ApplicationConstants.DIRECTION);
		directionElement.appendChild(doc.createTextNode(String.valueOf(direction)));
		positionElement.appendChild(directionElement);

		// X-axis elements
		Element XElement = doc.createElement(ApplicationConstants.X);
		XElement.appendChild(doc.createTextNode(String.valueOf(positionX)));
		positionElement.appendChild(XElement);

		// Y-axis elements
		Element YElement = doc.createElement(ApplicationConstants.Y);
		YElement.appendChild(doc.createTextNode(String.valueOf(positionY)));
		positionElement.appendChild(YElement);
		
		// Move elements
		Element moveElement = doc.createElement(ApplicationConstants.MOVE);
		rootElement.appendChild(moveElement);
		
		List<?> moveArray = (List<?>) jsonObj.get(ApplicationConstants.MOVE);
		Map<?, ?> mapObj = null;
		for (Object obj : moveArray) {

			mapObj = (Map<?, ?>) obj;
			
			// Order elements
			Element order = doc.createElement(ApplicationConstants.ORDER);
			order.appendChild(doc.createTextNode(String.valueOf(mapObj.get(ApplicationConstants.ORDER))));
			moveElement.appendChild(order);

			if (mapObj.containsKey(ApplicationConstants.LEFT)) {
				Element LElement = doc.createElement(ApplicationConstants.LEFT);
				LElement.appendChild(doc.createTextNode(String.valueOf(mapObj.get(ApplicationConstants.LEFT))));
				moveElement.appendChild(LElement);
			} else if (mapObj.containsKey(ApplicationConstants.RIGHT)) {
				Element RElement = doc.createElement(ApplicationConstants.RIGHT);
				RElement.appendChild(doc.createTextNode(String.valueOf(mapObj.get(ApplicationConstants.RIGHT))));
				moveElement.appendChild(RElement);
			}
			
			if (mapObj.containsKey(ApplicationConstants.FORWARD)) {
				Element FElement = doc.createElement(ApplicationConstants.FORWARD);
				FElement.appendChild(doc.createTextNode(String.valueOf(mapObj.get(ApplicationConstants.FORWARD))));
				moveElement.appendChild(FElement);
			} else if (mapObj.containsKey(ApplicationConstants.BACKWARD)) {
				Element BElement = doc.createElement(ApplicationConstants.BACKWARD);
				BElement.appendChild(doc.createTextNode(String.valueOf(mapObj.get(ApplicationConstants.BACKWARD))));
				moveElement.appendChild(BElement);
			}
		}
		
		// write the content into XML file
		writeXML(doc, path);

	}
	
	/**
	 * Write to XML file
	 * 
	 * @param doc
	 * @param path
	 * @throws TransformerException
	 */
	private static void writeXML(Document doc, final String path) throws TransformerException {
		TransformerFactory transformerFactory = TransformerFactory.newInstance();
		Transformer transformer = transformerFactory.newTransformer();
		DOMSource source = new DOMSource(doc);
		StreamResult result = new StreamResult(new File(path));

		transformer.transform(source, result);
	}

}
