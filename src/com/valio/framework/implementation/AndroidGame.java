package com.valio.framework.implementation;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.content.res.Configuration;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.PowerManager;
import android.os.PowerManager.WakeLock;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.InterstitialAd;
import com.valio.framework.Audio;
import com.valio.framework.FileIO;
import com.valio.framework.Game;
import com.valio.framework.Graphics;
import com.valio.framework.Input;
import com.valio.framework.Screen;
import com.valio.papoicheta.Assets;

import static com.valio.papoicheta.ProjectGame.settings;


public abstract class AndroidGame extends Activity implements Game {
    public static Activity activity;
    AndroidFastRenderView renderView;
    Graphics graphics;
    Audio audio;
    Input input;
    FileIO fileIO;
    Screen screen;
    WakeLock wakeLock;
    public static Context context;
    public static int screenRotation;
    private static InterstitialAd interstitial;
    private static Handler mHandler;
    private static Runnable displayAd;


    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context = this;
        activity = this;
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        boolean isPortrait = getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT;
//        int uiOptions = getWindow().getDecorView().getSystemUiVisibility();
//        int newUiOptions = uiOptions;
//        newUiOptions ^= View.SYSTEM_UI_FLAG_HIDE_NAVIGATION;
//        newUiOptions ^= View.SYSTEM_UI_FLAG_FULLSCREEN;
//        newUiOptions ^= View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY;
//        getWindow().getDecorView().setSystemUiVisibility(newUiOptions);

        int frameBufferWidth = isPortrait ? 480 : 800;
        int frameBufferHeight = isPortrait ? 800 : 480;
        Bitmap frameBuffer = Bitmap.createBitmap(frameBufferWidth,
                frameBufferHeight, Config.RGB_565);
        mHandler = new Handler(Looper.getMainLooper());
        displayAd = new Runnable() {
            public void run() {
                runOnUiThread(new Runnable() {
                    public void run() {
                        if (interstitial.isLoaded())  {
                            interstitial.show();
                        }
                    }
                });
            }
        };
        float scaleX = (float) frameBufferWidth
                / getWindowManager().getDefaultDisplay().getWidth();
        float scaleY = (float) frameBufferHeight
                / getWindowManager().getDefaultDisplay().getHeight();
        interstitial = new InterstitialAd(this);
        interstitial.setAdUnitId("ca-app-pub-5814210448762868/9534941233");
        interstitial.setAdListener(new AdListener() {
            @Override
            public void onAdClosed() {
                super.onAdClosed();
                loadAd();
            }
        });
        renderView = new AndroidFastRenderView(this, frameBuffer);
        graphics = new AndroidGraphics(getAssets(), frameBuffer);
        fileIO = new AndroidFileIO(this);
        audio = new AndroidAudio(this);
        input = new AndroidInput(this, renderView, scaleX, scaleY);
        screen = getInitScreen();
        setContentView(renderView);
        PowerManager powerManager = (PowerManager) getSystemService(Context.POWER_SERVICE);
        wakeLock = powerManager.newWakeLock(PowerManager.FULL_WAKE_LOCK,
                "MyGame");
    }

    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    @Override
    public void onResume() {
        super.onResume();
        loadAd();
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
            Assets.theme5.play();

        wakeLock.acquire();
        screen.resume();
        renderView.resume();
        WindowManager windowMgr = (WindowManager) this
                .getSystemService(Activity.WINDOW_SERVICE);
        screenRotation = windowMgr.getDefaultDisplay().getRotation();
    }
    @Override
    public void onPause() {
        super.onPause();
        Assets.theme1.pause();
        Assets.theme2.pause();
        Assets.theme3.pause();
        Assets.theme4.pause();
        Assets.theme5.pause();
        Assets.endTheme.stop();
        wakeLock.release();
        renderView.pause();
        screen.pause();
        if (isFinishing())
            screen.dispose();
    }

    static void loadAd() {
        AdRequest adRequest = new AdRequest.Builder()
                .build();
        interstitial.loadAd(adRequest);
    }
    public static void displayInterstitial() {
        mHandler.postDelayed(displayAd, 1);
    }
    @Override
    protected void onStop() {
        super.onStop();
        Assets.endTheme.stop();
//        wakeLock.release();
        renderView.pause();
        screen.pause();

        if (isFinishing())
            screen.dispose();
    }

    @Override
    public Input getInput() {
        return input;
    }

    @Override
    public FileIO getFileIO() {
        return fileIO;
    }

    @Override
    public Graphics getGraphics() {
        return graphics;
    }

    @Override
    public Audio getAudio() {
        return audio;
    }

    @Override
    public void setScreen(Screen screen) {
        if (screen == null)
            throw new IllegalArgumentException("Screen must not be null");

        this.screen.pause();
        this.screen.dispose();
        screen.resume();
        screen.update(0);
        this.screen = screen;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
//        wakeLock.release();
        renderView.pause();
        screen.pause();

        if (isFinishing())
            screen.dispose();
    }

    @Override
    public Screen getCurrentScreen() {

        return screen;
    }
}
