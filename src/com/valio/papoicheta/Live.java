package com.valio.papoicheta;

import android.graphics.Rect;

import com.valio.papoicheta.screens.GameScreen;

public class Live {
	private int centerX, centerY,speedX;
	private Background bg = GameScreen.getBackground1();
	public Rect r = new Rect(0, 0, 0, 0);

	public Live(int x, int y) {
		centerX = x * 40;
		centerY = y * 40;
	}

	// Behavioral Methods
	public void update() {
		centerX += speedX;
		speedX = bg.getSpeedX() * 5;
        if(centerX<=800)
        r.set(centerX, centerY, centerX + 30, centerY + 30);
	}
	public int getCenterX() {
		return centerX;
	}
	public int getCenterY() {
		return centerY;
	}
}