package izhibo.uestc.com.izhibo_and.uiInterface;

/**
 * Created by dongfanghong on 2017/10/8.
 */

public interface LoginView {
    //登录成功之后跳转主界面
    public void loginSuccess();
    //在http连接之前调用，此时dialog可见
    public void setDialogVisibleBeforeHttp();
    //服务器反馈消息的回调
    public void showToast(String msg);

}
