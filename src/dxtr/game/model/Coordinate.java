package dxtr.game.model;

public class Coordinate {

	public Coordinate(int x, int y) {
		this.file = x;
		this.rank = y;
	}

	private int file;
	private int rank;

	public int getFile() {
		return file;
	}

	public int getRank() {
		return rank;
	}

	public void updateCoordinate(boolean increaseFile, boolean increaseRank) {
		if (increaseFile) {
			this.file++;
		} else {
			this.file--;
		}

		if (increaseRank) {
			this.rank++;
		} else {
			this.rank--;
		}

	}

	public void increamentFile(int file) {
		this.file += file;
	}

	public void increamentRank(int rank) {
		this.rank += rank;
	}

	@Override
	public boolean equals(Object obj) {
		Coordinate coordiate = (Coordinate) obj;
		if (this.file == coordiate.file && this.rank == coordiate.rank) {
			return true;
		}
		return false;
	}
}
