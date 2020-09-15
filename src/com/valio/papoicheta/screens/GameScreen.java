package com.valio.papoicheta.screens;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Vibrator;
import android.util.Log;

import com.valio.framework.Game;
import com.valio.framework.Graphics;
import com.valio.framework.Input.TouchEvent;
import com.valio.framework.Screen;
import com.valio.framework.implementation.AndroidGame;
import com.valio.papoicheta.Ammo;
import com.valio.papoicheta.Assets;
import com.valio.papoicheta.Background;
import com.valio.papoicheta.Coin;
import com.valio.papoicheta.End;
import com.valio.papoicheta.Enemies.Flier;
import com.valio.papoicheta.Enemies.Follower;
import com.valio.papoicheta.Enemies.Jumper;
import com.valio.papoicheta.Enemies.Shooter;
import com.valio.papoicheta.EnemyProjectile;
import com.valio.papoicheta.FPSCounter;
import com.valio.papoicheta.Heroes.HeroOne;
import com.valio.papoicheta.Heroes.IHero;
import com.valio.papoicheta.Live;
import com.valio.papoicheta.ProjectGame;
import com.valio.papoicheta.Projectile;
import com.valio.papoicheta.Terrain;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static com.valio.papoicheta.ProjectGame.settings;

public class GameScreen extends Screen {
    enum GameState {
        Ready, Running, Paused, GameOver, NextLevel
    }

    GameState state;
    private static Background bg;
    public static IHero hero;
    private static ArrayList<Object> terrain = new ArrayList<Object>();
    private static ArrayList<Follower> followers = new ArrayList<Follower>();
    private static ArrayList<Shooter> shooters = new ArrayList<Shooter>();
    private static ArrayList<Jumper> jumpers = new ArrayList<Jumper>();
    private static ArrayList<Flier> fliers = new ArrayList<Flier>();
    private static ArrayList<Coin> coins = new ArrayList<Coin>();
    private static ArrayList<Live> lives = new ArrayList<Live>();
    private static ArrayList<Ammo> ammos = new ArrayList<Ammo>();
    private static ArrayList<String> lines;
    private static Follower follower;
    private static Shooter shooter;
    private static End end;
    private static Jumper jumper;
    private static Flier flier;
    private static Coin coin;
    private static Live live;
    private static Ammo ammo;
    Paint paint, paint2, paint3;
    private static long startTime = 0;
    long time = 0;

    boolean ad = false;
    public static Vibrator v = (Vibrator) AndroidGame.context.getSystemService(Context.VIBRATOR_SERVICE);

    public GameScreen(Game game) {
        super(game);
        bg = new Background(0, 0);

        if (settings.getCurrentLevel() == 1) {
            state = GameState.Ready;
        } else {
            state = GameState.Running;
        }
//        switch (settings.getCurrentHero()) {
//            case 1:
        hero = new HeroOne();

//                break;
//            case 2:
//                hero = new HeroTwo();
//                break;
//            case 3:
//                hero = new HeroThree();
//                break;
//            case 4:
//                hero = new HeroFour();
//                break;
//            case 5:
//                hero = new HeroFive();
//                break;
//            default:
//                break;
//        }
        ad = false;
        hero.animate();
        LoadingScreen.animCoin();
        if (settings.getCurrentLevel() == 1) {
            hero.bulletLimit = 5;
            hero.score = 0;
        }
        if (settings.getCurrentLevel() >= 6) {
            if (hero.bulletLimit < 7)
                hero.bulletLimit = 7;
        }
        if (settings.getCurrentLevel() >= 11) {
            if (hero.heroLive < 3)
                hero.heroLive = 3;
            if (hero.bulletLimit < 10)
                hero.bulletLimit = 10;
        }
        if (settings.getCurrentLevel() >= 16) {
            if (hero.heroLive < 4)
                hero.heroLive = 4;
            if (hero.bulletLimit < 12)
                hero.bulletLimit = 12;
        }
        if (settings.getCurrentLevel() >= 21) {
            if (hero.heroLive < 5)
                hero.heroLive = 5;
            if (hero.bulletLimit < 20)
                hero.bulletLimit = 20;
        }
//        if (settings.getCurrentChapter() == 1) {
//            LoadingScreen.animEnemyOne();
//        } else if (settings.getCurrentChapter() == 2)
//            LoadingScreen.animEnemyThree();
//        else if (settings.getCurrentChapter() == 3)
//            LoadingScreen.animEnemyTwo();
//        else
        LoadingScreen.animEnemyThree();
        loadMap();
        definePaints();
    }

    private void definePaints() {
        paint = new Paint();
        paint.setTypeface(ProjectGame.tf);
        paint.setShadowLayer(
                4f,   //float radius
                4f,  //float dx
                4f,  //float dy
                0xFF000000 //int color
        );
        paint.setTextSize(30);
        paint.setTextAlign(Paint.Align.CENTER);
        paint.setAntiAlias(true);
        paint.setColor(Color.YELLOW);

        paint3 = new Paint();
        paint3.setTypeface(ProjectGame.tf);
        paint3.setShadowLayer(
                4f,   //float radius
                4f,  //float dx
                4f,  //float dy
                0xFF000000 //int color
        );
        paint3.setTextSize(60);
        paint3.setTextAlign(Paint.Align.CENTER);
        paint3.setAntiAlias(true);
        paint3.setColor(Color.RED);

        paint2 = new Paint();
        paint2.setTypeface(ProjectGame.tf);
        paint2.setShadowLayer(
                4f,   //float radius
                4f,  //float dx
                4f,  //float dy
                0xFF000000 //int color
        );
        paint2.setTextSize(70);
        paint2.setTextAlign(Paint.Align.CENTER);
        paint2.setAntiAlias(true);
        paint2.setColor(Color.WHITE);
    }

