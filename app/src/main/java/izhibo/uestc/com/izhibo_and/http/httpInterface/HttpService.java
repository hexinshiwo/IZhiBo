package izhibo.uestc.com.izhibo_and.http.httpInterface;

import izhibo.uestc.com.izhibo_and.model.BaseHttpModel;
import izhibo.uestc.com.izhibo_and.model.LoginModel;
import izhibo.uestc.com.izhibo_and.model.LoginUserDataModel;
import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by dongfanghong on 2017/10/17.
 */

public interface HttpService {
    @GET("login")
    Observable<LoginModel>login(@Query("userAccount")String userAccount, @Query("userPassword")String userPassword);
    @GET("Register")
    Observable<BaseHttpModel>register(@Query("userAccount")String userAccount,@Query("userPassword")String userPassword
            ,@Query("userName")String userName);
    @GET("login")
    Observable<LoginModel>loginGetToken(@Query("userAccount")String userAccount, @Query("userPassword")String userPassword);
    @GET("login/userInfo")
    Observable<LoginUserDataModel>getUserData(@Query("token")String token);
}
