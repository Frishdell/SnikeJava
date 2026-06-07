package org.example;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class Assets {
    Texture snake;
    TextureRegion snakeRotate;
    Texture apple;
    Texture gameStole;
    private float angle;
    void loadTextures() {
        snake = new Texture(Gdx.files.internal("sneak.png"));
        snakeRotate = new TextureRegion(snake);
        apple = new Texture("apple.png");
        gameStole = new Texture("gameStole.png");
    }
    public float getAngle() { return  angle; }
    public void  setAngle(float setAngle) { if ( setAngle >= 0) angle = setAngle;
    else { return; }
    }
}
