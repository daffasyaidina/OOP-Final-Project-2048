package com.finalproject.main2048.input;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Keyboard implements KeyListener{

    public static boolean[] keys = new boolean[120];
    public static boolean[] lastKeys = new boolean[120];

    public void update() { 
        for(int i = 0; i < keys.length; i++) {
            lastKeys[i] = keys[i];
        }
    }

    public static boolean key(int key) {  //when the key is pressed
        return keys[key];
    }
    public static boolean keyDown(int key) { //when the key is started getting pressed
        return keys[key] && !lastKeys[key];
    }
    public static boolean keyUp(int key) { //when the key is being released
        return !keys[key] && lastKeys[key];
    }

    public void keyTyped(KeyEvent e) { 
        
    }

    public void keyPressed(KeyEvent e) { //when the key is being pressed it inputs
        keys[e.getKeyCode()] = true;
        
    }

    public void keyReleased(KeyEvent e) { //when the key released it stop the input
        keys[e.getKeyCode()] = false;
        
    }
    
}