    @Override
    public void update(float deltaTime) {
        List<TouchEvent> touchEvents = game.getInput().getTouchEvents();

        if (state == GameState.Ready)
            updateReady(touchEvents);
        if (state == GameState.Running)
            updateRunning(touchEvents);
        if (state == GameState.Paused)
            updatePaused(touchEvents);
        if (state == GameState.GameOver)
            updateGameOver(touchEvents);
        if (state == GameState.NextLevel)
            updateNextLevel(touchEvents);


    }

    private void loadMap() {
        lines = new ArrayList<String>();
        int width = readMaps();
        lines.size();
        addObjectsToMap(width);
    }


    private void addObjectsToMap(int width) {
        for (int j = 0; j < 12; j++) {
            String line = lines.get(j);
            for (int i = 0; i < width; i++) {
                if (i < line.length()) {
                    char ch = line.charAt(i);
                    if (Character.getNumericValue(ch) == 9) {
                        follower = new Follower(i, j - 1);
                        follower.setHealth(settings.getCurrentChapter());
                        followers.add(follower);
                        continue;
                    }
                    if (Character.getNumericValue(ch) == 1) {
                        shooter = new Shooter(i, j);
                        shooters.add(shooter);
                        continue;
                    }
                    if (Character.getNumericValue(ch) == 2) {
                        jumper = new Jumper(i, j);
                        jumpers.add(jumper);
                        continue;
                    }
                    if (Character.getNumericValue(ch) == 3) {
                        flier = new Flier(i, j);
                        fliers.add(flier);
                        continue;
                    }
                    if (Character.getNumericValue(ch) == 6) {
                        end = new End(i, j);
                        continue;
                    }
                    if (Character.getNumericValue(ch) == 5) {
                        live = new Live(i, j);
                        lives.add(live);
                        continue;
                    }
                    if (Character.getNumericValue(ch) == 4) {
                        ammo = new Ammo(i, j);
                        ammos.add(ammo);
                        continue;
                    }
                    if (Character.getNumericValue(ch) == 0) {
                        coin = new Coin(i, j);
                        coin.setMonetka(LoadingScreen.coin.getImage());
                        coins.add(coin);
                        continue;
                    }
                    Terrain t = new Terrain(i, j, Character.getNumericValue(ch));
                    terrain.add(t);
                }

            }
        }
    }

