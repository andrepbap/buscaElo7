package br.com.andrepbap.testebuscaelo7.network.infra;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitProvider {

    private static RetrofitProvider instance;
    private final Retrofit retrofit;

    private RetrofitProvider() {
        retrofit = new Retrofit.Builder()
                .baseUrl("https://5dc05c0f95f4b90014ddc651.mockapi.io/elo7/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    public static RetrofitProvider getInstance() {
        if(instance == null) {
            instance = new RetrofitProvider();
        }

        return instance;
    }

    public Retrofit getRetrofit() {
        return retrofit;
    }
}
