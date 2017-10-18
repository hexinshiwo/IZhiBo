package izhibo.uestc.com.izhibo_and.presenter;

import android.content.Context;
import android.content.SharedPreferences;

import com.tencent.ilivesdk.ILiveCallBack;
import com.tencent.ilivesdk.core.ILiveLoginManager;

import javax.inject.Inject;

import izhibo.uestc.com.izhibo_and.component.DaggerUserInfoComponent;
import izhibo.uestc.com.izhibo_and.http.widget.RetorfitManager;
import izhibo.uestc.com.izhibo_and.modle.BaseHttpModel;
import izhibo.uestc.com.izhibo_and.modle.Constants;
import izhibo.uestc.com.izhibo_and.modle.UserInfoModle;
import izhibo.uestc.com.izhibo_and.module.UserInfoModule;
import izhibo.uestc.com.izhibo_and.uiInterface.LoginAndRegisterView;
import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by dongfanghong on 2017/10/8.
 */

public class LoginAndRegisterPresenter {
    private LoginAndRegisterView loginAndRegisterView;

    @Inject
    UserInfoModle userInfoModle;

    public LoginAndRegisterPresenter(LoginAndRegisterView loginAndRegisterView) {

        this.loginAndRegisterView = loginAndRegisterView;
        DaggerUserInfoComponent.builder()
                .userInfoModule(new UserInfoModule())
                .build().inject(this);
    }

    public void goLogin(final String account, final String password) {
        RetorfitManager.getInstance().getService()
                .login(account, password)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<BaseHttpModel>() {
                    @Override
                    public void onCompleted() {
                        //自定义dialog在此dismiss
                    }

                    @Override
                    public void onError(Throwable e) {
                        //网络错误
                    }

                    @Override
                    public void onNext(BaseHttpModel baseHttpModel) {
                        int code = baseHttpModel.getCode();
                        //如果后台认证登录成功
                        if (code == 200) {
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
                    }
                });


    }

    public void goRegister(final String account, final String password, final String userName) {
        RetorfitManager.getInstance().getService()
                .register(account, password, userName)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<BaseHttpModel>() {
                    @Override
                    public void onCompleted() {
                        //自定义dialog在此dismiss
                    }

                    @Override
                    public void onError(Throwable e) {
                       //网络错误
                    }

                    @Override
                    public void onNext(BaseHttpModel baseHttpModel) {
                        int code = baseHttpModel.getCode();
                        //如果后台认证注册成功
                        if (code == 200) {
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
                    }
                });


    }

    public void getUserInfoFromCache(Context context) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(Constants.UESRINFO, 0);
        String userAccount = sharedPreferences.getString(Constants.UESACCOUNT, null);
        userInfoModle.setUserAccount(userAccount);
    }

    public UserInfoModle getUserInfoModle() {
        return userInfoModle;
    }

    public void saveUserInfoToCache(String userAccount, Context context) {
        userInfoModle.setUserAccount(userAccount);
        SharedPreferences sharedPreferences = context.getApplicationContext().getSharedPreferences(Constants.UESRINFO, 0);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(Constants.UESACCOUNT, userAccount);
        editor.commit();
    }
    public void clear(){

    }
}
