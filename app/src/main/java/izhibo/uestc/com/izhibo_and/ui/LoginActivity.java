package izhibo.uestc.com.izhibo_and.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import izhibo.uestc.com.izhibo_and.R;
import izhibo.uestc.com.izhibo_and.model.Constants;
import izhibo.uestc.com.izhibo_and.presenter.LoginAndRegisterPresenter;
import izhibo.uestc.com.izhibo_and.uiInterface.LoginView;
import izhibo.uestc.com.izhibo_and.widget.CommonProgressDialog;

/**
 * Created by dongfanghong on 2017/10/8.
 */

public class LoginActivity extends AppCompatActivity implements LoginView, View.OnClickListener {
    private EditText accountEdit;
    private EditText passwordEdit;
    private Button loginBtn;
    private Button registerBtn;
    private LoginAndRegisterPresenter loginAndRegisterPresenter;
    private CommonProgressDialog commonProgressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        initView();
        saveUserInfo();
    }

    private void initView() {
        accountEdit = (EditText) findViewById(R.id.edit_account);
        passwordEdit = (EditText) findViewById(R.id.edit_password);
        loginAndRegisterPresenter = new LoginAndRegisterPresenter(this);
        loginAndRegisterPresenter.getUserInfoFromCache(this);
        accountEdit.setText(loginAndRegisterPresenter.getUserInfoModle().getUserAccount());
        loginBtn = (Button) findViewById(R.id.btn_go_login);
        registerBtn = (Button) findViewById(R.id.btn_go_register);
        loginBtn.setOnClickListener(this);
        registerBtn.setOnClickListener(this);
        commonProgressDialog = new CommonProgressDialog.Builder(this)
                .setMessage(Constants.PROGRESSBAR_LOGIN)
                .setCancelable(false)
                .create();
    }

    private void saveUserInfo() {
        loginAndRegisterPresenter.saveUserInfoToCache(accountEdit.getText().toString(), this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_go_login:
                login();
                break;
            case R.id.btn_go_register:
                register();
                break;
        }
    }

    private void login() {
        loginAndRegisterPresenter.goLogin(accountEdit.getText().toString(), passwordEdit.getText().toString());

    }

    private void register() {
        Intent intent = new Intent(this, RegisterActivity.class);
        startActivity(intent);
    }


    @Override
    public void loginSuccess() {
        if (commonProgressDialog != null && commonProgressDialog.isShowing()) {
            commonProgressDialog.dismiss();
            Intent intent = new Intent(LoginActivity.this, HomeActivity.class);
            startActivity(intent);
        }
    }

    @Override
    public void setDialogVisibleBeforeHttp() {
        //设置dialog可见
        commonProgressDialog.show();
    }

    @Override
    public void showToast(String msg) {
        if (commonProgressDialog != null && commonProgressDialog.isShowing()) {
            commonProgressDialog.dismiss();
            Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
        }

    }


}
