package org.example;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Pysyc {
    private int sneakX;
    private int sneakY;
    private int score = 0;
    private String scoreToRender = "Score: 0";
    void input(Assets assets) {
        if (Gdx.input.isKeyPressed(Input.Keys.W)) { sneakY += 1; assets.setAngle(0f); }
        if (Gdx.input.isKeyPressed(Input.Keys.S)) { sneakY -= 1; assets.setAngle(180f);}
        if (Gdx.input.isKeyPressed(Input.Keys.A)) { sneakX -= 1; assets.setAngle(90);}
        if (Gdx.input.isKeyPressed(Input.Keys.D)) { sneakX += 1; assets.setAngle(270);}
    }
    void scoreLogic(Levels levels, BitmapFont font, SpriteBatch batch) {
        int[][] world = levels.getWorldMassive();
        int arrayX = (sneakX % 32 + 32) % 32;
        int arrayY = (sneakY % 32 + 32) % 32;
            if (world[arrayX][arrayY] == 2) {
                    score++;
                    scoreToRender = "Score: " + score;
                    world[sneakX][sneakY] = 0;
                    font.getData().setScale(2f);
                    font.draw(batch, scoreToRender, 30, 680);
            }
        font.draw(batch, scoreToRender, 30, 680);
    }
    public float getX() { return sneakX; }
    public float getY() { return sneakY; }
}
