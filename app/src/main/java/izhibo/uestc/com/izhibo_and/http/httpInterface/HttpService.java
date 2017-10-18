package izhibo.uestc.com.izhibo_and.http.httpInterface;

import izhibo.uestc.com.izhibo_and.modle.BaseHttpModel;
import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by dongfanghong on 2017/10/17.
 */

public interface HttpService {
    @GET("login")
    Observable<BaseHttpModel>login(@Query("userAccount")String userAccount,@Query("userPassword")String userPassword);
    @GET("register")
    Observable<BaseHttpModel>register(@Query("userAccount")String userAccount,@Query("userPassword")String userPassword
            ,@Query("userName")String userName);
}
