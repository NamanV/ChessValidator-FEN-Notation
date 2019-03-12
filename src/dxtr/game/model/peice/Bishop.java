package dxtr.game.model.peice;

import dxtr.game.model.Board;
import dxtr.game.model.Coordinate;
import dxtr.game.model.Player;
import dxtr.game.model.Spot;
import dxtr.util.EnumUtil.PieceNotation;
import dxtr.util.EnumUtil.PlayerType;
import dxtr.util.GameUtility;

public class Bishop extends Piece {

	public Bishop(PlayerType playerType, Coordinate currentPosition) {
		super(playerType, currentPosition);
	}

	@Override
	public boolean isValidMove(Board board, Player player, Spot toPosition) {
		return GameUtility.validateDiagonalMove(board, this, toPosition);
	}

	@Override
	public char pieceNotation() {
		return playerType == PlayerType.WHITE ? PieceNotation.BISHOP.getWhite() : PieceNotation.BISHOP.getBlack();
	}

	@Override
	public String toString() {
		return playerType.name() + " " + PieceNotation.BISHOP.name();
	}
}
