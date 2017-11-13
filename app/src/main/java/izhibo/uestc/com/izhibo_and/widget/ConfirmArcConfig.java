package izhibo.uestc.com.izhibo_and.widget;

import android.content.Context;
import android.support.annotation.NonNull;

import izhibo.uestc.com.izhibo_and.R;
import izhibo.uestc.com.izhibo_and.uiInterface.ConfirmArcViewAnimatorListener;
import izhibo.uestc.com.izhibo_and.uiInterface.OnCheckedChangeListener;

/**
 * ConfirmArcView控件的相关属性配置
 * Created by dongfanghong on 2017/11/3.
 */

public class ConfirmArcConfig {
    private volatile boolean isNeedToReApply;
    private boolean clickable = true;
    private int checkBaseColor;
    private int checkTickColor;
    private int radius;
    //勾的半径
    private float tickRadius;
    //勾的偏移
    private float tickRadiusOffset;
    private OnCheckedChangeListener mOnCheckedChangeListener;
    private ConfirmArcViewAnimatorListener mConfirmArcViewAnimatorListener;
    public ConfirmArcViewAnimatorListener getConfirmArcViewAnimatorListener() {
        return mConfirmArcViewAnimatorListener;
    }

    public ConfirmArcConfig setConfirmArcViewAnimatorListener(ConfirmArcViewAnimatorListener mConfirmArcViewAnimatorListener) {
        this.mConfirmArcViewAnimatorListener = mConfirmArcViewAnimatorListener;
        return setNeedToReApply(true);
    }


    public boolean isNeedToReApply() {
        return isNeedToReApply;
    }



    public int getCheckBaseColor() {
        return checkBaseColor;
    }

    public ConfirmArcConfig setCheckBaseColor(int checkBaseColor) {
        this.checkBaseColor = checkBaseColor;
        return setNeedToReApply(true);
    }

    public int getCheckTickColor() {
        return checkTickColor;
    }

    public ConfirmArcConfig setCheckTickColor(int checkTickColor) {
        this.checkTickColor = checkTickColor;
        return setNeedToReApply(true);
    }

    public int getRadius() {
        return radius;
    }

    public ConfirmArcConfig setRadius(int radius) {
        this.radius = radius;
        return setNeedToReApply(true);
    }

    public float getTickRadius() {
        return tickRadius;
    }

    public ConfirmArcConfig setTickRadius(float tickRadius) {
        this.tickRadius = tickRadius;
        return setNeedToReApply(true);
    }

    public float getTickRadiusOffset() {
        return tickRadiusOffset;
    }

    public ConfirmArcConfig setTickRadiusOffset(float tickRadiusOffset) {
        this.tickRadiusOffset = tickRadiusOffset;
        return setNeedToReApply(true);
    }

    public ConfirmArcConfig(Context context) {
        this(context, null);
    }

    public ConfirmArcConfig setClickable(boolean clickable) {
        this.clickable = clickable;
        return this;
    }

    public boolean isClickable() {
        return clickable;
    }

    public ConfirmArcConfig(Context context, ConfirmArcConfig config) {
        if (config != null) {
            applyConfig(config);
        } else {
            setNeedToReApply(true);
            setupDefaultValue(context);
        }
    }

    public ConfirmArcConfig setNeedToReApply(boolean isNeedToReApply) {
        this.isNeedToReApply = isNeedToReApply;
        return this;
    }

    public ConfirmArcConfig applyConfig(@NonNull ConfirmArcConfig config) {
        if (config == null) return this;
        return setClickable(config.isClickable())
                .setCheckBaseColor(config.getCheckBaseColor())
                .setCheckTickColor(config.getCheckTickColor())
                .setConfirmArcViewAnimatorListener(config.getConfirmArcViewAnimatorListener())
                .setTickRadius(config.getTickRadius())
                .setTickRadiusOffset(config.getTickRadiusOffset());

    }


    private void setupDefaultValue(Context context) {
        this.setCheckBaseColor(context.getResources().getColor(R.color.colorYellow))
                .setCheckTickColor(context.getResources().getColor(R.color.colorWhite))
                .setRadius(CommonUtils.dpToPx(context, 30))
                .setClickable(true)
                .setTickRadius(CommonUtils.dpToPx(context, 12))
                .setTickRadiusOffset(CommonUtils.dpToPx(context, 4));
    }

}
