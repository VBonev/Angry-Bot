package com.valio.papoicheta;


import android.graphics.Rect;

import com.valio.framework.Image;
import com.valio.papoicheta.screens.GameScreen;

import static com.valio.papoicheta.ProjectGame.settings;

public class Terrain {

    private int x, y, speedX, type;
    public Image image;
    private Background bg = GameScreen.getBackground1();
    public Rect r;

    public Terrain(int x, int y, int type) {
        this.x = x * 40;
        this.y = y * 40;
        this.type = type;
        if (type == 8) {
            if (settings.getCurrentChapter() == 1) {
                this.image = Assets.lvlOne;
            } else if (settings.getCurrentChapter() == 2) {
                if (settings.getCurrentLevel() == 8 || settings.getCurrentLevel() == 10)
                    this.image = Assets.lvlTen;
                else
                    this.image = Assets.lvlOne;
            } else if (settings.getCurrentChapter() == 3) {
                if (settings.getCurrentLevel() == 14)
                    this.image = Assets.lvlTen;
                else
                    this.image = Assets.lvlThree;
            } else if (settings.getCurrentChapter() == 4) {
                this.image = Assets.lvlTwo;
            } else if (settings.getCurrentChapter() == 5) {
                this.image = Assets.lvlFive;
            }

        } else if (type == 7) {
            if (settings.getCurrentChapter() == 1) {
                this.image = Assets.oneTop;
            } else if (settings.getCurrentChapter() == 2) {
                if (settings.getCurrentLevel() == 8 || settings.getCurrentLevel() == 10)
                    this.image = Assets.topTen;
                else
                    this.image = Assets.oneTop;
            } else if (settings.getCurrentChapter() == 3) {
                if (settings.getCurrentLevel() == 14)
                    this.image = Assets.topTen;
                else
                    this.image = Assets.threeTop;
            } else if (settings.getCurrentChapter() == 4) {
                this.image = Assets.twoTop;
            } else if (settings.getCurrentChapter() == 5) {
                this.image = Assets.fiveTop;
            }
        }
        r = new Rect();
    }

    public void update() {
        x += speedX;
        speedX = bg.getSpeedX() * 5;
//        y += speedY;
//        speedY = bg.getSpeedY();
        if (type == 7 || type == 8) {
            r.set(x, y, x + 40, y + 40);
        }
    }


    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public Image getImage() {
        return image;
    }

    public void setImage(Image image) {
        this.image = image;
    }
}
