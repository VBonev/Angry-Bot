package com.valio.papoicheta.Enemies;

import android.graphics.Rect;

import com.valio.framework.Image;
import com.valio.papoicheta.Background;
import com.valio.papoicheta.Terrain;
import com.valio.papoicheta.screens.GameScreen;

import static com.valio.papoicheta.ProjectGame.settings;

public class Follower extends IEvilEnemy {


    public Follower(int x, int y) {
        centerX = x * 40;
        centerY = y * 45;
    }

    @Override
    public void update() {

        centerX += speedX;
        centerY += speedY;
        speedX = bg.getSpeedX() * 5 + movementSpeed;
        if (speedY > 3) {
            jumped = true;
        }
        if (centerX <= 800) {
            speedY += 1;
            if (collide) {
                r.set(centerX - 28, centerY - 45, centerX + 28, centerY + 45);
                lowerRect.set(centerX - 20, centerY + 5, centerX + 20, centerY + 46);
                upRect.set(centerX - 30, centerY - 48, centerX + 30, centerY - 40);
                leftRect.set(centerX - 30, centerY - 5, centerX + 1, centerY + 5);
                rightRect.set(centerX - 1, centerY - 5, centerX + 30, centerY + 5);
                follow();
                checkCollision();
            }
        }
    }

    private void checkCollision() {
        for (int i = 0; i < GameScreen.getTerrain().size(); i++) {
            if (GameScreen.getTerrain().get(i) instanceof Terrain) {
                Terrain ter = (Terrain) GameScreen.getTerrain().get(i);
                if (Rect.intersects(ter.r, lowerRect)) {
                    centerY = ter.getY() - 40;
                    speedY = 0;
                    return;
                }
                if (Rect.intersects(ter.r, rightRect)) {
                    centerX = ter.getX() - 40;
                    jump();
                    return;
                } else if (Rect.intersects(ter.r, leftRect)) {
                    centerX = ter.getX() + 70;
                    jump();
                }
            }
        }
    }

    public void jump() {
        if (jumped == false) {
            speedY += JUMPSPEED;
            jumped = true;
        }

    }

    public void follow() {
        if (centerX < -95 || centerX > 810) {
            movementSpeed = 0;
        } else if (Math.abs(minion.getCenterX() - centerX) < 5) {
            movementSpeed = 0;
        } else {
            if (minion.getCenterX() >= centerX) {
                if (settings.getCurrentChapter() == 2)
                    movementSpeed = 2;
                else
                    movementSpeed = 1;
            } else {

                if (settings.getCurrentChapter() == 2)
                    movementSpeed = -2;
                else
                    movementSpeed = -1;
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

    public void setHealth(int health) {
        this.health = health;
    }

    public Image getBackEnemy() {
        return backEnemy;
    }

    public void setBackEnemy(Image backEnemy) {
        this.backEnemy = backEnemy;
    }
}