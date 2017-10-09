package izhibo.uestc.com.izhibo_and.presenter;

import com.tencent.ilivesdk.ILiveCallBack;
import com.tencent.ilivesdk.core.ILiveLoginManager;

import izhibo.uestc.com.izhibo_and.component.DaggerUserInfoComponent;
import izhibo.uestc.com.izhibo_and.component.UserInfoComponent;
import izhibo.uestc.com.izhibo_and.modle.UserInfoModle;
import izhibo.uestc.com.izhibo_and.module.UserInfoModule;
import izhibo.uestc.com.izhibo_and.uiinterface.LoginAndRegisterView;

/**
 * Created by dongfanghong on 2017/10/8.
 */

public class LoginAndRegisterPresenter {
    private LoginAndRegisterView loginAndRegisterView;

    public LoginAndRegisterPresenter(LoginAndRegisterView loginAndRegisterView) {

        this.loginAndRegisterView = loginAndRegisterView;
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

    public void saveUserInfo(String userAccount) {
        UserInfoComponent component = DaggerUserInfoComponent.builder()
                .userInfoModule(new UserInfoModule())
                .build();
        UserInfoModle userInfoModle = component.getUserInfo();
        userInfoModle.setUserAccount(userAccount);

    }
}
