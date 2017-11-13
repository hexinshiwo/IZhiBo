package izhibo.uestc.com.izhibo_and.mvvm.viewmodel;

import android.databinding.BaseObservable;
import android.databinding.Bindable;

import izhibo.uestc.com.izhibo_and.BR;
import izhibo.uestc.com.izhibo_and.mvvm.bridge.RegisterStateListener;
import izhibo.uestc.com.izhibo_and.mvvm.model.RegisterUserModel;
import izhibo.uestc.com.izhibo_and.mvvm.model.RegisterUserModelImpl;
import izhibo.uestc.com.izhibo_and.mvvm.view.RegisterView;

/**
 * Created by dongfanghong on 2017/11/10.
 */

public class RegisterViewModel extends BaseObservable implements ViewModel,RegisterStateListener {
    //view
    private RegisterView registerView;
    //model
    private RegisterUserModel model;
    private String userAccount;
    private String userName;
    private String userPassword;
    public  RegisterViewModel(RegisterView registerView){
        this.registerView=registerView;
        model=new RegisterUserModelImpl();
    }
    public void register(){
        model.register(userAccount,userPassword,userName,this);
    }
    @Bindable
    public String getUserAccount() {
        return userAccount;
    }

    public void setUserAccount(String userAccount) {
        this.userAccount = userAccount;
        notifyPropertyChanged(BR.userAccount);
    }

    @Bindable
    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
        notifyPropertyChanged(BR.userName);
    }

    @Bindable
    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
        notifyPropertyChanged(BR.userPassword);
    }


    @Override
    public void beforeRegister() {
        registerView.registerProcessing();
    }

    @Override
    public void registerSuccess() {
        registerView.registerSuccess();
    }

    @Override
    public void registerError(String msg) {
        setUserAccount("");
        setUserName("");
        setUserPassword("");
        registerView.registerError(msg);
    }
}
