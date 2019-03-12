package dxtr.util;

import dxtr.game.model.Coordinate;

public class EnumUtil {
	public enum PlayerType {
		WHITE('w'), BLACK('b');

		PlayerType(char notation) {
			this.notation = notation;
		}

		private char notation;

		public char getNotation() {
			return notation;
		}

		public static PlayerType toggleMove(PlayerType playerType) {
			if (playerType == WHITE) {
				return BLACK;
			}

			return WHITE;
		}
	}

	public enum PieceNotation {
		KING('k', 'K', 1), QUEEN('q', 'Q', 1), BISHOP('b', 'B', 2), ROOK('r', 'R', 2), KNIGHT('n', 'N', 2), PAWN('p',
				'P', 8);

		private PieceNotation(char black, char white, int count) {
			this.black = black;
			this.white = white;
			this.count = count;
		}

		private char black;
		private char white;
		private int count;

		public char getBlack() {
			return black;
		}

		public char getWhite() {
			return white;
		}

		public char getNotation(PlayerType playerType) {
			switch (playerType) {
			case WHITE:
				return getWhite();
			case BLACK:
				return getBlack();
			}
			return getWhite();
		}

	}

}
