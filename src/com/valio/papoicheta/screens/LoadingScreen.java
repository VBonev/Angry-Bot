package com.valio.papoicheta.screens;

import android.util.Log;

import com.valio.framework.Game;
import com.valio.framework.Graphics;
import com.valio.framework.Image;
import com.valio.framework.Screen;
import com.valio.framework.Graphics.ImageFormat;
import com.valio.framework.implementation.Animation;
import com.valio.papoicheta.Assets;

import java.util.Random;

public class LoadingScreen extends Screen {

    public static Image jumpFront, heroFront;
    public static Animation ant, robo, bear, roboJump, roboStay, jumper, ghost, alien, ugly, themes, resetButton, bird, bullets, bullet, evil, heart, coin;
    public static int[] adTime=new int[6];
    public LoadingScreen(Game game) {
        super(game);
        Graphics g = game.getGraphics();
        g.drawImage(Assets.splash, 0, 0);
        for(int i=0;i<adTime.length;i++){
            adTime[i]=randInt((i+1==1?1:i*4),((i+1)*4));
            Log.e("kopele", adTime[i] + " " + i + " nomer");
        }
        Log.e("kopele", "-------------------------");
    }
    public static int randInt(int min, int max) {
        min=min==1?2:min+1;
        max=max==24?25:max  ;
        Random rand = new Random();

        int randomNum = rand.nextInt((max - min) + 1) + min;

        return randomNum;
    }
    @Override
    public void update(float deltaTime) {
        Graphics g = game.getGraphics();
        g.drawImage(Assets.splash, 0, 0);

        Assets.reset1 = g.newImage("menu/lvl/reset1.png", ImageFormat.RGB565);
        Assets.reset2 = g.newImage("menu/lvl/reset2.png", ImageFormat.RGB565);

        Assets.lvl1 = g.newImage("Chapters/1.png", ImageFormat.RGB565);
        Assets.lvl2 = g.newImage("Chapters/2.png", ImageFormat.RGB565);
        Assets.lvl3 = g.newImage("Chapters/3.png", ImageFormat.RGB565);
        Assets.lvl4 = g.newImage("Chapters/4.png", ImageFormat.RGB565);
        Assets.lvl5 = g.newImage("Chapters/5.png", ImageFormat.RGB565);

        Assets.ugly0 = g.newImage("Evil/uglyy/0.png", ImageFormat.RGB565);
        Assets.ugly1 = g.newImage("Evil/uglyy/1.png", ImageFormat.RGB565);
        Assets.ugly2 = g.newImage("Evil/uglyy/2.png", ImageFormat.RGB565);
        Assets.ugly3 = g.newImage("Evil/uglyy/3.png", ImageFormat.RGB565);
        Assets.ugly4 = g.newImage("Evil/uglyy/4.png", ImageFormat.RGB565);
        Assets.ugly5 = g.newImage("Evil/uglyy/5.png", ImageFormat.RGB565);
        Assets.ugly6 = g.newImage("Evil/uglyy/6.png", ImageFormat.RGB565);
        Assets.ugly7 = g.newImage("Evil/uglyy/7.png", ImageFormat.RGB565);

        Assets.bear0 = g.newImage("Evil/Bear/0.png", ImageFormat.RGB565);
        Assets.bear1 = g.newImage("Evil/Bear/1.png", ImageFormat.RGB565);
        Assets.bear2 = g.newImage("Evil/Bear/2.png", ImageFormat.RGB565);
        Assets.bear3 = g.newImage("Evil/Bear/3.png", ImageFormat.RGB565);
        Assets.bear4 = g.newImage("Evil/Bear/4.png", ImageFormat.RGB565);
        Assets.bear5 = g.newImage("Evil/Bear/5.png", ImageFormat.RGB565);
        Assets.bear6 = g.newImage("Evil/Bear/6.png", ImageFormat.RGB565);
        Assets.bear7 = g.newImage("Evil/Bear/7.png", ImageFormat.RGB565);
        Assets.bear8 = g.newImage("Evil/Bear/8.png", ImageFormat.RGB565);
        Assets.bear9 = g.newImage("Evil/Bear/9.png", ImageFormat.RGB565);
        Assets.bear10 = g.newImage("Evil/Bear/10.png", ImageFormat.RGB565);
        Assets.bear11 = g.newImage("Evil/Bear/11.png", ImageFormat.RGB565);
        Assets.bear12 = g.newImage("Evil/Bear/12.png", ImageFormat.RGB565);
        Assets.bear13 = g.newImage("Evil/Bear/13.png", ImageFormat.RGB565);
        Assets.bear14 = g.newImage("Evil/Bear/14.png", ImageFormat.RGB565);
        Assets.bear15 = g.newImage("Evil/Bear/15.png", ImageFormat.RGB565);
        Assets.bear16 = g.newImage("Evil/Bear/16.png", ImageFormat.RGB565);

        Assets.alien0 = g.newImage("Evil/alien/0.png", ImageFormat.RGB565);
        Assets.alien1 = g.newImage("Evil/alien/1.png", ImageFormat.RGB565);
        Assets.alien2 = g.newImage("Evil/alien/2.png", ImageFormat.RGB565);
        Assets.alien3 = g.newImage("Evil/alien/3.png", ImageFormat.RGB565);
        Assets.alien4 = g.newImage("Evil/alien/4.png", ImageFormat.RGB565);
        Assets.alien5 = g.newImage("Evil/alien/5.png", ImageFormat.RGB565);


        Assets.jump1 = g.newImage("Evil/jumper/1.png", ImageFormat.RGB565);
        Assets.jump2 = g.newImage("Evil/jumper/2.png", ImageFormat.RGB565);
        Assets.jump3 = g.newImage("Evil/jumper/3.png", ImageFormat.RGB565);

        Assets.white1 = g.newImage("Evil/white/1.png", ImageFormat.RGB565);
        Assets.white2 = g.newImage("Evil/white/2.png", ImageFormat.RGB565);
        Assets.white3 = g.newImage("Evil/white/3.png", ImageFormat.RGB565);

        Assets.ghost1 = g.newImage("Evil/ghost/1.png", ImageFormat.RGB565);
        Assets.ghost2 = g.newImage("Evil/ghost/2.png", ImageFormat.RGB565);
        Assets.ghost3 = g.newImage("Evil/ghost/3.png", ImageFormat.RGB565);
        Assets.ghost4 = g.newImage("Evil/ghost/4.png", ImageFormat.RGB565);
        Assets.ghost5 = g.newImage("Evil/ghost/5.png", ImageFormat.RGB565);
        Assets.ghost6 = g.newImage("Evil/ghost/6.png", ImageFormat.RGB565);
        Assets.ghost7 = g.newImage("Evil/ghost/7.png", ImageFormat.RGB565);
        Assets.ghost8 = g.newImage("Evil/ghost/8.png", ImageFormat.RGB565);
        Assets.ghost9 = g.newImage("Evil/ghost/9.png", ImageFormat.RGB565);
        Assets.ghost10 = g.newImage("Evil/ghost/10.png", ImageFormat.RGB565);

        Assets.ant1 = g.newImage("Evil/ant/1.png", ImageFormat.RGB565);
        Assets.ant2 = g.newImage("Evil/ant/2.png", ImageFormat.RGB565);
        Assets.ant3 = g.newImage("Evil/ant/3.png", ImageFormat.RGB565);
        Assets.ant4 = g.newImage("Evil/ant/4.png", ImageFormat.RGB565);
        Assets.ant5 = g.newImage("Evil/ant/5.png", ImageFormat.RGB565);
        Assets.ant6 = g.newImage("Evil/ant/6.png", ImageFormat.RGB565);
        Assets.ant7 = g.newImage("Evil/ant/7.png", ImageFormat.RGB565);
        Assets.ant8 = g.newImage("Evil/ant/8.png", ImageFormat.RGB565);
        Assets.ant9 = g.newImage("Evil/ant/9.png", ImageFormat.RGB565);

        Assets.robo1 = g.newImage("hero/robot/robo1.png", ImageFormat.RGB565);
        Assets.robo2 = g.newImage("hero/robot/robo2.png", ImageFormat.RGB565);
        Assets.robo3 = g.newImage("hero/robot/robo3.png", ImageFormat.RGB565);
        Assets.robo4 = g.newImage("hero/robot/robo4.png", ImageFormat.RGB565);
        Assets.robo5 = g.newImage("hero/robot/robo5.png", ImageFormat.RGB565);
        Assets.robo6 = g.newImage("hero/robot/robo6.png", ImageFormat.RGB565);
        Assets.robo7 = g.newImage("hero/robot/robo7.png", ImageFormat.RGB565);
        Assets.robo8 = g.newImage("hero/robot/robo8.png", ImageFormat.RGB565);
        Assets.robo9 = g.newImage("hero/robot/robo9.png", ImageFormat.RGB565);
        Assets.robo10 = g.newImage("hero/robot/robo10.png", ImageFormat.RGB565);
        Assets.robo11 = g.newImage("hero/robot/robo11.png", ImageFormat.RGB565);
        Assets.robo12 = g.newImage("hero/robot/robo12.png", ImageFormat.RGB565);
        Assets.robo13 = g.newImage("hero/robot/robo13.png", ImageFormat.RGB565);
        Assets.robo14 = g.newImage("hero/robot/robo14.png", ImageFormat.RGB565);
        Assets.robo15 = g.newImage("hero/robot/robo15.png", ImageFormat.RGB565);
        Assets.robo16 = g.newImage("hero/robot/robo16.png", ImageFormat.RGB565);
        Assets.robo17 = g.newImage("hero/robot/robo17.png", ImageFormat.RGB565);
        Assets.robo18 = g.newImage("hero/robot/robo18.png", ImageFormat.RGB565);
        Assets.robo19 = g.newImage("hero/robot/robo19.png", ImageFormat.RGB565);
        Assets.robo20 = g.newImage("hero/robot/robo20.png", ImageFormat.RGB565);
        Assets.robo21 = g.newImage("hero/robot/robo21.png", ImageFormat.RGB565);
        Assets.robo22 = g.newImage("hero/robot/robo22.png", ImageFormat.RGB565);
        Assets.robo23 = g.newImage("hero/robot/robo23.png", ImageFormat.RGB565);
        Assets.robo24 = g.newImage("hero/robot/robo24.png", ImageFormat.RGB565);
        Assets.robo25 = g.newImage("hero/robot/robo25.png", ImageFormat.RGB565);
        Assets.robo26 = g.newImage("hero/robot/robo26.png", ImageFormat.RGB565);

        Assets.bullet1 = g.newImage("bullets/1.png", ImageFormat.ARGB4444);
        Assets.bullet2 = g.newImage("bullets/2.png", ImageFormat.ARGB4444);
        Assets.bullet3 = g.newImage("bullets/3.png", ImageFormat.ARGB4444);
        Assets.alienBul = g.newImage("bullets/enemy.png", ImageFormat.ARGB4444);

        Assets.pow = g.newImage("buttons/pow.png", ImageFormat.RGB565);
        Assets.jumpButton = g.newImage("buttons/JumpButton.png", ImageFormat.RGB565);
        Assets.fireButton = g.newImage("buttons/FireButton.png", ImageFormat.RGB565);
        Assets.leftButton = g.newImage("buttons/leftButton.png", ImageFormat.RGB565);
        Assets.rightButton = g.newImage("buttons/rightButton.png", ImageFormat.RGB565);
        Assets.b1_press = g.newImage("buttons/JumpButton_pressed.png", ImageFormat.RGB565);
        Assets.b2_press = g.newImage("buttons/FireButton_pressed.png", ImageFormat.RGB565);
        Assets.b3_press = g.newImage("buttons/leftButton_pressed.png", ImageFormat.RGB565);
        Assets.b4_press = g.newImage("buttons/rightButton_pressed.png", ImageFormat.RGB565);
        Assets.fireButton_empty_pressed = g.newImage("buttons/FireButton_empty_pressed.png", ImageFormat.RGB565);
        Assets.fireButton_empty = g.newImage("buttons/FireButton_empty.png", ImageFormat.RGB565);
        Assets.cover = g.newImage("menu/Settings/cover.png", ImageFormat.RGB565);

        Assets.lvlFive = g.newImage("bg/textFive.png", ImageFormat.RGB565);
        Assets.lvlOne = g.newImage("bg/lvlOne.png", ImageFormat.RGB565);
        Assets.oneTop = g.newImage("bg/topOne.png", ImageFormat.RGB565);
        Assets.lvlTwo = g.newImage("bg/lvlTwo.png", ImageFormat.RGB565);
        Assets.twoTop = g.newImage("bg/topTwo.png", ImageFormat.RGB565);
        Assets.lvlThree = g.newImage("bg/lvlThree.png", ImageFormat.RGB565);
        Assets.threeTop = g.newImage("bg/topThree.png", ImageFormat.RGB565);
        Assets.lvlFour = g.newImage("bg/lvlOne.png", ImageFormat.RGB565);
        Assets.fourTop = g.newImage("bg/topOne.png", ImageFormat.RGB565);
        Assets.fiveTop = g.newImage("bg/topFive.png", ImageFormat.RGB565);
        Assets.lvlTen = g.newImage("bg/lvlTen.png", ImageFormat.RGB565);
        Assets.topTen = g.newImage("bg/topTen.png", ImageFormat.RGB565);

        Assets.fly1 = g.newImage("Evil/flier/front1.png", ImageFormat.ARGB4444);
        Assets.fly2 = g.newImage("Evil/flier/front2.png", ImageFormat.ARGB4444);
        Assets.fly3 = g.newImage("Evil/flier/front3.png", ImageFormat.ARGB4444);
        Assets.fly4 = g.newImage("Evil/flier/front4.png", ImageFormat.ARGB4444);
        Assets.fly5 = g.newImage("Evil/flier/front5.png", ImageFormat.ARGB4444);

        Assets.end = g.newImage("end.png", ImageFormat.RGB565);
        Assets.end2 = g.newImage("end2.png", ImageFormat.RGB565);
        Assets.darkEnd = g.newImage("darkEnd.png", ImageFormat.RGB565);
        Assets.c1 = g.newImage("coin/coin1.png", ImageFormat.ARGB4444);
        Assets.c2 = g.newImage("coin/coin2.png", ImageFormat.ARGB4444);
        Assets.c3 = g.newImage("coin/coin3.png", ImageFormat.ARGB4444);
        Assets.c4 = g.newImage("coin/coin4.png", ImageFormat.ARGB4444);
        Assets.c5 = g.newImage("coin/coin5.png", ImageFormat.ARGB4444);
        Assets.c6 = g.newImage("coin/coin6.png", ImageFormat.ARGB4444);
        Assets.c7 = g.newImage("coin/coin7.png", ImageFormat.ARGB4444);

        Assets.heart = g.newImage("heart.png", ImageFormat.ARGB4444);
        Assets.heart2 = g.newImage("heart2.png", ImageFormat.ARGB4444);
        Assets.point = g.newImage("points.png", ImageFormat.ARGB4444);
        Assets.bullet = g.newImage("bullet.png", ImageFormat.ARGB4444);
        Assets.bul1 = g.newImage("bul1.png", ImageFormat.ARGB4444);
        Assets.bul2 = g.newImage("bul2.png", ImageFormat.ARGB4444);
        Assets.menu = g.newImage("menu/menu.png", ImageFormat.RGB565);
        Assets.readyScreen = g.newImage("menu/Ready.png", ImageFormat.RGB565);
        Assets.lvl = g.newImage("menu/lvl/level.png", ImageFormat.RGB565);
        Assets.endScreen = g.newImage("menu/end.png", ImageFormat.RGB565);
        Assets.dead = g.newImage("menu/dead.png", ImageFormat.RGB565);
        Assets.pause = g.newImage("menu/pause.png", ImageFormat.RGB565);
        Assets.settingsScreen = g.newImage("menu/Settings/SettingsScreen.png", ImageFormat.RGB565);
        Assets.sound_circle = g.newImage("menu/Settings/circle.png", ImageFormat.RGB565);
        Assets.dot = g.newImage("menu/Settings/dot.png", ImageFormat.RGB565);
        Assets.exit = g.newImage("menu/exit-confirm.png", ImageFormat.RGB565);
        Assets.themes1 = g.newImage("menu/Settings/themes1.png", ImageFormat.RGB565);
        Assets.themes2 = g.newImage("menu/Settings/themes2.png", ImageFormat.RGB565);

        Assets.story1 = g.newImage("Levels/1.jpg", ImageFormat.RGB565);
        Assets.story2 = g.newImage("Levels/2.jpg", ImageFormat.RGB565);
        Assets.story3 = g.newImage("Levels/3.jpg", ImageFormat.RGB565);
        Assets.story4 = g.newImage("Levels/4.jpg", ImageFormat.RGB565);
        Assets.story5 = g.newImage("Levels/5.jpg", ImageFormat.RGB565);

        Assets.once1 = g.newImage("bg/Chapter1/1.jpg", ImageFormat.RGB565);
        Assets.once2 = g.newImage("bg/Chapter1/2.jpg", ImageFormat.RGB565);
        Assets.once3 = g.newImage("bg/Chapter1/3.jpg", ImageFormat.RGB565);
        Assets.once4 = g.newImage("bg/Chapter1/4.jpg", ImageFormat.RGB565);
        Assets.once5 = g.newImage("bg/Chapter1/5.jpg", ImageFormat.RGB565);

        Assets.world1 = g.newImage("bg/Chapter2/1.jpg", ImageFormat.RGB565);
        Assets.world2 = g.newImage("bg/Chapter2/2.jpg", ImageFormat.RGB565);
        Assets.world3 = g.newImage("bg/Chapter2/3.jpg", ImageFormat.RGB565);
        Assets.world4 = g.newImage("bg/Chapter2/4.jpg", ImageFormat.RGB565);
        Assets.world5 = g.newImage("bg/Chapter2/5.jpg", ImageFormat.RGB565);

        Assets.dark1 = g.newImage("bg/Chapter3/1.jpg", ImageFormat.RGB565);
        Assets.dark2 = g.newImage("bg/Chapter3/2.jpg", ImageFormat.RGB565);
        Assets.dark3 = g.newImage("bg/Chapter3/3.jpg", ImageFormat.RGB565);
        Assets.dark4 = g.newImage("bg/Chapter3/4.jpg", ImageFormat.RGB565);
        Assets.dark5 = g.newImage("bg/Chapter3/5.jpg", ImageFormat.RGB565);

        Assets.day1 = g.newImage("bg/Chapter4/1.jpg", ImageFormat.RGB565);
        Assets.day2 = g.newImage("bg/Chapter4/2.jpg", ImageFormat.RGB565);
        Assets.day3 = g.newImage("bg/Chapter4/3.jpg", ImageFormat.RGB565);
        Assets.day4 = g.newImage("bg/Chapter4/4.jpg", ImageFormat.RGB565);
        Assets.day5 = g.newImage("bg/Chapter4/5.jpg", ImageFormat.RGB565);

        Assets.wonder1 = g.newImage("bg/Chapter5/1.jpg", ImageFormat.RGB565);
        Assets.wonder2 = g.newImage("bg/Chapter5/2.jpg", ImageFormat.RGB565);
        Assets.wonder3 = g.newImage("bg/Chapter5/3.jpg", ImageFormat.RGB565);
        Assets.wonder4 = g.newImage("bg/Chapter5/4.jpg", ImageFormat.RGB565);
        Assets.wonder5 = g.newImage("bg/Chapter5/5.jpg", ImageFormat.RGB565);

        Assets.lock2 = g.newImage("Levels/lvl2.png", ImageFormat.RGB565);
        Assets.lock3 = g.newImage("Levels/lvl3.png", ImageFormat.RGB565);
        Assets.lock4 = g.newImage("Levels/lvl4.png", ImageFormat.RGB565);
        Assets.lock5 = g.newImage("Levels/lvl5.png", ImageFormat.RGB565);

        Assets.help1 = g.newImage("help/1.jpg", ImageFormat.RGB565);
        Assets.help2 = g.newImage("help/2.jpg", ImageFormat.RGB565);
        Assets.help3 = g.newImage("help/3.jpg", ImageFormat.RGB565);
        Assets.help4 = g.newImage("help/4.jpg", ImageFormat.RGB565);

        Assets.shoot = game.getAudio().createSound("audio/sounds/shoot.mp3");
        Assets.coin = game.getAudio().createSound("audio/sounds/coin.mp3");
        Assets.what = game.getAudio().createSound("audio/sounds/what.mp3");
        Assets.ammo = game.getAudio().createSound("audio/sounds/ammo.mp3");
        Assets.bear = game.getAudio().createSound("audio/sounds/bear.mp3");
        Assets.zombie = game.getAudio().createSound("audio/sounds/zombie.mp3");
        Assets.bird = game.getAudio().createSound("audio/sounds/bird.mp3");
        Assets.reload = game.getAudio().createSound("audio/sounds/reload.mp3");
        Assets.jump = game.getAudio().createSound("audio/sounds/jump.mp3");
        Assets.alien = game.getAudio().createSound("audio/sounds/alien.mp3");
        Assets.ant = game.getAudio().createSound("audio/sounds/ant.mp3");
        Assets.evil = game.getAudio().createSound("audio/sounds/evil.mp3");
        Assets.jumper = game.getAudio().createSound("audio/sounds/jumpDeath.mp3");
        Assets.ghost = game.getAudio().createSound("audio/sounds/ghost.mp3");
        Assets.death = game.getAudio().createSound("audio/sounds/dead.mp3");
        Assets.heartBeat = game.getAudio().createSound("audio/sounds/heart.mp3");
        Assets.enemyShoot = game.getAudio().createSound("audio/sounds/enemyShoot.mp3");
        Assets.endLvl = game.getAudio().createSound("audio/sounds/endLevel.mp3");
        Assets.fall = game.getAudio().createSound("audio/sounds/fall.mp3");

//        Assets.evil1 = g.newImage("Evil/purple/1.png", ImageFormat.RGB565);
//        Assets.evil2 = g.newImage("Evil/purple/2.png", ImageFormat.RGB565);
//        Assets.evil3 = g.newImage("Evil/purple/3.png", ImageFormat.RGB565);
//
//        Assets.green1 = g.newImage("Evil/green/1.png", ImageFormat.RGB565);
//        Assets.green2 = g.newImage("Evil/green/2.png", ImageFormat.RGB565);
//        Assets.green3 = g.newImage("Evil/green/3.png", ImageFormat.RGB565);
//        Assets.stepOne = g.newImage("hero/main/d1.png", ImageFormat.RGB565);
//        Assets.stepTwo = g.newImage("hero/main/d2.png", ImageFormat.RGB565);
//        Assets.heroe = g.newImage("hero/main/Hero.png", ImageFormat.RGB565);
//        Assets.Jump = g.newImage("hero/main/Jump.png", ImageFormat.RGB565);

//        Assets.oneHulk = g.newImage("hero/hulk/d1.png", ImageFormat.RGB565);
//        Assets.twoHulk = g.newImage("hero/hulk/d2.png", ImageFormat.RGB565);
//        Assets.hulk = g.newImage("hero/hulk/Hero.png", ImageFormat.RGB565);
//        Assets.hulkJump = g.newImage("hero/hulk/Jump.png", ImageFormat.RGB565);

//        Assets.stick = g.newImage("hero/stick/Hero.png", ImageFormat.RGB565);
//        Assets.stickTwo = g.newImage("hero/stick/Hero2.png", ImageFormat.RGB565);
//        Assets.stickThree = g.newImage("hero/stick/Hero3.png", ImageFormat.RGB565);
//        Assets.threeStick = g.newImage("hero/stick/d1.png", ImageFormat.RGB565);
//        Assets.fourStick = g.newImage("hero/stick/d2.png", ImageFormat.RGB565);
//        Assets.fiveStick = g.newImage("hero/stick/d3.png", ImageFormat.RGB565);
//        Assets.oneStick = g.newImage("hero/stick/1.png", ImageFormat.RGB565);
//        Assets.twoStick = g.newImage("hero/stick/2.png", ImageFormat.RGB565);
//        Assets.sixStick = g.newImage("hero/stick/4.png", ImageFormat.RGB565);
//        Assets.sevenStick = g.newImage("hero/stick/5.png", ImageFormat.RGB565);

//        Assets.batOne = g.newImage("hero/batman/d1.png", ImageFormat.RGB565);
//        Assets.batTwo = g.newImage("hero/batman/d2.png", ImageFormat.RGB565);
//        Assets.bat = g.newImage("hero/batman/Hero.png", ImageFormat.RGB565);
//        Assets.JumpBat = g.newImage("hero/batman/Jump.png", ImageFormat.RGB565);

//        Assets.oneSup = g.newImage("hero/superman/d1.png", ImageFormat.RGB565);
//        Assets.twoSup = g.newImage("hero/superman/d2.png", ImageFormat.RGB565);
//        Assets.superman = g.newImage("hero/superman/Hero.png", ImageFormat.RGB565);
//        Assets.JumpSup = g.newImage("hero/superman/Jump.png", ImageFormat.RGB565);

        resetButton = new Animation();
        resetButton.addFrame(Assets.reset1, 50);
        resetButton.addFrame(Assets.reset2, 50);
        themes = new Animation();
        themes.addFrame(Assets.themes1, 50);
        themes.addFrame(Assets.themes2, 50);
        animBullets();
        animRobo();
        animCoin();

//        animSecondChar();
//        animThirdChar();
//        animForthChar();
//        animFifthChar();
//        animEnemyTwo();
//        animEnemyOne();
        animJumper();
        animEnemyThree();
        animFlier();
        animAnt();
        animBul();
        animHeart();
        animAlien();
        animGhost();
        animBear();
        animUgly();

        game.setScreen(new MainMenuScreen(game));
    }

