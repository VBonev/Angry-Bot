package com.valio.papoicheta;

import com.valio.papoicheta.screens.GameScreen;

import android.graphics.Rect;

public class End {

    private int centerX, centerY, speedX;
    private Background bg = GameScreen.getBackground1();
    public Rect r = new Rect(0, 0, 0, 0);

    public End(int x, int y) {
        centerX = x * 40;
        centerY = y * 40;
    }

    public void update() {
        centerX += speedX;
        speedX = bg.getSpeedX() * 5;
        if (centerX <= 800)
            r.set(centerX, centerY - 340, centerX + 64, centerY + 140);
    }

    public int getCenterX() {
        return centerX;
    }

    public int getCenterY() {
        return centerY;
    }
}