package br.com.andrepbap.testebuscaelo7.network.infra;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public abstract class BaseCallback<T> implements Callback<T> {

    @Override
    public void onResponse(Call<T> call, Response<T> response) {
        if(!response.isSuccessful()) {
            error();
            return;
        }

        success(response.body());
    }

    @Override
    public void onFailure(Call<T> call, Throwable t) {
        networkError();
    }

    public abstract void success(T response);
    public abstract void error();
    public abstract void networkError();
}
