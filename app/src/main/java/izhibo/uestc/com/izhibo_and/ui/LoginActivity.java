package izhibo.uestc.com.izhibo_and.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import izhibo.uestc.com.izhibo_and.R;
import izhibo.uestc.com.izhibo_and.presenter.LoginAndRegisterPresenter;
import izhibo.uestc.com.izhibo_and.uiInterface.LoginAndRegisterView;

/**
 * Created by dongfanghong on 2017/10/8.
 */

public class LoginActivity extends AppCompatActivity implements LoginAndRegisterView, View.OnClickListener {
    private EditText accountEdit;
    private EditText passwordEdit;
    private EditText userNameEdit;
    private Button loginBtn;
    private Button registerBtn;
    private LoginAndRegisterPresenter loginAndRegisterPresenter;
    private String mStrAccount;
    private String mStrPassword;
    private String mStrUserName;

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
        userNameEdit=(EditText)findViewById(R.id.edit_user_name);
        loginAndRegisterPresenter = new LoginAndRegisterPresenter(this);
        loginAndRegisterPresenter.getUserInfoFromCache(this);
        accountEdit.setText(loginAndRegisterPresenter.getUserInfoModle().getUserAccount());
        loginBtn = (Button) findViewById(R.id.btn_go_login);
        registerBtn = (Button) findViewById(R.id.btn_go_register);
        mStrAccount = accountEdit.getText().toString();
        mStrPassword = passwordEdit.getText().toString();
        mStrUserName=userNameEdit.getText().toString();
        loginBtn.setOnClickListener(this);
        registerBtn.setOnClickListener(this);
    }

    private void saveUserInfo() {
        loginAndRegisterPresenter.saveUserInfoToCache(mStrAccount,this);
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
        loginAndRegisterPresenter.goLogin(mStrAccount, mStrPassword);

    }

    private void register() {
        loginAndRegisterPresenter.goRegister(mStrAccount, mStrPassword,mStrUserName);

    }

    @Override
    public void loginSuccess() {
        Intent intent = new Intent(LoginActivity.this, HomeActivity.class);
        startActivity(intent);
    }

    @Override
    public void registerSuccess() {

    }
}
