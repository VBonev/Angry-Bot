package com.valio.papoicheta;

import android.util.Log;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.StringTokenizer;

/**
 * Created by inologica12 on 4/8/2014.
 */
public class Settings {

    private int currentLevel;
    private int currentChapter;
    private int currentHero;
    private int[] highScores;
    private boolean lockLevels;
    private float musicVolume;
    private float soundVolume;
    private int currentTheme;
    private int currStage;
    String file_name;
    private int musicX;
    private int soundX;
    private boolean buttons;
    private boolean touch;
    private boolean vibrate;


    Settings(String _file_name) {
        file_name = _file_name;
        vibrate = false;
        buttons = true;
        touch = false;
        soundX = 220;
        musicX = 220;
        currentLevel = 1;
        currStage = 1;
        currentChapter = 1;
        currentHero = 0;
        highScores = new int[]{0, 0, 0, 0, 0};
        currentTheme = 1;
        musicVolume = 0.50f;
        soundVolume = 0.50f;
        lockLevels = true;
        LoadSettings();
    }

    protected boolean LoadSettings() {
        String fileContent = "";
        String NL = System.getProperty("line.separator");

        try {
            FileInputStream fIn = new FileInputStream(file_name);
            InputStreamReader isr = new InputStreamReader(fIn);
            BufferedReader buffreader = new BufferedReader(isr);

            String readString = buffreader.readLine();
            while (readString != null) {
                fileContent = fileContent + readString + NL;
                readString = buffreader.readLine();
            }

            isr.close();
            fIn.close();
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        HashMap map = new HashMap();

        if (fileContent.length() > 0) {
            String foo = fileContent.substring(0, fileContent.length() - 1);
            StringTokenizer st = new StringTokenizer(foo, NL);
            while (st.hasMoreTokens()) {
                try {
                    String thisToken = st.nextToken();
                    StringTokenizer st2 = new StringTokenizer(thisToken, "=");
                    map.put(st2.nextToken(), st2.nextToken());
                } catch (Exception e) {
                    Log.i("INOREADER_SETTINGS", "Exception: " + e.toString());
                }
            }
        }
        for (int i = 0; i < 5; i++) {
            if (map.get("highscore" + i) != null) {
                highScores[i] = Integer.parseInt(map.get("highscore" + i).toString());

            }
        }
        if (map.get("vibrate") != null) {
            vibrate = map.get("vibrate").toString().equals("true") ? true : false;
        }

        if (map.get("buttons") != null) {
            buttons = map.get("buttons").toString().equals("true") ? true : false;
        }
        if (map.get("touch") != null) {
            touch = map.get("touch").toString().equals("true") ? true : false;
        }
        if (map.get("soundX") != null) {
            soundX = Integer.parseInt(map.get("soundX").toString());
        }
        if (map.get("musicX") != null) {
            musicX = Integer.parseInt(map.get("musicX").toString());
        }
        if (map.get("currentLevel") != null) {
            currentLevel = Integer.parseInt(map.get("currentLevel").toString());
        }
        if (map.get("currStage") != null) {
            currStage = Integer.parseInt(map.get("currStage").toString());
        }
        if (map.get("currentChapter") != null) {
            currentChapter = Integer.parseInt(map.get("currentChapter").toString());
        }

        if (map.get("currentHero") != null) {
            currentHero = Integer.parseInt(map.get("currentHero").toString());
        }
        if (map.get("currentTheme") != null) {
            currentTheme = Integer.parseInt(map.get("currentTheme").toString());
        }

        if (map.get("lockLevels") != null) {
            lockLevels = map.get("lockLevels").toString().equals("true") ? true : false;
        }
        if (map.get("musicVolume") != null) {
            musicVolume = Float.parseFloat(map.get("musicVolume").toString());
        }
        if (map.get("soundVolume") != null) {
            soundVolume = Float.parseFloat(map.get("soundVolume").toString());
        }

        map.clear();

        return true;

    }


    public boolean SaveSettings() {
        String NL = System.getProperty("line.separator");
        FileWriter fWriter;

        try {
            fWriter = new FileWriter(file_name, false); // false = not append
            for (int i = 0; i < 5; i++) {

                fWriter.write(new String("highscore" + i + "="));
                fWriter.write((Integer.toString(highScores[i])));

                fWriter.write(NL);
            }

            fWriter.write(new String("lockLevels="));
            fWriter.write(new String(lockLevels ? "true" : "false"));
            fWriter.write(NL);
            fWriter.write(new String("vibrate="));
            fWriter.write(new String(vibrate ? "true" : "false"));
            fWriter.write(NL);
            fWriter.write(new String("buttons="));
            fWriter.write(new String(buttons ? "true" : "false"));
            fWriter.write(NL);
            fWriter.write(new String("touch="));
            fWriter.write(new String(touch ? "true" : "false"));
            fWriter.write(NL);

            fWriter.write(new String("soundX="));
            fWriter.write(Integer.toString(soundX));
            fWriter.write(NL);
            fWriter.write(new String("musicX="));
            fWriter.write(Integer.toString(musicX));
            fWriter.write(NL);
            fWriter.write(new String("currentHero="));
            fWriter.write(Integer.toString(currentHero));
            fWriter.write(NL);
            fWriter.write(new String("currentTheme="));
            fWriter.write(Integer.toString(currentTheme));
            fWriter.write(NL);
            fWriter.write(new String("musicVolume="));
            fWriter.write(Float.toString(musicVolume));
            fWriter.write(NL);
            fWriter.write(new String("soundVolume="));
            fWriter.write(Float.toString(soundVolume));
            fWriter.write(NL);
            fWriter.write(new String("currentLevel="));
            fWriter.write(Integer.toString(currentLevel));
            fWriter.write(NL);
            fWriter.write(new String("currStage="));
            fWriter.write(Integer.toString(currStage));
            fWriter.write(NL);
            fWriter.write(new String("currentChapter="));
            fWriter.write(Integer.toString(currentChapter));
            fWriter.write(NL);
            fWriter.close();
        } catch (IOException e) {
            // log error
            e.printStackTrace();
            return false;
        }
        return true;
    }
    public void setHighScore(int score) {
        for (int i = 0; i < 5; i++) {
            if (highScores[i] < score) {
                for (int j = 4; j > i; j--)
                    highScores[j] = highScores[j - 1];
                highScores[i] = score;

                break;
            }
        }
        SaveSettings();
    }

    public void resetScore() {
        for (int i = 0; i < 5; i++)
            highScores[i] = 0;
        SaveSettings();
    }

    public boolean isScoreEmpty() {

        if (highScores[0] == 0)
            return true;
        else
            return false;
    }

    public int getCurrentLevel() {
        return currentLevel;
    }

    public void setCurrentLevel(int currentLevel) {
        this.currentLevel = currentLevel;
        SaveSettings();
    }

    public int getCurrStage() {
        return currStage;
    }

    public void setCurrStage(int currStage) {
        this.currStage = currStage;
        SaveSettings();
    }


    public int getCurrentChapter() {
        return currentChapter;
    }

    public void setCurrentChapter(int currentChapter) {
        this.currentChapter = currentChapter;
        SaveSettings();
    }

    public int getCurrentHero() {
        return currentHero;
    }

    public void setCurrentHero(int currentHero) {
        this.currentHero = currentHero;
        SaveSettings();
    }

    public int getHighScores(int i) {
        return highScores[i];
    }


    public float getMusicVolume() {
        return musicVolume;
    }

    public void setMusicVolume(float musicVolume) {
        this.musicVolume = musicVolume;
        SaveSettings();
    }

    public float getSoundVolume() {
        return soundVolume;
    }

    public void setSoundVolume(float soundVolume) {
        this.soundVolume = soundVolume;
        SaveSettings();
    }

    public int getCurrentTheme() {
        return currentTheme;
    }

    public void setCurrentTheme(int currentTheme) {
        this.currentTheme = currentTheme;
        SaveSettings();
    }

    public int getSoundX() {
        return soundX;
    }

    public void setSoundX(int soundX) {
        this.soundX = soundX;
        SaveSettings();
    }


    public int getMusicX() {
        return musicX;
    }

    public void setMusicX(int musicX) {
        this.musicX = musicX;
        SaveSettings();
    }

    public boolean isButtons() {
        return buttons;
    }

    public void setButtons(boolean buttons) {
        this.buttons = buttons;
        SaveSettings();
    }

    public boolean isTouch() {
        return touch;
    }

    public void setTouch(boolean touch) {
        this.touch = touch;
        SaveSettings();
    }


    public boolean isVibrate() {
        return vibrate;
    }

    public void setVibrate(boolean vibrate) {
        this.vibrate = vibrate;
        SaveSettings();
    }

    public boolean isLockLevels() {
        return lockLevels;
    }

    public void setLockLevels(boolean lockLevels) {
        this.lockLevels = lockLevels;
        SaveSettings();
    }

}

