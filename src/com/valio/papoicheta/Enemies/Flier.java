package com.valio.papoicheta.Enemies;

import android.graphics.Rect;

import com.valio.papoicheta.Background;
import com.valio.papoicheta.Terrain;
import com.valio.papoicheta.screens.GameScreen;

public class Flier extends IEvilEnemy {


    public Flier(int x, int y) {
        centerX = x * 40;
        centerY = y * 40;
    }

    @Override
    public void update() {
        centerX += speedX;
        speedX = bg.getSpeedX() * 5 + movementSpeed;
        centerY += speedY;
        if (centerX <= 800) {
            if (collide)
                speedY = 1;
            else
                speedY = 10;
            if (collide) {
                upRect.set(centerX - 17, centerY - 18, centerX + 17, centerY - 10);
//                leftRect.set(centerX - 17, centerY - 5, centerX + 1, centerY + 5);
//                rightRect.set(centerX - 1, centerY - 5, centerX + 17, centerY + 5);
                r.set(centerX - 15, centerY - 15, centerX + 15, centerY + 15);
                if (centerY > minion.getCenterY() - 20)
                    speedY = -1;
                follow();
                checkCollision();
            }
        }
    }

    public void jump() {
        if (jumped == false) {
            speedY = -40;
            jumped = true;
        }

    }

    private void checkCollision() {
        for (int i = 0; i < GameScreen.getTerrain().size(); i++) {
            if (GameScreen.getTerrain().get(i) instanceof Terrain) {
                Terrain ter = (Terrain) GameScreen.getTerrain().get(i);
                if (Rect.intersects(ter.r, r)) {
                    centerY = ter.getY() - 80;
                    speedY = -20;
                    return;
                }
                if (Rect.intersects(ter.r, r)) {
                    centerX = ter.getX() - 20;
                    return;
                }  if (Rect.intersects(ter.r, r)) {
                    centerX = ter.getX() + 20;
                }
            }
        }
    }

    public void follow() {
        if (centerX < -95 || centerX > 810) {
            movementSpeed = 0;
        } else if (Math.abs(minion.getCenterX() - centerX) < 5) {
            movementSpeed = 0;

        } else {
            if (minion.getCenterX() >= centerX) {
                movementSpeed = 2;

            } else {
                movementSpeed = -2;
            }
        }

    }

    public boolean isMovingRight() {
        if (movementSpeed > 0)
            return true;
        return false;
    }


    @Override
    public int getSpeedX() {
        return speedX;
    }

    @Override
    public int getCenterX() {
        return centerX;
    }

    @Override
    public int getCenterY() {
        return centerY;
    }

    @Override
    public Background getBg() {
        return bg;
    }

    @Override
    public double getSpeedY() {
        return speedY;
    }

    @Override
    public void setSpeedY(int speedY) {
        this.speedY = speedY;
    }

    @Override
    public void setSpeedX(int speedX) {
        this.speedX = speedX;
    }

    @Override
    public void setCenterX(int centerX) {
        this.centerX = centerX;
    }

    @Override
    public void setCenterY(int centerY) {
        this.centerY = centerY;
    }

    @Override
    public void setBg(Background bg) {
        this.bg = bg;
    }

}