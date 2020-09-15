package com.valio.papoicheta;
public class Background {
    private int bgX, bgY, speedX;
    public Background(int x, int y) {
        bgX = x;
        bgY = y;
        speedX = 0;
    }
    public void update() {
        bgX += speedX;
    }
    public int getBgX() {
        return bgX;
    }
    public int getBgY() {
        return bgY;
    }
    public int getSpeedX() {
        return speedX;
    }
    public void setSpeedX(int speedX) {
        this.speedX = speedX;
    }
}
