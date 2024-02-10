package com.example.mentalhealthapp;


import androidx.appcompat.app.AppCompatActivity;


import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;




import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Locale;


public class ThirdActivity extends AppCompatActivity
{
    private static final long startTime = 6000;
    private TextView countDownTextView;
    private Button startPause;
    private TextView breathe;
    private long timeLeftInMillis = startTime;


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_third);

        ImageButton homeBtn = findViewById(R.id.toHome);
        homeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                startActivity(new Intent(ThirdActivity.this, MainActivity.class));
            }
        });

        countDownTextView = findViewById(R.id.textView4);
        breathe = findViewById(R.id.textView5);

        Button startPause = findViewById(R.id.button);
        startPause.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                countDownTextView.setVisibility(View.VISIBLE);
                breathe.setVisibility(View.VISIBLE);
                new CountDownTimer(7000, 1000) {
                    public void onTick(long millisUntilFinished) {
                        //Used for formatting digit to be in 2 digits only
                        NumberFormat f = new DecimalFormat("0");
                        long sec = (millisUntilFinished / (long) 1000) % 60;
                        countDownTextView.setText(f.format(sec));
                    }
                    // When the task is over it will print 00:00:00 there
                    public void onFinish() {
                        countDownTextView.setText("0");
                        breathe.setText("Breathe Out!");
                        new CountDownTimer(7000, 1000)
                        {
                            public void onTick(long millisUntilFinished) {
                                // Used for formatting digit to be in 2 digits only
                                NumberFormat f = new DecimalFormat("0");
                                long sec = (millisUntilFinished / (long) 1000) % 60;
                                countDownTextView.setText(f.format(sec));
                            }
                            public void onFinish() {
                                breathe.setText("Good Job!");
                            }
                        }.start();
                    }
                }.start();
            }
        });
    }
}
