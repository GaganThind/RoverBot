package in.gagan.roverbot.model;

/**
 * Model class for moves the rover should follow
 * 
 * @author thindgagan
 *
 */
public class Move {
	
	public Move(int order, int moveSteps, int rotationAngle) {
		super();
		this.order = order;
		this.moveSteps = moveSteps;
		this.rotationAngle = rotationAngle;
	}

	/**
	 * This field maintains the order of move
	 * */
	private final int order;
	
	/**
	 * This field saves the steps to be moved in each move.
	 * Positive value denotes forward movement
	 * Negative value denotes backward movement  
	 * 
	 * */
	private final int moveSteps;
	
	/**
	 * This field saves the rotation angle for each move.
	 * Positive value denotes Clockwise movement
	 * Negative value denotes Anti-Clockwise movement
	 *  
	 * */
	private final int rotationAngle;
	
	public int getOrder() {
		return order;
	}

	public int getMoveSteps() {
		return moveSteps;
	}

	public int getRotationAngle() {
		return rotationAngle;
	}

	@Override
	public String toString() {
		return "Move [order=" + order + ", moveSteps=" + moveSteps + ", rotationAngle=" + rotationAngle + "]";
	}
	
}
