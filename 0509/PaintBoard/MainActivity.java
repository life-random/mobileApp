package com.example.paintboard;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.SeekBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private DrawingView drawingView;
    private Button colorButton, strokeButton, eraserButton, clearButton;
    private int currentColor = Color.BLACK;
    private float currentStrokeWidth = 10f;

    // 기본 색상 배열
    private final int[] colors = new int[] {
            Color.BLACK, Color.RED, Color.GREEN, Color.BLUE,
            Color.YELLOW, Color.CYAN, Color.MAGENTA, Color.DKGRAY,
            Color.GRAY, Color.LTGRAY, Color.WHITE
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        drawingView = findViewById(R.id.drawingView);
        colorButton = findViewById(R.id.colorButton);
        strokeButton = findViewById(R.id.strokeButton);
        eraserButton = findViewById(R.id.eraserButton);
        clearButton = findViewById(R.id.clearButton);

        // 색상 선택 버튼 이벤트
        colorButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openColorPickerDialog();
            }
        });

        // 굵기 조절 버튼 이벤트
        strokeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openStrokeWidthDialog();
            }
        });

        // 지우개 버튼 이벤트
        eraserButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                drawingView.setEraser(true);
            }
        });

        // 전체 지우기 버튼 이벤트
        clearButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                drawingView.clearCanvas();
            }
        });
    }

    // 색상 선택 다이얼로그 표시 (내장 기능 사용)
    private void openColorPickerDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("색상 선택");

        // 색상 버튼을 담을 레이아웃 생성
        LinearLayout layout = new LinearLayout(this);
        layout.setOrientation(LinearLayout.VERTICAL);
        layout.setPadding(40, 40, 40, 40);

        // 색상 버튼의 행을 생성
        LinearLayout row = null;
        int buttonsPerRow = 5;

        for (int i = 0; i < colors.length; i++) {
            if (i % buttonsPerRow == 0) {
                row = new LinearLayout(this);
                row.setLayoutParams(new LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.MATCH_PARENT,
                        LinearLayout.LayoutParams.WRAP_CONTENT));
                layout.addView(row);
            }

            final Button colorBtn = new Button(this);
            final int colorIndex = i;
            colorBtn.setBackgroundColor(colors[i]);

            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                    0,
                    100,
                    1.0f);
            params.setMargins(5, 5, 5, 5);
            colorBtn.setLayoutParams(params);

            colorBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    currentColor = colors[colorIndex];
                    drawingView.setColor(currentColor);
                    // 다이얼로그 닫기
                    if (dialog != null) {
                        dialog.dismiss();
                    }
                }
            });

            row.addView(colorBtn);
        }

        builder.setView(layout);
        builder.setNegativeButton("취소", null);

        dialog = builder.create();
        dialog.show();
    }

    private AlertDialog dialog;

    // 굵기 조절 다이얼로그 표시
    private void openStrokeWidthDialog() {
        final Dialog dialog = new Dialog(this);
        dialog.setContentView(R.layout.dialog_stroke_width);
        dialog.setTitle("선 굵기 선택");

        final SeekBar seekBar = dialog.findViewById(R.id.seekBarStrokeWidth);
        final TextView textViewStrokeWidth = dialog.findViewById(R.id.textViewStrokeWidth);
        Button buttonApply = dialog.findViewById(R.id.buttonApply);

        seekBar.setProgress((int) currentStrokeWidth);
        textViewStrokeWidth.setText(String.valueOf((int) currentStrokeWidth));

        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                textViewStrokeWidth.setText(String.valueOf(progress));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
            }
        });

        buttonApply.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                currentStrokeWidth = seekBar.getProgress();
                drawingView.setBrushSize(currentStrokeWidth);
                dialog.dismiss();
            }
        });

        dialog.show();
    }
}