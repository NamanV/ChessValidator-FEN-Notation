package dxtr.game.model.interfaces;

public interface IBoard {

	public void initializeBoard(IPlayer white, IPlayer black);

	public void printFEN();

	public String fenNotation();

	public IBoard parseFenNotation(String fenNotation);

	public void move(ISpot fromPosition, ISpot toPosition);

	public ISpot[][] getBoard();
}
