package org.example;

public class Levels {
    private int[][] world = new int[32][32];
    private int appleX;
    private int appleY;

    // Конструктор: сработает ОДИН раз при старте игры
    public Levels() {
        spawnApple();
    }

    void spawnApple() {
        appleX = (int) (Math.random() * 32);
        appleY = (int) (Math.random() * 22);
        world[appleX][appleY] = 2;
    }

    public int[][] getWorldMassive() { return world; }
    public int getAppleX() { return appleX; }
    public int getAppleY() { return appleY; }
}
