package com.oneshot.rxjavaapp.service;

import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by brettn on 4/24/17.
 */
public class PhotoRetrofit {
    private static volatile PhotoRetrofit sInstance;

    public static synchronized PhotoRetrofit getInstance() {
        if (sInstance == null) {
            synchronized (PhotoRetrofit.class) {
                if (sInstance == null) {
                    sInstance = new PhotoRetrofit();
                }
            }
        }
        return sInstance;
    }

    public static Retrofit getRetrofit() {
        return getInstance().mRetrofit;
    }

    private Retrofit mRetrofit;
    private OkHttpClient mOkClient;

    public PhotoRetrofit() {
        mOkClient = new OkHttpClient();

        OkHttpClient.Builder httpClientBuilder = new OkHttpClient.Builder();

        httpClientBuilder.interceptors().add(chain -> {
            Request request = chain.request();
            HttpUrl url = request.url().newBuilder().addQueryParameter("consumer_key", "mSDECDmxoEEEw32OgaNxZxhUFuwiZetUaK9xTyTW").build();
            request = request.newBuilder().url(url).build();
            return chain.proceed(request);
        });

        mOkClient = httpClientBuilder.build();

        mRetrofit = new Retrofit.Builder()
                .baseUrl("https://api.500px.com/v1/")
                .client(mOkClient)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }
}
