package org.example;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;


public class Client extends ApplicationAdapter {
    Assets assets;
    Renderer renderer;
    Levels levels;
    BitmapFont font;
    DataBase dataBase;
    SpriteBatch batch;
    Pysyc pysyc;
    @Override

    public void create() {
        batch = new SpriteBatch();
        pysyc = new Pysyc();
        levels = new Levels();
        assets = new Assets();
        renderer = new Renderer();
        assets.loadTextures();
        dataBase = new DataBase();
        dataBase.initBase();

        // 2. Читаем прошлый счет и сразу пихаем его в физику змейки
        int loadedScore = DataBase.getBaseUniversal("game_saves", "saved_score", "score");
        pysyc.setScore(loadedScore);

        font = new BitmapFont();
    }

    void saveGame() {
        if (Gdx.input.isKeyJustPressed(Input.Keys.ESCAPE)) {
            String saveAndExit = "CREATE TABLE IF NOT EXISTS game_saves ("
                    + "save_name TEXT NOT NULL, "
                    + "saved_score INTEGER NOT NULL"
                    + ");";
            dataBase.createNewTable(saveAndExit);
            DataBase.insertProject("score", pysyc.getScore());
            Gdx.app.exit();
        }
    }


    @Override
    public void render() {
        com.badlogic.gdx.utils.ScreenUtils.clear(0f, 0f, 0f, 1f);
        pysyc.input(assets);
        renderer.render(assets, batch, pysyc, levels, font, this);
        saveGame();
    }
}
