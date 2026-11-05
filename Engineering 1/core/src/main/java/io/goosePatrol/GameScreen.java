package io.goosePatrol;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;

public class GameScreen extends ScreenAdapter {
    private Main game;
    private SpriteBatch batch;
    private BitmapFont font;

    public GameScreen(Main game) {
        this.game = game;
        batch = new SpriteBatch();
        font = new BitmapFont();
    }

    @Override
    public void render(float delta) {
        ScreenUtils.clear(0.5294f, 0.8078f, 0.9216f, 1);
        batch.begin();
        font.setColor(Color.WHITE);
        font.getData().setScale(2);
        float width = Gdx.graphics.getWidth();
        float height = Gdx.graphics.getHeight();
        font.draw(batch, "GAME STARTED", width / 2 - 100, height * 0.85f);
        batch.end();
    }

    @Override
    public void dispose() {
        batch.dispose();
        font.dispose();
    }
}
