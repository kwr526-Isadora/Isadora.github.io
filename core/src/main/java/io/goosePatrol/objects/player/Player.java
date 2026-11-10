package io.goosePatrol.objects.player;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;

import static io.goosePatrol.TileMapHelper.PPM;

public class Player extends GameEntity {

    public Player(float width, float height, Body body) {
        super(width, height, body);
        this.speed = 10f;
    }

    @Override
    public void update() {
        checkUserInput();
    }


    @Override
    public void render(SpriteBatch batch) {

    }

    private void checkUserInput() {
        velX = 0;
        velY = 0;

        if (Gdx.input.isKeyPressed(Input.Keys.D)) {
            velX = 1;
        }
        if (Gdx.input.isKeyPressed(Input.Keys.A)) {
            velX = -1;
        }
        if (Gdx.input.isKeyPressed(Input.Keys.W)) {
            velY = 1;
        }
        if (Gdx.input.isKeyPressed(Input.Keys.S)) {
            velY = -1;
        }

        // Ensures that moving diagonally does not make you faster by normalising the vector
        Vector2 movement = new Vector2(velX, velY);
        if (movement.len() > 0) {
            movement.nor().scl(speed);
        }
        body.setLinearVelocity(movement);

    }
}
