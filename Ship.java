public class Ship {
    // Ship class stores ship's info (size, beginning coords, orientation, health)

    private int size;
    private String name;
    private int x;
    private int y;
    private boolean horizontal;
    private int health;

    public Ship (int len, String nm, int xPos, int yPos, boolean hor) {
        size = len;
        name = nm;
        x = xPos;
        x = yPos;
        horizontal = hor;
        health = len;
    }
    
    public void updateHit() {
        health--;
        if (health == 0) {
            System.out.println(name + " has been sunk");
        }
    }
}