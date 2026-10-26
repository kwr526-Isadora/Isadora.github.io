/**
 * this is a class the contains the effect that each item has on the geese
 */
public class GooseEffect {

    /** this is what the items do */
    public enum Type {
        SCARE, // the effected geese are removed from this area of the map
        DISTRACT, // the effected geese go towards the item for a set amount of time
        TAME // the geese stop flowing the player
    }

    private final Type type;
    private final int duration; // how long the effect lasts (in seconds)
    private final int radius; // hthis is the radius of the item, any geese inside will bee effected by it

    public GooseEffect(Type type, int duration, int radius) {
        this.type = type;
        this.duration = duration;
        this.radius = radius;
    }

    public Type getType() {
        return type;
    }

    public int getDuration() {
        return duration;
    }

    public int getRadius() {
        return radius;
    }

    @Override
    public String toString() {
        return type + " (" + duration + ", radius " + radius + ")";
    }
}