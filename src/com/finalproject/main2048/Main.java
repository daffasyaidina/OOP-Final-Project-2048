package com.finalproject.main2048;

import java.awt.Canvas;
import java.awt.Dimension;
import java.awt.Graphics2D;

import javax.swing.JFrame;
import com.finalproject.main2048.game.*;
import com.finalproject.main2048.input.*;

import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;

public class Main extends Canvas implements Runnable{

    public static final int WIDTH = 400, HEIGHT = 400;
    public static float scale = 2.0f;

    public JFrame frame;
    public Thread thread;
    public Keyboard key;
    public Game game;
    public boolean running = false;

    public static BufferedImage image = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);
    public static int[] pixels = ((DataBufferInt) image.getRaster().getDataBuffer()).getData();

    public Main() { 
        setPreferredSize(new Dimension((int) (WIDTH * scale), (int) (HEIGHT * scale)));
        frame = new JFrame();
        game = new Game();
        key = new Keyboard();
        addKeyListener(key);
    }



    public void start() { //start a the thread or game loop
        running = true;
        thread = new Thread(this, "loopthread");
        thread.start();

    }

    public void stop() { // stop the thread
        try {
            thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


    }

    public void run() { //update 60 times per second and render as many times as possible(depends on how strong the computer is)
        long lastTimeInNanoSeconds = System.nanoTime();
        long timer = System.currentTimeMillis();
        double nanoSecondsPerUpdate = 1000000000.0 / 60.0;
        double updateToPerform = 0.0;
        int frames = 0;
        int updates = 0;
        requestFocus();
        while(running) {
            long currentTimeInNanoSeconds = System.nanoTime();
            updateToPerform += (currentTimeInNanoSeconds - lastTimeInNanoSeconds) / nanoSecondsPerUpdate;
            if(updateToPerform >= 1) {
                update();
                updates++;
                updateToPerform--;
            }
            lastTimeInNanoSeconds = currentTimeInNanoSeconds;

            render();
            frames++;

            if(System.currentTimeMillis() - timer > 1000) {
                frame.setTitle("2048" + updates + "updates" + frames + "frames");
                updates = 0;
                frames = 0;
                timer += 1000;           
            }
        }
        
    }

    public void update() {  //updates when the keybinds is pressed
        game.update();
        key.update();

    }

    public void render() { //renders the background and numbers
        BufferStrategy bs = getBufferStrategy();
        if(bs == null) {
            createBufferStrategy(3);
            return;
        }

        game.render();

        Graphics2D g = (Graphics2D) bs.getDrawGraphics();
        g.drawImage(image, 0, 0, (int) (WIDTH * scale), (int) (HEIGHT * scale), null);
        game.renderText(g);
        g.dispose();
        bs.show();


    }
    
    public static void main(String[] args) { //the main method that creates the game window
        Main m = new Main();
        m.frame.setResizable(false);
        m.frame.setTitle("2048 FP");
        m.frame.add(m);
        m.frame.pack();
        m.frame.setVisible(true);
        m.frame.setLocationRelativeTo(null);
        m.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        m. frame.setAlwaysOnTop(true);
        m.start();

    }
}
