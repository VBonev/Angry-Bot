package com.valio.papoicheta.screens;

import com.valio.framework.Game;
import com.valio.framework.Graphics;
import com.valio.framework.Graphics.ImageFormat;
import com.valio.framework.Screen;
import com.valio.papoicheta.Assets;

public class SplashLoadingScreen extends Screen {

    public SplashLoadingScreen(Game game) {
        super(game);
    }

    @Override
    public void update(float deltaTime) {
        Graphics g = game.getGraphics();
        Assets.splash = g.newImage("menu/Splash.png", ImageFormat.RGB565);
        g.drawImage(Assets.splash, 0, 0);
        game.setScreen(new LoadingScreen(game));

    }

    @Override
    public void paint(float deltaTime) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void dispose() {

    }

    @Override
    public void backButton() {

    }
}