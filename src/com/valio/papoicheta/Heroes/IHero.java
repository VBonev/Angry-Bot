package com.valio.papoicheta.Heroes;

import android.graphics.Rect;

import com.valio.framework.Image;
import com.valio.papoicheta.Background;
import com.valio.papoicheta.LimitedArrayList;
import com.valio.papoicheta.Projectile;
import com.valio.papoicheta.screens.GameScreen;

import java.util.ArrayList;

public abstract class IHero {
    public Rect bordRect = new Rect(0, 0, 0, 0);
    public Rect leftRect = new Rect(0, 0, 0, 0);
    public Rect rightRect = new Rect(0, 0, 0, 0);
    public Rect lowerRect = new Rect(0, 0, 0, 0);
    public Rect r = new Rect(0, 0, 0, 0);
    public Rect upperRect = new Rect(0, 0, 0, 0);
    public static boolean isWounded = false;
    public static boolean leftPos = false;
    public static boolean nextLvl = false;
    public static boolean superShot = false;
    public static boolean isDead = false;
    public static boolean immortal = false;
    public static int bulletLimit = 5;
    public static boolean jumped = false;
    public static boolean readyToFire = true;
    public static int heroLive = 2;
    public static long immortalTime = 0;
    public static int score = 0;
    public static boolean isShooting = false;
    public static int jumps = 0;
    public static boolean drawJump = false;
    public int MOVE_SPEED = 5;
    public int JUMP_SPEED = -17;
    public int centerX = 100;
    public int centerY = 100;
    public float speedX = 0;
    public double speedY = 0;
    public boolean movingLeft = false;
    public boolean movingRight = false;
    public static boolean jumpImg = false;
    public Background bg1 = GameScreen.getBackground1();
    public LimitedArrayList<Projectile> projectiles = new LimitedArrayList<Projectile>();
    public static Image  frontMinion;
    public static Image backMinion;

    public abstract void update();

    public abstract void jump();

    public abstract void moveRight();

    public abstract void moveLeft();

    public abstract void stopRight();

    public abstract void stopLeft();

    public abstract void shoot();

    public abstract boolean isMovingRight();

    public abstract void setMovingRight(boolean movingRight);

    public abstract boolean isMovingLeft();

    public abstract void setMovingLeft(boolean movingLeft);

    public abstract int getMOVE_SPEED();

    public abstract int getCenterX();

    public abstract int getCenterY();

    public abstract float getSpeedX();

    public abstract double getSpeedY();

    public abstract void setCenterX(int centerX);

    public abstract void setCenterY(int centerY);

    public abstract void setSpeedX(int speedX);

    public abstract void setSpeedY(int speedY);

    public abstract boolean isJumped();

    public abstract void setJumped(boolean jumped);

    public abstract boolean isReadyToFire();

    public abstract ArrayList<Projectile> getProjectiles();

    public abstract void enemyCollision(int i);

    public abstract void liveCollision(int i);

    public abstract void coinCollision(int i);

    public abstract void endCheck();

    public abstract void checkCollision();

    public abstract void animate();
    public Image getFrontMinion() {
        return frontMinion;
    }

    public void setFrontMinion(Image frontMinion) {
        this.frontMinion = frontMinion;
    }

    public Image getBackMinion() {
        return backMinion;
    }

    public void setBackMinion(Image backMinion) {
        this.backMinion = backMinion;
    }
}