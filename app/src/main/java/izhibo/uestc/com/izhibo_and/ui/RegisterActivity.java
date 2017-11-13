package izhibo.uestc.com.izhibo_and.ui;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Toast;

import com.trello.rxlifecycle2.components.support.RxAppCompatActivity;

import izhibo.uestc.com.izhibo_and.R;
import izhibo.uestc.com.izhibo_and.databinding.ActivityRegisterBinding;
import izhibo.uestc.com.izhibo_and.model.Constants;
import izhibo.uestc.com.izhibo_and.mvvm.view.RegisterView;
import izhibo.uestc.com.izhibo_and.mvvm.viewmodel.RegisterViewModel;
import izhibo.uestc.com.izhibo_and.widget.CommonArcCancelDialog;
import izhibo.uestc.com.izhibo_and.widget.CommonArcConfirmDialog;
import izhibo.uestc.com.izhibo_and.widget.CommonProgressDialog;

/**
 * Created by dongfanghong on 2017/11/10.
 */

public class RegisterActivity extends RxAppCompatActivity implements RegisterView {
    private RegisterViewModel viewModel;
    private CommonArcConfirmDialog confirmDialog;
    private Handler handler;
    private CommonProgressDialog commonProgressDialog;
    private CommonArcCancelDialog cancelDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityRegisterBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_register);
        viewModel = new RegisterViewModel(this);
        binding.setViewModel(viewModel);
        binding.setRegisterListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                viewModel.register();
            }
        });
        initDialog();
    }

    @Override
    public void registerSuccess() {
        if (commonProgressDialog != null && commonProgressDialog.isShowing()) {
            commonProgressDialog.dismiss();
            confirmDialog.show();
        }
    }

    @Override
    public void registerError(String message) {
        if (commonProgressDialog != null && commonProgressDialog.isShowing()) {
            commonProgressDialog.dismiss();
            Toast.makeText(this,message,Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void registerProcessing() {
        commonProgressDialog.show();
    }

    public void initDialog() {
        handler=new Handler();
        cancelDialog = new CommonArcCancelDialog.Builder(this)
                .setAnimationAdapter(new AnimatorListenerAdapter() {
                    @Override
                    public void onAnimationEnd(Animator animation) {
                        super.onAnimationEnd(animation);
                        handler.postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                if (cancelDialog != null && cancelDialog.isShowing()) {
                                    cancelDialog.dismiss();
                                }
                            }
                        }, 400);
                    }
                }).create();
        confirmDialog = new CommonArcConfirmDialog.Builder(this)
                .setAnimationAdapter(new AnimatorListenerAdapter() {
                    @Override
                    public void onAnimationEnd(Animator animation) {
                        super.onAnimationEnd(animation);
                        handler.postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                if (confirmDialog != null && confirmDialog.isShowing()) {
                                    confirmDialog.dismiss();
                                    Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
                                    startActivity(intent);
                                    finish();
                                }
                            }
                        }, 400);
                    }
                })
                .create();
        commonProgressDialog = new CommonProgressDialog.Builder(this)
                .setMessage(Constants.PROGRESSBAR_REGISTER)
                .setCancelable(false)
                .create();

    }


}
