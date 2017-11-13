package izhibo.uestc.com.izhibo_and.widget;

import android.content.Context;
import android.support.annotation.Nullable;
import android.text.TextUtils;

/**
 * Created by dongfanghong on 2017/10/10.
 */

public class CommonUtils {
    public static int getStringToInt(String data,int defaultValue){
        try{
            return Integer.parseInt(data);
        }
        catch (NumberFormatException e)
        {
            return defaultValue;
        }

    }
    public static Boolean isEmpty(@Nullable CharSequence sequence){
        return TextUtils.isEmpty(sequence);
    }
    //根据手机的分辨率将dp单位转化成px
    public static int dpToPx(Context context, float dpValue) {
        if (context == null) return (int) (dpValue * 1.5f + 0.5f);
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }
    //根据手机分辨率将px转化成dp
    public static int pxToDp(Context context, float pxValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (pxValue / scale + 0.5f);
    }

}
