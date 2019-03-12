package dxtr.game.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import dxtr.game.model.interfaces.Builder;
import dxtr.game.model.peice.King;
import dxtr.game.model.peice.Piece;
import dxtr.util.Configuration;
import dxtr.util.EnumUtil.PieceNotation;
import dxtr.util.EnumUtil.PlayerType;

public class Player implements Builder<Player> {
	private List<Piece> pieces = new ArrayList<>();
	private PlayerType playerType;

	public void initializePlayer(PlayerType pType) {
		playerType = pType;
		switch (pType) {
		case BLACK:
			pieces.add(PieceFactory.getPeice(PieceNotation.KING, new Coordinate(0, 4), pType));
			pieces.add(PieceFactory.getPeice(PieceNotation.QUEEN, new Coordinate(0, 3), pType));
			pieces.add(PieceFactory.getPeice(PieceNotation.BISHOP, new Coordinate(0, 5), pType));
			pieces.add(PieceFactory.getPeice(PieceNotation.BISHOP, new Coordinate(0, 2), pType));
			pieces.add(PieceFactory.getPeice(PieceNotation.KNIGHT, new Coordinate(0, 6), pType));
			pieces.add(PieceFactory.getPeice(PieceNotation.KNIGHT, new Coordinate(0, 1), pType));
			pieces.add(PieceFactory.getPeice(PieceNotation.ROOK, new Coordinate(0, 0), pType));
			pieces.add(PieceFactory.getPeice(PieceNotation.ROOK, new Coordinate(0, 7), pType));
			for (int i = 0; i < Configuration.BOARD_SIZE; i++) {
				pieces.add(PieceFactory.getPeice(PieceNotation.PAWN, new Coordinate(1, i), pType));
			}
			break;
		case WHITE:
			pieces.add(PieceFactory.getPeice(PieceNotation.KING, new Coordinate(7, 4), pType));
			pieces.add(PieceFactory.getPeice(PieceNotation.QUEEN, new Coordinate(7, 3), pType));
			pieces.add(PieceFactory.getPeice(PieceNotation.BISHOP, new Coordinate(7, 5), pType));
			pieces.add(PieceFactory.getPeice(PieceNotation.BISHOP, new Coordinate(7, 2), pType));
			pieces.add(PieceFactory.getPeice(PieceNotation.KNIGHT, new Coordinate(7, 6), pType));
			pieces.add(PieceFactory.getPeice(PieceNotation.KNIGHT, new Coordinate(7, 1), pType));
			pieces.add(PieceFactory.getPeice(PieceNotation.ROOK, new Coordinate(7, 0), pType));
			pieces.add(PieceFactory.getPeice(PieceNotation.ROOK, new Coordinate(7, 7), pType));

			for (int i = 0; i < Configuration.BOARD_SIZE; i++) {
				pieces.add(PieceFactory.getPeice(PieceNotation.PAWN, new Coordinate(6, i), pType));
			}
			break;
		}

	}

	public List<Piece> getPieces() {
		return pieces;
	}

	public PlayerType playerType() {
		return playerType;
	}

	public void removePiece(Piece piece) {
		try {
			this.pieces.remove(piece);
		} catch (Exception e) {

		}
	}

	public boolean isKingSideCastling(Board board) {
		if (getKing().hasMoved() || Objects.isNull(getKingSideRook(board)) || getKingSideRook(board).hasMoved()) {
			return false;
		}

		return true;
	}

	public boolean isQueenSideCastling(Board board) {
		if (getKing().hasMoved() || Objects.isNull(getQueenSideRook(board)) || getQueenSideRook(board).hasMoved()) {
			return false;
		}

		return true;
	}

	public Piece getKingSideRook(final Board board) {
		if (playerType == PlayerType.WHITE) {
			return board.getSpot(7, 7).getPiece();
		} else {
			return board.getSpot(0, 7).getPiece();
		}
	}

	public Piece getQueenSideRook(final Board board) {
		if (playerType == PlayerType.WHITE) {
			return board.getSpot(7, 0).getPiece();
		} else {
			return board.getSpot(0, 0).getPiece();
		}
	}

	public Piece getKing() {
		for (Piece piece : pieces) {
			if (piece instanceof King) {
				return piece;
			}

		}
		return null;
	}

	@Override
	public Player build() {
		return null;
	}

}
