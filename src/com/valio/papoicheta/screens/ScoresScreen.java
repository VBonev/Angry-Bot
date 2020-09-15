package com.valio.papoicheta.screens;

import android.graphics.Color;
import android.graphics.Paint;

import com.valio.framework.Game;
import com.valio.framework.Graphics;
import com.valio.framework.Input;
import com.valio.framework.Screen;
import com.valio.framework.implementation.AndroidGame;
import com.valio.papoicheta.Assets;
import com.valio.papoicheta.ProjectGame;

import java.util.List;

import static com.valio.papoicheta.ProjectGame.settings;

public class ScoresScreen extends Screen {
    private Paint paint, paint2;

    public ScoresScreen(Game game) {
        super(game);
        Assets.endTheme.play();
        paint = new Paint();
        paint.setTypeface(ProjectGame.tf);
        paint.setShadowLayer(
                4f,   //float radius
                4f,  //float dx
                4f,  //float dy
                0xFF000000 //int color
        );
        paint.setTextSize(60);
        paint.setTextAlign(Paint.Align.CENTER);
        paint.setAntiAlias(true);
        paint.setColor(Color.YELLOW);

        paint2 = new Paint();
        paint2.setTypeface(ProjectGame.tf);
        paint2.setShadowLayer(
                4f,   //float radius
                4f,  //float dx
                4f,  //float dy
                0xFF000000 //int color
        );
        paint2.setTextSize(80);
        paint2.setTextAlign(Paint.Align.CENTER);
        paint2.setAntiAlias(true);
        paint2.setColor(Color.RED);
    }


    @Override
    public void update(float deltaTime) {
        stopMusic();
        LoadingScreen.resetButton.update(8);
        List<Input.TouchEvent> touchEvents = game.getInput().getTouchEvents();
        int len = touchEvents.size();
        for (int i = 0; i < len; i++) {
            Input.TouchEvent event = touchEvents.get(i);
            if (event.type == Input.TouchEvent.TOUCH_UP) {
                if (inBounds(event, 650, 410, 150, 60))
                    AndroidGame.displayInterstitial();
                    game.setScreen(new MainMenuScreen(game));
                if (inBounds(event, 10, 10, 150, 60)) {
                    if (!settings.isScoreEmpty()) {
                        settings.resetScore();
                        game.setScreen(new ScoresScreen(game));
                    }
                }
            }
        }
    }

    private boolean inBounds(Input.TouchEvent event, int x, int y, int width,
                             int height) {
        if (event.x > x && event.x < x + width - 1 && event.y > y
                && event.y < y + height - 1)
            return true;
        else
            return false;
    }

    @Override
    public void paint(float deltaTime) {
        Graphics g = game.getGraphics();
        g.drawImage(Assets.endScreen, 0, 0);
        g.drawString("1: " + (settings.getHighScores(0) == 0 ? "-----" : settings.getHighScores(0)), 400, 180, paint2);
        g.drawString("2: " + (settings.getHighScores(1) == 0 ? "-------" : settings.getHighScores(1)), 400, 240, paint);
        g.drawString("3: " + (settings.getHighScores(2) == 0 ? "-------" : settings.getHighScores(2)), 400, 300, paint);
        g.drawString("4: " + (settings.getHighScores(3) == 0 ? "-------" : settings.getHighScores(3)), 400, 360, paint);
        g.drawString("5: " + (settings.getHighScores(4) == 0 ? "-------" : settings.getHighScores(4)), 400, 420, paint);
        if (!settings.isScoreEmpty())
            g.drawImage(LoadingScreen.resetButton.getImage(), 0, 0);
    }

    private void stopMusic() {
        Assets.theme1.pause();
        Assets.theme2.pause();
        Assets.theme3.pause();
        Assets.theme4.pause();
        Assets.theme5.pause();
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
        game.setScreen(new MainMenuScreen(game));
        AndroidGame.displayInterstitial();

    }

}
