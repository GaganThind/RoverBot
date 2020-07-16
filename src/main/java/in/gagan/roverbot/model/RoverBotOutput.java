package in.gagan.roverbot.model;

/**
 * Model class to represent API output to be sent to the user
 * 
 * @author thindgagan
 *
 */
public class RoverBotOutput {
	
	public RoverBotOutput(Position position) {
		super();
		this.position = position;
	}

	/**
	 * Final Position of the rover bot
	 * */
	private Position position;

	public Position getPosition() {
		return position;
	}

	@Override
	public String toString() {
		return "RoverBotOutput [position=" + position + "]";
	}
	
}
