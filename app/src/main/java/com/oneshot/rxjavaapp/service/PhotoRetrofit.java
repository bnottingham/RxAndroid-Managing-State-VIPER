package com.oneshot.rxjavaapp.service;

import android.content.Context;

import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import com.oneshot.rxjavaapp.R;

import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by brettn on 4/24/17.
 */
public class PhotoRetrofit {
    public static final String KEY_CONSUMER_KEY = "consumer_key";
    public static final String BASE_URL = "https://api.500px.com/v1/";


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
    private String mConsumerKey;

    public PhotoRetrofit() {
        mOkClient = new OkHttpClient();

        OkHttpClient.Builder httpClientBuilder = new OkHttpClient.Builder();

        httpClientBuilder.interceptors().add(chain -> {
            Request request = chain.request();
            HttpUrl url = request.url().newBuilder().addQueryParameter(KEY_CONSUMER_KEY, mConsumerKey).build();
            request = request.newBuilder().url(url).build();
            return chain.proceed(request);
        });

        mOkClient = httpClientBuilder.build();

        mRetrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(mOkClient)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    public void init(Context context) {
        mConsumerKey = context.getString(R.string.api_consumer_key);
    }
}
