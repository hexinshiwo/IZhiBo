package izhibo.uestc.com.izhibo_and.mvvm.view;

/**
 * Created by dongfanghong on 2017/11/13.
 */

public interface RegisterView extends BaseView {
    public void registerSuccess();
    public void registerError(String message);
    public void registerProcessing();
}
