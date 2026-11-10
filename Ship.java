public class Ship {
    // Ship class stores ship's info (size, beginning coords, orientation, health)

    private int size;
    private String name;
    private int x;
    private int y;
    private boolean horizontal;
    private int health;
    private Board b;

    public Ship (int len, String nm, int xPos, int yPos, boolean hor, Board bo) {
        size = len;
        name = nm;
        x = xPos;
        x = yPos;
        horizontal = hor;
        health = len;
        b = bo;
    }

    private boolean addShip() {
        if (x < 0 || x > 9 || y < 0 || y > 9) { // checks whether given coords actually lie on board
			return false;
		}

        if (horizontal) {
            if (x + size > 9) {
                return false;
            }

            for (int i = 0; i < size; i++) {
                if (b.getValue(x + i, y).equals("b")) { // only need to check if b is there (hit and miss not options yet)
                    return false;
                }
            }

            for (int j = 0; j < size; j++) {
                b.setValue(x + i, y, "b");
            }
        }
    }
}