package com.valio.papoicheta.Enemies;

import android.graphics.Rect;

import com.valio.papoicheta.Assets;
import com.valio.papoicheta.Background;
import com.valio.papoicheta.EnemyProjectile;
import com.valio.papoicheta.Terrain;
import com.valio.papoicheta.screens.GameScreen;

import java.util.ArrayList;

import static com.valio.papoicheta.ProjectGame.settings;

public class Shooter extends IEvilEnemy {

boolean rightPosition=false;
    public Shooter(int x, int y) {
        centerX = x * 40;
        centerY = y * 45;
    }

    @Override
    public void update() {
        centerX += speedX;
        centerY += speedY;
        follow();
        speedX = bg.getSpeedX() * 5 + movementSpeed;
        if (centerX <= 800) {
            speedY += 1;
            if (collide) {
                r.set(centerX - 28, centerY - 45, centerX + 28, centerY + 45);
                lowerRect.set(centerX - 20, centerY + 5, centerX + 20, centerY + 46);
                upRect.set(centerX - 30, centerY - 48, centerX + 30, centerY - 40);
                leftRect.set(centerX - 30, centerY - 5, centerX + 1, centerY + 5);
                rightRect.set(centerX - 1, centerY - 5, centerX + 30, centerY + 5);
                checkCollision();
                shoot();
            }
        }
    }
    public void follow() {
            if (minion.getCenterX() >= centerX)
                rightPosition=true;
            else
                rightPosition=false;
    }
    public void jump() {
        if (jumped == false) {
            speedY += JUMPSPEED;
            jumped = true;
        }
    }
    private void checkCollision() {
        for (int i = 0; i < GameScreen.getTerrain().size(); i++) {
            if (GameScreen.getTerrain().get(i) instanceof Terrain) {
                Terrain ter = (Terrain) GameScreen.getTerrain().get(i);
                if (Rect.intersects(ter.r, lowerRect)) {
                    this.centerY = ter.getY() - 40;
                    this.speedY = 0;
                    return;
                }
            }
        }
    }

    public void shoot() {
        EnemyProjectile p;
        if (centerX <= 800) {
            if (isMovingRight()) {
                p = new EnemyProjectile(centerX + 30, centerY , false);
            } else {
                p = new EnemyProjectile(centerX - 30, centerY , true);
            }

            if (enemyTime == 0)
                enemyTime = System.nanoTime();
            if (System.nanoTime() - enemyTime > (50000000 * 100000000)) {
                if (settings.getSoundVolume() > 0)
                    Assets.enemyShoot.play(settings.getSoundVolume());
                projectiles.add(p);
                enemyTime = 0;
            }
        }
    }


    public boolean isMovingRight() {
        if (rightPosition)
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

    public ArrayList<EnemyProjectile> getProjectiles() {
        return this.projectiles;
    }

}