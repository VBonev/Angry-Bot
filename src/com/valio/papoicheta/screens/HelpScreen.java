package com.valio.papoicheta.screens;

import com.valio.framework.Game;
import com.valio.framework.Graphics;
import com.valio.framework.Input;
import com.valio.framework.Screen;
import com.valio.framework.implementation.AndroidGame;
import com.valio.papoicheta.Assets;

import java.util.List;

public class HelpScreen extends Screen {
    int page = 1;

    public HelpScreen(Game game) {
        super(game);
    }


    @Override
    public void update(float deltaTime) {
        LoadingScreen.resetButton.update(8);
        List<Input.TouchEvent> touchEvents = game.getInput().getTouchEvents();
        int len = touchEvents.size();
        for (int i = 0; i < len; i++) {
            Input.TouchEvent event = touchEvents.get(i);
            if (event.type == Input.TouchEvent.TOUCH_UP) {

                if (inBounds(event, 20, 410, 150, 60)) {
                    if (page == 1)
                        game.setScreen(new MainMenuScreen(game));
                    else
                        page--;
                }
                if (inBounds(event, 630, 410, 150, 60)) {
                    if (page < 4)
                        page++;
                    else
                        game.setScreen(new MainMenuScreen(game));
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
        if (page == 1)
            g.drawImage(Assets.help1, 0, 0);
        else if (page == 2)
            g.drawImage(Assets.help2, 0, 0);
        else if (page == 3)
            g.drawImage(Assets.help3, 0, 0);
        else if (page == 4)
            g.drawImage(Assets.help4, 0, 0);
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
