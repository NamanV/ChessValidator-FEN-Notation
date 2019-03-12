package dxtr.game.model;

import java.util.Objects;

import dxtr.game.exceptions.IllegalMoveException;
import dxtr.util.Configuration;
import dxtr.util.Constants;
import dxtr.util.EnumUtil.PlayerType;
import dxtr.util.GameUtility;
import dxtr.util.Logger;

public class Game {
	private Player black;
	private Player white;
	private Board chessBoard;
	private PlayerType nextMove;
	private int halfMoon;
	private int fullMoon;

	public void initializeGame() {

		black = new Player();
		black.initializePlayer(PlayerType.BLACK);
		white = new Player();
		white.initializePlayer(PlayerType.WHITE);
		chessBoard = new Board();
		chessBoard.initializeBoard(white, black);
		nextMove = Configuration.FIRST_MOVE;
		halfMoon = 0;
		fullMoon = 1;
	}

	public void restartGame() {
		Logger.printLog(Constants.MESSAGE_RESTARTING_GAME);
		initializeGame();
	}

	public String fenNotation() {
		StringBuilder fenNotationBuilder = new StringBuilder();
		fenNotationBuilder.append(chessBoard.fenNotation()).append(" ").append(nextMove.getNotation()).append(" ")
				.append(white.isKingSideCastling(chessBoard) ? 'K' : '-')
				.append(white.isQueenSideCastling(chessBoard) ? 'Q' : '-')
				.append(black.isKingSideCastling(chessBoard) ? 'k' : '-')
				.append(black.isQueenSideCastling(chessBoard) ? 'q' : '-').append(" ").append("-").append(" ")
				.append(halfMoon).append(" ").append(fullMoon);
		return fenNotationBuilder.toString();
	}

	public void move(Coordinate fromPosition, Coordinate toPosition) throws IllegalMoveException {

		if (!validatePosition(fromPosition, toPosition)) {
			return;
		}

		Spot currentSpot = chessBoard.getSpot(fromPosition);
		Spot toSpot = chessBoard.getSpot(toPosition);

		if (!preValidation(currentSpot, toSpot)) {
			return;
		}

		if (chessBoard.isValidMove(currentSpot, toSpot, nextMove == PlayerType.WHITE ? white : black)) {
			boolean killPiece = currentSpot.isKillPiece(toSpot);

			chessBoard.move(chessBoard, white, black, currentSpot, toSpot, nextMove);

			if (killPiece) {
				halfMoon++;
			}
			nextMove = PlayerType.toggleMove(nextMove);
			fullMoon++;
		} else {
			Logger.printLog(currentSpot, toSpot, Constants.MOVE_NOT_AVAILABLE);
		}

	}

	private boolean validatePosition(Coordinate fromPosition, Coordinate toPosition) {
		if (!GameUtility.isInsideBoard(fromPosition) || !GameUtility.isInsideBoard(toPosition)) {
			Logger.printLog(Constants.ERROR_INVALID_POSITION);
			return false;
		}

		return true;
	}

	private boolean preValidation(Spot currentSpot, Spot toSpot) {

		if (Objects.isNull(currentSpot.getPiece())) {
			Logger.printLog(currentSpot, toSpot, Constants.ERROR_EMPTY_SLOT);
			return false;
		}

		if (currentSpot.getPiece().getPlayerType() != nextMove) {
			Logger.printLog(currentSpot, toSpot, Constants.MOVE_NOT_POSSIBLE);
			return false;
		}
		return true;
	}

	public void move(String fromPosition, String toPosition) throws IllegalMoveException {
		move(GameUtility.getCoordinate(fromPosition), GameUtility.getCoordinate(toPosition));
	}

	@Override
	public String toString() {
		return "Current State (FEN Notation):\n" + fenNotation() + "\nNext Move : " + nextMove.name();
	}

}
