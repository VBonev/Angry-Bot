package com.valio.papoicheta.screens;

import com.valio.framework.Game;
import com.valio.framework.Graphics;
import com.valio.framework.Input.TouchEvent;
import com.valio.framework.Screen;
import com.valio.papoicheta.Assets;

import java.util.List;

import static com.valio.papoicheta.ProjectGame.settings;

public class MainMenuScreen extends Screen {
    boolean exit_confirm = false;

    public MainMenuScreen(Game game) {
        super(game);
        playCurrentTheme();
    }

    @Override
    public void update(float deltaTime) {
        List<TouchEvent> touchEvents = game.getInput().getTouchEvents();
        int len = touchEvents.size();
        for (int i = 0; i < len; i++) {
            if (len > 0) {
                 TouchEvent event = touchEvents.get(i);
                if (event.type == TouchEvent.TOUCH_UP) {
                    if (!exit_confirm) {
                        if (inBounds(event, 330, 130, 150, 150)) {
                            settings.setCurrentHero(1);
                            game.setScreen(new ChapterScreen(game));
                        }
                        if (inBounds(event, 180, 370, 80, 80)) {
                            game.setScreen(new ScoresScreen(game));
                        }
                        if (inBounds(event, 100, 190, 80, 80)) {
                            game.setScreen(new HelpScreen(game));
                        }
                        if (inBounds(event, 30, 270, 100, 100)) {
                            game.setScreen(new SettingsScreen(game));
                        }
                        if (inBounds(event, 690, 380, 90, 90)) {
                            exit_confirm = true;
                        }
 //                        if (inBounds(event, 750, 0, 50, 40)) {
//                            settings.setCurrStage(26);
//                            game.setScreen(new ChapterScreen(game));
//                        }
                    }else{
                        if (inBounds(event, 230, 280, 120, 120)) {
                            android.os.Process.killProcess(android.os.Process.myPid());
                        }
                        if (inBounds(event, 490, 280, 120, 120)) {
                            exit_confirm = false;
                        }
                    }
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

    private void playCurrentTheme() {
        Assets.endTheme.stop();
        if (settings.getCurrentTheme() == 1)
            Assets.theme1.play();
        else if (settings.getCurrentTheme() == 2)
            Assets.theme2.play();
        else if (settings.getCurrentTheme() == 3)
            Assets.theme3.play();
        else if (settings.getCurrentTheme() == 4)
            Assets.theme4.play();
        else if (settings.getCurrentTheme() == 5)
            Assets.theme5.play();
    }

    @Override
    public void paint(float deltaTime) {
        Graphics g = game.getGraphics();
        g.drawImage(Assets.menu, 0, 0);
        if (exit_confirm)
            g.drawImage(Assets.exit, 0, 0);
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
        exit_confirm = true;
    }
}