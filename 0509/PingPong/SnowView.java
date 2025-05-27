package com.example.pingpong;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.View;

import java.util.ArrayList;
import java.util.Random;

class SnowView extends View {
    private ArrayList<Snowflake> snowflakes;
    private Paint snowPaint;
    private Paint backgroundPaint;
    private Random random;
    private Thread animationThread;
    private boolean isAnimating = false;
    private int snowSize = 10;
    private int snowCount = 50;

    public SnowView(Context context) {
        super(context);
        init();
    }

    private void init() {
        snowflakes = new ArrayList<>();
        random = new Random();

        // 눈 그리기용 페인트
        snowPaint = new Paint();
        snowPaint.setColor(Color.WHITE);
        snowPaint.setAntiAlias(true);
        snowPaint.setStyle(Paint.Style.FILL);

        // 배경 그리기용 페인트
        backgroundPaint = new Paint();
        backgroundPaint.setColor(Color.parseColor("#2C3E50"));
        backgroundPaint.setAntiAlias(true);

        initializeSnowflakes();
    }

    private void initializeSnowflakes() {
        snowflakes.clear();
        for (int i = 0; i < snowCount; i++) {
            snowflakes.add(new Snowflake(
                    random.nextFloat() * getWidth(),
                    random.nextFloat() * getHeight(),
                    random.nextFloat() * 3 + 1,  // 속도
                    snowSize + random.nextFloat() * 5  // 크기 변화
            ));
        }
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        // 배경 그리기 (겨울 풍경 시뮬레이션)
        canvas.drawColor(Color.parseColor("#87CEEB")); // 하늘색

        // 간단한 언덕 그리기
        backgroundPaint.setColor(Color.parseColor("#FFFFFF"));
        canvas.drawCircle(getWidth() * 0.2f, getHeight() * 0.8f, 150, backgroundPaint);
        canvas.drawCircle(getWidth() * 0.7f, getHeight() * 0.9f, 120, backgroundPaint);
        canvas.drawCircle(getWidth() * 0.5f, getHeight() * 0.85f, 100, backgroundPaint);

        // 나무 그리기
        backgroundPaint.setColor(Color.parseColor("#8B4513"));
        canvas.drawRect(getWidth() * 0.1f - 10, getHeight() * 0.6f,
                getWidth() * 0.1f + 10, getHeight() * 0.8f, backgroundPaint);

        backgroundPaint.setColor(Color.parseColor("#228B22"));
        canvas.drawCircle(getWidth() * 0.1f, getHeight() * 0.6f, 40, backgroundPaint);

        // 눈 그리기
        for (Snowflake flake : snowflakes) {
            snowPaint.setAlpha((int)(255 * flake.opacity));
            canvas.drawCircle(flake.x, flake.y, flake.size, snowPaint);
        }
    }

    public void startAnimation() {
        if (isAnimating) return;

        isAnimating = true;
        animationThread = new Thread(new Runnable() {
            @Override
            public void run() {
                while (isAnimating) {
                    updateSnowflakes();
                    post(new Runnable() {
                        @Override
                        public void run() {
                            invalidate();
                        }
                    });

                    try {
                        Thread.sleep(50); // 20 FPS
                    } catch (InterruptedException e) {
                        break;
                    }
                }
            }
        });
        animationThread.start();
    }

    public void stopAnimation() {
        isAnimating = false;
        if (animationThread != null) {
            animationThread.interrupt();
        }
    }

    public boolean isAnimating() {
        return isAnimating;
    }

    private void updateSnowflakes() {
        for (Snowflake flake : snowflakes) {
            flake.y += flake.speed;
            flake.x += Math.sin(flake.y * 0.01f) * 0.5f; // 좌우 흔들림

            // 화면 아래로 나가면 위에서 다시 시작
            if (flake.y > getHeight() + flake.size) {
                flake.y = -flake.size;
                flake.x = random.nextFloat() * getWidth();
            }

            // 화면 옆으로 나가면 반대편에서 나타남
            if (flake.x > getWidth() + flake.size) {
                flake.x = -flake.size;
            } else if (flake.x < -flake.size) {
                flake.x = getWidth() + flake.size;
            }
        }
    }

    public void setSnowSize(int size) {
        this.snowSize = size;
        for (Snowflake flake : snowflakes) {
            flake.size = size + random.nextFloat() * 5;
        }
    }

    public void setSnowCount(int count) {
        this.snowCount = count;
        initializeSnowflakes();
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        initializeSnowflakes();
    }
}