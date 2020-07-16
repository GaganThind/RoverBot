package in.gagan.roverbot.util;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;

import org.json.simple.JSONObject;

import in.gagan.roverbot.constant.ApplicationConstants;
import in.gagan.roverbot.model.Move;
import in.gagan.roverbot.model.Position;
import in.gagan.roverbot.model.RoverBotInput;

/**
 * This utility class is used to convert the JSON object to Roverbot input pojo
 * object
 * 
 * @author thindgagan
 *
 */
public class JSONConverterUtil {

	/**
	 * Convert the provided JSON object to the model objects
	 * 
	 * @param jsonObj
	 * @return RoverbotInput
	 * @throws IllegalArgumentException
	 */
	public static RoverBotInput convertJSONObjectToRoverBotInput(JSONObject jsonObj) throws IllegalArgumentException {

		// Position obj
		Position position = createPosition(jsonObj);

		// Move list
		List<Move> moves = createMoveList(jsonObj);

		return new RoverBotInput(position, moves);
	}

	/**
	 * Create Position object from the provided JSON
	 * 
	 * @param jsonObj
	 * @return
	 */
	private static Position createPosition(JSONObject jsonObj) {
		Map<?, ?> positionMap = (Map<?, ?>) jsonObj.get(ApplicationConstants.POSITION);
		char direction = ((String) positionMap.get(ApplicationConstants.DIRECTION)).charAt(0);
		int positionX = (int) positionMap.get(ApplicationConstants.X);
		int positionY = (int) positionMap.get(ApplicationConstants.Y);
		return new Position(direction, positionX, positionY);
	}

	/**
	 * Create Move List
	 * 
	 * @param jsonObj
	 * @return List<Move>
	 */
	private static List<Move> createMoveList(JSONObject jsonObj) {
		List<Move> moves = new ArrayList<>();
		List<?> moveArray = (List<?>) jsonObj.get(ApplicationConstants.MOVE);
		Map<?, ?> mapObj = null;
		int stepsLR = 0;
		int stepsFB = 0;
		for (Object obj : moveArray) {

			mapObj = (Map<?, ?>) obj;

			if (mapObj.containsKey(ApplicationConstants.LEFT)) {
				stepsLR = (int) mapObj.get(ApplicationConstants.LEFT);
				stepsLR = -stepsLR;
			} else if (mapObj.containsKey(ApplicationConstants.RIGHT)) {
				stepsLR = (int) mapObj.get(ApplicationConstants.RIGHT);
			}

			if (mapObj.containsKey(ApplicationConstants.FORWARD)) {
				stepsFB = (int) mapObj.get(ApplicationConstants.FORWARD);
			} else if (mapObj.containsKey(ApplicationConstants.BACKWARD)) {
				stepsFB = (int) mapObj.get(ApplicationConstants.BACKWARD);
				stepsFB = -stepsFB;
			}

			moves.add(new Move(Integer.valueOf((String) mapObj.get(ApplicationConstants.ORDER)), stepsFB, stepsLR));
		}
		Comparator<Move> byOrder = (Move move1, Move move2) -> move1.getOrder() - move2.getOrder();
		Collections.sort(moves, byOrder);

		return moves;
	}

}