    @Override
    public void paint(float deltaTime) {
        Graphics g = game.getGraphics();

    }

    @Override
    public void pause() {
        // TODO Auto-generated method stub

    }

    @Override
    public void resume() {
        // TODO Auto-generated method stub

    }

    @Override
    public void dispose() {
        // TODO Auto-generated method stub

    }

    @Override
    public void backButton() {
        // TODO Auto-generated method stub

    }


    public static void animCoin() {
        coin = new Animation();
        coin.addFrame(Assets.c1, 50);
        coin.addFrame(Assets.c2, 50);
        coin.addFrame(Assets.c3, 50);
        coin.addFrame(Assets.c4, 50);
        coin.addFrame(Assets.c5, 50);
        coin.addFrame(Assets.c6, 50);
        coin.addFrame(Assets.c7, 50);

    }

    public static void animUgly() {
        ugly = new Animation();
        ugly.addFrame(Assets.ugly0, 50);
        ugly.addFrame(Assets.ugly1, 50);
        ugly.addFrame(Assets.ugly2, 50);
        ugly.addFrame(Assets.ugly3, 50);
        ugly.addFrame(Assets.ugly4, 50);
        ugly.addFrame(Assets.ugly5, 50);
        ugly.addFrame(Assets.ugly6, 50);
        ugly.addFrame(Assets.ugly7, 50);

    }

