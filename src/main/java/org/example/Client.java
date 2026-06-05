package org.example;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import org.lwjgl.opengl.GL20;

public class Client extends ApplicationAdapter {
    Assets assets;
    Renderer renderer;
    SpriteBatch batch;
    Pysyc pysyc;
    @Override
    public void create() {
        batch = new SpriteBatch();
        pysyc = new Pysyc();
        assets = new Assets();
        renderer = new Renderer();
        assets.loadTextures();
    }
    @Override
    public void render() {
        com.badlogic.gdx.utils.ScreenUtils.clear(0f, 0f, 0f, 1f);
        pysyc.input(assets);
        renderer.render(assets, batch, pysyc);
    }
}
