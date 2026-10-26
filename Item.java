/**
 * this is a class to create the items that the player can pickup.
 * each item has a name, image, whether it has been used or not, a start postion
 * on the screen and the effect it has on the geeese
 */
public class Item {

    /** this tells use weather the item has ben used yet */
    public enum State {
        ACTIVE,
        USED
    }

    private final String name; // name of the item
    private final String photo; // path to the image file, e.g. "images/bread.png"
    private final int startX; // position of the item on x axis, set at the start but can be updated
    private final int startY; // poistion of the item on y axis
    private final GooseEffect gooseEffect; // seperate class that is the effect that the item has on the geese
    private State state; // weather or not the item has been picked up

    public Item(String name, String photo, int startX, int startY, GooseEffect gooseEffect) {
        this.name = name;
        this.photo = photo;
        this.startX = startX;
        this.startY = startY;
        this.gooseEffect = gooseEffect;
        this.state = State.ACTIVE; // items begin as active
    }

    public String getName() {
        return name;
    }

    public String getPhoto() {
        return photo;
    }

    public int getStartX() {
        return startX;
    }

    public int getStartY() {
        return startY;
    }

    public GooseEffect getGooseEffect() {
        return gooseEffect;
    }

    public State getState() {
        return state;
    }

    public boolean isActive() {
        return state == State.ACTIVE;
    }

    public boolean isUsed() {
        return state == State.USED;
    }

    /**
     * this canges the state to used and returns false if the item has already been
     * used
     */
    public boolean use() {
        if (state == State.USED) {
            return false;
        }
        state = State.USED;
        return true;
    }

    @Override
    public String toString() {
        return name + " [" + gooseEffect + ", " + state + "] at (" + startX + ", " + startY + ")";
    }

}