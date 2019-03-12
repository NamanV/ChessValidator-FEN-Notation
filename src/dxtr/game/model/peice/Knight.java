package dxtr.game.model.peice;

import dxtr.game.model.Board;
import dxtr.game.model.Coordinate;
import dxtr.game.model.Player;
import dxtr.game.model.Spot;
import dxtr.util.EnumUtil.PieceNotation;
import dxtr.util.EnumUtil.PlayerType;

public class Knight extends Piece {

	public Knight(PlayerType playerType, Coordinate currentPosition) {
		super(playerType, currentPosition);
	}

	@Override
	public boolean isValidMove(Board board, Player player, Spot toPosition) {
		if (!toPosition.isEmpty() && toPosition.getPiece().playerType == this.playerType) {
			return false;
		}

		int diffFile = Math.abs(toPosition.getPosition().getFile() - this.getCurrentPostion().getFile());
		int diffRank = Math.abs(toPosition.getPosition().getRank() - this.getCurrentPostion().getRank());

		if ((diffFile == 1 && diffRank == 2) || (diffFile == 2 && diffRank == 1)) {
			return true;
		}

		return false;
	}

	@Override
	public char pieceNotation() {
		return playerType == PlayerType.WHITE ? PieceNotation.KNIGHT.getWhite() : PieceNotation.KNIGHT.getBlack();
	}

	@Override
	public String toString() {
		return playerType.name() + " " + PieceNotation.KNIGHT.name();
	}

}
