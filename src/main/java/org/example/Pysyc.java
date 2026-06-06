
package org.example;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;

public class Pysyc {
    private int sneakX = 100; // Добавим начальное значение, чтобы змейка не стартовала в 0,0
    private int sneakY = 100;
    private int score = 0;
    private String scoreToRender = "Score: 0";

    private Rectangle sneak = new Rectangle(100, 100, 32, 32);
    private Rectangle apple = new Rectangle(0, 0, 32, 32);

    void input(Assets assets) {
        // Если змейка ползет слишком медленно, можно поменять 1 на 2 или 3
        if (Gdx.input.isKeyPressed(Input.Keys.W)) { sneakY += 2; assets.setAngle(0f); }
        if (Gdx.input.isKeyPressed(Input.Keys.S)) { sneakY -= 2; assets.setAngle(180f); }
        if (Gdx.input.isKeyPressed(Input.Keys.A)) { sneakX -= 2; assets.setAngle(90f); }
        if (Gdx.input.isKeyPressed(Input.Keys.D)) { sneakX += 2; assets.setAngle(270f); }
    }

    void scoreLogic(Levels levels, BitmapFont font, SpriteBatch batch) {
        // Обновляем позицию змейки в пикселях
        sneak.setPosition(sneakX, sneakY);

        // ВАЖНО: Умножаем индекс ячейки на 32 (CELL_SIZE), чтобы перевести в экранные пиксели!
        int applePixelX = levels.getAppleX() * 32;
        int applePixelY = levels.getAppleY() * 32;
        apple.setPosition(applePixelX, applePixelY);

        int[][] world = levels.getWorldMassive();

        // Меняем contains на overlaps для фиксации любого касания
        if (sneak.overlaps(apple)) {
            score++;
            scoreToRender = "Score: " + score;

            // Зануляем ячейку в массиве, где лежало съеденное яблоко
            world[levels.getAppleX()][levels.getAppleY()] = 0;

            // Просим уровни заспавнить новое случайное яблоко
            levels.spawnApple();
        }

        // Отрисовка счета
        font.getData().setScale(2f);
        font.draw(batch, scoreToRender, 30, 680);
    }

    public float getX() { return sneakX; }
    public float getY() { return sneakY; }
}
