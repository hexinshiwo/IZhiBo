package izhibo.uestc.com.izhibo_and.widget;

import android.content.Context;
import android.support.annotation.NonNull;

import izhibo.uestc.com.izhibo_and.R;
import izhibo.uestc.com.izhibo_and.uiInterface.ConfirmArcViewAnimatorListener;

/**
 * CancelArcView控件的相关属性
 * Created by dongfanghong on 2017/11/7.
 */

public class CancelArcConfig {
    private volatile boolean isNeedToReApply;
    private boolean clickable = true;
    private int checkBaseColor;
    private int checkTickColor;
    private int radius;
    //勾的偏移
    private float tickRadiusOffset;
    private ConfirmArcViewAnimatorListener mConfirmArcViewAnimatorListener;
    public ConfirmArcViewAnimatorListener getConfirmArcViewAnimatorListener() {
        return mConfirmArcViewAnimatorListener;
    }

    public CancelArcConfig setConfirmArcViewAnimatorListener(ConfirmArcViewAnimatorListener mConfirmArcViewAnimatorListener) {
        this.mConfirmArcViewAnimatorListener = mConfirmArcViewAnimatorListener;
        return setNeedToReApply(true);
    }


    public boolean isNeedToReApply() {
        return isNeedToReApply;
    }



    public int getCheckBaseColor() {
        return checkBaseColor;
    }

    public CancelArcConfig setCheckBaseColor(int checkBaseColor) {
        this.checkBaseColor = checkBaseColor;
        return setNeedToReApply(true);
    }

    public int getCheckTickColor() {
        return checkTickColor;
    }

    public CancelArcConfig setCheckTickColor(int checkTickColor) {
        this.checkTickColor = checkTickColor;
        return setNeedToReApply(true);
    }

    public int getRadius() {
        return radius;
    }

    public CancelArcConfig setRadius(int radius) {
        this.radius = radius;
        return setNeedToReApply(true);
    }
    public float getTickRadiusOffset() {
        return tickRadiusOffset;
    }

    public CancelArcConfig setTickRadiusOffset(float tickRadiusOffset) {
        this.tickRadiusOffset = tickRadiusOffset;
        return setNeedToReApply(true);
    }

    public CancelArcConfig(Context context) {
        this(context, null);
    }

    public CancelArcConfig setClickable(boolean clickable) {
        this.clickable = clickable;
        return this;
    }

    public boolean isClickable() {
        return clickable;
    }

    public CancelArcConfig(Context context, CancelArcConfig config) {
        if (config != null) {
            applyConfig(config);
        } else {
            setNeedToReApply(true);
            setupDefaultValue(context);
        }
    }

    public CancelArcConfig setNeedToReApply(boolean isNeedToReApply) {
        this.isNeedToReApply = isNeedToReApply;
        return this;
    }

    public CancelArcConfig applyConfig(@NonNull CancelArcConfig config) {
        if (config == null) return this;
        return setClickable(config.isClickable())
                .setCheckBaseColor(config.getCheckBaseColor())
                .setCheckTickColor(config.getCheckTickColor())
                .setConfirmArcViewAnimatorListener(config.getConfirmArcViewAnimatorListener())
                .setTickRadiusOffset(config.getTickRadiusOffset());

    }


    private void setupDefaultValue(Context context) {
        this.setCheckBaseColor(context.getResources().getColor(R.color.colorYellow))
                .setCheckTickColor(context.getResources().getColor(R.color.colorWhite))
                .setRadius(CommonUtils.dpToPx(context, 30))
                .setClickable(true)
                .setTickRadiusOffset(CommonUtils.dpToPx(context, 4));
    }
}
