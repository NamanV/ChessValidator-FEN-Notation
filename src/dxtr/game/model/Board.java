package dxtr.game.model;

import java.util.Objects;

import dxtr.game.model.peice.Piece;
import dxtr.util.Configuration;
import dxtr.util.EnumUtil.PlayerType;

public class Board {

	private Spot[][] board;

	public void initializeBoard(Player white, Player black) {
		board = new Spot[Configuration.BOARD_SIZE][Configuration.BOARD_SIZE];
		for (int i = 0; i < Configuration.BOARD_SIZE; i++) {
			for (int j = 0; j < Configuration.BOARD_SIZE; j++) {
				board[i][j] = new Spot(new Coordinate(i, j), null);
			}
		}
		for (Piece piece : white.getPieces()) {
			board[piece.getCurrentPostion().getFile()][piece.getCurrentPostion().getRank()].setPiece(piece);
		}

		for (Piece piece : black.getPieces()) {
			board[piece.getCurrentPostion().getFile()][piece.getCurrentPostion().getRank()].setPiece(piece);
		}
	}

	public boolean isValidMove(Spot fromPosition, Spot toPosition, Player player) {
		return fromPosition.getPiece().isValidMove(this, player, toPosition);
	}

	public void move(Board board, Player white, Player black, Spot fromSpot, Spot toSpot, PlayerType currentMove) {
		fromSpot.getPiece().move(board, white, black, fromSpot, toSpot, currentMove);
	}

	public String fenNotation() {
		StringBuilder fenNotation = new StringBuilder();
		int count;

		for (int i = 0; i < Configuration.BOARD_SIZE; i++) {
			count = 0;
			for (int j = 0; j < Configuration.BOARD_SIZE; j++) {
				Spot spot = board[i][j];
				if (Objects.nonNull(spot) && Objects.nonNull(spot.getPiece())) {
					if (count > 0) {
						fenNotation.append(count);
						count = 0;
					}
					fenNotation.append(spot.getPiece().pieceNotation());
				} else if (j == Configuration.BOARD_SIZE - 1) {
					fenNotation.append(++count);
				} else {
					count++;
				}
			}
			if (i < Configuration.BOARD_SIZE - 1) {
				fenNotation.append(" \\ ");
			}
		}
		return fenNotation.toString();
	}

	public Board parseFenNotation(String fenNotation) {
		String[] rows = fenNotation.split("/");
		if (rows.length != 8) {
			return null;
		}
		for (String row : rows) {
			for (char ch : row.toCharArray()) {
				if (Character.isAlphabetic(ch)) {

				} else if (Character.isDigit(ch)) {

				} else {
				}
			}
		}

		return null;
	}

	public Spot getSpot(int file, int rank) {
		return board[file][rank];
	}

	public Spot getSpot(Coordinate position) {
		return board[position.getFile()][position.getRank()];
	}

	public void setSpot(Coordinate position, Spot spot) {
		board[position.getFile()][position.getRank()] = spot;
	}

}
