package br.com.andrepbap.testebuscaelo7.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.SearchView;

import java.util.List;

import br.com.andrepbap.testebuscaelo7.R;
import br.com.andrepbap.testebuscaelo7.model.ProductCardModel;
import br.com.andrepbap.testebuscaelo7.network.search.SearchClient;
import br.com.andrepbap.testebuscaelo7.ui.recyclerAdapter.ProductListAdapter;

public class SearchActivity extends AppCompatActivity implements 
        SearchClient.SearchCallback,
        SearchView.OnQueryTextListener {

    private static final String EMPTY_QUERY_FOR_MOCK_SEARCH = "";

    private SearchView searchView;
    private RecyclerView productListRecyclerView;
    private ProductListAdapter productListAdapter;
    private SearchClient searchClient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        searchClient = new SearchClient();

        setupToolbar();
        setupSearchView();
        setupBackButton();
    }

    private void setupBackButton() {
        ImageView toolbarBackButton = findViewById(R.id.toolbar_back_button);
        toolbarBackButton.setOnClickListener(view -> finish());
    }

    private void setupSearchView() {
        searchView = (SearchView) findViewById(R.id.search_view);
        searchView.setOnQueryTextListener(this);
    }

    private void setupToolbar() {
        Toolbar appToolbar = findViewById(R.id.search_toolbar);
        setSupportActionBar(appToolbar);
    }

    @Override
    public void onSearchSuccess(List<ProductCardModel> productCardModelList) {
        if(productListRecyclerView == null) {
            productListRecyclerView = findViewById(R.id.product_list_recycler_view);
            productListAdapter = new ProductListAdapter(this, productCardModelList);
            productListRecyclerView.setAdapter(productListAdapter);
            return;
        }

        productListAdapter.refreshWith(productCardModelList);
    }

    @Override
    public void onSearchError() {

    }

    @Override
    public boolean onQueryTextSubmit(String query) {
        searchClient.search(EMPTY_QUERY_FOR_MOCK_SEARCH, SearchActivity.this);
        searchView.clearFocus();
        return true;
    }

    @Override
    public boolean onQueryTextChange(String query) {
        return false;
    }
}