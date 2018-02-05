package com.yasir.timerapp;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    SeekBar timeSeekBar;
    TextView timerTextView;
    Button controlerButton;
    CountDownTimer countDownTimer;
    Boolean counterIsActive= false;

    public void updateTimer(int secondsLeft)
    {
        int minute= secondsLeft /60;
        String minuteString=Integer.toString(minute);
        if(minute<=9)
        {
            minuteString="0" +minuteString;
        }

        int seconds = secondsLeft -minute*60;

        String secondString=Integer.toString(seconds);

        if (seconds<=9)
        {
            secondString="0" +secondString;
        }

        timerTextView.setText(minuteString + ":" + secondString );
    }




    public void controlTimer(View view){

        if(counterIsActive==false) {
            Toast.makeText(getApplicationContext()," Timer Started", Toast.LENGTH_SHORT).show();
            counterIsActive = true;
            timeSeekBar.setEnabled(false);
            controlerButton.setText("Stop");

            countDownTimer=new CountDownTimer(timeSeekBar.getProgress() * 1000 + 100, 1000) {
                @Override
                public void onTick(long l) {

                    updateTimer((int) l / 1000);
                }

                @Override
                public void onFinish() {
                    timerTextView.setText("0:00");
                    controlerButton.setText("Reset");
                    Toast.makeText(getApplicationContext(), " Timer Done", Toast.LENGTH_SHORT).show();
                    MediaPlayer mediaPlayer = MediaPlayer.create(getApplicationContext(), R.raw.horn);
                    mediaPlayer.start();
                }
            }.start();
        }
        else{
            Toast.makeText(getApplicationContext()," Timer Stopped", Toast.LENGTH_SHORT).show();
                timerTextView.setText("10:00");
                timeSeekBar.setProgress(600);
                countDownTimer.cancel();
                controlerButton.setText("Go");
                timeSeekBar.setEnabled(true);
                counterIsActive=false;
            }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

         timeSeekBar = findViewById(R.id.timerSeekBar);
        timerTextView = findViewById(R.id.timerTextView);
        controlerButton=findViewById(R.id.controllerButton);
            timeSeekBar.setMax(1200);
            timeSeekBar.setProgress(600);

            timeSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
                @Override
                public void onProgressChanged(SeekBar seekBar, int progress, boolean b) {

                    updateTimer(progress);
                }

                @Override
                public void onStartTrackingTouch(SeekBar seekBar) {

                }

                @Override
                public void onStopTrackingTouch(SeekBar seekBar) {

                }
            });



















  /*
        new CountDownTimer(10000,1000)
        {
             public void onTick(long millisecondUntilDone)
             {
                 Log.i("Seconds left",String.valueOf(millisecondUntilDone/1000)  );
             }
            public void onFinish()

            {
                Log.i("Done!","Countdown Timer Finished");
            }
        }.start();



        /*
        final Handler handler =new Handler();
        Runnable run =new Runnable() {
            @Override
            public void run() {
                Log.i("Runnable has Run!", "a second has passed");
                handler.postDelayed(this,1000);
            }
        };
            handler.post(run);

*/
    }
}
