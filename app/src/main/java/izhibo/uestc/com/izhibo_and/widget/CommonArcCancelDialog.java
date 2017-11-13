package izhibo.uestc.com.izhibo_and.widget;

import android.animation.AnimatorListenerAdapter;
import android.app.Dialog;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.support.annotation.NonNull;

import izhibo.uestc.com.izhibo_and.R;

/**
 * Created by dongfanghong on 2017/11/7.
 */

public class CommonArcCancelDialog extends Dialog {
    private CancelArcView cancelArcView;

    public CommonArcCancelDialog(@NonNull Context context) {
        super(context);
        this.setContentView(R.layout.widget_common_arccancel_dialog);
        cancelArcView = (CancelArcView) findViewById(R.id.cancel_view);
    }

    @Override
    public void show() {
        super.show();
        ColorDrawable drawable = new ColorDrawable(0x0000ff00);
        getWindow().setBackgroundDrawable(drawable);

    }

    //建造者模式
    public static class Builder {
        private DialogParams dialogParams;
        public Builder(Context context) {
            dialogParams = new DialogParams(context);
        }
        public Builder setAnimationAdapter(AnimatorListenerAdapter adapter){
            dialogParams.mAdapter=adapter;
            return this;
        }
        public CommonArcCancelDialog create() {
            final CommonArcCancelDialog dialog = new CommonArcCancelDialog(dialogParams.mContext);
            dialog.apply(dialogParams);
            dialog.setCancelable(dialogParams.mCancleable);
            if (dialogParams.mCancleable) {
                //如果dialog可以取消，则点击dialog之外的部位即可取消
                dialog.setCanceledOnTouchOutside(true);
            }
            return dialog;

        }
    }

    private void apply(DialogParams dialogParams) {
        if(dialogParams.mAdapter!=null){
            cancelArcView .setAnimationListener(dialogParams.mAdapter);
        }

    }

    //公共dialog的共有属性
    private static class DialogParams {
        private Context mContext;
        private Boolean mCancleable = false;//设置dialog是否可以被取消,默认是不可以
        private AnimatorListenerAdapter mAdapter;
        private DialogParams(Context context){
            this.mContext=context;
        }
    }

}

