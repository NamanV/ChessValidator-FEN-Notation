package dxtr.util;

import java.util.Objects;

import dxtr.game.model.Board;
import dxtr.game.model.Coordinate;
import dxtr.game.model.Spot;
import dxtr.game.model.peice.King;
import dxtr.game.model.peice.Piece;

public class GameUtility {

	public static Coordinate getCoordinate(String move) {
		if (move.length() == 2) {
			return new Coordinate(getRankAxisIntIndexFromMoveIndex(move), getFileAxisIntIndexFromMoveIndex(move));
		}
		return null;
	}

	public static int getFileAxisIntIndexFromMoveIndex(String move) {
		switch (move.charAt(0)) {
		case 'a':
			return 0;
		case 'b':
			return 1;
		case 'c':
			return 2;
		case 'd':
			return 3;
		case 'e':
			return 4;
		case 'f':
			return 5;
		case 'g':
			return 6;
		case 'h':
			return 7;
		default:
			return -1;
		}
	}

	public static int getRankAxisIntIndexFromMoveIndex(String move) {
		switch (move.charAt(1) - '0') {
		case 8:
			return 0;
		case 7:
			return 1;
		case 6:
			return 2;
		case 5:
			return 3;
		case 4:
			return 4;
		case 3:
			return 5;
		case 2:
			return 6;
		case 1:
			return 7;

		default:
			return -1;
		}
	}

	public static int getMoveIndexFromRankAxisIntIndex(int rank) {
		switch (rank) {
		case 7:
			return 1;
		case 6:
			return 2;
		case 5:
			return 3;
		case 4:
			return 4;
		case 3:
			return 5;
		case 2:
			return 6;
		case 1:
			return 7;
		case 0:
			return 8;

		default:
			return '#';
		}
	}

	public static char getMoveIndexFromFileAxisIntIndex(int file) {
		switch (file) {
		case 0:
			return 'a';
		case 1:
			return 'b';
		case 2:
			return 'c';
		case 3:
			return 'd';
		case 4:
			return 'e';
		case 5:
			return 'f';
		case 6:
			return 'g';
		case 7:
			return 'h';
		default:
			return '#';
		}
	}

	public static String getMoveNotationFromCoordinate(Coordinate position) {
		return new StringBuilder().append(getMoveIndexFromFileAxisIntIndex(position.getRank()))
				.append(getMoveIndexFromRankAxisIntIndex(position.getFile())).toString();
	}

	public static boolean isInsideBoard(Coordinate coordinate) {
		if ((coordinate.getFile() >= 0 && coordinate.getFile() < Configuration.BOARD_SIZE)
				&& (coordinate.getRank() >= 0 && coordinate.getRank() < Configuration.BOARD_SIZE)) {
			return true;
		}

		return false;
	}

	public static boolean validateDiagonalMove(final Board board, final Piece currentPiece, final Spot toPosition) {
		if (!toPosition.isEmpty() && toPosition.getPiece().getPlayerType() == currentPiece.getPlayerType()) {
			return false;
		}

		int diffFile = Math.abs(toPosition.getPosition().getFile() - currentPiece.getCurrentPostion().getFile());
		int diffRank = Math.abs(toPosition.getPosition().getRank() - currentPiece.getCurrentPostion().getRank());

		if (diffFile != diffRank || diffFile == 0) {
			return false;
		}

		int rowOffset, colOffset;

		rowOffset = toPosition.getPosition().getFile() - currentPiece.getCurrentPostion().getFile() > 0 ? 1 : -1;
		colOffset = toPosition.getPosition().getRank() - currentPiece.getCurrentPostion().getRank() > 0 ? 1 : -1;

		int file, rank;
		for (file = currentPiece.getCurrentPostion().getFile()
				+ rowOffset, rank = currentPiece.getCurrentPostion().getRank()
						+ colOffset; file != toPosition.getPosition().getFile(); file += rowOffset, rank += colOffset) {

			if (Objects.nonNull(board.getSpot(file, rank).getPiece())) {
				return false;
			}

		}

		return true;
	}

	public static boolean validateAxisMove(final Board board, final Piece currentPiece, final Spot toPosition) {
		if (!toPosition.isEmpty() && toPosition.getPiece().getPlayerType() == currentPiece.getPlayerType()) {
			return false;
		}

		int diffFile = toPosition.getPosition().getFile() - currentPiece.getCurrentPostion().getFile();
		int diffRank = toPosition.getPosition().getRank() - currentPiece.getCurrentPostion().getRank();
		int offset = -1;
		if ((diffFile == 0 && diffRank == 0) || (diffFile != 0 && diffRank != 0)) {
			return false;
		}
		if (diffFile == 0) {
			offset = diffRank > 0 ? 1 : -1;
			for (int x = currentPiece.getCurrentPostion().getRank() + offset; x != toPosition.getPosition()
					.getRank(); x += offset) {
				if (Objects.nonNull(board.getSpot(currentPiece.getCurrentPostion().getFile(), x).getPiece())) {
					return false;
				}
			}
		} else if (diffRank == 0) {
			offset = diffFile > 0 ? 1 : -1;
			for (int x = currentPiece.getCurrentPostion().getFile() + offset; x != toPosition.getPosition()
					.getFile(); x += offset) {
				if (Objects.nonNull(board.getSpot(x, currentPiece.getCurrentPostion().getRank()).getPiece())) {
					return false;
				}
			}
		}

		return true;
	}

	public static boolean isPieceKing(Piece piece) {
		if (piece instanceof King) {
			return true;
		}

		return false;

	}

}
