package com.example.myapplication_mof;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class TemperatureActivity extends AppCompatActivity {
    private ImageButton btnBack2;
    private TextView tvNickName, tvTitle, tvCurrentTemperature, tvTargetTemperature;
    private ProgressBar progressBar;
    private SeekBar seekBar;
    private EditText etReason;
    private Button btnSubmit;

    private String userNickname = "닉네임";
    private double currentTemp = 36.5;
    private double userSelectedTemp = 36.5;

    @Override
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_temperature);

        btnBack2 = findViewById(R.id.btnBack2);
        tvNickName = findViewById(R.id.nickName);
        tvTitle = findViewById(R.id.textView);
        tvCurrentTemperature = findViewById(R.id.currentTemperature);

        View secondLayout = (View) findViewById(R.id.seekBar).getParent();
        if (secondLayout instanceof View) {
            tvTargetTemperature = ((View) secondLayout).findViewById(R.id.targetTemperature);
        }

        progressBar = findViewById(R.id.progressBar);
        seekBar = findViewById(R.id.seekBar);
        etReason = findViewById(R.id.editTextText7);
        btnSubmit = findViewById(R.id.button10);

        tvNickName.setText(userNickname + "님의 온도");
        tvTitle.setText(userNickname + "님의 현재 온도입니다");

        tvCurrentTemperature.setText(currentTemp + "도");
        progressBar.setMax(730);
        progressBar.setProgress((int) (currentTemp * 10));

        seekBar.setMax(730);
        seekBar.setProgress(365);
        tvTargetTemperature.setText("36.5도");

        btnBack2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                userSelectedTemp = progress / 10.0;
                tvTargetTemperature.setText(userSelectedTemp + "도");
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {}

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {}
        });

        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String reason = etReason.getText().toString().trim();

                if (reason.isEmpty() || reason.equals("이렇게 평가한 이유를 적어주세요")) {
                    Toast.makeText(TemperatureActivity.this, "사유를 입력해주세요.", Toast.LENGTH_SHORT).show();
                    return;
                }

                String resultMessage = "선택 온도: " + userSelectedTemp + "도\n사유: " + reason + "\n저장되었습니다.";
                Toast.makeText(TemperatureActivity.this, resultMessage, Toast.LENGTH_LONG).show();
            }
        });
    }
}