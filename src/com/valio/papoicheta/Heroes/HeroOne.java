package com.valio.papoicheta.Heroes;

import android.graphics.Rect;

import com.valio.papoicheta.Ammo;
import com.valio.papoicheta.Assets;
import com.valio.papoicheta.Coin;
import com.valio.papoicheta.End;
import com.valio.papoicheta.Enemies.Flier;
import com.valio.papoicheta.Enemies.Follower;
import com.valio.papoicheta.Enemies.Jumper;
import com.valio.papoicheta.Enemies.Shooter;
import com.valio.papoicheta.Live;
import com.valio.papoicheta.Projectile;
import com.valio.papoicheta.Terrain;
import com.valio.papoicheta.screens.GameScreen;
import com.valio.papoicheta.screens.LoadingScreen;

import java.util.ArrayList;

import static com.valio.papoicheta.ProjectGame.settings;


public class HeroOne extends IHero {

    @Override
    public void update() {
        if (centerY >= 440) {
            if (settings.getSoundVolume() > 0)
                Assets.fall.play(settings.getSoundVolume());
            if (settings.isVibrate())
                GameScreen.getVibrator().vibrate(150);
            heroLive -= 1;
            for (int i = 0; i < GameScreen.getTerrain().size(); i++) {
                if (((Terrain) GameScreen.getTerrain().get(i)).getType() == 7 &&
                        (((Terrain) GameScreen.getTerrain().get(i)).getX() > 0 && ((Terrain) GameScreen.getTerrain().get(i)).getX() < 200)) {
                    setCenterX(((Terrain) GameScreen.getTerrain().get(i)).getX());
                    break;
                }
            }
            setCenterY(100);
        }
        if (heroLive <= 0)
            isDead = true;
        if (speedX < 0) {
            centerX += speedX;
        }
        if (centerX <= 400 && speedX > 0) {
            centerX += speedX;
        }
        if (speedX == 0 || speedX < 0) {
            bg1.setSpeedX(0);
        }
        centerY += speedY;
        speedY += 1;

        if (speedX > 0 && centerX > 400) {
            bg1.setSpeedX(-MOVE_SPEED / 5);
        }
        if (centerX + speedX <= 25) {
            centerX = 26;
        }
        r.set(centerX - 25, centerY - 35, centerX + 25, centerY + 33);
        bordRect.set(centerX + 19, centerY - 35, centerX + 21, centerY + 35);
        lowerRect.set(centerX - 20, centerY + 5, centerX + 20, centerY + 37);
        leftRect.set(centerX - 27, centerY - 20, centerX - 20, centerY + 5);
        rightRect.set(centerX + 20, centerY - 20, centerX + 27, centerY + 5);
        upperRect.set(centerX - 10, centerY - 41, centerX + 10, centerY - 20);
        checkCollision();

    }

    // here we set different jump behaviours for different hero skins
    @Override
    public void jump() {
        if (!jumped) {
            if (jumps != 1)
                speedY += JUMP_SPEED;
            else
                speedY += (JUMP_SPEED + 7);
            jumped = true;
            jumps += 1;
        }
        jumpImg = true;
    }

    @Override
    public void checkCollision() {
        for (int i = 0; i < GameScreen.getTerrain().size(); i++) {
            if ((((Terrain) GameScreen.getTerrain().get(i)).getX() < 450)) {
                terrainCollision(i);
            }
        }
        for (int i = 0; i < GameScreen.getFollower().size(); i++) {
            if (GameScreen.getFollower().get(i).getCenterX() < 450)
                enemyCollision(i);

        }
        for (int i = 0; i < GameScreen.getJumpers().size(); i++) {
            if (GameScreen.getJumpers().get(i).getCenterX() < 450)
                jumperCollision(i);

        }
        for (int i = 0; i < GameScreen.getFliers().size(); i++) {
            if (GameScreen.getFliers().get(i).getCenterX() < 450)
                flyCollision(i);

        }
        for (int i = 0; i < GameScreen.getShootEvil().size(); i++) {
            if (GameScreen.getShootEvil().get(i).getCenterX() < 450)
                shootEnCollision(i);

        }
        for (int i = 0; i < GameScreen.getCoins().size(); i++) {
            if (GameScreen.getCoins().get(i).getCenterX() < 450)
                coinCollision(i);

        }
        for (int i = 0; i < GameScreen.getLives().size(); i++) {
            if (GameScreen.getLives().get(i).getCenterX() < 450)
                liveCollision(i);

        }
        for (int i = 0; i < GameScreen.getAmmos().size(); i++) {
            if (GameScreen.getAmmos().get(i).getCenterX() < 450)
                ammoCollision(i);

        }
        endCheck();
    }

    @Override
    public void animate() {
        LoadingScreen.animBul();
    }

    @Override
    public void endCheck() {
        End end = GameScreen.getEnd();
        if (end.getCenterX() <= 450 && end.getCenterX() >= 400) {
            if (Rect.intersects(lowerRect, end.r)
                    || Rect.intersects(upperRect, end.r)) {
                nextLvl = true;
                if (settings.getSoundVolume() > 0)
                    Assets.endLvl.play(settings.getSoundVolume());
            }
        }
    }

