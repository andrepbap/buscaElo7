package br.com.andrepbap.testebuscaelo7.network.search;

import java.util.List;

import br.com.andrepbap.testebuscaelo7.model.ProductCardModel;
import br.com.andrepbap.testebuscaelo7.network.infra.BaseClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SearchClient extends BaseClient<SearchService> {

    public SearchClient() {
        super();
    }

    public void search(String term, SearchCallback callback) {
        service.search(term)
                .enqueue(new Callback<List<ProductCardModel>>() {
                    @Override
                    public void onResponse(Call<List<ProductCardModel>> call, Response<List<ProductCardModel>> response) {
                        if(!response.isSuccessful()) {
                            callback.onSearchError();
                            return;
                        }

                        callback.onSearchSuccess(response.body());
                    }

                    @Override
                    public void onFailure(Call<List<ProductCardModel>> call, Throwable t) {
                        callback.onSearchError();
                    }
                });
    }

    @Override
    public Class<SearchService> getClazz() {
        return SearchService.class;
    }

    public interface SearchCallback {
        void onSearchSuccess(List<ProductCardModel> productCardModelList);
        void onSearchError();
    }
}