    private static int readMaps() {
        int width = 0;
        Scanner scanner;
        switch (settings.getCurrentLevel()) {
            case 1:
                scanner = new Scanner(ProjectGame.map1);
                break;
            case 2:
                scanner = new Scanner(ProjectGame.map2);
                break;
            case 3:
                scanner = new Scanner(ProjectGame.map3);
                break;
            case 4:
                scanner = new Scanner(ProjectGame.map4);
                break;
            case 5:
                scanner = new Scanner(ProjectGame.map5);
                break;
            case 6:
                scanner = new Scanner(ProjectGame.map6);
                break;
            case 7:
                scanner = new Scanner(ProjectGame.map7);
                break;
            case 8:
                scanner = new Scanner(ProjectGame.map8);
                break;
            case 9:
                scanner = new Scanner(ProjectGame.map9);
                break;
            case 10:
                scanner = new Scanner(ProjectGame.map10);
                break;
            case 11:
                scanner = new Scanner(ProjectGame.map11);
                break;
            case 12:
                scanner = new Scanner(ProjectGame.map12);
                break;
            case 13:
                scanner = new Scanner(ProjectGame.map13);
                break;
            case 14:
                scanner = new Scanner(ProjectGame.map14);
                break;
            case 15:
                scanner = new Scanner(ProjectGame.map15);
                break;
            case 16:
                scanner = new Scanner(ProjectGame.map16);
                break;
            case 17:
                scanner = new Scanner(ProjectGame.map17);
                break;
            case 18:
                scanner = new Scanner(ProjectGame.map18);
                break;
            case 19:
                scanner = new Scanner(ProjectGame.map19);
                break;
            case 20:
                scanner = new Scanner(ProjectGame.map20);
                break;
            case 21:
                scanner = new Scanner(ProjectGame.map21);
                break;
            case 22:
                scanner = new Scanner(ProjectGame.map22);
                break;
            case 23:
                scanner = new Scanner(ProjectGame.map23);
                break;
            case 24:
                scanner = new Scanner(ProjectGame.map24);
                break;
            case 25:
                scanner = new Scanner(ProjectGame.map25);
                break;
            default:
                scanner = new Scanner(ProjectGame.map1);
                break;
        }
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            // no more lines to read
            if (line == null) {
                break;
            }
            if (!line.startsWith("!")) {
                lines.add(line);
                width = Math.max(width, line.length());
            }
        }
        return width;
    }

    // paint Terrain objects
    private void paintTerrain(Graphics g) {
        for (int i = 0; i < terrain.size(); i++) {
            Terrain t = (Terrain) terrain.get(i);
            if (t.getX() <= 800) {
                if (t.getType() == 8) {
                    g.drawImage(t.getImage(), t.getX(), t.getY());
                } else if (t.getType() == 7) {
                    g.drawImage(t.getImage(), t.getX(), t.getY());
                }
            }
        }

    }

    private void paintEnd(Graphics g) {
        if (settings.getCurrentChapter() == 2)
            g.drawImage(Assets.end2, end.getCenterX(),
                    end.getCenterY() - 40);
        else if (settings.getCurrentChapter() == 3)
            g.drawImage(Assets.darkEnd, end.getCenterX(),
                    end.getCenterY() - 232);
        else
            g.drawImage(Assets.end, end.getCenterX(),
                    end.getCenterY() - 40);
    }


    private void paintLives(Graphics g) {
        for (int i = 0; i < lives.size(); i++) {
            live = lives.get(i);
            if (live.getCenterX() <= 800) {
                g.drawImage(LoadingScreen.heart.getImage(), live.getCenterX(),
                        live.getCenterY());
            }
        }
    }

    private void paintAmmos(Graphics g) {
        for (int i = 0; i < ammos.size(); i++) {
            ammo = ammos.get(i);
            if (ammo.getCenterX() <= 800) {
                g.drawImage(LoadingScreen.bullet.getImage(), ammo.getCenterX(),
                        ammo.getCenterY());
            }
        }
    }

    private void paintCoins(Graphics g) {
        for (int i = 0; i < coins.size(); i++) {
            coin = coins.get(i);
            if (coin.getCenterX() <= 800) {

                g.drawImage(coin.getMonetka(), coin.getCenterX(),
                        coin.getCenterY());
            }
        }
    }


    private void paintFollowers(Graphics g) {
        for (int i = 0; i < followers.size(); i++) {
            follower = followers.get(i);
            if (follower.getCenterX() <= 800) {
                if (settings.getCurrentChapter() == 1) {
                    if (follower.health > 0) {
                        if (follower.isMovingRight())
                            g.drawImage(LoadingScreen.bear.getImage(), follower.getCenterX() - 28,
                                    follower.getCenterY() - 45);
                        else
                            g.drawMirror(LoadingScreen.bear.getImage(), follower.getCenterX() - 28,
                                    follower.getCenterY() - 45);
                    } else {
                        if (follower.isMovingRight())
                            g.drawUpDown(Assets.bear0, follower.getCenterX() - 28,
                                    follower.getCenterY() - 45);
                        else
                            g.drawUpDown(Assets.bear0, follower.getCenterX() - 28,
                                    follower.getCenterY() - 45);
                    }
                } else if (settings.getCurrentChapter() == 2) {
                    if (follower.health > 0) {
                        if (follower.isMovingRight())
                            g.drawImage(LoadingScreen.ugly.getImage(), follower.getCenterX() - 28,
                                    follower.getCenterY() - 45);
                        else
                            g.drawMirror(LoadingScreen.ugly.getImage(), follower.getCenterX() - 28,
                                    follower.getCenterY() - 45);
                    } else {
                        if (follower.isMovingRight())
                            g.drawUpDown(Assets.ugly0, follower.getCenterX() - 28,
                                    follower.getCenterY() - 45);
                        else
                            g.drawUpDown(Assets.ugly0, follower.getCenterX() - 28,
                                    follower.getCenterY() - 45);
                    }
                } else if (settings.getCurrentChapter() == 3) {
                    if (follower.health > 0) {
                        if (follower.isMovingRight())

                            g.drawImage(LoadingScreen.evil.getImage(), follower.getCenterX() - 28,
                                    follower.getCenterY() - 45);
                        else
                            g.drawMirror(LoadingScreen.evil.getImage(), follower.getCenterX() - 28,
                                    follower.getCenterY() - 45);
                    } else {
                        if (follower.isMovingRight())
                            g.drawUpDown(Assets.white1, follower.getCenterX() - 28,
                                    follower.getCenterY() - 45);
                        else
                            g.drawUpDown(Assets.white1, follower.getCenterX() - 28,
                                    follower.getCenterY() - 45);
                    }
                } else if (settings.getCurrentChapter() == 4) {
                    if (follower.health > 0) {
                        if (follower.isMovingRight())
                            g.drawImage(LoadingScreen.ant.getImage(), follower.getCenterX() - 28,
                                    follower.getCenterY() - 45);
                        else
                            g.drawMirror(LoadingScreen.ant.getImage(), follower.getCenterX() - 28,
                                    follower.getCenterY() - 45);
                    } else {
                        if (follower.isMovingRight())
                            g.drawUpDown(Assets.ant1, follower.getCenterX() - 28,
                                    follower.getCenterY() - 45);
                        else
                            g.drawUpDown(Assets.ant1, follower.getCenterX() - 28,
                                    follower.getCenterY() - 45);
                    }
                } else if (settings.getCurrentChapter() == 5) {
                    if (follower.health > 0) {
                        if (follower.isMovingRight())
                            g.drawImage(LoadingScreen.ghost.getImage(), follower.getCenterX() - 28,
                                    follower.getCenterY() - 45);
                        else
                            g.drawMirror(LoadingScreen.ghost.getImage(), follower.getCenterX() - 28,
                                    follower.getCenterY() - 45);
                    } else {
                        if (follower.isMovingRight())
                            g.drawUpDown(Assets.ghost1, follower.getCenterX() - 28,
                                    follower.getCenterY() - 45);
                        else
                            g.drawUpDown(Assets.ghost1, follower.getCenterX() - 28,
                                    follower.getCenterY() - 45);
                    }
                }
            }
        }
    }

    private void paintJumpers(Graphics g) {
        for (int i = 0; i < jumpers.size(); i++) {
            jumper = jumpers.get(i);
            if (jumper.getCenterX() <= 800) {
                if (jumper.health > 0) {
                    if (jumper.isMovingRight())
                        g.drawImage(LoadingScreen.jumper.getImage(), jumper.getCenterX(),
                                jumper.getCenterY());
                    else
                        g.drawMirror(LoadingScreen.jumper.getImage(), jumper.getCenterX(),
                                jumper.getCenterY());
                } else
                    g.drawUpDown(Assets.jump1, jumper.getCenterX(),
                            jumper.getCenterY());
            }
        }

    }

    private void paintFliers(Graphics g) {
        for (int i = 0; i < fliers.size(); i++) {
            flier = fliers.get(i);
            if (flier.getCenterX() <= 800) {
                if (flier.health > 0) {
                    if (flier.isMovingRight())
                        g.drawImage(LoadingScreen.bird.getImage(), flier.getCenterX(),
                                flier.getCenterY());
                    else
                        g.drawMirror(LoadingScreen.bird.getImage(), flier.getCenterX(),
                                flier.getCenterY());
                }
            } else {
                if (flier.isMovingRight())
                    g.drawUpDown(Assets.fly1, flier.getCenterX(),
                            flier.getCenterY());
                else
                    g.drawUpDown(Assets.fly1, flier.getCenterX(),
                            flier.getCenterY());
            }
        }

    }

    private void paintShooters(Graphics g) {
        for (int i = 0; i < shooters.size(); i++) {
            shooter = shooters.get(i);
            if (shooter.getCenterX() <= 800) {
                if (shooter.health > 0) {
                    if (shooter.isMovingRight())
                        g.drawImage(LoadingScreen.alien.getImage(), shooter.getCenterX() - 28,
                                shooter.getCenterY() - 45);
                    else
                        g.drawMirror(LoadingScreen.alien.getImage(), shooter.getCenterX() - 28,
                                shooter.getCenterY() - 45);
                } else {
                    if (shooter.isMovingRight())
                        g.drawUpDown(Assets.alien0, shooter.getCenterX() - 28,
                                shooter.getCenterY() - 45);
                    else
                        g.drawUpDown(Assets.alien0, shooter.getCenterX() - 28,
                                shooter.getCenterY() - 45);
                }
            }
        }
    }

    // update Terrain objects
    private void updateTerrain() {
        for (int i = 0; i < terrain.size(); i++) {
            if (terrain.get(i) instanceof Terrain) {
                Terrain t = (Terrain) terrain.get(i);
                t.update();
                if (t.getX() <= -50)
                    terrain.remove(t);
            }
        }

    }

    private void updateLives() {
        for (int i = 0; i < lives.size(); i++) {
            Live live = lives.get(i);
            live.update();
            if (live.getCenterX() <= -50)
                lives.remove(live);
        }
    }

    private void updateAmmos() {
        for (int i = 0; i < ammos.size(); i++) {
            Ammo ammo = ammos.get(i);
            ammo.update();
            if (ammo.getCenterX() <= -50)
                ammos.remove(ammo);
        }
    }

    private void updateCoins() {
        for (int i = 0; i < coins.size(); i++) {
            Coin coin = coins.get(i);
            coin.update();
            if (coin.getCenterX() <= -50)
                coins.remove(coin);
        }
    }

    private void updateEvil() {
        for (int i = 0; i < followers.size(); i++) {
            follower = followers.get(i);
            follower.update();
            if (follower.getCenterX() <= -50)
                followers.remove(follower);
            if (follower.getCenterY() > 480)
                followers.remove(follower);
        }
    }

    private void updateJumpers() {
        for (int i = 0; i < jumpers.size(); i++) {
            jumper = jumpers.get(i);
            jumper.update();
            if (jumper.getCenterX() <= -50)
                jumpers.remove(jumper);
            if (jumper.getCenterY() > 500)
                jumpers.remove(jumper);
        }
    }

    private void updateFliers() {
        for (int i = 0; i < fliers.size(); i++) {
            flier = fliers.get(i);
            flier.update();
            if (flier.getCenterX() <= -50)
                fliers.remove(flier);
            if (flier.getCenterY() > 500)
                fliers.remove(flier);
        }
    }

    private void updateShooters() {
        for (int i = 0; i < shooters.size(); i++) {
            shooter = shooters.get(i);
            shooter.update();
            if (shooter.getCenterX() <= -50)
                shooters.remove(shooter);
            if (shooter.getCenterY() > 500)
                shooters.remove(shooter);
        }
    }


    @Override
    public void paint(float deltaTime) {
        Graphics g = game.getGraphics();
        paintBackground(g);
        paintTerrain(g);
        paintJumpers(g);
        paintFliers(g);
        paintFollowers(g);
        paintShooters(g);
        paintCoins(g);
        paintLives(g);
        paintAmmos(g);
        paintHero(g);
        paintBullets(g);
        paintEnemyProjectiles(g);
        paintEnd(g);
        if (state == GameState.Ready)
            drawReadyUI();
        else if (state == GameState.Running)
            drawRunningUI();
        else if (state == GameState.Paused)
            drawPausedUI();
        else if (state == GameState.GameOver)
            drawGameOverUI();
        else if (state == GameState.NextLevel)
            drawNextLevel();
    }

    private void paintEnemyProjectiles(Graphics g) {
        ArrayList<EnemyProjectile> projectiles = new ArrayList<EnemyProjectile>();
        for (int i = 0; i < shooters.size(); i++) {
            if (shooters.get(i).getCenterX() <= 800)
                projectiles = shooters.get(i).getProjectiles();
            for (int p = 0; p < projectiles.size(); p++) {
                if (!projectiles.get(p).goLeft)
                    g.drawImage(Assets.alienBul, projectiles.get(p).getX(), projectiles.get(p).getY());
                else
                    g.drawMirror(Assets.alienBul, projectiles.get(p).getX(), projectiles.get(p).getY());
            }
        }

    }

    private void paintBullets(Graphics g) {
        ArrayList<Projectile> projectiles = hero.getProjectiles();
        for (int i = 0; i < projectiles.size(); i++) {
            Projectile p = projectiles.get(i);
            if (!p.goLeft)
                g.drawImage(LoadingScreen.bullets.getImage(), p.getX(), p.getY());
            else
                g.drawMirror(LoadingScreen.bullets.getImage(), p.getX(), p.getY());
        }
    }

    private void paintHero(Graphics g) {
        if (hero.immortal)
            g.drawRect(hero.getCenterX() - 30, hero.getCenterY() - 31, 50, 70, Color.YELLOW);
        else {
            if (hero.isWounded) {
                if (startTime == 0)
                    startTime = System.nanoTime();
                g.drawImage(Assets.pow, hero.getCenterX() - 30,
                        hero.getCenterY() - 41);
                if (System.nanoTime() - startTime > (5000 * 100000)) {
                    hero.isWounded = false;
                    startTime = 0;
                }
            } else {
                if (hero.leftPos) {
                    if (hero.jumpImg) {
                        g.drawMirror(LoadingScreen.roboJump.getImage(), hero.getCenterX() - 30,
                                hero.getCenterY() - 41);
                    } else if (hero.isMovingLeft()) {
                        g.drawMirror(LoadingScreen.robo.getImage(), hero.getCenterX() - 30,
                                hero.getCenterY() - 41);
                    } else {
                        g.drawMirror(LoadingScreen.roboStay.getImage(), hero.getCenterX() - 30,
                                hero.getCenterY() - 41);
                    }
                } else {
                    if (hero.jumpImg) {
                        g.drawImage(LoadingScreen.roboJump.getImage(), hero.getCenterX() - 30,
                                hero.getCenterY() - 41);
                    } else if (hero.isMovingRight()) {
                        g.drawImage(LoadingScreen.robo.getImage(), hero.getCenterX() - 30,
                                hero.getCenterY() - 41);
                    } else {
                        g.drawImage(LoadingScreen.roboStay.getImage(), hero.getCenterX() - 30,
                                hero.getCenterY() - 41);
                    }
                }
            }
        }
    }


    private void paintBackground(Graphics g) {
        if (settings.getCurrentLevel() == 1)
            g.drawImage(Assets.once1, bg.getBgX(), bg.getBgY());
        else if (settings.getCurrentLevel() == 2)
            g.drawImage(Assets.once2, bg.getBgX(), bg.getBgY());
        else if (settings.getCurrentLevel() == 3)
            g.drawImage(Assets.once3, bg.getBgX(), bg.getBgY());
        else if (settings.getCurrentLevel() == 4)
            g.drawImage(Assets.once4, bg.getBgX(), bg.getBgY());
        else if (settings.getCurrentLevel() == 5)
            g.drawImage(Assets.once5, bg.getBgX(), bg.getBgY());
        else if (settings.getCurrentLevel() == 6)
            g.drawImage(Assets.world1, bg.getBgX(), bg.getBgY());
        else if (settings.getCurrentLevel() == 7)
            g.drawImage(Assets.world2, bg.getBgX(), bg.getBgY());
        else if (settings.getCurrentLevel() == 8)
            g.drawImage(Assets.world3, bg.getBgX(), bg.getBgY());
        else if (settings.getCurrentLevel() == 9)
            g.drawImage(Assets.world4, bg.getBgX(), bg.getBgY());
        else if (settings.getCurrentLevel() == 10)
            g.drawImage(Assets.world5, bg.getBgX(), bg.getBgY());
        else if (settings.getCurrentLevel() == 11)
            g.drawImage(Assets.dark1, bg.getBgX(), bg.getBgY());
        else if (settings.getCurrentLevel() == 12)
            g.drawImage(Assets.dark2, bg.getBgX(), bg.getBgY());
        else if (settings.getCurrentLevel() == 13)
            g.drawImage(Assets.dark3, bg.getBgX(), bg.getBgY());
        else if (settings.getCurrentLevel() == 14)
            g.drawImage(Assets.dark4, bg.getBgX(), bg.getBgY());
        else if (settings.getCurrentLevel() == 15)
            g.drawImage(Assets.dark5, bg.getBgX(), bg.getBgY());
        else if (settings.getCurrentLevel() == 16)
            g.drawImage(Assets.day1, bg.getBgX(), bg.getBgY());
        else if (settings.getCurrentLevel() == 17)
            g.drawImage(Assets.day2, bg.getBgX(), bg.getBgY());
        else if (settings.getCurrentLevel() == 18)
            g.drawImage(Assets.day3, bg.getBgX(), bg.getBgY());
        else if (settings.getCurrentLevel() == 19)
            g.drawImage(Assets.day4, bg.getBgX(), bg.getBgY());
        else if (settings.getCurrentLevel() == 20)
            g.drawImage(Assets.day5, bg.getBgX(), bg.getBgY());
        else if (settings.getCurrentLevel() == 21)
            g.drawImage(Assets.wonder1, bg.getBgX(), bg.getBgY());
        else if (settings.getCurrentLevel() == 22)
            g.drawImage(Assets.wonder2, bg.getBgX(), bg.getBgY());
        else if (settings.getCurrentLevel() == 23)
            g.drawImage(Assets.wonder3, bg.getBgX(), bg.getBgY());
        else if (settings.getCurrentLevel() == 24)
            g.drawImage(Assets.wonder4, bg.getBgX(), bg.getBgY());
        else if (settings.getCurrentLevel() == 25)
            g.drawImage(Assets.wonder5, bg.getBgX(), bg.getBgY());
    }

    private void updateReady(List<TouchEvent> touchEvents) {
        if (touchEvents.size() > 0)
            state = GameState.Running;
    }

    // here we define the behaviour of all events
    private void updateRunning(List<TouchEvent> touchEvents) {

        FPSCounter fpsCounter = new FPSCounter();
        fpsCounter.logFrame();

        // 1. All touch input is handled here:

        if (settings.isButtons())
            defineButtons(touchEvents);
        else if (settings.isTouch()) {
            defineTouch(touchEvents);
        }
        defineOtherEvents();
        updateProjectiles();
        updateEnemyBullets();
        animate();
        updateTerrain();
        updateEvil();
        updateJumpers();
        updateFliers();
        updateShooters();
        updateCoins();
        updateLives();
        updateAmmos();
        end.update();
        bg.update();
    }

    private void defineOtherEvents() {
        hero.update();
        if (hero.isDead) {
            hero.isDead = false;
            state = GameState.GameOver;
            if (settings.isVibrate())
                v.vibrate(150);
            if (settings.getSoundVolume() > 0)
                Assets.death.play(settings.getSoundVolume());
        }
        if (hero.nextLvl) {
            state = GameState.NextLevel;
            if (settings.isVibrate())
                v.vibrate(150);
        }

    }


    private void updateEnemyBullets() {
        ArrayList<EnemyProjectile> projectiles;
        for (int i = 0; i < shooters.size(); i++) {
            if (shooters.get(i).getCenterX() <= 800) {
                projectiles = shooters.get(i).getProjectiles();
                for (int p = 0; p < projectiles.size(); p++) {
                    if (projectiles.get(p).isVisible() == true) {
                        projectiles.get(p).update();
                    } else {
                        projectiles.remove(p);
                    }
                }
//                Log.e("shooter", "shoot " +projectiles.size()+"      "+ i);
            }
        }

    }

    private void updateProjectiles() {
        ArrayList<Projectile> projectiles = hero.getProjectiles();
        for (int i = 0; i < projectiles.size(); i++) {
            Projectile p = projectiles.get(i);
            if (p.isVisible() == true) {
                p.update();
            } else {
                projectiles.remove(i);
            }
        }
    }

    private void defineButtons(List<TouchEvent> touchEvents) {
        int len = touchEvents.size();
        for (int i = 0; i < len; i++) {
            TouchEvent event = touchEvents.get(i);
            if (event.type == TouchEvent.TOUCH_DOWN) {
                if (event.x >= 0 && event.x <= 80 && event.y >= 400
                        && event.y <= 480) {
                    if (hero.jumps < 2) {

                        hero.drawJump = true;
                        hero.jump();
                    }
                } else if (event.x >= 0 && event.x <= 80 && event.y >= 320
                        && event.y <= 400) {
                    if (hero.isReadyToFire()) {
                        hero.shoot();
                        if (hero.bulletLimit > 0) {
                            if (settings.getSoundVolume() > 0)
                                Assets.shoot.play(settings.getSoundVolume());
                            hero.isShooting = true;
                        } else {
                            if (settings.getSoundVolume() > 0)
                                Assets.reload.play(settings.getSoundVolume());
                        }
                    }
                } else if (event.x >= 720 && event.y >= 400 && event.y < 480) {
                    hero.moveRight();
                    hero.setMovingRight(true);
                } else if (event.x >= 640 && event.x <= 720 && event.y >= 400 && event.y < 480) {
                    hero.moveLeft();
                    hero.setMovingLeft(true);
                }
            }

            if (event.type == TouchEvent.TOUCH_UP) {

                if (hero.isMovingLeft() && !(event.x >= 0 && event.x <= 80 && event.y >= 400
                        && event.y <= 480) && !(event.x >= 0 && event.x <= 80 && event.y >= 320
                        && event.y <= 400)) {
                    hero.stopLeft();
                } else if (hero.isMovingRight() && !(event.x >= 0 && event.x <= 80 && event.y >= 400
                        && event.y <= 480) && !(event.x >= 0 && event.x <= 80 && event.y >= 320
                        && event.y <= 400)) {
                    hero.stopRight();
                }
                if (event.x >= 0 && event.x <= 80 && event.y >= 320) {
                    hero.isShooting = false;
                }
                if (event.x >= 0 && event.x <= 80 && event.y >= 400
                        && event.y <= 480) {
                    hero.setJumped(false);
                    hero.drawJump = false;
                }
            }
        }
    }


    private void defineTouch(List<TouchEvent> touchEvents) {
        int len = touchEvents.size();
        for (int i = 0; i < len; i++) {
            TouchEvent event = touchEvents.get(i);
            if (event.type == TouchEvent.TOUCH_DOWN) {
                if (event.x >= 0 && event.x <= 80 && event.y >= 400
                        && event.y <= 480) {
                    if (hero.jumps < 2) {
                        hero.drawJump = true;
                        hero.jump();
                    }
                } else if (event.x >= 0 && event.x <= 80 && event.y >= 320
                        && event.y <= 400) {
                    if (hero.isReadyToFire()) {
                        hero.shoot();
                        if (settings.getSoundVolume() > 0)
                            Assets.shoot.play(settings.getSoundVolume());
                        hero.isShooting = true;
                    }
                } else if (event.x <= 400 && !(event.x >= 0 && event.x <= 80 && event.y >= 320
                        && event.y <= 400) && !(event.x >= 0 && event.x <= 80 && event.y >= 400
                        && event.y <= 480)) {

                    hero.moveLeft();
                    hero.setMovingLeft(true);
                    hero.stopRight();


                } else if (event.x >= 400 && !(event.x >= 0 && event.x <= 80 && event.y >= 320
                        && event.y <= 400) && !(event.x >= 0 && event.x <= 80 && event.y >= 400
                        && event.y <= 480)) {

                    hero.moveRight();
                    hero.setMovingRight(true);
                    hero.stopLeft();

                }
            }

            if (event.type == TouchEvent.TOUCH_UP) {

                if (event.x <= 400 && hero.isMovingLeft() && !(event.x >= 20 && event.x <= 100 && event.y >= 380
                        && event.y <= 460)) {
                    // Stop moving left.
                    hero.stopLeft();
                } else if (event.x >= 400 && hero.isMovingRight() && !(event.x >= 20 && event.x <= 100 && event.y >= 380
                        && event.y <= 460)) {
                    // Stop moving right.
                    hero.stopRight();
                } else if (event.x <= 400 && hero.isMovingRight() && !(event.x >= 20 && event.x <= 100 && event.y >= 380
                        && event.y <= 460) && hero.isJumped()) {
                    hero.stopRight();
                }
                if (event.x >= 0 && event.x <= 80 && event.y >= 400
                        && event.y <= 480) {
                    hero.setJumped(false);
                    hero.drawJump = false;
                }
            }

        }

    }


    private boolean inBounds(TouchEvent event, int x, int y, int width,
                             int height) {
        if (event.x > x && event.x < x + width - 1 && event.y > y
                && event.y < y + height - 1)
            return true;
        else
            return false;
    }

    private void updatePaused(List<TouchEvent> touchEvents) {
        int len = touchEvents.size();
        for (int i = 0; i < len; i++) {
            TouchEvent event = touchEvents.get(i);
            if (event.type == TouchEvent.TOUCH_UP) {
                if (inBounds(event, 300, 120, 200, 55)) {
                    resume();
                }
                if (inBounds(event, 350, 310, 150, 55)) {
                    nullify();
                    hero.score = 0;
                    game.setScreen(new ChapterScreen(game));
                }
                if (inBounds(event, 70, 345, 90, 90)) {
                    if (settings.getMusicVolume() != 0) {
                        settings.setMusicVolume(0);
                        settings.setMusicX(21);
                    } else {
                        settings.setMusicVolume(0.5f);
                        settings.setMusicX(150);
                    }
                    SettingsScreen.updateVolume();
                }
                if (inBounds(event, 600, 345, 100, 90)) {
                    if (settings.getSoundVolume() != 0) {
                        settings.setSoundVolume(0);
                        settings.setSoundX(21);
                    } else {
                        settings.setSoundVolume(0.5f);
                        settings.setSoundX(150);
                        Assets.what.play(settings.getSoundVolume());
                    }
                }
            }
        }
    }

    private void updateGameOver(List<TouchEvent> touchEvents) {
        hero.bulletLimit = 5;
        hero.score = 0;
        hero.heroLive = 2;

        int len = touchEvents.size();
        for (int i = 0; i < len; i++) {
            TouchEvent event = touchEvents.get(i);
            if (event.type == TouchEvent.TOUCH_UP) {
                if (event.x > 300 && event.x < 980 && event.y > 100
                        && event.y < 500) {
                    nullify();
                    AndroidGame.displayInterstitial();
                    game.setScreen(new LevelScreen(game));
                    return;
                }
            }
        }

    }

    private void updateNextLevel(List<TouchEvent> touchEvents) {
//        if (settings.getCurrentLevel() > (settings.getCurrentChapter() * 5)) {
//            if (time == 0)
//                time = System.nanoTime();
//            if (System.nanoTime() - time > (3000 * 100000)) {
//                if (!ad) {
//                    AndroidGame.displayInterstitial();
//                    ad = true;
//                }
//            }
//        }
        int len = touchEvents.size();
        for (int i = 0; i < len; i++) {
            TouchEvent event = touchEvents.get(i);
            if (event.type == TouchEvent.TOUCH_UP) {
                if (event.x > 200 && event.x < 600 && event.y > 260
                        && event.y < 340) {
                    for (int p = 0; p < LoadingScreen.adTime.length; p++) {
                        Log.e("kopele", LoadingScreen.adTime[p] + " nomer " + p + " " + "    " + settings.getCurrentLevel());
                        if (settings.getCurrentLevel() == LoadingScreen.adTime[p]) {
                            AndroidGame.displayInterstitial();
                            break;
                        }
                    }
                    nullify();
                    settings.setCurrentLevel(settings.getCurrentLevel() + 1);
                    if (settings.getCurrentLevel() > 26)
                        settings.setCurrentLevel(26);
                    if (settings.getCurrStage() < settings.getCurrentLevel())
                        settings.setCurrStage(settings.getCurrentLevel());
                    if (settings.getCurrentLevel() > (settings.getCurrentChapter() * 5)) {
                        settings.setCurrentChapter(settings.getCurrentChapter() + 1);
                        settings.setHighScore(hero.score);
                        game.setScreen(new ChapterScreen(game));
                    }

                    if (settings.getCurrentLevel() > 25) {
//                        settings.setHighScore(hero.score);
                        hero.score = 0;
                        game.setScreen(new ScoresScreen(game));
                    } else {
                        game.setScreen(new GameScreen(game));
                    }
                    return;
                }

            }
        }
    }

    private void drawNextLevel() {

        Graphics g = game.getGraphics();
        g.drawString("Total Score: " + hero.score, 460, 165, paint2);
        g.drawString("Next Level", 400, 300, paint2);
        if (settings.getCurrentLevel() == 5 * settings.getCurrentChapter())
            if (settings.getCurrStage() < 25)
                g.drawString("You Go to Chapter: " + (settings.getCurrentChapter() + 1), 400, 400, paint);

    }

    private void drawReadyUI() {
        Graphics g = game.getGraphics();
//        g.drawARGB(155, 0, 0, 0);

        if (settings.isButtons()) {
            g.drawImage(Assets.readyScreen, 0, 0);
            g.drawImage(Assets.jumpButton, 0, 400);
            g.drawImage(Assets.fireButton, 0, 320);
            g.drawImage(Assets.leftButton, 640, 400);
            g.drawImage(Assets.rightButton, 720, 400);
        } else if (settings.isTouch()) {
            g.drawImage(Assets.fireButton, 20, 380);
            g.drawImage(Assets.jumpButton, 0, 400);
        }

        g.drawImage(Assets.heart, 30, 30);
        g.drawImage(Assets.bullet, 120, 30);
        g.drawImage(Assets.point, 200, 30);
        g.drawString("" + hero.heroLive, 80, 55, paint);
        g.drawString("" + (IHero.bulletLimit < 0 ? 0 : IHero.bulletLimit), 180, 55, paint);
        g.drawString("" + hero.score, 300, 55, paint);
        g.drawString("Tap to Start.", 400, 400, paint);

    }

    private void drawRunningUI() {
        Graphics g = game.getGraphics();
        if (settings.isButtons()) {
            g.drawImage(Assets.jumpButton, 0, 400);
            g.drawImage(Assets.leftButton, 640, 400);
            g.drawImage(Assets.rightButton, 720, 400);
            if (hero.bulletLimit == 0) {
                g.drawImage(Assets.fireButton_empty, 0, 320);
                if (hero.isShooting)
                    g.drawImage(Assets.fireButton_empty_pressed, 0, 320);
            } else {
                g.drawImage(Assets.fireButton, 0, 320);
                if (hero.isShooting)
                    g.drawImage(Assets.b2_press, 0, 320);
            }
            if (hero.drawJump)
                g.drawImage(Assets.b1_press, 0, 400);
            if (hero.isMovingLeft())
                g.drawImage(Assets.b3_press, 640, 400);
            if (hero.isMovingRight())
                g.drawImage(Assets.b4_press, 720, 400);

        } else if (settings.isTouch()) {
            g.drawImage(Assets.jumpButton, 0, 400);
            if (hero.bulletLimit == 0) {
                g.drawImage(Assets.fireButton_empty, 0, 320);
                if (hero.isShooting)
                    g.drawImage(Assets.fireButton_empty_pressed, 0, 320);
            } else {
                g.drawImage(Assets.fireButton, 0, 320);
                if (hero.isShooting)
                    g.drawImage(Assets.b2_press, 0, 320);
            }
            if (hero.drawJump)
                g.drawImage(Assets.b1_press, 0, 400);
        }

        g.drawImage(Assets.heart, 30, 30);
        g.drawImage(Assets.bullet, 120, 30);
        g.drawImage(Assets.point, 200, 30);
        g.drawString("" + hero.heroLive, 80, 55, paint);
        g.drawString("" + (IHero.bulletLimit < 0 ? 0 : IHero.bulletLimit), 180, 55, paint);
        g.drawString("" + hero.score, 300, 55, paint);
        g.drawString("lvl: " + settings.getCurrentLevel(), 750, 55, paint);
    }

    private void drawPausedUI() {

        Graphics g = game.getGraphics();
        g.drawImage(Assets.pause, 0, 0);
        g.drawString("Resume", 400, 165, paint2);
        g.drawString("Menu", 400, 360, paint2);
        if (settings.getMusicVolume() == 0) {
            g.drawImage(Assets.cover, 85, 360);
        }
        if (settings.getSoundVolume() == 0) {
            g.drawImage(Assets.cover, 610, 355);
        }
    }

    private void drawGameOverUI() {
        Graphics g = game.getGraphics();
        g.drawImage(Assets.dead, 0, 0);
        g.drawString("Tap to return.", 420, 420, paint3);
    }

    public void animate() {
        LoadingScreen.roboStay.update(5);
        LoadingScreen.robo.update(30);
        LoadingScreen.roboJump.update(5);

        LoadingScreen.bullet.update(8);
        LoadingScreen.bullets.update(8);
        LoadingScreen.coin.update(8);
        LoadingScreen.alien.update(8);
        LoadingScreen.bird.update(8);
        LoadingScreen.heart.update(3);
        LoadingScreen.jumper.update(3);

        if (settings.getCurrentChapter() == 1)
            LoadingScreen.bear.update(12);
        else if (settings.getCurrentChapter() == 2)
            LoadingScreen.ugly.update(10);
        else if (settings.getCurrentChapter() == 3)
            LoadingScreen.evil.update(10);
        else if (settings.getCurrentChapter() == 5)
            LoadingScreen.ghost.update(10);
        else
            LoadingScreen.ant.update(16);
    }

    private void nullify() {
        fliers.clear();
        jumpers.clear();
        shooters.clear();
        lines.clear();
        terrain.clear();
        followers.clear();
        coins.clear();
        ammos.clear();
        lives.clear();
        bg = null;
        follower = null;
        hero.nextLvl = false;
        System.gc();
    }

    @Override
    public void pause() {

        if (state == GameState.Running)
            state = GameState.Paused;

    }

    @Override
    public void resume() {
        if (state == GameState.Paused)
            state = GameState.Running;
//        else if (state == GameState.Paused)
//            state = GameState.Running;

    }

    @Override
    public void dispose() {
//        nullify();
//        Assets.theme1.pause();
//        Assets.theme2.pause();
//        Assets.theme3.pause();
//        Assets.theme4.pause();
//        Assets.theme5.pause();
        pause();
        if (state == GameState.Running)
            state = GameState.Paused;
    }

    @Override
    public void backButton() {
        if (state == GameState.Paused)
            state = GameState.Running;
        else if (state == GameState.Running)
            state = GameState.Paused;

    }


    public static Background getBackground1() {
        return bg;
    }

    public static IHero getHero() {
        return hero;
    }

    public static ArrayList<Object> getTerrain() {
        return terrain;
    }

    public static ArrayList<Follower> getFollower() {
        return followers;
    }

    public static ArrayList<Jumper> getJumpers() {
        return jumpers;
    }

    public static ArrayList<Shooter> getShootEvil() {
        return shooters;
    }

    public static ArrayList<Coin> getCoins() {
        return coins;
    }

    public static ArrayList<Live> getLives() {
        return lives;
    }

    public static ArrayList<Ammo> getAmmos() {
        return ammos;
    }

    public static End getEnd() {
        return end;
    }

    public static Vibrator getVibrator() {
        return v;
    }

    public static ArrayList<Flier> getFliers() {
        return fliers;
    }
}
