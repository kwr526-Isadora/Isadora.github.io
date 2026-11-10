package io.goosePatrol;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;

/**
 * Represents the instructions screen of the game
 * Displays text containing how to play and the controls of the game
 * Allows the user to return back to the main menu once they have finished reading
 */
public class InstructionsScreen extends ScreenAdapter {
    private Main game;
    private SpriteBatch batch;
    private BitmapFont font;

    /**
     * Constructs the instructions screen
     * Initialises the objects on the instructions screen
     * @param game the main game instance, which is used to switch between screens
     */
    public InstructionsScreen(Main game) {
        this.game = game;
        batch = new SpriteBatch();
        font = new BitmapFont();
    }

    /**
     * Renders the instruction screen
     * Renders its background, title and text
     * Checks the player's mouse input to determine whether to return to the main menu
     * @param delta The time in seconds since the last render.
     */
    @Override
    public void render(float delta) {
        ScreenUtils.clear(0.5294f, 0.8078f, 0.9216f, 1);
        batch.begin();
        font.setColor(Color.WHITE);
        font.getData().setScale(2);
        float width = Gdx.graphics.getWidth();
        float height = Gdx.graphics.getHeight();
        font.draw(batch, "HOW TO PLAY", width / 2 - 100, height * 0.85f);
        font.getData().setScale(1.5f);
        font.draw(batch, "Defeat all the geese on the map to win", width / 2 - 170, height * 0.65f);
        font.draw(batch, "Use the keys WASD to move your character", width / 2 - 170, height * 0.55f);
        font.draw(batch, "Find and use items (E) around the map to help you", width / 2 - 170, height * 0.45f);
        font.draw(batch, "Avoid the geese to prevent taking damage", width / 2 - 170, height * 0.35f);
        font.draw(batch, "Try and find all the hidden events!", width / 2 - 170, height * 0.25f);
        font.draw(batch, "Click anywhere to return", width / 2 - 100, height * 0.05f);
        batch.end();

        if (Gdx.input.justTouched()) {
            game.setScreen(new MainMenu(game));
        }
    }

    /**
     * Disposes the resources used by the instruction screen
     * Disposes the SpriteBatch and BitmapFont
     */
    @Override
    public void dispose() {
        batch.dispose();
        font.dispose();
    }
}
