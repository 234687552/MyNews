package com.example.administrator.mynews.utils;


import retrofit2.Retrofit;

/**
 * Created by Administrator on 2016/8/3 0003.
 */
public class RetrofitUtil {
    private static final String TAG = "RetrofitUtil";


    private static final String BASEURL="http://ip.taobao.com/service/";

    private static RetrofitUtil instance;
    private Retrofit retrofit;
    public   RetrofitUtil() {
        this.retrofit=new Retrofit.Builder()
                .baseUrl(BASEURL)
                .build();
    }

    //single instance
    public static RetrofitUtil getInstance(){
        if (instance == null) {
            synchronized (RetrofitUtil.class){

                if (instance==null){
                    instance=new RetrofitUtil();
                    return instance;
                }
            }
        }
        return instance;
    }

    public <S> S createService(Class<S> serviceClass) {
        return retrofit.create(serviceClass);
    }

}
