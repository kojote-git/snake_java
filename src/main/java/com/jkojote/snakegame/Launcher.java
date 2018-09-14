package com.jkojote.snakegame;

import com.jkojote.snakegame.game.Game;

public class Launcher {
    public static void main(String[] args) throws Exception {
        System.setProperty("sun.java2d.opengl", "true");
        Thread thread = new Thread(new Game());
        thread.start();
        thread.join();
    }
}
