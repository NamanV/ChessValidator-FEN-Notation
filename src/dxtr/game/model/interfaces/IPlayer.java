package dxtr.game.model.interfaces;

import java.util.List;

import dxtr.game.model.peice.Piece;
import dxtr.util.EnumUtil.PlayerType;

public interface IPlayer {
	public void initializePlayer(PlayerType pType);

	public List<Piece> getPieces();

	public PlayerType playerType();
}
