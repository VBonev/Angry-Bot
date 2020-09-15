package com.valio.papoicheta;

import android.graphics.Rect;

import com.valio.framework.Image;
import com.valio.papoicheta.screens.GameScreen;
import com.valio.papoicheta.screens.LoadingScreen;

public class Coin {
    private int centerX, centerY, speedX;
    private Background bg = GameScreen.getBackground1();
    public Rect r = new Rect(0, 0, 0, 0);
    Image monetka;

    public Coin(int x, int y) {
        centerX = x * 40;
        centerY = y * 40;
    }

    public void update() {
            monetka = LoadingScreen.coin.getImage();
        centerX += speedX;
        speedX = bg.getSpeedX() * 5;
        if (centerX <= 800) {
            r.set(centerX, centerY, centerX + 30, centerY + 30);

        }
    }


    public int getCenterX() {
        return centerX;
    }

    public int getCenterY() {
        return centerY;
    }

    public Image getMonetka() {
        return monetka;
    }

    public void setMonetka(Image monetka) {
        this.monetka = monetka;
    }
}