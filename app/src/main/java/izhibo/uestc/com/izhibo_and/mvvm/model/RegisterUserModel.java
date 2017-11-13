package izhibo.uestc.com.izhibo_and.mvvm.model;

import izhibo.uestc.com.izhibo_and.mvvm.bridge.RegisterStateListener;

/**
 * Created by dongfanghong on 2017/11/13.
 */

public interface RegisterUserModel {
  public void register(String userAccount,String userPassword,String userName,RegisterStateListener listener);
}
