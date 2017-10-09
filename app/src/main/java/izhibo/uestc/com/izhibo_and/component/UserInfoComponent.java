package izhibo.uestc.com.izhibo_and.component;

import dagger.Component;
import izhibo.uestc.com.izhibo_and.modle.UserInfoModle;
import izhibo.uestc.com.izhibo_and.module.UserInfoModule;

/**
 * Created by dongfanghong on 2017/10/8.
 */
@Component(modules = UserInfoModule.class)
public interface UserInfoComponent {
    public UserInfoModle getUserInfo();


}
