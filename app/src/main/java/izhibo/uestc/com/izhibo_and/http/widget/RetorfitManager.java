package izhibo.uestc.com.izhibo_and.http.widget;

import android.content.Context;

import com.google.gson.GsonBuilder;

import izhibo.uestc.com.izhibo_and.http.httpInterface.HttpService;
import izhibo.uestc.com.izhibo_and.http.url.UrlManager;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by dongfanghong on 2017/10/17.
 */

public class RetorfitManager {
    private Context mContext;
    private OkHttpClient mClient = new OkHttpClient();
    private Retrofit mRetrofit = null;
    private GsonConverterFactory mFactory = GsonConverterFactory.create(new GsonBuilder().create());
    private volatile static RetorfitManager instance = null;

    private RetorfitManager() {

        init();
    }

    public static RetorfitManager getInstance() {
        if (instance == null) {
            synchronized (RetorfitManager.class) {
                if (instance == null)
                    instance = new RetorfitManager();
            }
        }
        return instance;
    }

    private void init() {
        mRetrofit = new Retrofit.Builder()
                .baseUrl(UrlManager.URL_BASE)
                .client(mClient)
                .addConverterFactory(mFactory)
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();
    }
    public HttpService getService(){
        return mRetrofit.create(HttpService.class);
    }


}
