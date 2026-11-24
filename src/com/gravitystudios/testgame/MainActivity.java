package com.gravitystudios.testgame;

import android.app.Activity;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(new GameView(this));
    }

    static class GameView extends View {
        int targetHp = 100;
        int weaponDamage = 20;
        int score = 0;

        Paint bgPaint = new Paint();
        Paint targetPaint = new Paint();
        Paint textPaint = new Paint();
        RectF targetBox = new RectF(200, 350, 500, 650);

        public GameView(Context context) {
            super(context);
            bgPaint.setColor(Color.rgb(20, 24, 38));
            targetPaint.setColor(Color.rgb(220, 60, 60));
            textPaint.setColor(Color.WHITE);
            textPaint.setTextSize(48f);
        }

        @Override
        protected void onDraw(Canvas canvas) {
            super.onDraw(canvas);
            canvas.drawRect(0, 0, getWidth(), getHeight(), bgPaint);

            float cx = getWidth() / 2f;
            float cy = getHeight() / 2f;
            targetBox.set(cx - 140, cy - 140, cx + 140, cy + 140);

            canvas.drawRect(targetBox, targetPaint);
            canvas.drawText("HP: " + targetHp + "  |  DMG: " + weaponDamage + "  |  SCORE: " + score, 40, 100, textPaint);
            canvas.drawText("Tap the red target!", 40, 170, textPaint);
        }

        @Override
        public boolean onTouchEvent(MotionEvent event) {
            if (event.getAction() == MotionEvent.ACTION_DOWN) {
                float x = event.getX();
                float y = event.getY();

                if (targetBox.contains(x, y)) {
                    targetHp -= weaponDamage;
                    if (targetHp <= 0) {
                        score += 1;
                        targetHp = 100;
                    }
                    invalidate();
                }
            }
            return true;
        }
    }
}
