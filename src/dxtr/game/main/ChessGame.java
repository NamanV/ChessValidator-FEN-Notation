package dxtr.game.main;

import java.util.Scanner;

import dxtr.game.exceptions.IllegalMoveException;
import dxtr.game.model.Game;
import dxtr.util.Logger;

public class ChessGame {

	public static void main(String[] args) {
		// playGame();
		try {
			test();	
		} catch (Exception e) {
			// TODO: handle exception
		}
		
	}

	public static void playGame() {
		Game chessGame = new Game();
		chessGame.initializeGame();

		System.out.println("FEN Notation of board at the start: ");
		System.out.println(chessGame.fenNotation());

		Scanner sc = new Scanner(System.in);
		try {
			String move;
			String[] moves;
			while (true) {
				System.out.println(chessGame.toString());
				move = sc.nextLine();
				if (move.equalsIgnoreCase("adios")) {
					return;
				}
				if (move.equalsIgnoreCase("fen")) {
					System.out.println(chessGame.fenNotation());
					continue;
				}
				if (move.equalsIgnoreCase("restart")) {
					System.out.println(chessGame.fenNotation());
					continue;
				}
				moves = move.split(":");
				if (moves.length != 2 || moves[0].length() != 2 || moves[1].length() != 2) {
					System.out.println("Invalid move, please enter something like a2:a4");
					continue;
				}

				chessGame.move(moves[0], moves[1]);

			}
		} catch (IllegalMoveException e) {
			Logger.printException("Illegal Moves", e);
		} finally {
			sc.close();
		}

	}

	public static void test() throws Exception,IllegalMoveException {

		Game chessGame = new Game();
		chessGame.initializeGame();

		System.out.println("Starting Position:");
		System.out.println(chessGame.fenNotation());

		chessGame.move("g1", "f3");
		System.out.println(chessGame.fenNotation());
		chessGame.move("h7", "h6");
		System.out.println(chessGame.fenNotation());
		chessGame.move("g2", "g3");
		System.out.println(chessGame.fenNotation());
		chessGame.move("h6", "h5");
		System.out.println(chessGame.fenNotation());
		chessGame.move("f1", "h3");
		System.out.println(chessGame.fenNotation());
		chessGame.move("h8", "h6");
		System.out.println(chessGame.fenNotation());

		System.out.println("----------------");
		System.out.println("----------------");
		System.out.println("----------------");
		System.out.println("----------------");
		System.out.println("----------------");
		System.out.println("----------------");

		System.out.println("Starting Position:");
		System.out.println(chessGame.fenNotation());

		chessGame.move("g1", "f3");
		System.out.println(chessGame.fenNotation());
		chessGame.move("b8", "a6");
		System.out.println(chessGame.fenNotation());
		chessGame.move("g2", "g3");
		System.out.println(chessGame.fenNotation());
		chessGame.move("d7", "d6");
		System.out.println(chessGame.fenNotation());
		chessGame.move("f1", "h3");
		System.out.println(chessGame.fenNotation());
		chessGame.move("c8", "g4");
		System.out.println(chessGame.fenNotation());
		chessGame.move("e1", "g1");
		System.out.println(chessGame.fenNotation());
		chessGame.move("e7", "e6");
		System.out.println(chessGame.fenNotation());
		chessGame.move("b1", "c3");
		System.out.println(chessGame.fenNotation());
		chessGame.move("d8", "h4");
		System.out.println(chessGame.fenNotation());
		chessGame.move("e8", "c8");
		System.out.println(chessGame.fenNotation());
		chessGame.move("c3", "a4");
		System.out.println(chessGame.fenNotation());
		chessGame.move("e8", "c8");
		System.out.println(chessGame.fenNotation());

		System.out.println("----------------");
		System.out.println("----------------");
		System.out.println("----------------");
		System.out.println("----------------");
		System.out.println("----------------");
		System.out.println("----------------");

		chessGame.restartGame();
		chessGame.move("d2", "d3");
		System.out.println(chessGame.fenNotation());
		chessGame.move("e7", "e6");
		System.out.println(chessGame.fenNotation());
		chessGame.move("a2", "a3");
		System.out.println(chessGame.fenNotation());
		chessGame.move("e6", "e5");
		System.out.println(chessGame.fenNotation());
		chessGame.move("a3", "a4");
		System.out.println(chessGame.fenNotation());
		chessGame.move("e8", "e7");
		System.out.println(chessGame.fenNotation());
		chessGame.move("a4", "a5");
		System.out.println(chessGame.fenNotation());
		System.out.println("----------------");
		System.out.println("----------------");
		System.out.println("----------------");
		System.out.println("----------------");
		System.out.println("----------------");
		chessGame.restartGame();
		System.out.println(chessGame.fenNotation());
	}
}
