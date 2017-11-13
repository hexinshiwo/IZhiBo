package izhibo.uestc.com.izhibo_and.module;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import izhibo.uestc.com.izhibo_and.model.UserInfoModel;

/**
 * Created by dongfanghong on 2017/10/8.
 */
@Module
public class UserInfoModule {
    @Provides
    @Singleton
    public UserInfoModel providerUserInfo(){
        return new UserInfoModel();
    }
}
