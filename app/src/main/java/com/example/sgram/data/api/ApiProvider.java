package com.example.sgram.data.api;

import android.content.Context;
import android.content.SharedPreferences;

import com.example.sgram.data.local.ResponseInterceptor;
import com.example.sgram.data.local.TokenInterceptor;
import com.google.gson.Gson;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiProvider {
    private static Retrofit retrofit;
    private static final String BASE_URL = "http://localhost:8080/example/";

    private static Context context;

    public ApiProvider(Context context) {
        ApiProvider.context = context;
    }

    public static Retrofit getRetrofit() {
        if (retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .client(getOkHttpClient(context))
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }

    public static OkHttpClient getOkHttpClient(Context context) {
        SharedPreferences sharedPreferences = context.getSharedPreferences("sgram", Context.MODE_PRIVATE);
        int t = sharedPreferences.getInt("sgram",0);

        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .addInterceptor(new TokenInterceptor(sharedPreferences))
                .addInterceptor(new ResponseInterceptor())
                .build();

        return okHttpClient;
    }

    public static AuthApi getAuthApi() {
        return getRetrofit().create(AuthApi.class);
    }

    public static ChatApi getChatApi() {
        return getRetrofit().create(ChatApi.class);
    }
}



