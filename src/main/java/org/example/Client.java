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
    Pysyc.Money money;
    SpriteBatch batch;
    Pysyc pysyc;


    @Override

    public void create() {
        batch = new SpriteBatch();
        pysyc = new Pysyc();
        levels = new Levels();
        assets = new Assets();
        money = pysyc.new Money();
        renderer = new Renderer();
        assets.loadTextures();
        dataBase = new DataBase();
        dataBase.initBase();

        // 2. Читаем прошлый счет и сразу пихаем его в физику змейки
        int loadedScore = DataBase.getBaseUniversal("game_saves", "saved_score", "score");
        int loadedMoney = DataBase.getBaseUniversal("game_saves", "saved_monet", "money");
        pysyc.setScore(loadedScore);
        money.setMoney(loadedMoney);

        font = new BitmapFont();
    }

    void saveGame() {
        if (Gdx.input.isKeyJustPressed(Input.Keys.ESCAPE)) {
            String saveAndExit = "CREATE TABLE IF NOT EXISTS game_saves ("
                    + "save_name TEXT NOT NULL, "
                    + "saved_score INTEGER NOT NULL,"
                    + "saved_monet INTEGER NOT NULL" // Эта часть у тебя уже есть
                    + ");";
            dataBase.createNewTable(saveAndExit);
            DataBase.insertProject("money", money.getMoney());
            DataBase.insertProject("score", pysyc.getScore());
            Gdx.app.exit();
        }
    }



    @Override
    public void render() {
        batch.begin();
        com.badlogic.gdx.utils.ScreenUtils.clear(0f, 0f, 100f, 1f);
        pysyc.input(assets);
        pysyc.scoreLogic(levels);
        money.moneyPhysyc();
        renderer.render(assets, batch, pysyc, levels, font, money);
        pysyc.goMenuScene(batch, assets);
        batch.end();
        saveGame();
    }
}
