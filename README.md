# **Android-Circle-Indicator**
This is a Simple Android Progress Circle Indicator.

# **How To use**
Copy **CircleIndicator.java** to the project.

In Layout xml,
            <com.scriptedpapers.circleindicator.CircleIndicator
                android:id="@+id/secondsTimer"
                android:layout_centerInParent="true"
                android:layout_width="300dp"
                android:layout_height="300dp" />

In View,
            secondsTimer.setIndicatorRingColor(INDICATOR_COLOR);
            secondsTimer.setOuterRingColor(FILL_COLOR);
            secondsTimer.setDefaultIndicatorWidth(INDICATOR_WIDTH);
            secondsTimer.setIndicatorDiameter(indicatorDiameter);

# **Output**
![alt tag](https://github.com/maheswaranapk/Android-Circle-Indicator/blob/master/sample/CircleindicatorSample.gif)

