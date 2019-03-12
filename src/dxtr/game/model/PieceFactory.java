package dxtr.game.model;

import dxtr.game.model.peice.Bishop;
import dxtr.game.model.peice.King;
import dxtr.game.model.peice.Knight;
import dxtr.game.model.peice.Pawn;
import dxtr.game.model.peice.Piece;
import dxtr.game.model.peice.Queen;
import dxtr.game.model.peice.Rook;
import dxtr.util.EnumUtil.PieceNotation;
import dxtr.util.EnumUtil.PlayerType;

public class PieceFactory {

	public static Piece getPeice(PieceNotation pieceNotaiton, Coordinate currentPosition, PlayerType playerType) {
		switch (pieceNotaiton) {
		case KING:
			return new King(playerType, currentPosition);
		case QUEEN:
			return new Queen(playerType, currentPosition);
		case KNIGHT:
			return new Knight(playerType, currentPosition);
		case ROOK:
			return new Rook(playerType, currentPosition);
		case BISHOP:
			return new Bishop(playerType, currentPosition);
		case PAWN:
			return new Pawn(playerType, currentPosition);
		default:
			return null;
		}

	}

}
