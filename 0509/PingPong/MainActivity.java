package com.example.pingpong;

import android.app.Activity;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.SeekBar;
import android.widget.TextView;
import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends Activity {
    private SnowView snowView;
    private SeekBar sizeSeekBar;
    private SeekBar countSeekBar;
    private TextView sizeLabel;
    private TextView countLabel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        LinearLayout mainLayout = new LinearLayout(this);
        mainLayout.setOrientation(LinearLayout.VERTICAL);
        mainLayout.setPadding(20, 20, 20, 20);

        // 그라데이션 배경 설정
        GradientDrawable gradient = new GradientDrawable();
        gradient.setColors(new int[]{Color.parseColor("#667eea"), Color.parseColor("#764ba2")});
        gradient.setOrientation(GradientDrawable.Orientation.TOP_BOTTOM);
        mainLayout.setBackground(gradient);

        // 제목
        TextView title = new TextView(this);
        title.setText("PingPong - 눈 내리는 앱");
        title.setTextSize(24);
        title.setTextColor(Color.WHITE);
        title.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
        title.setPadding(0, 0, 0, 20);
        mainLayout.addView(title);

        // 눈 뷰
        snowView = new SnowView(this);
        LinearLayout.LayoutParams snowParams = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT, 0, 1.0f);
        snowView.setLayoutParams(snowParams);
        mainLayout.addView(snowView);

        // 컨트롤 패널
        LinearLayout controlPanel = new LinearLayout(this);
        controlPanel.setOrientation(LinearLayout.VERTICAL);
        controlPanel.setPadding(0, 20, 0, 0);

        // 눈 크기 조절
        sizeLabel = new TextView(this);
        sizeLabel.setText("눈 크기: 10");
        sizeLabel.setTextColor(Color.WHITE);
        sizeLabel.setTextSize(16);
        controlPanel.addView(sizeLabel);

        sizeSeekBar = new SeekBar(this);
        sizeSeekBar.setMax(30);
        sizeSeekBar.setProgress(10);
        sizeSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                int size = Math.max(5, progress);
                sizeLabel.setText("눈 크기: " + size);
                snowView.setSnowSize(size);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {}

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {}
        });
        controlPanel.addView(sizeSeekBar);

        // 눈 개수 조절
        countLabel = new TextView(this);
        countLabel.setText("눈 개수: 50");
        countLabel.setTextColor(Color.WHITE);
        countLabel.setTextSize(16);
        countLabel.setPadding(0, 20, 0, 0);
        controlPanel.addView(countLabel);

        countSeekBar = new SeekBar(this);
        countSeekBar.setMax(200);
        countSeekBar.setProgress(50);
        countSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                int count = Math.max(10, progress);
                countLabel.setText("눈 개수: " + count);
                snowView.setSnowCount(count);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {}

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {}
        });
        controlPanel.addView(countSeekBar);

        // 시작/정지 버튼
        Button toggleButton = new Button(this);
        toggleButton.setText("일시정지");
        toggleButton.setTextColor(Color.WHITE);
        toggleButton.setBackgroundColor(Color.parseColor("#4CAF50"));
        toggleButton.setPadding(20, 15, 20, 15);
        LinearLayout.LayoutParams buttonParams = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        buttonParams.setMargins(0, 20, 0, 0);
        toggleButton.setLayoutParams(buttonParams);

        toggleButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (snowView.isAnimating()) {
                    snowView.stopAnimation();
                    toggleButton.setText("시작");
                    toggleButton.setBackgroundColor(Color.parseColor("#F44336"));
                } else {
                    snowView.startAnimation();
                    toggleButton.setText("일시정지");
                    toggleButton.setBackgroundColor(Color.parseColor("#4CAF50"));
                }
            }
        });
        controlPanel.addView(toggleButton);

        mainLayout.addView(controlPanel);
        setContentView(mainLayout);
    }

    @Override
    protected void onResume() {
        super.onResume();
        snowView.startAnimation();
    }

    @Override
    protected void onPause() {
        super.onPause();
        snowView.stopAnimation();
    }
}