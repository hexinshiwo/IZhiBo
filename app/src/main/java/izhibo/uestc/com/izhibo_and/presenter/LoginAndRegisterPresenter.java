package izhibo.uestc.com.izhibo_and.presenter;

import android.content.Context;
import android.content.SharedPreferences;

import javax.inject.Inject;

import izhibo.uestc.com.izhibo_and.component.DaggerUserInfoComponent;
import izhibo.uestc.com.izhibo_and.http.widget.RetorfitManager;
import izhibo.uestc.com.izhibo_and.model.Constants;
import izhibo.uestc.com.izhibo_and.model.LoginModel;
import izhibo.uestc.com.izhibo_and.model.UserInfoModel;
import izhibo.uestc.com.izhibo_and.module.UserInfoModule;
import izhibo.uestc.com.izhibo_and.uiInterface.LoginView;
import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by dongfanghong on 2017/10/8.
 */

public class LoginAndRegisterPresenter {
    private LoginView loginView;

    @Inject
    UserInfoModel userInfoModel;

    public LoginAndRegisterPresenter(LoginView loginView) {
        this.loginView = loginView;
        DaggerUserInfoComponent.builder()
                .userInfoModule(new UserInfoModule())
                .build().inject(this);
    }

    public void goLogin(final String account, final String password) {
        loginView.setDialogVisibleBeforeHttp();//dialog显示
        RetorfitManager.getInstance().getService()
                .login(account, password)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<LoginModel>() {
                    @Override
                    public void onCompleted() {
                        //自定义dialog在此dismiss
                    }

                    @Override
                    public void onError(Throwable e) {
                        //网络错误
                        loginView.showToast(Constants.NETWORK_ERROR);
                    }

                    @Override
                    public void onNext(LoginModel loginModel) {
                        int code = loginModel.getCode();
                        String msg=loginModel.getMsg();
                        //如果后台认证登录成功
                        if (code == 200) {
                            userInfoModel.setUserName(loginModel.getData().getUserName());//获取用户名
                            userInfoModel.setToken(loginModel.getData().getToken());
                            loginView.loginSuccess();
                        }
                        else {
                            loginView.showToast(msg);
                        }
                    }
                });


    }
    //token认证的支持
//    public void goAccessTokenAndLogin(final String userAccount, final String userPassword) {
//        RetorfitManager.getInstance().getService().loginGetToken(userAccount, userPassword)
//                .subscribeOn(Schedulers.io())//指定loginGetToken操作发生在io线程
//                .throttleFirst(500, java.util.concurrent.TimeUnit.MILLISECONDS)//按钮抖动过滤，监听间隔为500ms
//                .doOnSubscribe(new Action0() {
//                    @Override
//                    public void call() {
//                        loginView.setDialogVisibleBeforeHttp();
//                    }
//                })
//                .subscribeOn(AndroidSchedulers.mainThread())//设置上一个doOnSubscribe操作的线程为主线程
//                .observeOn(Schedulers.newThread())//指定flatMap的操作在一个新线程之中
//                .flatMap(new Func1<LoginBaseUserDataModel, Observable<LoginModel>>() {
//                    @Override
//                    public Observable<LoginModel> call(LoginBaseUserDataModel loginBaseUserDataModel) {
//                        String token = loginBaseUserDataModel.getToken();
//                        userInfoModel.setToken(token);
//                        return RetorfitManager.getInstance().getService().getUserData(userInfoModel.getToken());
//                    }
//
//                })
//                .observeOn(AndroidSchedulers.mainThread())//指定下面的subscribe的操作在主线程
//                .subscribe(new Observer<LoginModel>() {
//                    @Override
//                    public void onCompleted() {
//
//                    }
//
//                    @Override
//                    public void onError(Throwable e) {
//
//                    }
//
//                    @Override
//                    public void onNext(LoginModel loginModel ) {
//                        int code = loginModel.getCode();
//                        //如果后台认证登录成功
//                        if (code == 200) {
//                            userInfoModel.setUserName(loginModel.getData().getUserName());//获取用户名
//                            userInfoModel.setAttentionAnchorList(loginModel.getData().getAnchorAttentionList());//获取关注的主播列表
//                            ILiveLoginManager.getInstance().tlsLoginAll(userAccount, userPassword, new ILiveCallBack() {
//                                @Override
//                                public void onSuccess(Object data) {
//                                    loginView.loginSuccess();
//                                }
//
//                                @Override
//                                public void onError(String module, int errCode, String errMsg) {
//
//                                }
//                            });
//                        }
//                    }
//                });
//    }

    public void getUserInfoFromCache(Context context) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(Constants.UESRINFO, 0);
        String userAccount = sharedPreferences.getString(Constants.UESACCOUNT, null);
        userInfoModel.setUserAccount(userAccount);
    }

    public UserInfoModel getUserInfoModle() {
        return userInfoModel;
    }

    public void saveUserInfoToCache(String userAccount, Context context) {
        userInfoModel.setUserAccount(userAccount);
        SharedPreferences sharedPreferences = context.getApplicationContext().getSharedPreferences(Constants.UESRINFO, 0);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(Constants.UESACCOUNT, userAccount);
        editor.commit();
    }

    //回收数据，避免内存泄漏
    public void clear() {


    }
}
