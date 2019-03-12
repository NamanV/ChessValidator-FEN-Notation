package dxtr.game.model.interfaces;

public interface IGame {
	public void initializeGame();

	public String fenNotation();

	public void move(ICoordinate fromPosition, ICoordinate toPosition);

	public void move(String fromPosition, String toPosition);
}
