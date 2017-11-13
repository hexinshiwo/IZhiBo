package izhibo.uestc.com.izhibo_and.widget;

import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.DecelerateInterpolator;

import izhibo.uestc.com.izhibo_and.R;

/**
 * Created by dongfanghong on 2017/11/6.
 */

public class CancelArcView extends View {
    //内圆的画笔
    private Paint mPaintCircleInside;
    //外层圆环的画笔
    private Paint mPaintCircleOutside;
    //打叉的画笔,分别画两条线
    private Paint mPaintCancel;
    //整个圆外切的矩形
    private RectF mRectF = new RectF();
    //记录打叉路径的两条直线的坐标路径
    private float[] mPointsLine1 = new float[4];
    private float[] mPointsLine2 = new float[4];
    //控件中心的X,Y坐标
    private int centerX;
    private int centerY;

    //计数器
    private int circleRadius = -1;
    private int ringProgress = 0;
    //是否处于动画中
    private boolean isAnimationRunning = false;
    //最后扩大缩小动画中，画笔的宽度的最大倍数
    private static final int SCALE_TIMES = 6;
    private AnimatorSet mFinalAnimatorSet;
    private int mRingAnimatorDuration;
    private int mCircleAnimatorDuration;
    private int mScaleAnimatorDuration;
    private CancelArcConfig mConfig;

    public CancelArcView(Context context) {
        this(context, null);
    }

