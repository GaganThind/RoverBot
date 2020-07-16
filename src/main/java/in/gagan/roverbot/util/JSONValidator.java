package in.gagan.roverbot.util;

import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

import org.json.simple.JSONObject;

import in.gagan.roverbot.constant.ApplicationConstants;

/**
 * This class provides the JSON validation method
 * 
 * @author thindgagan
 *
 */
public class JSONValidator {
	
	/**
	 * Validate input JSON
	 * 
	 * @param jsonObj
	 * @throws IllegalArgumentException
	 */
	public static void validateInputJSONObj(JSONObject jsonObj) throws IllegalArgumentException {
		
		String regex = "([0-9]|[1-9][0-9]|[1-9][0-9[0-9]])";
		
		if (!jsonObj.containsKey(ApplicationConstants.POSITION)) {
			throw new IllegalArgumentException("Position is not defined in JSON object");
		}
		
		if (!jsonObj.containsKey(ApplicationConstants.MOVE)) {
			throw new IllegalArgumentException("Move is not defined in JSON object");
		}
		
		Map<?, ?> positionMap = (Map<?, ?>) jsonObj.get(ApplicationConstants.POSITION);
		if (!positionMap.containsKey(ApplicationConstants.DIRECTION) || !positionMap.containsKey(ApplicationConstants.X) || 
				!positionMap.containsKey(ApplicationConstants.Y)) {
			throw new IllegalArgumentException("Position is not defined correctly in JSON object");
		}
		
		if (!Pattern.matches(regex, String.valueOf(positionMap.get(ApplicationConstants.X)))) {
			throw new IllegalArgumentException("X coordinate should be between 0 to 999");
		}
		
		if (!Pattern.matches(regex, String.valueOf(positionMap.get(ApplicationConstants.Y)))) {
			throw new IllegalArgumentException("Y coordinate should be between 0 to 999");
		}
		
		List<?> moveArray = (List<?>) jsonObj.get(ApplicationConstants.MOVE);
		Map<?, ?> mapObj = null;
		for (Object obj: moveArray) {
			mapObj = (Map<?, ?>) obj;
			if (!mapObj.containsKey(ApplicationConstants.ORDER) || 
					!(mapObj.containsKey(ApplicationConstants.LEFT) || mapObj.containsKey(ApplicationConstants.RIGHT)) ||
					!(mapObj.containsKey(ApplicationConstants.FORWARD) || mapObj.containsKey(ApplicationConstants.BACKWARD))) {
				throw new IllegalArgumentException("Move is not defined correctly in JSON object");
			}
			
			if (mapObj.containsKey(ApplicationConstants.FORWARD)) {
				if (!Pattern.matches(regex, String.valueOf(mapObj.get(ApplicationConstants.FORWARD)))) {
					throw new IllegalArgumentException("Forward coordinate should be between 0 to 999");
				}
			}
			
			if (mapObj.containsKey(ApplicationConstants.BACKWARD)) {
				if (!Pattern.matches(regex, String.valueOf(mapObj.get(ApplicationConstants.BACKWARD)))) {
					throw new IllegalArgumentException("Backward coordinate should be between 0 to 999");
				}
			}
		}
		
	}

}
