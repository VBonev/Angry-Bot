package com.valio.papoicheta.screens;


import java.util.List;

import com.valio.framework.Game;
import com.valio.framework.Graphics;
import com.valio.framework.Input.TouchEvent;
import com.valio.framework.Screen;
import com.valio.papoicheta.Assets;

import static com.valio.papoicheta.ProjectGame.settings;

public class LevelScreen extends Screen {

    public LevelScreen(Game game) {
        super(game);

    }

    @Override
    public void update(float deltaTime) {
        List<TouchEvent> touchEvents = game.getInput().getTouchEvents();

        int len = touchEvents.size();
        for (int i = 0; i < len; i++) {
            TouchEvent event = touchEvents.get(i);
            if (event.type == TouchEvent.TOUCH_UP) {
                if (inBounds(event, 50, 130, 120, 120)) {
                    if (settings.getCurrStage() >= setLevel(1)) {
                        settings.setCurrentLevel(setLevel(1));
                        if (settings.getCurrStage() < settings.getCurrentLevel())
                            settings.setCurrStage(settings.getCurrentLevel());
                        game.setScreen(new GameScreen(game));
                    }
                }
                if (inBounds(event, 390, 130, 120, 120)) {
                    if (settings.getCurrStage() >= setLevel(2)) {
                        settings.setCurrentLevel(setLevel(2));
                        if (settings.getCurrStage() < settings.getCurrentLevel())
                            settings.setCurrStage(settings.getCurrentLevel());
                        game.setScreen(new GameScreen(game));

                    }
                }
                if (inBounds(event, 670, 130, 120, 120)) {
                    if (settings.getCurrStage() >= setLevel(3)) {
                        settings.setCurrentLevel(setLevel(3));
                        if (settings.getCurrStage() < settings.getCurrentLevel())
                            settings.setCurrStage(settings.getCurrentLevel());
                        game.setScreen(new GameScreen(game));
                    }
                }
                if (inBounds(event, 190, 350, 120, 120)) {

                    if (settings.getCurrStage() >= setLevel(4)) {
                        settings.setCurrentLevel(setLevel(4));
                        if (settings.getCurrStage() < settings.getCurrentLevel())
                            settings.setCurrStage(settings.getCurrentLevel());
                        game.setScreen(new GameScreen(game));
                    }
                }
                if (inBounds(event, 550, 350, 120, 120)) {

                    if (settings.getCurrStage() >= setLevel(5)) {
                        settings.setCurrentLevel(setLevel(5));
                        if (settings.getCurrStage() < settings.getCurrentLevel())
                            settings.setCurrStage(settings.getCurrentLevel());
                        game.setScreen(new GameScreen(game));
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


    private int setLevel(int lvl) {
        lvl = (settings.getCurrentChapter() - 1) * 5 + lvl;
        return lvl;
    }

    @Override
    public void paint(float deltaTime) {
        Graphics g = game.getGraphics();
        switch (settings.getCurrentChapter()) {
            case 1:
                g.drawImage(Assets.story1, 0, 0);
                break;
            case 2:
                g.drawImage(Assets.story2, 0, 0);
                break;
            case 3:
                g.drawImage(Assets.story3, 0, 0);
                break;
            case 4:
                g.drawImage(Assets.story4, 0, 0);
                break;
            case 5:
                g.drawImage(Assets.story5, 0, 0);
                break;
            default:
                break;
        }
        if (settings.getCurrStage() > setLevel(1))
            g.drawImage(Assets.lock2, 0, 0);
        if (settings.getCurrStage() > setLevel(2))
            g.drawImage(Assets.lock3, 0, 0);
        if (settings.getCurrStage() > setLevel(3))
            g.drawImage(Assets.lock4, 0, 0);
        if (settings.getCurrStage() > setLevel(4))
            g.drawImage(Assets.lock5, 0, 0);
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
        game.setScreen(new ChapterScreen(game));

    }

}
