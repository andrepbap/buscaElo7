package br.com.andrepbap.testebuscaelo7.network.infra;

import retrofit2.Retrofit;

public abstract class BaseClient<T> {

    protected final T service;

    public BaseClient() {
        Retrofit retrofit = RetrofitProvider.getInstance().getRetrofit();
        service = retrofit.create(getClazz());
    }

    protected abstract Class<T> getClazz();
}
