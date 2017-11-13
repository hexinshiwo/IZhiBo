package izhibo.uestc.com.izhibo_and.mvvm.bridge;

/**
 * 注册过程的状态监听器
 * Created by dongfanghong on 2017/11/13.
 */

public interface RegisterStateListener {
    public void beforeRegister();
    public void registerSuccess();
    public void registerError(String msg);
}
