package org.example;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;

public class Pysyc {
    private int sneakX = 100;
    private int sneakY = 100;
    private int score = 0;
    private boolean isMenuOpen;

    private String scoreToRender = "Score: 0";

    public class Money {
        private int money;
        void moneyPhysyc() {
            money = (score * 2) + 4 * 6;
        }
        public int getMoney() { return  money;}
        public void setMoney(int setMoney ) { money = setMoney;}
    }

    private Rectangle sneak = new Rectangle(100, 100, 32, 32);
    private Rectangle apple = new Rectangle(0, 0, 32, 32);

    void input(Assets assets) {
        if (Gdx.input.isKeyPressed(Input.Keys.W)) { sneakY += 2; assets.setAngle(0f); }
        if (Gdx.input.isKeyPressed(Input.Keys.S)) { sneakY -= 2; assets.setAngle(180f); }
        if (Gdx.input.isKeyPressed(Input.Keys.A)) { sneakX -= 2; assets.setAngle(90f); }
        if (Gdx.input.isKeyPressed(Input.Keys.D)) { sneakX += 2; assets.setAngle(270f); }

        // ВОЗВРАЩАЕМ БАЗУ: вызываем сохранение при нажатии ESCAPE
        saveGame();
    }

    // Тот самый метод сохранения, который ИИ потерял
    void saveGame() {
        if (Gdx.input.isKeyJustPressed(Input.Keys.ESCAPE)) {
            // Создаем чертеж таблицы для сохранений
            String saveTableSql = "CREATE TABLE IF NOT EXISTS game_saves ("
                    + "save_name TEXT NOT NULL, "
                    + "saved_score INTEGER NOT NULL"
                    + ");";

            // Сначала создаем/проверяем таблицу
            DataBase.createNewTable(saveTableSql);

            // Потом впихиваем текущий score
            DataBase.insertProject("score", score);

            System.out.println("SQL: Счет " + score + " успешно сохранен в базу! Закрываемся...");
            Gdx.app.exit(); // Выходим из LibGDX
        }
    }

    void scoreLogic(Levels levels) {
        sneak.setPosition(sneakX, sneakY);

        int applePixelX = levels.getAppleX() * 32;
        int applePixelY = levels.getAppleY() * 32;
        apple.setPosition(applePixelX, applePixelY);

        int[][] world = levels.getWorldMassive();

        if (sneak.overlaps(apple)) {
            score++;
            scoreToRender = "Score: " + score;
            world[levels.getAppleX()][levels.getAppleY()] = 0;
            levels.spawnApple();
        }

        // ИСПРАВЛЕНО: Перенесли обновление строки СЮДА (вне блока if).
        // Теперь счетчик на экране будет сразу показывать то, что прилетело из базы!
    }

    public String getScoreToRender() { return scoreToRender;}

    public int getScore() { return score; }

    // Сюда твоя игра при старте будет закидывать счет из DataBase.getBaseUniversal
    public void setScore(int setScore) {
        if (setScore >= 0) {
            score = setScore;
        }
    }

    public void goMenuScene(SpriteBatch batch, Assets assets) {
        if ( MenuScene() ) {
            batch.draw(assets.MenuScene, 0, -60, 1270, 900);
        }
    }

    private Boolean MenuScene() {
        if ( Gdx.input.isKeyJustPressed(Input.Keys.M)) {
            isMenuOpen = !isMenuOpen;
        }

        return isMenuOpen;
    }

    public float getX() { return sneakX; }
    public float getY() { return sneakY; }
}
