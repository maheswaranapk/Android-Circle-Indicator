package com.scriptedpapers.circleindicator;

import android.os.Handler;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Display;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.Timer;
import java.util.TimerTask;

/**
 * @author Mahes Sivakumar
 *
 */
public class MainActivity extends ActionBarActivity {


    private CircleIndicator secondsTimer;
    private TextView timeView;

    private static final int INDICATOR_COLOR = 0xFFFF0000;
    private static final int FILL_COLOR = 0xFF00FF00;
    private static final int INDICATOR_WIDTH = 60;

    private Calendar cal = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        secondsTimer = (CircleIndicator) findViewById(R.id.secondsTimer);
        timeView = (TextView) findViewById(R.id.timeView);

        cal = Calendar.getInstance();

        Display display = getWindowManager().getDefaultDisplay();
        int indicatorDiameter = display.getWidth() * 3 / 4;

        secondsTimer.setIndicatorRingColor(INDICATOR_COLOR);
        secondsTimer.setOuterRingColor(FILL_COLOR);
        secondsTimer.setDefaultIndicatorWidth(INDICATOR_WIDTH);
        secondsTimer.setIndicatorDiameter(indicatorDiameter);

        initializeTimer();

    }

    void initializeTimer() {

        final Handler mHandler = new Handler();

        final Runnable myRunnable = new Runnable() {
            @Override
            public void run() {

                Date date = new Date();

                cal.setTime(date);

                /**
                 *Indicator Percentage specifies the percentage of progress in circle.
                 *
                 * Seconds lies in range of 0 to 59.
                 * Convert seconds to percentage and set as progress percentage.
                 */
                float secondsInPercentage = (float) (cal.get(Calendar.SECOND) * 100 / 59);

                secondsTimer
                        .setProgressPercentage(secondsInPercentage);

                secondsTimer.invalidate();

                timeView.setText(new SimpleDateFormat("hh:mm:ss a zzz").format(date));
            }
        };

        Timer t = new Timer();
        t.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                mHandler.post(myRunnable);
            }
        }, 0, 1000);
    }



}
