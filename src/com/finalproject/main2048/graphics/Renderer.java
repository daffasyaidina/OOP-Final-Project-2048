package com.finalproject.main2048.graphics;

import com.finalproject.main2048.Main;

public class Renderer {
    
    public static int width = Main.WIDTH, height = Main.HEIGHT;
    public static int[] pixels = new int[width * height];

    public static void renderBackground() { //set the looks of the background
        for(int y = 0; y < height; y++) {
            for(int x = 0; x < width; x++) {
                pixels[x + y * width] = 0xff6d6d6d;

                if(x % 100 < 3 || x % 100 > 97 || y % 100 < 3 || y % 100 > 97) {
                    pixels[x + y * width] = 0xff3e3e3e;
                }
            }
        }
    }
    public static void renderSprite(Sprite sprite, int xp, int yp) { //set the color of the objects to tranparant
        if(xp < -sprite.width || xp > width || yp < -sprite.height || yp > height) return;

        for(int y = 0; y < sprite.height; y++) {
            int yy = y + yp;
            if(yy < 0 || yy > height) continue;

            for(int x = 0; x < sprite.width; x++) {
                int xx = x + xp;
                if(xx < 0 || xx > width) continue;
                int col = sprite.pixels[x + y * sprite.width];
                if(col == 0xffff00ff) continue;
                else pixels[xx + yy * width] =  col;           
            }
        }
    } 
    
}
