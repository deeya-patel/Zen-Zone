package com.example.mentalhealthapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.RotateAnimation;
import android.widget.ImageButton;
import android.widget.ImageView;

import java.util.Random;

public class FirstActivity extends AppCompatActivity {

    private static final String [] slices = {"White Noise","Piano Chords","Nature Sounds","Ocean Waves","Bubble Pops","Dripping Water"};
    private static final int[] degreeOfSlices = new int [slices.length];
    private static final Random random = new Random();
    private int degree = 0;
    private boolean isSpinning = false;
    private ImageView asmrWheel;

    private MediaPlayer audios;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first);

        ImageButton homeBtn = findViewById(R.id.toHome);
        homeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                startActivity(new Intent(FirstActivity.this, MainActivity.class));
            }
        });

        final ImageView btn = findViewById(R.id.spinBtn);
        asmrWheel = findViewById(R.id.asmrWheel);
        getPosition();

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!isSpinning){
                    spinWheel();
                    isSpinning = true;
                }
            }
        });
    }

    private void spinWheel(){
        degree = random.nextInt(slices.length-1);

        RotateAnimation rotating = new RotateAnimation(0,(360*slices.length) + degreeOfSlices[degree],
                RotateAnimation.RELATIVE_TO_SELF,0.5f,RotateAnimation.RELATIVE_TO_SELF, 0.5f);

        rotating.setDuration(3600);
        rotating.setFillAfter(true);
        rotating.setInterpolator(new DecelerateInterpolator());
        rotating.setAnimationListener(new Animation.AnimationListener(){
            @Override
            public void onAnimationStart(Animation animation){

            }
            @Override
            public void onAnimationEnd(Animation animation){
                isSpinning = false;
                if(slices[slices.length - (degree + 1)].equals("White Noise")) {
                    audios = MediaPlayer.create(getApplicationContext(), R.raw.whitenoise);
                    audios.start();
                }else if(slices[slices.length - (degree + 1)].equals("Piano Chords")) {
                    audios = MediaPlayer.create(getApplicationContext(), R.raw.pianochords);
                    audios.start();
                }else if(slices[slices.length - (degree + 1)].equals("Nature Sounds")){
                    audios = MediaPlayer.create(getApplicationContext(), R.raw.naturesounds);
                    audios.start();
                }else if(slices[slices.length - (degree + 1)].equals("Ocean Waves")){
                    audios = MediaPlayer.create(getApplicationContext(), R.raw.oceanwaves);
                    audios.start();
                }else if(slices[slices.length - (degree + 1)].equals("Bubble Pops")){
                    audios = MediaPlayer.create(getApplicationContext(), R.raw.bubblepops);
                    audios.start();
                }else if(slices[slices.length - (degree + 1)].equals("Dripping Water")){
                    audios = MediaPlayer.create(getApplicationContext(), R.raw.drippingwater);
                    audios.start();
                }
            }
            @Override
            public void onAnimationRepeat(Animation animation){

            }
        });

        asmrWheel.startAnimation(rotating);
    }

    private void getPosition(){
        int degreeOfSlice = 360/slices.length;
        for(int i=0;i<slices.length;i++){
            degreeOfSlices[i] = (i+1) * degreeOfSlice;
        }
    }
}