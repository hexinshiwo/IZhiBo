package izhibo.uestc.com.izhibo_and.widget;

import android.app.Dialog;
import android.content.Context;
import android.widget.ProgressBar;
import android.widget.TextView;

import izhibo.uestc.com.izhibo_and.R;

/**
 * Created by dongfanghong on 2017/10/28.
 */

public class CommonProgressDialog extends Dialog {
    private ProgressBar mProgressBar;
    private TextView mTextView;

    public CommonProgressDialog(Context context) {
        super(context);
        setContentView(R.layout.widget_common_dialog);
        mProgressBar = (ProgressBar) findViewById(R.id.common_dialog_progressbar);
        mTextView = (TextView) findViewById(R.id.common_dialog_text_content);
//        DoubleBounce doubleBounce = new DoubleBounce();
//        mProgressBar.setIndeterminateDrawable(doubleBounce);
    }
    private void apply(DialogParams dialogParams){
        if(!CommonUtils.isEmpty(dialogParams.mMessage)){
            mTextView.setText(dialogParams.mMessage);
        }

    }
    @Override
    public void show(){
        super.show();
//        ColorDrawable drawable=new ColorDrawable(0x0000ff00);
//        getWindow().setBackgroundDrawable(drawable);

    }

    //建造者模式
    public static class Builder {
        private DialogParams dialogParams;

        public Builder(Context context) {
            dialogParams = new DialogParams(context);
        }

        public Builder setMessage(String message) {
            dialogParams.mMessage = message;
            return this;
        }

        public Builder setCancelable(Boolean cancelable){
            dialogParams.mCancelable=cancelable;
            return this;
        }

        public CommonProgressDialog create() {
            final CommonProgressDialog dialog=new CommonProgressDialog(dialogParams.mContext);
            dialog.apply(dialogParams);
            dialog.setCancelable(dialogParams.mCancelable);
            if(dialogParams.mCancelable){
                //如果dialog可以取消，则点击dialog之外的部位即可取消
                dialog.setCanceledOnTouchOutside(true);
            }
            return dialog;

        }
    }

    //公共dialog的共有属性
    private static class DialogParams {
        private String mMessage;
        private Context mContext;
        private Boolean mCancelable=false;//设置dialog是否可以被取消,默认是不可以
        DialogParams(Context context){
            mContext=context;
        }
    }
}
