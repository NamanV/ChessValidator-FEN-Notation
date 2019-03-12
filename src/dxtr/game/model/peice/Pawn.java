package dxtr.game.model.peice;

import java.util.Objects;

import dxtr.game.model.Board;
import dxtr.game.model.Coordinate;
import dxtr.game.model.Player;
import dxtr.game.model.Spot;
import dxtr.util.EnumUtil.PieceNotation;
import dxtr.util.EnumUtil.PlayerType;

public class Pawn extends Piece {

	public Pawn(PlayerType playerType, Coordinate currentPosition) {
		super(playerType, currentPosition);
	}

	@Override
	public boolean isValidMove(Board board, Player player, Spot endSpot) {

		if (!endSpot.isEmpty()) {
			return false;
		}

		int diffFile = endSpot.getPosition().getFile() - this.getCurrentPostion().getFile();
		int diffRank = endSpot.getPosition().getRank() - this.getCurrentPostion().getRank();

		if (playerType == PlayerType.WHITE) {
			if (diffFile == -1 && diffRank == -1 && Objects.nonNull(endSpot.getPiece())
					&& !this.isSamePlayer(endSpot.getPiece())) {
				return true;
			}

			return diffRank == 0 && diffFile == -1;
		} else {
			if (diffFile == 1 && diffRank == 1 && Objects.nonNull(endSpot.getPiece())
					&& !this.isSamePlayer(endSpot.getPiece())) {
				return true;
			}
			return diffRank == 0 && diffFile == 1;
		}
	}

	@Override
	public char pieceNotation() {
		return playerType == PlayerType.WHITE ? PieceNotation.PAWN.getWhite() : PieceNotation.PAWN.getBlack();
	}

	@Override
	public String toString() {
		return playerType.name() + " " + PieceNotation.PAWN.name();
	}

}
