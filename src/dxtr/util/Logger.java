package dxtr.util;

import dxtr.game.model.Spot;

public class Logger {

	public static void printLog(String message) {
		System.out.println(message);
	}

	public static void printLog(Spot fromSpot, Spot toSpot, String message) {
		System.out.println(message);
	}

	public static void printException(String message, Exception e) {

	}

	public static void pringMoveLog(Spot fromSpot, Spot toSpot) {
		System.out.println(fromSpot.getPiece().toString() + " from "
				+ GameUtility.getMoveNotationFromCoordinate(fromSpot.getPosition()) + " to "
				+ GameUtility.getMoveNotationFromCoordinate(toSpot.getPosition()));
	}

}