    public static void animGhost() {
        ghost = new Animation();
        ghost.addFrame(Assets.ghost1, 50);
        ghost.addFrame(Assets.ghost2, 50);
        ghost.addFrame(Assets.ghost3, 50);
        ghost.addFrame(Assets.ghost4, 50);
        ghost.addFrame(Assets.ghost5, 50);
        ghost.addFrame(Assets.ghost6, 50);
        ghost.addFrame(Assets.ghost7, 50);
        ghost.addFrame(Assets.ghost8, 50);
        ghost.addFrame(Assets.ghost9, 50);
        ghost.addFrame(Assets.ghost10, 50);

    }

    public static void animAlien() {
        alien = new Animation();
        alien.addFrame(Assets.alien0, 50);
        alien.addFrame(Assets.alien1, 50);
        alien.addFrame(Assets.alien2, 50);
        alien.addFrame(Assets.alien3, 50);
        alien.addFrame(Assets.alien4, 50);
        alien.addFrame(Assets.alien5, 50);
    }

    public static void animBul() {
        bullet = new Animation();
        bullet.addFrame(Assets.bul1, 50);
        bullet.addFrame(Assets.bullet, 50);
        bullet.addFrame(Assets.bul2, 50);
    }

    public static void animJumper() {
        jumper = new Animation();
        jumper.addFrame(Assets.jump1, 20);
        jumper.addFrame(Assets.jump2, 50);
        jumper.addFrame(Assets.jump3, 50);
    }

