package izhibo.uestc.com.izhibo_and.module;

import dagger.Module;
import dagger.Provides;
import izhibo.uestc.com.izhibo_and.modle.UserInfoModle;

/**
 * Created by dongfanghong on 2017/10/8.
 */
@Module
public class UserInfoModule {
    @Provides
    public UserInfoModle providerUserInfo(){
        return new UserInfoModle();
    }
}
