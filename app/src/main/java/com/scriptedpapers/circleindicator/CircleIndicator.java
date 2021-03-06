package com.scriptedpapers.circleindicator;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

/**
 * @author Mahes Sivakumar
 *
 */
public class CircleIndicator extends View {

    private static final String TAG = "CircleIndicator";

	private static final int DEFAULT_INDICATOR_WIDTH = 80;
	private static final int DEFAULT_INDICATOR_COLOR = 0xFF0000FF;
    private static final int DEFAULT_FILL_COLOR = 0xFFFFAF00;
	
	private Paint outerCirclePaint = new Paint();
	private Paint innerCirclePaint = new Paint();

    private int indicatorWidth = DEFAULT_INDICATOR_WIDTH;

	private RectF circleBounds = new RectF();

	float indicatorValue = 0;


    public CircleIndicator(Context context)
    {
        super(context);
        init();
    }
    public CircleIndicator(Context context, AttributeSet attrs)
    {
        super(context, attrs);
        init();
    }
    public CircleIndicator(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init();
    }

	void init() {

		outerCirclePaint.setColor(DEFAULT_INDICATOR_COLOR);
		innerCirclePaint.setColor(DEFAULT_FILL_COLOR);

		outerCirclePaint.setAntiAlias(true);
		innerCirclePaint.setAntiAlias(true);

		outerCirclePaint.setStyle(Style.STROKE);
		innerCirclePaint.setStyle(Style.STROKE);

		outerCirclePaint.setStrokeWidth(DEFAULT_INDICATOR_WIDTH);
        innerCirclePaint.setStrokeWidth(DEFAULT_INDICATOR_WIDTH);
	}


	@Override
	protected void onSizeChanged(int width, int height, int oldWidth, int oldHeight) {

		super.onSizeChanged(width, height, oldWidth, oldHeight);
		
		circleBounds = new RectF(indicatorWidth, indicatorWidth,
				this.getLayoutParams().width - indicatorWidth,
				this.getLayoutParams().height - indicatorWidth);
	}

	@Override
	protected void onDraw(Canvas canvas) {
		super.onDraw(canvas);

        canvas.drawArc(circleBounds, -90, 360, false, innerCirclePaint);
		canvas.drawArc(circleBounds, -90, indicatorValue, false, outerCirclePaint);
	}

    /**
     * Indicator Percentage specifies the percentage of progress in circle.
     * The Indicator value should ranges between 0 to 100.
     *
     * 0   - Empty Circle
     * 50  - Half Circle
     * 100 - Full Circle
     */
	public void setProgressPercentage(float indicatorValue) {

        if(indicatorValue < 0 || indicatorValue > 100) {
            Log.d(TAG, "The Indicator value should ranges between 0 to 100.");
            return;
        }

		indicatorValue = (float) (indicatorValue * 360 / 100);
		this.indicatorValue = indicatorValue;

        invalidate();
	}
	
	public void setIndicatorRingColor(int indicatorLevelColor){
		outerCirclePaint.setColor(indicatorLevelColor);

        invalidate();
	}

    public void setOuterRingColor(int innerFillColor){
        innerCirclePaint.setColor(innerFillColor);

        invalidate();
    }

    /**
     * Set Diameter of the Progress Circle.
     *
     * @param diameter - Diameter of the Progress Circle.
     */
    public void setIndicatorDiameter(int diameter) {
        getLayoutParams().width = diameter;
        getLayoutParams().height = diameter;

        invalidate();
    }

    /**
     * Set Circle Progress Indicator Width.
     *
     * @param indicatorWidth - Indicator Width of the Progress Circle.
     */
    public void setDefaultIndicatorWidth(int indicatorWidth) {
        this.indicatorWidth = indicatorWidth;
        innerCirclePaint.setStrokeWidth(indicatorWidth);
        outerCirclePaint.setStrokeWidth(indicatorWidth);

        invalidate();
    }

}