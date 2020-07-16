package in.gagan.roverbot.model;

/**
 * Model class to Keep track of current position of the roverbot
 * 
 * @author thindgagan
 *
 */
public class Position {
	
	public Position(char direction, int x, int y) {
		super();
		this.direction = direction;
		this.x = x;
		this.y = y;
	}
	
	/**
	 * This field keeps track of the current direction of the rover bot
	 * */
	private final char direction;
	
	/**
	 * This filed keeps track of the x coordinate of the rover on x, y plane
	 * */
	private final int x;
	
	/**
	 * This filed keeps track of the y coordinate of the rover on x, y plane
	 * */
	private final int y;

	public char getDirection() {
		return direction;
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	@Override
	public String toString() {
		return "Position [Direction=" + direction + ", X=" + x + ", Y=" + y + "]";
	}
	
}
