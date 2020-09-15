package com.valio.papoicheta.Enemies;

import android.graphics.Rect;

import com.valio.framework.Image;
import com.valio.papoicheta.Background;
import com.valio.papoicheta.Terrain;
import com.valio.papoicheta.screens.GameScreen;

public class Jumper extends IEvilEnemy {

    public Jumper(int x, int y) {
        centerX = x * 40;
        centerY = y * 40;
    }

    @Override
    public void update() {

        centerX += speedX;
        if (centerX == 800)
            movementSpeed = -3;
        if (centerX == 0)
            movementSpeed = 3;
        speedX = bg.getSpeedX() * 5 + movementSpeed;
        centerY += speedY;
        if (centerX <= 800) {
            speedY += 0.5;
            if (collide) {
                r.set(centerX - 15, centerY - 40, centerX + 15, centerY + 40);
//                lowerRect.set(centerX - 20, centerY - 40, centerX + 20, centerY + 45);
//                upRect.set(centerX - 15, centerY - 43, centerX + 16, centerY - 35);
//                leftRect.set(centerX -16, centerY - 5, centerX + 1, centerY + 5);
                rightRect.set(centerX - 1, centerY - 5, centerX + 20, centerY + 5);
                follow();
                checkCollision();
            }
        }
    }

    private void checkCollision() {
        for (int i = 0; i < GameScreen.getTerrain().size(); i++) {
            if (GameScreen.getTerrain().get(i) instanceof Terrain) {
                Terrain ter = (Terrain) GameScreen.getTerrain().get(i);
                if (Rect.intersects(ter.r, r)) {
                    centerY = ter.getY() - 50;
                    speedY = -10;
                    return;
                }
                if (Rect.intersects(ter.r, r)) {
                    centerY = ter.getY() - 50;
                    movementSpeed = 3;
                    return;
                }  if (Rect.intersects(ter.r, r)) {
                    centerY = ter.getY() - 50;
                    movementSpeed = -3;
                    return;
                }
            }
        }
    }

    public void jump() {
        if (jumped == false) {
            speedY += -5;
            jumped = true;
        }

    }

    public void follow() {
        if (centerX < -95 || centerX > 810) {
            movementSpeed = 0;
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

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public Image getFrontEnemy() {
        return frontEnemy;
    }

    public void setFrontEnemy(Image frontEnemy) {
        this.frontEnemy = frontEnemy;
    }

    public Image getBackEnemy() {
        return backEnemy;
    }

    public void setBackEnemy(Image backEnemy) {
        this.backEnemy = backEnemy;
    }
}