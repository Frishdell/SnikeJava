package org.example;

import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Renderer {
    int CELL_SIZE = 32;
    void render(Assets assets, SpriteBatch batch, Pysyc pysyc, Levels levels, BitmapFont font) {
        batch.begin();
        int[][] world = levels.getWorldMassive();
        batch.draw(assets.snakeRotate, pysyc.getX(), pysyc.getY(), 30, 30, 60, 60,  1, 1, assets.getAngle() );
        for(int x = 0; x < 32; x++) {
            for ( int y = 0; y < 32; y++) {
                int screenX = x * CELL_SIZE;
                int screenY = y * CELL_SIZE;
                if ( world[x][y] == 2) {
                    batch.draw(assets.apple, screenX,screenY, 32, 32);
                }
            }
        }
        pysyc.scoreLogic(levels, font, batch);
        batch.end();
    }
}
