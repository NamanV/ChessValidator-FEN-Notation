package dxtr.game.model.peice;

import java.util.Objects;

import dxtr.game.model.Board;
import dxtr.game.model.Coordinate;
import dxtr.game.model.Player;
import dxtr.game.model.Spot;
import dxtr.util.Constants;
import dxtr.util.Logger;
import dxtr.util.EnumUtil.PieceNotation;
import dxtr.util.EnumUtil.PlayerType;

public class King extends Piece {

	public King(PlayerType playerType, Coordinate currentPosition) {
		super(playerType, currentPosition);
	}

	@Override
	public boolean isValidMove(Board board, Player player, Spot toPosition) {
		if (!toPosition.isEmpty() && toPosition.getPiece().playerType == this.playerType) {
			return false;
		}
		int diffFile = Math.abs(toPosition.getPosition().getFile() - this.getCurrentPostion().getFile());
		int diffRank = Math.abs(toPosition.getPosition().getRank() - this.getCurrentPostion().getRank());

		if (diffFile == 0 && diffRank == 0) {
			return false;
		}

		if (diffFile <= 1 && diffRank <= 1) {
			return true;
		}

		if (diffFile == 0 && diffRank == 2 && !hasMoved) {
			// Offset positive means king side castling else queen side
			int offset = toPosition.getPosition().getRank() - this.getCurrentPostion().getRank() > 0 ? 1 : -1;
			if ((offset == 1
					&& (Objects.isNull(player.getKingSideRook(board)) || !player.getKingSideRook(board).hasMoved()))
					|| (offset == -1 && (Objects.isNull(player.getQueenSideRook(board))
							|| !player.getQueenSideRook(board).hasMoved()))) {
				if (!validateHorizontalSpots(board, offset, toPosition)) {
					return false;
				} else {
					return true;
				}
			}
		}

		return false;

	}

	private boolean isCastling(Spot toSpot) {
		int diffRank = Math.abs(toSpot.getPosition().getRank() - this.getCurrentPostion().getRank());
		if (diffRank == 2) {
			return true;
		}
		return false;
	}

	@Override
	public void move(Board board, Player white, Player black, Spot fromSpot, Spot toSpot, PlayerType currentMove) {
		if (isCastling(toSpot)) {
			int diffRank = toSpot.getPosition().getRank() - this.getCurrentPostion().getRank();
			int offset = diffRank > 0 ? 1 : -1;
			// Offset positive means king side castling else queen side
			Player player = currentMove == PlayerType.WHITE ? white : black;
			if (diffRank == 2 && player.isKingSideCastling(board)) {
				doCastling(board, player, fromSpot, toSpot, offset, player.getKingSideRook(board));
			} else if (diffRank == -2 && player.isQueenSideCastling(board)) {
				doCastling(board, player, fromSpot, toSpot, offset, player.getQueenSideRook(board));
			} else {
				Logger.printLog(Constants.MOVE_NOT_POSSIBLE);
			}

		} else {
			super.move(board, white, black, fromSpot, toSpot, currentMove);
		}
	}

	private void doCastling(Board board, Player player, Spot fromSpot, Spot toSpot, int offset, Piece rook) {
		Logger.pringMoveLog(fromSpot, toSpot);
		Piece pieceAtCurrentPosition = fromSpot.getPiece();
		pieceAtCurrentPosition.currentPosition = toSpot.getPosition();
		toSpot.setPiece(pieceAtCurrentPosition);
		fromSpot.setPiece(null);

		board.getSpot(rook.currentPosition).setPiece(null);
		rook.currentPosition = new Coordinate(toSpot.getPosition().getFile(), toSpot.getPosition().getRank() - offset);
		board.getSpot(toSpot.getPosition().getFile(), toSpot.getPosition().getRank() - offset).setPiece(rook);
		hasMoved = true;
	}

	private boolean validateHorizontalSpots(Board board, int offset, Spot toPosition) {
		for (int x = getCurrentPostion().getRank() + offset; x != toPosition.getPosition().getRank(); x += offset) {
			if (Objects.nonNull(board.getSpot(getCurrentPostion().getFile(), x).getPiece())) {
				return false;
			}
		}

		return true;

	}

	@Override
	public char pieceNotation() {
		return playerType == PlayerType.WHITE ? PieceNotation.KING.getWhite() : PieceNotation.KING.getBlack();
	}

	@Override
	public String toString() {
		return playerType.name() + " " + PieceNotation.KING.name();
	}

}
