package dxtr.game.model.peice;

import dxtr.game.model.Board;
import dxtr.game.model.Coordinate;
import dxtr.game.model.Player;
import dxtr.game.model.Spot;
import dxtr.util.Logger;
import dxtr.util.EnumUtil.PlayerType;

public abstract class Piece {
	protected Coordinate currentPosition;
	protected PlayerType playerType;
	protected boolean hasMoved;

	public Piece(PlayerType playerType, Coordinate currentPosition) {
		this.playerType = playerType;
		this.currentPosition = currentPosition;
	}

	public abstract char pieceNotation();

	public abstract boolean isValidMove(Board board, Player player, Spot toPosition);

	public Coordinate getCurrentPostion() {
		return currentPosition;
	}

	private void move(Spot fromSpot, Spot toPosition) {
		this.currentPosition = toPosition.getPosition();
		toPosition.setPiece(this);
		fromSpot.setPiece(null);
		hasMoved = true;
	}

	public void move(Board board, Player white, Player black, Spot fromSpot, Spot toSpot, PlayerType currentMove) {

		Logger.pringMoveLog(fromSpot, toSpot);
		Piece pieceAtCurrentPosition = fromSpot.getPiece();
		Piece pieceAtToPosition = toSpot.getPiece();
		boolean killPiece = fromSpot.isKillPiece(toSpot);

		move(fromSpot, toSpot);

		if (killPiece) {
			Logger.printLog(pieceAtCurrentPosition.toString() + " killed " + pieceAtToPosition.toString());
			if (currentMove == PlayerType.WHITE) {
				black.removePiece(pieceAtToPosition);
			} else {
				white.removePiece(pieceAtToPosition);
			}
		}
	}

	public boolean isSamePlayer(Piece piece) {
		if (piece.playerType == this.playerType) {
			return true;
		}

		return false;
	}

	public PlayerType getPlayerType() {
		return playerType;
	}

	public boolean hasMoved() {
		return hasMoved;
	}

}
