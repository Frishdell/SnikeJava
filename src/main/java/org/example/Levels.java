package org.example;

import java.util.ArrayList;

public class Levels {
    private int[][] world = new int[32][32];
    private int appleX;
    private int appleY;
    void world() {
        appleX = (int) (Math.random() * 32);
        appleY = (int) (Math.random() * 32);
        world[appleX][appleY] = 2;
    }
    public int[][] getWorldMassive() { return world; }
}