    public CancelArcView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CancelArcView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initAttrs(attrs);
        initPaint();
        initAnimatorCounter();
    }

    private void initAttrs(AttributeSet attrs) {
        if (mConfig == null) {
            mConfig = new CancelArcConfig(getContext());
        }
        TypedArray typedArray = getContext().obtainStyledAttributes(attrs, R.styleable.cancelArcView);
        mConfig.setCheckBaseColor(typedArray.getColor(R.styleable.cancelArcView_check_base_color, getResources().getColor(R.color.colorRed_Deep)))
                .setCheckTickColor(typedArray.getColor(R.styleable.cancelArcView_check_tick_color, getResources().getColor(R.color.colorWhite)))
                .setRadius(typedArray.getDimensionPixelOffset(R.styleable.cancelArcView_radius, dp2px(60)))
                .setClickable(typedArray.getBoolean(R.styleable.cancelArcView_clickable, false))
                .setTickRadiusOffset(dp2px(25));

        int rateMode = 1;
        TickRateEnum mTickRateEnum = TickRateEnum.getRateEnum(rateMode);
        mRingAnimatorDuration = mTickRateEnum.getmRingAnimatorDuration();
        mCircleAnimatorDuration = mTickRateEnum.getmCircleAnimatorDuration();
        mScaleAnimatorDuration = mTickRateEnum.getmScaleAnimatorDuration();
        typedArray.recycle();
        applyConfig(mConfig);
    }

    private void applyConfig(CancelArcConfig config) {
        assert mConfig != null : "TickView Config must not be null";
        if (config != null && config != mConfig) {
            mConfig.applyConfig(config);
        }
        if (mConfig.isNeedToReApply()) {
            initPaint();
            initAnimatorCounter();
            mConfig.setNeedToReApply(false);
        }
    }

    /**
     * 初始化画笔
     */
    private void initPaint() {
        if (mPaintCircleOutside == null) mPaintCircleOutside = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaintCircleOutside.setStyle(Paint.Style.STROKE);
        mPaintCircleOutside.setColor(mConfig.getCheckBaseColor());
        mPaintCircleOutside.setStrokeCap(Paint.Cap.ROUND);
        mPaintCircleOutside.setStrokeWidth(dp2px(2.5f));

        if (mPaintCircleInside == null) mPaintCircleInside = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaintCircleInside.setColor(mConfig.getCheckBaseColor());

        if (mPaintCancel == null) mPaintCancel = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaintCancel.setColor(mConfig.getCheckTickColor());
        mPaintCancel.setAlpha(0);
        mPaintCancel.setStyle(Paint.Style.STROKE);
        mPaintCancel.setStrokeCap(Paint.Cap.ROUND);
        mPaintCancel.setStrokeWidth(dp2px(7f));
    }

    /**
     * 用ObjectAnimator初始化一些计数器
     */
    private void initAnimatorCounter() {
        //圆环进度
        ObjectAnimator mRingAnimator = ObjectAnimator.ofInt(this, "ringProgress", 0, 360);
        mRingAnimator.setDuration(mRingAnimatorDuration);
        mRingAnimator.setInterpolator(null);
        //收缩动画
        ObjectAnimator mCircleAnimator = ObjectAnimator.ofInt(this, "circleRadius", mConfig.getRadius() - 5, 0);
        mCircleAnimator.setInterpolator(new DecelerateInterpolator());
        mCircleAnimator.setDuration(mCircleAnimatorDuration);
        //勾出来的透明渐变
        ObjectAnimator mAlphaAnimator = ObjectAnimator.ofInt(this, "tickAlpha", 0, 255);
        mAlphaAnimator.setDuration(200);
        //最后的放大再回弹的动画，改变画笔的宽度来实现
        ObjectAnimator mScaleAnimator = ObjectAnimator.ofFloat(this, "ringStrokeWidth", mPaintCircleOutside.getStrokeWidth(), mPaintCircleOutside.getStrokeWidth() * SCALE_TIMES, mPaintCircleOutside.getStrokeWidth() / SCALE_TIMES);
        mScaleAnimator.setInterpolator(null);
        mScaleAnimator.setDuration(mScaleAnimatorDuration);
        //打钩和放大回弹的动画一起执行
        AnimatorSet mAlphaScaleAnimatorSet = new AnimatorSet();
        mAlphaScaleAnimatorSet.playTogether(mAlphaAnimator, mScaleAnimator);
        mFinalAnimatorSet = new AnimatorSet();
        mFinalAnimatorSet.playSequentially(mRingAnimator, mCircleAnimator, mAlphaScaleAnimatorSet);


    }

    private int getMySize(int defaultSize, int measureSpec) {
        int mySize = defaultSize;

        int mode = MeasureSpec.getMode(measureSpec);
        int size = MeasureSpec.getSize(measureSpec);

        switch (mode) {
            case MeasureSpec.UNSPECIFIED:
            case MeasureSpec.AT_MOST:
                mySize = defaultSize;
                break;
            case MeasureSpec.EXACTLY:
                mySize = size;
                break;
        }
        return mySize;
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        final int radius = mConfig.getRadius();
        final float tickRadiusOffset = mConfig.getTickRadiusOffset();
        //控件的宽度等于动画最后的扩大范围的半径
        int width = getMySize((radius + dp2px(2.5f) * SCALE_TIMES) * 2, widthMeasureSpec);
        int height = getMySize((radius + dp2px(2.5f) * SCALE_TIMES) * 2, heightMeasureSpec);
        height = width = Math.max(width, height);
        setMeasuredDimension(width, height);
        centerX = getMeasuredWidth() / 2;
        centerY = getMeasuredHeight() / 2;
        //设置圆圈的外切矩形
        mRectF.set(centerX - radius, centerY - radius, centerX + radius, centerY + radius);
        //设置打叉的几个点坐标
        mPointsLine1[0] = centerX - tickRadiusOffset;
        mPointsLine1[1] = centerY - tickRadiusOffset;
        mPointsLine1[2] = centerX + tickRadiusOffset;
        mPointsLine1[3] = centerY + tickRadiusOffset;
        mPointsLine2[0] = centerX + tickRadiusOffset;
        mPointsLine2[1] = centerY - tickRadiusOffset;
        mPointsLine2[2] = centerX - tickRadiusOffset;
        mPointsLine2[3] = centerY + tickRadiusOffset;

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        //画圆弧进度
        canvas.drawArc(mRectF, 90, ringProgress, false, mPaintCircleOutside);
        //画黄色的背景
        mPaintCircleInside.setColor(mConfig.getCheckBaseColor());
        canvas.drawCircle(centerX, centerY, ringProgress == 360 ? mConfig.getRadius() : 0, mPaintCircleInside);
        //画收缩的白色圆
        if (ringProgress == 360) {
            mPaintCircleInside.setColor(mConfig.getCheckTickColor());
            canvas.drawCircle(centerX, centerY, circleRadius, mPaintCircleInside);
        }
        //画勾,以及放大收缩的动画
        if (circleRadius == 0) {
            canvas.drawLines(mPointsLine1, mPaintCancel);
            canvas.drawLines(mPointsLine2, mPaintCancel);
            canvas.drawArc(mRectF, 0, 360, false, mPaintCircleOutside);
        }
        //ObjectAnimator动画替换计数器
        if (!isAnimationRunning) {
            isAnimationRunning = true;
            mFinalAnimatorSet.start();

        }


    }

    /*--------------属性动画---getter/setter begin---------------*/

    private int getRingProgress() {
        return ringProgress;
    }

    private void setRingProgress(int ringProgress) {
        this.ringProgress = ringProgress;
        postInvalidate();
    }

    private int getCircleRadius() {
        return circleRadius;
    }

    private void setCircleRadius(int circleRadius) {
        this.circleRadius = circleRadius;
        postInvalidate();
    }

    private int getTickAlpha() {
        return 0;
    }

    private void setTickAlpha(int tickAlpha) {
        mPaintCancel.setAlpha(tickAlpha);
        postInvalidate();
    }

    private float getRingStrokeWidth() {
        return mPaintCircleOutside.getStrokeWidth();
    }

    private void setRingStrokeWidth(float strokeWidth) {
        mPaintCircleOutside.setStrokeWidth(strokeWidth);
        postInvalidate();
    }

     /*--------------属性动画---getter/setter end---------------*/

    /**
     * 设置动画的监听器
     *
     * @param animatorListenerAdapter
     */
    public void setAnimationListener(AnimatorListenerAdapter animatorListenerAdapter) {
        mFinalAnimatorSet.addListener(animatorListenerAdapter);
    }

    /**
     * 重置
     */
    private void reset() {
        initPaint();
        mFinalAnimatorSet.cancel();
        ringProgress = 0;
        circleRadius = -1;
        isAnimationRunning = false;
        final int radius = mConfig.getRadius();
        mRectF.set(centerX - radius, centerY - radius, centerX + radius, centerY + radius);
        invalidate();
    }

    private int dp2px(float dpValue) {
        return CommonUtils.dpToPx(getContext(), dpValue);
    }
}
