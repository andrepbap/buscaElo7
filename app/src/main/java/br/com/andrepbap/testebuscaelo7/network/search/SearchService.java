package br.com.andrepbap.testebuscaelo7.network.search;

import java.util.List;

import br.com.andrepbap.testebuscaelo7.model.ProductCardModel;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface SearchService {

    @GET("api/1/products")
    Call<List<ProductCardModel>> search(@Query("q") String term);

    @GET("api/1/products")
    Call<List<ProductCardModel>> paginate(@Query("q") String term, @Query("page") int page);

}