    public static void animBullets() {
        bullets = new Animation();
        bullets.addFrame(Assets.bullet1, 50);
        bullets.addFrame(Assets.bullet2, 50);
        bullets.addFrame(Assets.bullet3, 50);
    }

    public static void animHeart() {
        heart = new Animation();
        heart.addFrame(Assets.heart, 50);
        heart.addFrame(Assets.heart2, 50);
    }

    public static void animRobo() {
        robo = new Animation();
        robo.addFrame(Assets.robo1, 50);
        robo.addFrame(Assets.robo2, 50);
        robo.addFrame(Assets.robo3, 50);
        robo.addFrame(Assets.robo4, 50);
        robo.addFrame(Assets.robo5, 50);
        robo.addFrame(Assets.robo6, 50);
        robo.addFrame(Assets.robo7, 50);
        robo.addFrame(Assets.robo8, 50);
        robo.addFrame(Assets.robo9, 50);
        robo.addFrame(Assets.robo10, 50);
        robo.addFrame(Assets.robo11, 50);
        robo.addFrame(Assets.robo12, 50);
        robo.addFrame(Assets.robo13, 50);
        robo.addFrame(Assets.robo14, 50);
        robo.addFrame(Assets.robo15, 50);
        robo.addFrame(Assets.robo16, 50);
        robo.addFrame(Assets.robo17, 50);
        robo.addFrame(Assets.robo18, 50);
        robo.addFrame(Assets.robo19, 50);
        robo.addFrame(Assets.robo20, 50);
        robo.addFrame(Assets.robo21, 50);
        robo.addFrame(Assets.robo22, 50);
        robo.addFrame(Assets.robo23, 50);
        robo.addFrame(Assets.robo24, 50);
        robo.addFrame(Assets.robo25, 50);
        robo.addFrame(Assets.robo26, 50);
        roboJump = new Animation();
        roboJump.addFrame(Assets.robo22, 50);
        roboJump.addFrame(Assets.robo23, 50);
        roboJump.addFrame(Assets.robo24, 50);
        roboStay = new Animation();
        roboStay.addFrame(Assets.robo20, 200);
        roboStay.addFrame(Assets.robo19, 100);
        roboStay.addFrame(Assets.robo18, 80);


    }

