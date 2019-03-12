package dxtr.game.model.interfaces;

import dxtr.game.model.peice.Piece;

public interface ISpot {

	public boolean isEmpty();

	public boolean isValid();

	public Piece getPiece();

	public void setPiece(Piece piece);

	public void setPosition(ICoordinate position);

	public ICoordinate getPosition();

}