    @Override
    public void coinCollision(int i) {
        Coin coin = GameScreen.getCoins().get(i);
        if (coin.getCenterX() <= 450) {
            if (Rect.intersects(r, coin.r)) {
                score += 500;
                if (settings.getSoundVolume() > 0)
                    Assets.coin.play(settings.getSoundVolume());
                GameScreen.getCoins().remove(coin);
            }
        }
    }


    @Override
    public void liveCollision(int i) {
        Live live = GameScreen.getLives().get(i);
        if (live.getCenterX() <= 450) {
            if (Rect.intersects(lowerRect, live.r)
                    || Rect.intersects(upperRect, live.r)) {
                if (settings.getSoundVolume() > 0)
                    Assets.heartBeat.play(settings.getSoundVolume());
                heroLive++;
                GameScreen.getLives().remove(live);
            }
        }
    }

    public void ammoCollision(int i) {
        Ammo ammo = GameScreen.getAmmos().get(i);
        if (ammo.getCenterX() <= 450) {
            if (Rect.intersects(lowerRect, ammo.r)
                    || Rect.intersects(upperRect, ammo.r)) {
                if (settings.getSoundVolume() > 0)
                    Assets.ammo.play(settings.getSoundVolume());
                bulletLimit += 5;
                GameScreen.getAmmos().remove(ammo);
            }
        }
    }

    public void terrainCollision(int i) {
        Terrain t = (Terrain) GameScreen.getTerrain().get(i);
        if (t.getX() <= 450) {
            if ((Rect.intersects(lowerRect, t.r) && (t.getType() == 7 || t.getType() == 2)
            )
                    ) {
                int lowBorder;
                setJumped(false);
                if (settings.isTouch())
                    lowBorder = 26;
                else {
                    if (settings.getCurrentHero() == 1)
                        lowBorder = 21;
                    else if (settings.getCurrentHero() == 5)
                        lowBorder = 16;
                    else
                        lowBorder = 26;
                }
                setCenterY(t.getY() - lowBorder);
                setSpeedY(0);
                jumps = 0;
                jumpImg = false;

            }
            if (Rect.intersects(upperRect, t.r) && t.getType() == 7) {
                setCenterY(t.getY() + 80);
                setSpeedY(3);
            }

            if (Rect.intersects(leftRect, t.r)) {
                if (isMovingLeft())
                    setCenterX(t.getX() + 65);
            }

            if (Rect.intersects(rightRect, t.r) && t.getType() == 8) {
                if (isMovingRight()) {
                    setCenterX(t.getX() - 20);
                    bg1.setSpeedX(0);
                } else {
                    if (isMovingRight())
                        speedX = MOVE_SPEED;
                    else if (isMovingLeft())
                        speedX = -MOVE_SPEED;
                }
            }
        }
    }

    @Override
    public void enemyCollision(int i) {
        Follower enemy = GameScreen.getFollower().get(i);
        if (enemy.getCenterX() <= 450) {
            if (Rect.intersects(lowerRect, enemy.upRect)) {
                setSpeedY(-13);
                IHero.score += 1000;
                if (settings.getCurrentChapter() == 1) {
                    if (settings.getSoundVolume() > 0)
                        Assets.bear.play(settings.getSoundVolume());
                } else if (settings.getCurrentChapter() == 2) {
                    if (settings.getSoundVolume() > 0)
                        Assets.zombie.play(settings.getSoundVolume());
                } else if (settings.getCurrentChapter() == 3) {
                    if (settings.getSoundVolume() > 0)
                        Assets.evil.play(settings.getSoundVolume());
                } else if (settings.getCurrentChapter() == 4) {
                    if (settings.getSoundVolume() > 0)
                        Assets.ant.play(settings.getSoundVolume());
                } else if (settings.getCurrentChapter() == 5) {
                    if (settings.getSoundVolume() > 0)
                        Assets.ghost.play(settings.getSoundVolume());
                }
                if (settings.isVibrate())
                    GameScreen.getVibrator().vibrate(150);
                GameScreen.getFollower().remove(enemy);
            } else if (Rect.intersects(r, enemy.r)) {
                if (enemy.collide) {
                    if (settings.isVibrate())
                        GameScreen.getVibrator().vibrate(150);
                    if (settings.getSoundVolume() > 0)
                        Assets.what.play(settings.getSoundVolume());
                    isWounded = true;
                    if (!immortal)
                        heroLive -= settings.getCurrentChapter();
                    GameScreen.getFollower().remove(enemy);
                }
            }
        }
    }

