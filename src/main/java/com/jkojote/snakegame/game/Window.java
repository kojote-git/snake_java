package com.jkojote.snakegame.game;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferStrategy;

public class Window extends Canvas {

    private JFrame jFrame;

    private String title;

    private int width;

    private int height;

    private Game game;

    public Window(String title, int width, int height, Game game) {
        this.title = title;
        this.width = width;
        this.height = height;
        this.game = game;
        this.jFrame = new JFrame(title);
        jFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        jFrame.setResizable(false);
        jFrame.setMinimumSize(new Dimension(width, height));
        jFrame.setResizable(false);
        jFrame.add(this);
        jFrame.setVisible(true);
        jFrame.pack();
    }
}
