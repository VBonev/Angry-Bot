package com.valio.papoicheta;

import android.graphics.Typeface;
import android.util.Log;

import com.valio.framework.Screen;
import com.valio.framework.implementation.AndroidGame;
import com.valio.papoicheta.screens.SplashLoadingScreen;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class ProjectGame extends AndroidGame {

    public static String map1, map2, map3, map4, map5, map6, map7, map8, map9, map10, map11, map12, map13, map14, map15, map16, map17, map18, map19, map20, map21, map22, map23, map24, map25;
    boolean firstCreation = true;
    public static Settings settings;
    public static Typeface tf;

    public static void load(ProjectGame sampleGame) {
        Assets.theme1 = sampleGame.getAudio().createMusic("audio/music/beginning.mp3");
        Assets.theme1.setLooping(true);
        Assets.theme1.setVolume(settings.getMusicVolume());

        Assets.theme2 = sampleGame.getAudio().createMusic("audio/music/around_world.mp3");
        Assets.theme2.setLooping(true);
        Assets.theme2.setVolume(settings.getMusicVolume());

        Assets.theme3 = sampleGame.getAudio().createMusic("audio/music/dark_forest.mp3");
        Assets.theme3.setLooping(true);
        Assets.theme3.setVolume(settings.getMusicVolume());

        Assets.theme4 = sampleGame.getAudio().createMusic("audio/music/space.mp3");
        Assets.theme4.setLooping(true);
        Assets.theme4.setVolume(settings.getMusicVolume());

        Assets.theme5 = sampleGame.getAudio().createMusic("audio/music/wonderlands.mp3");
        Assets.theme5.setLooping(true);
        Assets.theme5.setVolume(settings.getMusicVolume());

        Assets.endTheme = sampleGame.getAudio().createMusic("audio/music/endSong.mp3");
        Assets.endTheme.setLooping(true);
        Assets.endTheme.setVolume(settings.getMusicVolume());
    }
    @Override
    public Screen getInitScreen() {

        if (firstCreation) {
            settings = new Settings(getFilesDir().toString() + "/settings.ini");
            load(this);
            firstCreation = false;
        }
        tf = Typeface.createFromAsset(getAssets(),
                "Animated.ttf");
        map1 = convertStreamToString(getResources().openRawResource(R.raw.map1));
        map2 = convertStreamToString(getResources().openRawResource(R.raw.map2));
        map3 = convertStreamToString(getResources().openRawResource(R.raw.map3));
        map4 = convertStreamToString(getResources().openRawResource(R.raw.map4));
        map5 = convertStreamToString(getResources().openRawResource(R.raw.map5));
        map6 = convertStreamToString(getResources().openRawResource(R.raw.map6));
        map7 = convertStreamToString(getResources().openRawResource(R.raw.map7));
        map8 = convertStreamToString(getResources().openRawResource(R.raw.map8));
        map9 = convertStreamToString(getResources().openRawResource(R.raw.map9));
        map10 = convertStreamToString(getResources().openRawResource(R.raw.map10));
        map11 = convertStreamToString(getResources().openRawResource(R.raw.map11));
        map12 = convertStreamToString(getResources().openRawResource(R.raw.map12));
        map13 = convertStreamToString(getResources().openRawResource(R.raw.map13));
        map14 = convertStreamToString(getResources().openRawResource(R.raw.map14));
        map15 = convertStreamToString(getResources().openRawResource(R.raw.map15));
        map16 = convertStreamToString(getResources().openRawResource(R.raw.map16));
        map17 = convertStreamToString(getResources().openRawResource(R.raw.map17));
        map18 = convertStreamToString(getResources().openRawResource(R.raw.map18));
        map19 = convertStreamToString(getResources().openRawResource(R.raw.map19));
        map20 = convertStreamToString(getResources().openRawResource(R.raw.map20));
        map21 = convertStreamToString(getResources().openRawResource(R.raw.map21));
        map22 = convertStreamToString(getResources().openRawResource(R.raw.map22));
        map23 = convertStreamToString(getResources().openRawResource(R.raw.map23));
        map24 = convertStreamToString(getResources().openRawResource(R.raw.map24));
        map25 = convertStreamToString(getResources().openRawResource(R.raw.map25));

        return new SplashLoadingScreen(this);
    }

    public void onBackPressed() {
        getCurrentScreen().backButton();
    }

    private static String convertStreamToString(InputStream is) {

        BufferedReader reader = new BufferedReader(new InputStreamReader(is));
        StringBuilder sb = new StringBuilder();

        String line;
        try {
            while ((line = reader.readLine()) != null) {
                sb.append((line + "\n"));
            }
        } catch (IOException e) {
            Log.w("LOG", e.getMessage());
        } finally {
            try {
                is.close();
            } catch (IOException e) {
                Log.w("LOG", e.getMessage());
            }
        }

        return sb.toString();
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();


    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        settings.SaveSettings();
    }
}
