package izhibo.uestc.com.izhibo_and.mvvm.model;

import izhibo.uestc.com.izhibo_and.http.widget.RetorfitManager;
import izhibo.uestc.com.izhibo_and.model.BaseHttpModel;
import izhibo.uestc.com.izhibo_and.model.Constants;
import izhibo.uestc.com.izhibo_and.mvvm.bridge.RegisterStateListener;
import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by dongfanghong on 2017/11/13.
 */

public class RegisterUserModelImpl implements RegisterUserModel {
    @Override
    public void register(String userAccount, String userPassword, String userName, final RegisterStateListener listener) {
        listener.beforeRegister();
        RetorfitManager.getInstance().getService()
                .register(userAccount, userPassword, userName)
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
                        listener.registerError(Constants.NETWORK_ERROR);
                    }

                    @Override
                    public void onNext(BaseHttpModel baseHttpModel) {
                        int code = baseHttpModel.getCode();
                        String msg = baseHttpModel.getMsg();
                        //如果后台认证注册成功
                        if (code == 200) {
                           listener.registerSuccess();

                        }
                        //后台注册失败
                        else {
                          listener.registerError(msg);

                        }
                    }
                });
    }
}
