package in.gagan.roverbot.controller;

import java.io.IOException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;

import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.xml.sax.SAXException;

import com.fasterxml.jackson.core.JsonProcessingException;

import in.gagan.roverbot.model.Position;
import in.gagan.roverbot.model.RoverBotInput;
import in.gagan.roverbot.model.RoverBotOutput;
import in.gagan.roverbot.service.RoverBotService;
import in.gagan.roverbot.util.JSONConverterUtil;
import in.gagan.roverbot.util.JSONValidator;
import in.gagan.roverbot.util.XMLWriterUtil;

/**
 * This controller class provides API for the rover bot movement and for rover
 * bot current position.
 * 
 * @author thindgagan
 *
 */
@RestController()
@RequestMapping(value = "/api/v1")
public class RoverBotController {

	@Autowired
	private RoverBotService roverBotSvc;
	
	/**
	 * Move the rover bot based on the inputs
	 * 
	 * @param jsonObject
	 * @return Position - This object determines the final position of the bot
	 * @throws IllegalArgumentException
	 * @throws JsonProcessingException
	 * @throws TransformerException
	 * @throws ParserConfigurationException
	 */
	@PostMapping(value = "/roverbot", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public RoverBotOutput moveRoverBot(@RequestBody JSONObject jsonObject)
			throws IllegalArgumentException, JsonProcessingException, ParserConfigurationException, TransformerException {
		
		// Validate if the JSON is correct
		JSONValidator.validateInputJSONObj(jsonObject);
		
		// Write the provided input to XML
		XMLWriterUtil.writeToInputXMLFile(jsonObject);
		
		// Create RoverBotInput object from the provided JSON
		RoverBotInput roverbotInput = JSONConverterUtil.convertJSONObjectToRoverBotInput(jsonObject);
		
		// Based on the Initial condition and moves, calculate the final position.
		Position finalPosition = roverBotSvc.moveRoverBot(roverbotInput);

		// Write the final position to the output XML
		XMLWriterUtil.writeToOutputXMLFile(finalPosition);

		return new RoverBotOutput(finalPosition);
	}

	/**
	 * Fetch the rover position of the bot
	 * 
	 * @return Position - This object determines the final position of the bot
	 * @throws IOException
	 * @throws SAXException
	 * @throws ParserConfigurationException
	 */
	@GetMapping(value = "/roverbot", produces = MediaType.APPLICATION_JSON_VALUE)
	public RoverBotOutput roverBotPosition() throws ParserConfigurationException, SAXException, IOException {
		Position finalPosition = roverBotSvc.roverBotPosition();
		return new RoverBotOutput(finalPosition);
	}

}
