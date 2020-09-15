package com.valio.papoicheta.screens;

import android.content.Context;
import android.os.Vibrator;

import com.valio.framework.Game;
import com.valio.framework.Graphics;
import com.valio.framework.Input;
import com.valio.framework.Screen;
import com.valio.framework.implementation.AndroidGame;
import com.valio.papoicheta.Assets;

import java.util.List;

import static com.valio.papoicheta.ProjectGame.settings;

public class SettingsScreen extends Screen {
    public static Vibrator vibrator = (Vibrator) AndroidGame.context.getSystemService(Context.VIBRATOR_SERVICE);

    public SettingsScreen(Game game) {
        super(game);
    }

    @Override
    public void update(float deltaTime) {
        LoadingScreen.themes.update(1);
        List<Input.TouchEvent> touchEvents = game.getInput().getTouchEvents();
        updateVolume();
        int len = touchEvents.size();
        for (int i = 0; i < len; i++) {
            Input.TouchEvent event = touchEvents.get(i);
            if (event.type == Input.TouchEvent.TOUCH_DOWN) {
                if (inBounds(event, 50, 330, 350, 50)) {
                    settings.setButtons(true);
                    settings.setTouch(false);
                } else if (inBounds(event, 50, 392, 350, 50)) {
                    settings.setButtons(false);
                    settings.setTouch(true);
                } else if (inBounds(event, 530, 92, 250, 50)) {

                        setCurrentTheme(1);
                        Assets.theme1.play();

                } else if (inBounds(event, 530, 170, 250, 50)) {

                        setCurrentTheme(2);
                        Assets.theme2.play();

                } else if (inBounds(event, 530, 240, 250, 50)) {

                        setCurrentTheme(3);
                        Assets.theme3.play();

                } else if (inBounds(event, 530, 318, 250, 50)) {

                        setCurrentTheme(4);
                        Assets.theme4.play();

                } else if (inBounds(event, 530, 396, 250, 50)) {

                        setCurrentTheme(5);
                        Assets.theme5.play();

                } else if (inBounds(event, 430, 15, 75, 75)) {
                    if (settings.getMusicVolume() != 0) {
                        settings.setMusicVolume(0);
                        settings.setMusicX(21);
                    } else {
                        settings.setMusicVolume(0.5f);
                        settings.setMusicX(150);
                    }
                } else if (inBounds(event, 430, 90, 75, 75)) {
                    if (settings.getSoundVolume() != 0) {
                        settings.setSoundVolume(0);
                        settings.setSoundX(21);
                    } else {
                        settings.setSoundVolume(0.5f);
                        settings.setSoundX(150);
                        Assets.what.play(settings.getSoundVolume());
                    }
                } else if (inBounds(event, 430, 165, 75, 75)) {
                    if (settings.isVibrate())
                        settings.setVibrate(false);
                    else {
                        settings.setVibrate(true);
                        vibrator.vibrate(500);
                    }
                }

            } else if (event.type == Input.TouchEvent.TOUCH_DRAGGED) {
                if (inBounds(event, 0, 73, 330, 50)) {
                    settings.setMusicX(event.x);
                    if (event.x <= 40) {
                        settings.setMusicVolume(0.0f);
                    } else {
                        if (settings.getMusicX() > 21 && settings.getMusicX() <= 96) {
                            settings.setMusicVolume(0.15f);
                        } else if (settings.getMusicX() > 97 && settings.getMusicX() <= 153) {
                            settings.setMusicVolume(0.30f);
                        } else if (settings.getMusicX() > 154 && settings.getMusicX() <= 21) {
                            settings.setMusicVolume(0.45f);
                        } else if (settings.getMusicX() > 211 && settings.getMusicX() <= 267) {
                            settings.setMusicVolume(0.60f);
                        } else if (settings.getMusicX() > 268 && settings.getMusicX() <= 320) {
                            settings.setMusicVolume(1.0f);
                        }
                    }
                }
                if (inBounds(event, 0, 197, 330, 50)) {
                    settings.setSoundX(event.x);
                    if (event.x <= 40)
                        settings.setSoundVolume(0.0f);
                    else {
                        if (event.x > 21 && event.x <= 96) {
                            settings.setSoundVolume(0.15f);
                        } else if (event.x > 97 && event.x <= 153) {
                            settings.setSoundVolume(0.30f);
                        } else if (event.x > 154 && event.x <= 210) {
                            settings.setSoundVolume(0.45f);
                        } else if (event.x > 211 && event.x <= 267) {
                            settings.setSoundVolume(0.60f);
                        } else if (event.x > 268 && event.x <= 320) {
                            settings.setSoundVolume(0.75f);
                        }
                    }
                }
            }
        }

    }


    private void setCurrentTheme(int num) {
        Assets.theme1.pause();
        Assets.theme2.pause();
        Assets.theme3.pause();
        Assets.theme4.pause();
        Assets.theme5.pause();
        settings.setCurrentTheme(num);
    }

    public static void updateVolume() {
        Assets.theme1.setVolume(settings.getMusicVolume());
        Assets.theme2.setVolume(settings.getMusicVolume());
        Assets.theme3.setVolume(settings.getMusicVolume());
        Assets.theme4.setVolume(settings.getMusicVolume());
        Assets.theme5.setVolume(settings.getMusicVolume());
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
        g.drawImage(Assets.settingsScreen, 0, 0);
            g.drawImage(LoadingScreen.themes.getImage(), 0, 0);
        g.drawImage(Assets.sound_circle, (settings.getMusicX() <= 3 ? 35 : settings.getMusicX()), 76);
        g.drawImage(Assets.sound_circle, (settings.getSoundX() <= 35 ? 35 : settings.getSoundX()), 203);
        if (settings.getCurrentTheme() == 1) {
            g.drawImage(Assets.dot, 733, 102);
        } else if (settings.getCurrentTheme() == 2) {
            g.drawImage(Assets.dot, 733, 178);
        } else if (settings.getCurrentTheme() == 3) {
            g.drawImage(Assets.dot, 733, 258);
        } else if (settings.getCurrentTheme() == 4 ) {
            g.drawImage(Assets.dot, 733, 330);
        } else if (settings.getCurrentTheme() == 5 ) {
            g.drawImage(Assets.dot, 733, 409);
        }
        if (settings.getMusicVolume() == 0) {
            g.drawImage(Assets.cover, 450, 20);
        }
        if (settings.getSoundVolume() == 0) {
            g.drawImage(Assets.cover, 450, 95);
        }
        if (!settings.isVibrate()) {
            g.drawImage(Assets.cover, 450, 170);
        }
        if (settings.isButtons())
            g.drawImage(Assets.dot, 328, 340);
        else if (settings.isTouch())
            g.drawImage(Assets.dot, 328, 409);
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
