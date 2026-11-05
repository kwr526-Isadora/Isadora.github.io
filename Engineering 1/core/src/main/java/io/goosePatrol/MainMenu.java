package io.goosePatrol;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Rectangle;

/**
 * Represents the main menu screen of the game
 * Displays the game title
 * Displays three options for the player to choose: Play, Instructions and Quit
 * Allows the player to choose whether to start the game, read the instructions, or exit the game
 */
public class MainMenu extends ScreenAdapter {
    private Main game;
    private SpriteBatch batch;
    private BitmapFont font;
    private ShapeRenderer shape;
    private Rectangle playButton;
    private Rectangle instructionsButton;
    private Rectangle quitButton;

    /**
     * Constructs the main menu
     * Initialises the objects on the main menu
     * @param game the main game instance, which is used to switch between screens
     */
    public MainMenu(Main game) {
        this.game = game;
        batch = new SpriteBatch();
        font = new BitmapFont();
        shape = new ShapeRenderer();
        playButton = new Rectangle();
        instructionsButton = new Rectangle();
        quitButton = new Rectangle();
    }

    /**
     * Renders the main menu screen
     * Renders its background, title and buttons
     * Checks the player's mouse input to determine
     * which menu option they have selected
     * @param delta The time in seconds since the last render.
     */
    @Override
    public void render(float delta) {
        ScreenUtils.clear(0.5294f, 0.8078f, 0.9216f, 1); //The colour of sky blue
        float width = Gdx.graphics.getWidth();
        float height = Gdx.graphics.getHeight();

        float buttonWidth = 260;
        float buttonHeight = 55;
        float gap = 20;

        float x = (width - buttonWidth) / 2;
        float startY = (height + 205) / 2;

        playButton.set(x, startY - buttonHeight, buttonWidth, buttonHeight);
        instructionsButton.set(x, startY - buttonHeight * 2 - gap, buttonWidth, buttonHeight);
        quitButton.set(x, startY - buttonHeight * 3 - gap * 2, buttonWidth, buttonHeight);

        shape.begin(ShapeRenderer.ShapeType.Filled);
        shape.setColor(Color.WHITE);
        shape.rect(playButton.x, playButton.y, playButton.width, playButton.height);
        shape.rect(instructionsButton.x, instructionsButton.y, instructionsButton.width, instructionsButton.height);
        shape.rect(quitButton.x, quitButton.y, quitButton.width, quitButton.height);
        shape.end();

        batch.begin();
        font.setColor(Color.BLACK);
        font.getData().setScale(2);
        font.draw(batch, "Goose Patrol", width / 2 - 100, height * 0.8f);
        font.draw(batch, "PLAY", playButton.x + 100, playButton.y + 40);
        font.draw(batch, "INSTRUCTIONS", instructionsButton.x + 25, instructionsButton.y + 40);
        font.draw(batch, "QUIT", quitButton.x + 100, quitButton.y + 40);
        batch.end();

        if (Gdx.input.justTouched()) {
            float mouseX = Gdx.input.getX();
            float mouseY = height - Gdx.input.getY();

            if (playButton.contains(mouseX, mouseY)) {
                game.setScreen(new GameScreen(game));
            }

            if (instructionsButton.contains(mouseX, mouseY)) {
                game.setScreen(new InstructionsScreen(game));
            }

            if (quitButton.contains(mouseX, mouseY)) {
                Gdx.app.exit();
            }
        }
    }

    /**
     * Disposes the resources used by the main menu
     * Disposes the SpriteBatch, BitmapFont and ShapeRenderer
     */
    @Override
    public void dispose() {
        batch.dispose();
        font.dispose();
        shape.dispose();
    }
}
