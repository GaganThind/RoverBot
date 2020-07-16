package in.gagan.roverbot.model;

import java.util.List;

/**
 * Model class to represent the input received from JSON
 * 
 * @author thindgagan
 *
 */
public class RoverBotInput {
	
	public RoverBotInput(Position position, List<Move> moves) {
		super();
		this.position = position;
		this.moves = moves;
	}

	/**
	 * Initial position of the rover bot
	 * */
	private final Position position;
	
	/**
	 * The moves to be performed on the reover bot
	 * */
	private final List<Move> moves;

	public Position getPosition() {
		return position;
	}

	public List<Move> getMoves() {
		return moves;
	}

	@Override
	public String toString() {
		return "RoverBotInput [position=" + position + ", moves=" + moves + "]";
	}
	
}
