package com.valio.papoicheta.Enemies;

import android.graphics.Rect;

import com.valio.framework.Image;
import com.valio.papoicheta.Background;
import com.valio.papoicheta.EnemyProjectile;
import com.valio.papoicheta.Heroes.IHero;
import com.valio.papoicheta.screens.GameScreen;

import java.util.ArrayList;

public abstract class IEvilEnemy {

    public int centerX, speedX, centerY;
    public double speedY=0;
    public Background bg = GameScreen.getBackground1();
    public IHero minion = GameScreen.getHero();
    public static Image backEnemy, frontEnemy;
    public int health = 2;
    public static Rect leftRect = new Rect(0, 0, 0, 0);
    public static Rect rightRect = new Rect(0, 0, 0, 0);
    public Rect upRect = new Rect(0, 0, 0, 0);
    public Rect r = new Rect(0, 0, 0, 0);
    public static Rect lowerRect = new Rect(0, 0, 0, 0);
    public int movementSpeed = 0;
    public static long enemyTime = 0;
    public ArrayList<EnemyProjectile> projectiles = new ArrayList<EnemyProjectile>();
    public boolean jumped = false;
    public boolean collide = true;
    public final int JUMPSPEED = -15;

    public abstract void update();

    public abstract int getSpeedX();

    public abstract int getCenterX();

    public abstract int getCenterY();

    public abstract Background getBg();

    public abstract double getSpeedY();

    public abstract void setSpeedY(int speedY);

    public abstract void setSpeedX(int speedX);

    public abstract void setCenterX(int centerX);

    public abstract void setCenterY(int centerY);

    public abstract void setBg(Background bg);


}