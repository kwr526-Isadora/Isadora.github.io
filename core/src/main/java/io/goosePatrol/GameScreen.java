package io.goosePatrol;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.OrthographicCamera;
import io.goosePatrol.objects.player.Player;

import static io.goosePatrol.TileMapHelper.PPM;

public class GameScreen extends ScreenAdapter {
    private Main game;
    private SpriteBatch batch;
    private BitmapFont font;
    private World world;
    private Box2DDebugRenderer box2DDebugRenderer;
    private OrthographicCamera camera;
    private OrthogonalTiledMapRenderer orthogonalTiledMapRenderer;
    private TileMapHelper tileMapHelper;
    private float gameStartedTimer = 0f;
    private float gameTimer = 300f;
    private Music music;


    // game objects
    private Player player;

    public GameScreen(Main game) {
        this.game = game;

        batch = new SpriteBatch();
        font = new BitmapFont();
        this.world = new World(new Vector2(0, 0), false);
        this.box2DDebugRenderer = new Box2DDebugRenderer();

        camera = new OrthographicCamera();
        camera.setToOrtho(false, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        camera.update();

        this.tileMapHelper = new TileMapHelper(this);
        this.orthogonalTiledMapRenderer = tileMapHelper.setupMap();

        orthogonalTiledMapRenderer.setView(camera);

        //music
        music = Gdx.audio.newMusic(Gdx.files.internal("music1.mp3"));
        music.setLooping(true);
        music.setVolume(1f);

        AssetManager assets = new AssetManager();
    }

    private void update(float delta) {

        player.update();
        world.step(delta, 6, 2);
        cameraUpdate();
        batch.setProjectionMatrix(camera.combined);
        orthogonalTiledMapRenderer.setView(camera);

        if (Gdx.input.isKeyPressed(Input.Keys.ESCAPE)) {
            Gdx.app.exit();
        }
    }

    private void cameraUpdate() {
        Vector3 position = camera.position;
        position.x = Math.round(player.getBody().getPosition().x * PPM * 10) / 10f;
        position.y = Math.round(player.getBody().getPosition().y * PPM * 10) / 10f;
        camera.position.set(position);
        camera.update();
    }

    @Override
    public void render(float delta) {
        update(delta);
        ScreenUtils.clear(0, 0, 0, 1);
        camera.update();
        orthogonalTiledMapRenderer.setView(camera);
        orthogonalTiledMapRenderer.render();

        batch.begin();
        if (gameTimer > 0) {
            gameTimer -= delta;
        }
        int minutes = (int) gameTimer / 60;
        int seconds = (int) gameTimer % 60;
        font.setColor(Color.WHITE);
        font.getData().setScale(2);
        font.draw(batch, String.format("Time: %02d:%02d", minutes, seconds), 20, Gdx.graphics.getHeight() - 20);
        if (gameStartedTimer < 3f) {
            font.setColor(Color.WHITE);
            font.getData().setScale(2);
            float width = Gdx.graphics.getWidth();
            float height = Gdx.graphics.getHeight();
            font.draw(batch, "GAME STARTED", width / 2 - 100, height * 0.85f);
        }
        batch.end();
        box2DDebugRenderer.render(world, camera.combined.scl(PPM));
        gameStartedTimer += delta;
    }

    public World getWorld() {
        return world;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    @Override
    public void resize(int width, int height) {
        camera.setToOrtho(false, width, height);
        camera.update();
        orthogonalTiledMapRenderer.setView(camera);
    }
    @Override
    public void dispose() {
        batch.dispose();
        font.dispose();
    }
}
