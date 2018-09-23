package com.jkojote.snakegame.game;

import com.jkojote.snakegame.game.obj.base.GameObject;
import com.jkojote.snakegame.game.rendering.Renderers;

import javax.swing.JFrame;
import javax.swing.WindowConstants;
import java.awt.image.BufferStrategy;
import java.util.List;
import java.awt.Dimension;
import java.awt.Canvas;
import java.awt.Graphics;

public class Window extends Canvas {

    private JFrame jFrame;

    private String title;

    private int width;

    private int height;

    private Game game;

    public Window(String title, int width, int height, Game game) {
        this.title = title;
        this.width = width;
        this.height = height + 28;
        this.game = game;
        this.jFrame = new JFrame(title);
        jFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        jFrame.setResizable(false);
        jFrame.setMinimumSize(new Dimension(this.width, this.height));
        jFrame.setResizable(false);
        jFrame.add(this);
        jFrame.setVisible(true);
        jFrame.pack();
        createBufferStrategy(3);
    }

    public void renderAllObjects(List<GameObject> gameObjects) {
        BufferStrategy bs = getBufferStrategy();
        Graphics g = bs.getDrawGraphics();
        g.clearRect(0, 0, width, height);
        Renderers.renderAll(g, gameObjects);
        g.dispose();
        bs.show();
    }
}
