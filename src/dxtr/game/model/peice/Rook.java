package dxtr.game.model.peice;

import dxtr.game.model.Board;
import dxtr.game.model.Coordinate;
import dxtr.game.model.Player;
import dxtr.game.model.Spot;
import dxtr.util.EnumUtil.PieceNotation;
import dxtr.util.EnumUtil.PlayerType;
import dxtr.util.GameUtility;

public class Rook extends Piece {

	public Rook(PlayerType playerType, Coordinate currentPosition) {
		super(playerType, currentPosition);
	}

	@Override
	public boolean isValidMove(Board board, Player player, Spot toPosition) {
		return GameUtility.validateAxisMove(board, this, toPosition);
	}

	@Override
	public char pieceNotation() {
		return playerType == PlayerType.WHITE ? PieceNotation.ROOK.getWhite() : PieceNotation.ROOK.getBlack();
	}

	@Override
	public String toString() {
		return playerType.name() + " " + PieceNotation.ROOK.name();
	}

}
