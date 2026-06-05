package org.example;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Renderer {
    void render(Assets assets, SpriteBatch batch, Pysyc pysyc) {
        batch.begin();
        batch.draw(assets.snakeRotate, pysyc.getX(), pysyc.getY(), 30, 30, 60, 60,  1, 1, assets.getAngle() );
        batch.end();
    }
}
