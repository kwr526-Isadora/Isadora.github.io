import com.badlogic.gdx.math.MathUtils;

public class HiddenEvents {

    // locations of the events (need to change these to match the map)
    private float statueX = 400, statueY = 300;
    private float poopX = 800, poopY = 120;
    private float dogBuildingX = 200, dogBuildingY = 500;
    private float alarmX = 600, alarmY = 600;

    // how close the player has to be
    private float radius = 64;

    // longboi statue stuff
    private boolean nearStatue = false;
    private boolean speedBoost = false;
    private float speedBoostTimer = 0;

    // so the events only happen once
    private boolean poopTriggered = false;
    private boolean dogTriggered = false;
    private boolean alarmTriggered = false;

    private boolean hasExtinguisher = false;

    // gets the distance between the player and a place
    private float getDistance(float playerX, float playerY, float x, float y) {
        float dx = playerX - x;
        float dy = playerY - y;
        return (float) Math.sqrt(dx * dx + dy * dy);
    }

    public void update(float delta, float playerX, float playerY, boolean ePressed) {

        // --- longboi statue ---
        // only roll the dice when the player first walks up to it
        if (getDistance(playerX, playerY, statueX, statueY) < radius) {
            if (!nearStatue) {
                nearStatue = true;
                int roll = MathUtils.random(1, 10);
                if (roll == 1) {
                    speedBoost = true;
                    speedBoostTimer = 30;
                    // TODO make geese faster
                }
            }
        } else {
            nearStatue = false;
        }

        // count down the 30 seconds
        if (speedBoost) {
            speedBoostTimer -= delta;
            if (speedBoostTimer <= 0) {
                speedBoost = false;
                // goose speed back to normal
            }
        }

        // the other events need the player to press E
        if (ePressed) {

            // --- slipping on goose poop ---
            if (!poopTriggered && getDistance(playerX, playerY, poopX, poopY) < radius) {
                poopTriggered = true;
                // drop all the items
            }

            // --- dog walker ---
            if (!dogTriggered && getDistance(playerX, playerY, dogBuildingX, dogBuildingY) < radius) {
                dogTriggered = true;
                // spawn the dog and scare geese
            }

            // --- fire alarm ---
            if (!alarmTriggered && getDistance(playerX, playerY, alarmX, alarmY) < radius) {
                alarmTriggered = true;
                hasExtinguisher = true;
                // remove the geese
            }
        }
    }
}
}
