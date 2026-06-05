package org.example;

import com.badlogic.gdx.backends.lwjgl3.Lwjgl3Application;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3ApplicationConfiguration;


public class Main {
    public static void main(String[] args) {
        Lwjgl3ApplicationConfiguration config = new Lwjgl3ApplicationConfiguration();

        config.setTitle("Snake Game");
        config.setWindowedMode(1270, 720); // Размер окна
        config.useVsync(true);            // Плавность (вертикальная синхронизация)
        config.setForegroundFPS(60);      // Ограничение в 60 кадров

        // Запускаем саму игру (класс SnakeGame создадим следующим шагом)
        new Lwjgl3Application(new Client(), config);
    }
}
