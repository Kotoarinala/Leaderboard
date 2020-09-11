package com.ralemon.android.java.leaderboard;

import android.os.Build;

import java.io.IOException;
import java.util.Locale;
import java.util.concurrent.TimeUnit;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ServiceBuilder {
    public static final String URL = "https://gadsapi.herokuapp.com/api/";

    public static HttpLoggingInterceptor sLogger =
            new HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY);

    public static OkHttpClient.Builder sOkHttp =
            new OkHttpClient.Builder()

                    .addInterceptor(sLogger);

    private static Retrofit.Builder sBuilder = new Retrofit.Builder().baseUrl(URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(sOkHttp.build());


    public static Retrofit sRetrofit = sBuilder.build();
    public static <S> S buildService(Class<S> serviceType){
        return sRetrofit.create(serviceType);
    }
}
