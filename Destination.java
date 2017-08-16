package HorseSea;
public class Destination extends Game {
	/* Đích */
	static final int NO_RANK = -1;
	static final int NUMBER_RANK = 6;
	static final int NO_HORSE = -1;
	private int color;
	private int peek = NUMBER_RANK - 1;
	private int rank[] = new int[NUMBER_RANK]; // Vị trí cá ngựa trên bảng phong thần trên màn hình.

	Destination(int color) {
		for(int i = 0; i < NUMBER_RANK; i++){
			rank[i] = NO_HORSE;
		}
	}

	public boolean isWin() {
		return peek == 1;
	}

	public int getRank(int rank){
		return this.rank[rank];
	}

	public boolean setDestination(int rank, int number, HorseSea horse) {
		if (rank + number > peek) {
			showError("Không thể thăng hạng quân này.");
			return false;
		}

		for (int i = rank + 1; i < rank + number; i++) {
			if (this.rank[i] != NO_HORSE) {
				showError("Không thể thăng hạng quân này.");
				return false;
			}
		}		
		
		if(rank != NO_RANK) {
			this.rank[rank + number] = this.rank[rank];
			horse.setRank(rank + number);
			this.rank[rank] =NO_HORSE;
		} else {
			rank += number;
			this.rank[rank] = horse.getId();
			horse.setRank(rank);
		}

		if (this.rank[peek] != NO_HORSE) {
			peek--;
		}
		return true;
	}
}