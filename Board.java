public class Board {
	private String[][] squares = new String[10][10];
	// Battleship board is always 10 x 10, so squares is of fixed size

	// fills the board with "-"
	public Board() {
		for (int i = 0; i < 10; i++) {
			for (int j = 0; j < 10; j++) {
				squares[i][j] = "-";
			}
		}
	}

	// returns entre board condensed to a single string, string meant to be printed
	public String toString() {
		String output = "";

		for (int r = 0; r < 10; r++) {
			for (int c = 0; c < 10; c++) {
				output += squares[r][c] + " ";
			}
			output += "\n";
		}

		return output;
	}

	/* tries to add ship at given row and col
		ship goes from left to right if horizontal true, else up to down when horizontal false
		if ship can't be placed (off grid or intersect another boat), returns false
	*/
	public boolean addShip (int row, int col, int len, boolean horizontal) {
		if (row < 0 || row > 9 || col < 0 || col > 9) { // checks whether given coords actually lie on board
			return false;
		}

		if (horizontal) {
			if (col + len > 9) { // checks if farthest coord lies on board
				return false;
			}

			for (int i = 0; i < len; i++) {
				if (squares[row][col + i].equals("b")) { // at this point, only b or - are on board, so no need to worry about x and m
					return false; // outputs false if location described will interect another ship
				}
			}

			for (int j = 0; j < len; j++) { // records b on board
				squares[row][col + j] = "b";
			}
			
			return true;
		} else { // same comments as previous, refer to that code
			if (row + len > 9) {
				return false;
			}

			for (int i = 0; i < len; i++) {
				if (squares[row + i][col].equals("b")) {
					return false;
				}
			}

			for (int j = 0; j < len; j++) {
				squares[row + j][col] = "b";
			}

			return true;
		}
	}

	// returns true if found ship of len, false if can't find
	public boolean foundShip (int len) {
		int frequency = 0; // counts length of ship during finding process

		// checks horizontal ships
		for (int r = 0; r < 10; r++) {
			for (int i = 0; i < 10; i++) {
				if (frequency != 0 && !squares[r][i].equals("b")) {
					frequency = 0;
				} else if (squares[r][i].equals("b")) {
					frequency++;
				}

				if (frequency == len) {
					return true;
				}
			}
			frequency = 0; // resets frequency every row
		}

		// checks vertical ships
		for (int c = 0; c < 10; c++) {
			for (int j = 0; j < 10; j++) {
				if (frequency != 0 && !squares[j][c].equals("b")) {
					frequency = 0;
				} else if (squares[j][c].equals("b")) {
					frequency++;
				}

				if (frequency == len) {
					return true;
				}
			}
			frequency = 0; // resets frequency every column
		}

		return false; // only reaches this if can't find ship of length len
	}

	/* shoots at given coord
		returns -1 if coord not on board
		returns 0 if missed
		returns 1 if hit
		returns 2 if squares already been shot
	*/
	public int shoot(int row, int col) {
		if (row < 0 || row > 9 || col < 0 || col > 9) { // checks whether given coords actually lie on board
			return -1;
		}

		if (squares[row][col].equals("-")) {
			squares[row][col] = "m";
			return 0;
		} else if (squares[row][col].equals("b")) {
			squares[row][col] = "x";
			return 1;
		} else {
			return 2;
		}
	}

	// returns true if no ships left, false if there is a ship (b) on the board
	public boolean gameOver() {
		if (!foundShip(1)) { // only true if can't find any ships of length 1 (no b on board)
			return true;
		}
		return false;
	}
}