    public static void animAnt() {
        ant = new Animation();
        ant.addFrame(Assets.ant1, 50);
        ant.addFrame(Assets.ant2, 50);
        ant.addFrame(Assets.ant3, 50);
        ant.addFrame(Assets.ant4, 50);
        ant.addFrame(Assets.ant5, 50);
        ant.addFrame(Assets.ant6, 50);
        ant.addFrame(Assets.ant7, 50);
        ant.addFrame(Assets.ant8, 50);
        ant.addFrame(Assets.ant9, 50);

    }

    public static void animFlier() {
        bird = new Animation();
        bird.addFrame(Assets.fly1, 50);
        bird.addFrame(Assets.fly2, 50);
        bird.addFrame(Assets.fly3, 50);
        bird.addFrame(Assets.fly4, 50);
        bird.addFrame(Assets.fly5, 50);


    }

    public static void animBear() {
        bear = new Animation();
        bear.addFrame(Assets.bear0, 50);
        bear.addFrame(Assets.bear1, 50);
        bear.addFrame(Assets.bear2, 50);
        bear.addFrame(Assets.bear3, 50);
        bear.addFrame(Assets.bear4, 50);
        bear.addFrame(Assets.bear5, 50);
        bear.addFrame(Assets.bear6, 50);
        bear.addFrame(Assets.bear7, 50);
        bear.addFrame(Assets.bear8, 50);
        bear.addFrame(Assets.bear9, 50);
        bear.addFrame(Assets.bear10, 50);
        bear.addFrame(Assets.bear11, 50);
        bear.addFrame(Assets.bear12, 50);
        bear.addFrame(Assets.bear13, 50);
        bear.addFrame(Assets.bear14, 50);
        bear.addFrame(Assets.bear15, 50);
        bear.addFrame(Assets.bear16, 50);
    }

//
//    public static void animSecondChar() {
//        runFront = new Animation();
//        runFront.addFrame(Assets.oneHulk, 50);
//        runFront.addFrame(Assets.twoHulk, 50);
//        runFront.addFrame(Assets.hulk, 50);
//        heroFront = Assets.hulk;
//        jumpFront = Assets.hulkJump;
//    }
//
//    public static void animThirdChar() {
//        runFront = new Animation();
//        runFront.addFrame(Assets.batOne, 50);
//        runFront.addFrame(Assets.batTwo, 50);
//        runFront.addFrame(Assets.bat, 50);
//
//        heroFront = Assets.bat;
//        jumpFront = Assets.JumpBat;
//
//
//    }
//
//    public static void animForthChar() {
//        runFront = new Animation();
//        runFront.addFrame(Assets.oneSup, 50);
//        runFront.addFrame(Assets.twoSup, 50);
//        runFront.addFrame(Assets.superman, 50);
//
//        heroFront = Assets.superman;
//        jumpFront = Assets.JumpSup;
//
//
//    }
//
//    public static void animFifthChar() {
//        runFront = new Animation();
//        stayFront = new Animation();
//        stayBack = new Animation();
//
//        stayFront.addFrame(Assets.stick, 40);
//        stayFront.addFrame(Assets.stickTwo, 40);
//        stayFront.addFrame(Assets.stickThree, 40);
//        runFront.addFrame(Assets.oneStick, 40);
//        runFront.addFrame(Assets.twoStick, 40);
//        runFront.addFrame(Assets.threeStick, 40);
//        runFront.addFrame(Assets.fourStick, 40);
//        runFront.addFrame(Assets.fiveStick, 40);
//        runFront.addFrame(Assets.sixStick, 40);
//        runFront.addFrame(Assets.sevenStick, 40);
//
//        heroFront = stayFront.getImage();
//        heroBack = stayBack.getImage();
//    }

//    public static void animEnemyOne() {
//        evil = new Animation();
//        evil.addFrame(Assets.evil1, 50);
//        evil.addFrame(Assets.evil2, 50);
//        evil.addFrame(Assets.evil3, 50);
//
//    }
//
//    public static void animEnemyTwo() {
//        evil = new Animation();
//        evil.addFrame(Assets.green1, 50);
//        evil.addFrame(Assets.green2, 50);
//        evil.addFrame(Assets.green3, 50);
//
//    }

    public static void animEnemyThree() {
        evil = new Animation();
        evil.addFrame(Assets.white1, 50);
        evil.addFrame(Assets.white2, 50);
        evil.addFrame(Assets.white3, 50);

    }


}
