package com.ralemon.android.java.leaderboard;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ServiceProjectBuilder {
    public static final String URL = "https://docs.google.com/forms/d/";
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
