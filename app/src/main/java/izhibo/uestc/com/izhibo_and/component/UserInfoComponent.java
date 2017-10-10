package izhibo.uestc.com.izhibo_and.component;

import javax.inject.Singleton;

import dagger.Component;
import izhibo.uestc.com.izhibo_and.module.UserInfoModule;
import izhibo.uestc.com.izhibo_and.presenter.IOnlinePresenter;
import izhibo.uestc.com.izhibo_and.presenter.LoginAndRegisterPresenter;

/**
 * Created by dongfanghong on 2017/10/8.
 */
@Singleton
@Component(modules = UserInfoModule.class)
public interface UserInfoComponent {
    void inject (LoginAndRegisterPresenter presenter);
    void inject (IOnlinePresenter presenter);

}