    public void flyCollision(int i) {
        Flier flier = GameScreen.getFliers().get(i);
        if (flier.getCenterX() <= 450) {
            if (Rect.intersects(lowerRect, flier.upRect)) {
                setSpeedY(-13);
                if (settings.isVibrate())
                    GameScreen.getVibrator().vibrate(150);
                if (settings.getSoundVolume() > 0)
                    Assets.bird.play(settings.getSoundVolume());
                IHero.score += 2000;
                GameScreen.getFliers().remove(flier);
            }
            if (Rect.intersects(r, flier.r)) {
                if (flier.collide) {
                    if (settings.isVibrate())
                        GameScreen.getVibrator().vibrate(150);
                    if (settings.getSoundVolume() > 0)
                        Assets.what.play(settings.getSoundVolume());
                    isWounded = true;
                    if (!immortal)
                        heroLive -= settings.getCurrentChapter();
                    GameScreen.getFliers().remove(flier);
                }
            }
        }
    }

    public void jumperCollision(int i) {
        Jumper jumper = GameScreen.getJumpers().get(i);
        if (jumper.getCenterX() <= 450) {
            if (Rect.intersects(lowerRect, jumper.upRect)) {
                setSpeedY(-13);
                if (settings.isVibrate())
                    GameScreen.getVibrator().vibrate(150);
                IHero.score += 2000;
                if (settings.getSoundVolume() > 0)
                    Assets.jumper.play(settings.getSoundVolume());
                GameScreen.getJumpers().remove(jumper);
            }
            if (Rect.intersects(r, jumper.r)) {
                if (jumper.collide) {
                    if (settings.getSoundVolume() > 0)
                        Assets.what.play(settings.getSoundVolume());
                    if (settings.getSoundVolume() > 0)
                        Assets.what.play(settings.getSoundVolume());
                    isWounded = true;
                    if (!immortal)
                        heroLive -= settings.getCurrentChapter();
                    GameScreen.getJumpers().remove(jumper);
                }
            }
        }
    }

    public void shootEnCollision(int i) {
        Shooter enemy = GameScreen.getShootEvil().get(i);
        if (enemy.getCenterX() <= 450) {
            if (Rect.intersects(lowerRect, enemy.upRect)) {
                setSpeedY(-13);
                if (settings.isVibrate())
                    GameScreen.getVibrator().vibrate(150);
                if (settings.getSoundVolume() > 0)
                    Assets.alien.play(settings.getSoundVolume());
                GameScreen.getShootEvil().remove(enemy);
            } else if (Rect.intersects(r, enemy.r)) {
                if (enemy.collide) {
                    if (settings.isVibrate())
                        GameScreen.getVibrator().vibrate(150);
                    if (settings.getSoundVolume() > 0)
                        Assets.what.play(settings.getSoundVolume());
                    isWounded = true;
                    if (!immortal)
                        heroLive--;
                    GameScreen.getShootEvil().remove(enemy);
                }
            }
        }
    }


    @Override
    public void moveRight() {
        setMovingRight(true);
        setMovingLeft(false);
        speedX = MOVE_SPEED;
        leftPos = false;
    }

    @Override
    public void moveLeft() {
        setMovingRight(false);
        setMovingLeft(true);

        speedX = -MOVE_SPEED;
        leftPos = true;
    }

    @Override
    public void stopRight() {
        setMovingRight(false);
        stop();
    }

    @Override
    public void stopLeft() {
        setMovingLeft(false);
        stop();
        if (!isMovingRight())
            leftPos = true;

    }

    private void stop() {
        if (isMovingRight() == false && isMovingLeft() == false) {
            speedX = 0;
        }
        if (isMovingRight() == true && isMovingLeft() == true) {
            speedX = 0;
        }
        if (isMovingRight() == false && isMovingLeft() == true) {
            moveLeft();
        }
        if (isMovingRight() == true && isMovingLeft() == false) {
            moveRight();
        }
    }

    @Override
    public void shoot() {
        if (readyToFire) {
            projectiles.setLimit(bulletLimit);
            Projectile p;
            if (leftPos || isMovingLeft()) {
                p = new Projectile(centerX - 30, centerY - 20, true);
            } else {
                p = new Projectile(centerX + 30, centerY - 20, false);
            }
            projectiles.add(p);
            bulletLimit--;
            if (bulletLimit < 0)
                bulletLimit = 0;
        }

    }

    @Override
    public boolean isMovingRight() {
        return movingRight;
    }

    @Override
    public void setMovingRight(boolean movingRight) {
        this.movingRight = movingRight;
    }

    @Override
    public boolean isMovingLeft() {
        return movingLeft;
    }

    @Override
    public void setMovingLeft(boolean movingLeft) {
        this.movingLeft = movingLeft;
    }

    @Override
    public int getMOVE_SPEED() {
        return MOVE_SPEED;
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
    public float getSpeedX() {
        return speedX;
    }

    @Override
    public double getSpeedY() {
        return speedY;
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
    public void setSpeedX(int speedX) {
        this.speedX = speedX;
    }

    @Override
    public void setSpeedY(int speedY) {
        this.speedY = speedY;
    }

    @Override
    public boolean isJumped() {
        return jumped;
    }

    @Override
    public void setJumped(boolean jumped) {
        this.jumped = jumped;
    }

    @Override
    public boolean isReadyToFire() {
        return this.readyToFire;
    }

    @Override
    public ArrayList<Projectile> getProjectiles() {
        return this.projectiles;
    }
}
