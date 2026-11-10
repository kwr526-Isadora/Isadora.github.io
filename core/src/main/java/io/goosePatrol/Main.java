package io.goosePatrol;
import com.badlogic.gdx.Game;

/**
 * The main class of the game that is called when the game is run
 * Responsible for setting the initial screen to MainMenu
 */
public class Main extends Game {
    /**
     * This method sets the initial game screen to MainMenu
     */

    @Override
    public void create() {
        setScreen(new MainMenu(this));
        //The main menu is the first screen displayed when the program is run
    }

    /**
     * Disposes the current screen and its resources
     */

    @Override
    public void dispose() {
       getScreen().dispose();
    }
}
