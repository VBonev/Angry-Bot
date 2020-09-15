package com.valio.papoicheta;

import android.graphics.Rect;

import com.valio.papoicheta.Enemies.Flier;
import com.valio.papoicheta.Enemies.Follower;
import com.valio.papoicheta.Enemies.Jumper;
import com.valio.papoicheta.Enemies.Shooter;
import com.valio.papoicheta.Heroes.IHero;
import com.valio.papoicheta.screens.GameScreen;

import static com.valio.papoicheta.ProjectGame.settings;

public class Projectile {

    private int x, y, speedX;
    private boolean visible;
    private Rect r;
    public boolean goLeft = false;

    public Projectile(int startX, int startY, boolean leftPos) {
        x = startX;
        y = startY;
        goLeft = leftPos;
        if (IHero.superShot)
            speedX = 17;
        else
            speedX += 7;
        visible = true;
        r = new Rect(0, 0, 0, 0);
    }

    public void update() {
        if (goLeft)
            x -= speedX;
        else
            x += speedX;
        r.set(x - 24, y - 10, x + 24, y + 10);
        if (x < 0 || x > 800) {
            visible = false;
        }
        if (visible) {
            checkCollision();
        }

    }

    // here we define the interaction between enemy and bullets
    private void checkCollision() {
        shooterCollision();
        followerCollision();
        terrainCollision();
        flierCollision();
        jumperCollision();
    }

    private void terrainCollision() {
        for (int i = 0; i < GameScreen.getTerrain().size(); i++) {
            Terrain ter = (Terrain) GameScreen.getTerrain().get(i);
            if (Rect.intersects(ter.r, r)) {
                visible = false;
            }
        }
    }

    private void shooterCollision() {
        for (int k = 0; k < GameScreen.getShootEvil().size(); k++) {
            Shooter shooter = GameScreen.getShootEvil().get(k);
            if (shooter.getCenterX() < 800) {
                if (shooter.collide) {
                    if (Rect.intersects(r, shooter.r)) {
                        visible = false;
                        if (shooter.health > 0) {
                            if (IHero.superShot)
                                shooter.health -= 3;
                            else
                                shooter.health--;
                        }
                        if (shooter.health <= 0) {
                            IHero.score += 2000;
                            shooter.jump();
                            shooter.collide = false;
                            if (settings.getSoundVolume() > 0)
                                Assets.alien.play(settings.getSoundVolume());
//                        GameScreen.getShootEvil().remove(shooter);
                        }
                    }
                }
            }
        }
    }


    private void followerCollision() {
        for (int i = 0; i < GameScreen.getFollower().size(); i++) {
            Follower follower = GameScreen.getFollower().get(i);
            if (follower.getCenterX() < 800) {
                if (follower.collide) {
                    if (Rect.intersects(r, follower.r)) {
                        visible = false;
                        if (follower.health > 0) {
                            if (IHero.superShot)
                                follower.health -= 3;
                            else
                                follower.health--;
                        }
                        if (follower.health <= 0) {
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
                            follower.jump();
                            follower.collide = false;
                        }
                    }
                }
            }
        }
    }

    private void flierCollision() {
        for (int i = 0; i < GameScreen.getFliers().size(); i++) {
            Flier flier = GameScreen.getFliers().get(i);
            if (flier.getCenterX() < 800) {
                if (flier.collide) {
                    if (Rect.intersects(r, flier.r)) {
                        if (flier.health > 0)
                            flier.health--;
                        if (flier.health <= 0) {
                            visible = false;
                            IHero.score += 2000;
                            if (settings.getSoundVolume() > 0)
                                Assets.bird.play(settings.getSoundVolume());
                            flier.jump();
                            flier.collide = false;
                        }
                    }
                }
            }
        }
    }


    private void jumperCollision() {
        for (int i = 0; i < GameScreen.getJumpers().size(); i++) {
            Jumper jumper = GameScreen.getJumpers().get(i);
            if (jumper.getCenterX() < 800) {
                if (Rect.intersects(r, jumper.r)) {
                    if (jumper.collide) {
                        if (jumper.health > 0)
                            jumper.health--;
                        if (jumper.health <= 0) {
                            visible = false;
                            IHero.score += 2000;
                            jumper.jump();
                            if (settings.getSoundVolume() > 0)
                                Assets.jumper.play(settings.getSoundVolume());
                            jumper.collide = false;
                        }
                    }
                }
            }
        }
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getSpeedX() {
        return speedX;
    }

    public boolean isVisible() {
        return visible;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public void setSpeedX(int speedX) {
        this.speedX = speedX;
    }


}
