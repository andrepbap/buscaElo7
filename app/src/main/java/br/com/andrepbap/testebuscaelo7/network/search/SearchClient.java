package br.com.andrepbap.testebuscaelo7.network.search;

import java.util.List;

import br.com.andrepbap.testebuscaelo7.model.ProductCardModel;
import br.com.andrepbap.testebuscaelo7.network.infra.BaseCallback;
import br.com.andrepbap.testebuscaelo7.network.infra.BaseClient;

public class SearchClient extends BaseClient<SearchService> {

    public SearchClient() {
        super();
    }

    public void search(String term, OnSearchListener listener) {
        service.search(term)
                .enqueue(new SearchCallback(listener));
    }

    public void paginate(String term, int page, OnSearchPaginateListener listener) {
        service.paginate(term, page)
                .enqueue(new SearchPaginateCallback(listener));
    }

    @Override
    public Class<SearchService> getClazz() {
        return SearchService.class;
    }

    private class SearchCallback extends BaseCallback<List<ProductCardModel>> {

        private final OnSearchListener listener;

        public SearchCallback(OnSearchListener listener) {
            this.listener = listener;
        }

        @Override
        public void success(List<ProductCardModel> productCardModelList) {
            listener.onSearchSuccess(productCardModelList);
        }

        @Override
        public void error() {
            listener.onSearchError();
        }

        @Override
        public void networkError() {
            listener.onSearchError();
        }
    }

    private class SearchPaginateCallback extends BaseCallback<List<ProductCardModel>> {

        private final OnSearchPaginateListener listener;

        public SearchPaginateCallback(OnSearchPaginateListener listener) {
            this.listener = listener;
        }

        @Override
        public void success(List<ProductCardModel> productCardModelList) {
            listener.onSearchPaginateSuccess(productCardModelList);
        }

        @Override
        public void error() {
            listener.onSearchPaginateError();
        }

        @Override
        public void networkError() {
            listener.onSearchPaginateError();
        }
    }

    public interface OnSearchListener {
        void onSearchSuccess(List<ProductCardModel> productCardModelList);
        void onSearchError();
    }

    public interface OnSearchPaginateListener {
        void onSearchPaginateSuccess(List<ProductCardModel> productCardModelList);
        void onSearchPaginateError();
    }
}
