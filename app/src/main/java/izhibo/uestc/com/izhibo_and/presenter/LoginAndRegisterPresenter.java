package izhibo.uestc.com.izhibo_and.presenter;

import android.content.Context;
import android.content.SharedPreferences;

import com.tencent.ilivesdk.ILiveCallBack;
import com.tencent.ilivesdk.core.ILiveLoginManager;

import izhibo.uestc.com.izhibo_and.component.DaggerUserInfoComponent;
import izhibo.uestc.com.izhibo_and.component.UserInfoComponent;
import izhibo.uestc.com.izhibo_and.modle.Constants;
import izhibo.uestc.com.izhibo_and.modle.UserInfoModle;
import izhibo.uestc.com.izhibo_and.module.UserInfoModule;
import izhibo.uestc.com.izhibo_and.uiinterface.LoginAndRegisterView;

/**
 * Created by dongfanghong on 2017/10/8.
 */

public class LoginAndRegisterPresenter {
    private LoginAndRegisterView loginAndRegisterView;
    private UserInfoComponent component;
    private UserInfoModle userInfoModle;
    public LoginAndRegisterPresenter(LoginAndRegisterView loginAndRegisterView) {

        this.loginAndRegisterView = loginAndRegisterView;
         component = DaggerUserInfoComponent.builder()
                .userInfoModule(new UserInfoModule())
                .build();
    }

    public void goLogin(String account, String password) {
        ILiveLoginManager.getInstance().tlsLoginAll(account, password, new ILiveCallBack() {
            @Override
            public void onSuccess(Object data) {
                loginAndRegisterView.loginSuccess();
            }

            @Override
            public void onError(String module, int errCode, String errMsg) {

            }
        });



    }

    public void goRegister(String account, String password) {
        ILiveLoginManager.getInstance().tlsRegister(account, password, new ILiveCallBack() {
            @Override
            public void onSuccess(Object data) {
                loginAndRegisterView.registerSuccess();
            }

            @Override
            public void onError(String module, int errCode, String errMsg) {

            }
        });


    }
    public void getUserInfoFromCache(Context context){
        SharedPreferences sharedPreferences=context.getSharedPreferences(Constants.UESRINFO,0);
        String userAccount=sharedPreferences.getString(Constants.UESACCOUNT,null);
        userInfoModle = component.getUserInfo();
        userInfoModle.setUserAccount(userAccount);
    }
    public UserInfoModle getUserInfoModle()
    {
        return userInfoModle;
    }
    public void saveUserInfoToCache(String userAccount, Context context) {
        userInfoModle = component.getUserInfo();
        userInfoModle.setUserAccount(userAccount);
        SharedPreferences sharedPreferences=context.getApplicationContext().getSharedPreferences(Constants.UESRINFO,0);
        SharedPreferences.Editor editor=sharedPreferences.edit();
        editor.putString(Constants.UESACCOUNT,userAccount);
        editor.commit();
    }
}
