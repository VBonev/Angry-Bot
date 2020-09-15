package com.valio.papoicheta.screens;

import java.util.List;

import com.valio.framework.Game;
import com.valio.framework.Graphics;
import com.valio.framework.Input.TouchEvent;
import com.valio.framework.Screen;
import com.valio.papoicheta.Assets;

import static com.valio.papoicheta.ProjectGame.settings;

public class ChapterScreen extends Screen {

    public ChapterScreen(Game game) {
        super(game);
    }

    @Override
    public void update(float deltaTime) {
        if (settings.getCurrStage() > 25)
            LoadingScreen.resetButton.update(8);
        List<TouchEvent> touchEvents = game.getInput().getTouchEvents();
        int len = touchEvents.size();
        for (int i = 0; i < len; i++) {
            TouchEvent event = touchEvents.get(i);
            if (event.type == TouchEvent.TOUCH_UP) {
                if (inBounds(event, 480, 40, 120, 120)) {
                    settings.setCurrentChapter(1);
                    game.setScreen(new LevelScreen(game));
                }
                if (inBounds(event, 320, 190, 120, 120)) {
                    if (settings.getCurrStage() > 5) {
                        settings.setCurrentChapter(2);
                        game.setScreen(new LevelScreen(game));
                    }
                }
                if (inBounds(event, 580, 280, 120, 120)) {
                    if (settings.getCurrStage() > 10) {
                        settings.setCurrentChapter(3);
                        game.setScreen(new LevelScreen(game));
                    }

                }
                if (inBounds(event, 40, 90, 120, 120)) {
//                    if (!settings.isLockLevels())
                    if (settings.getCurrStage() > 15) {
                        settings.setCurrentChapter(4);
                        game.setScreen(new LevelScreen(game));
                    }
                }
                if (inBounds(event, 20, 330, 120, 120)) {
//                    if (!settings.isLockLevels()) {
                    if (settings.getCurrStage() > 20) {
                        settings.setCurrentChapter(5);
                        game.setScreen(new LevelScreen(game));
                    }
                }
                if (inBounds(event, 650, 410, 150, 60)) {
                    game.setScreen(new MainMenuScreen(game));
                }
                if (inBounds(event, 10, 10, 150, 60)) {
                    if (settings.getCurrStage() > 25) {
                        settings.setCurrStage(1);
                        settings.setCurrentLevel(1);
                        settings.setCurrentChapter(1);
                        game.setScreen(new ChapterScreen(game));
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


    @Override
    public void paint(float deltaTime) {
        Graphics g = game.getGraphics();

        g.drawImage(Assets.lvl, 0, 0);
//        if (settings.getCurrStage() <= 5)
        g.drawImage(Assets.lvl1, 0, 0);
        if (settings.getCurrStage() > 5)
            g.drawImage(Assets.lvl2, 0, 0);
        if (settings.getCurrStage() > 10)
            g.drawImage(Assets.lvl3, 0, 0);
        if (settings.getCurrStage() > 15)
            g.drawImage(Assets.lvl4, 0, 0);
        if (settings.getCurrStage() > 20)
            g.drawImage(Assets.lvl5, 0, 0);
        if (settings.getCurrStage() > 25) {
            g.drawImage(LoadingScreen.resetButton.getImage(), 0, 0);
        }


    }

    @Override
    public void pause() {
        // TODO Auto-generated method stub

    }

    @Override
    public void resume() {


    }

    @Override
    public void dispose() {
        // TODO Auto-generated method stub

    }

    @Override
    public void backButton() {
        game.setScreen(new MainMenuScreen(game));

    }

}
