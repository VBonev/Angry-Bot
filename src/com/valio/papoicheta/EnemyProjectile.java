package com.valio.papoicheta;

import com.valio.papoicheta.Heroes.IHero;
import com.valio.papoicheta.screens.GameScreen;

import android.graphics.Rect;

import static com.valio.papoicheta.ProjectGame.settings;

public class EnemyProjectile {

    private int x, y, speedX;
    private boolean visible;
    private Rect r;
    public boolean goLeft = false;
    private Background bg = GameScreen.getBackground1();
    private int movementSpeed;

    public EnemyProjectile(int startX, int startY, boolean leftPos) {
        x = startX;
        y = startY;
        goLeft = leftPos;
        visible = true;
        r = new Rect(0, 0, 0, 0);
    }

    public void update() {
        if (x > 0 || x < 800) {
            r.set(x - 5, y - 4, x + 5, y + 4);
            x += speedX;
            speedX = bg.getSpeedX() * 5 + movementSpeed;
            if (visible)
                checkCollision();
            follow();
        } else
            visible = false;
    }

    public void follow() {

        if (!goLeft) {
            movementSpeed = 5;
        } else {
            movementSpeed = -5;
        }

    }

    private void checkCollision() {

        for (int i = 0; i < GameScreen.getTerrain().size(); i++) {
            if (GameScreen.getTerrain().get(i) instanceof Terrain) {
                Terrain ter = (Terrain) GameScreen.getTerrain().get(i);
                if (Rect.intersects(r,ter.r)) {
                    visible = false;
                }
            }
        }
        IHero hero = GameScreen.getHero();
        if (Rect.intersects(r, hero.r)) {
            if (hero.heroLive > 0) {
                if (settings.getSoundVolume() > 0)
                    Assets.what.play(settings.getSoundVolume());
                if (settings.getSoundVolume() > 0)
                    Assets.what.play(settings.getSoundVolume());
                visible = false;
                hero.isWounded = true;
                hero.heroLive--;
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
