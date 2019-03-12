package dxtr.game.model;

import java.util.Objects;

import dxtr.game.model.interfaces.ICoordinate;
import dxtr.game.model.peice.Piece;

public class Spot {
	private Coordinate position;
	private Piece piece;

	public Spot(Coordinate position, Piece piece) {
		this.position = position;
		this.piece = piece;
	}

	public boolean isEmpty() {
		if (Objects.nonNull(piece)) {
			return false;
		}
		return true;
	}

	public Piece getPiece() {
		return piece;
	}

	public void setPiece(Piece piece) {
		this.piece = piece;
	}

	public Coordinate getPosition() {
		return position;
	}

	public void setPosition(ICoordinate position) {

	}

	public boolean isKillPiece(Spot toSpot) {
		if (Objects.nonNull(toSpot.getPiece()) && !getPiece().isSamePlayer(toSpot.getPiece())) {
			return true;
		}
		return false;
	}
}
