package org.example;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;

public class Pysyc {
    private float sneakX;
    private float sneakY;
    void input(Assets assets) {
        if (Gdx.input.isKeyPressed(Input.Keys.W)) { sneakY += 10; assets.setAngle(0f); }
        if (Gdx.input.isKeyPressed(Input.Keys.S)) { sneakY -= 10; assets.setAngle(180f);}
        if (Gdx.input.isKeyPressed(Input.Keys.A)) { sneakX -= 10; assets.setAngle(90);}
        if (Gdx.input.isKeyPressed(Input.Keys.D)) { sneakX += 10; assets.setAngle(270);}
    }
    public float getX() { return sneakX; }
    public float getY() { return sneakY; }
}
