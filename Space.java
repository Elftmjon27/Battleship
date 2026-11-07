// Class for spaces in board & stores what's on said space on board

public class Space {
    String status; // stores status at the space, {"empty", "miss", "hit"}
    boolean hasShip; // stores whether the space has a ship
    Ship s; // stores reference to ship

    public Space () {
        status = "empty";
        hasShip = false;
    }

    public String getStatus() {
        return status;
    }

    public boolean getHasShip() {
        return hasShip;
    }

    public Ship getShip() {
        if (hasShip) {
            return s;
        } else {
            return null;
        }
    }

    public void updateStatus(String stat) {
        status = stat;
    }

    public void addShip(Ship sp) {
        s = sp;
        hasShip = true;
    }
}