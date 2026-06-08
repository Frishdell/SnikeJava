package org.example;

import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Renderer {
    int CELL_SIZE = 32;
    void render(Assets assets, SpriteBatch batch, Pysyc pysyc, Levels levels, BitmapFont font, Pysyc.Money money) {
        batch.draw(assets.gameStole, 0,0,1270,720);
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
        font.draw(batch, "Money: " + money.getMoney(), 30, 630);
        font.draw(batch, pysyc.getScoreToRender(), 30, 680);

        // Отрисовка счета

        font.getData().setScale(2f);
    }
}
