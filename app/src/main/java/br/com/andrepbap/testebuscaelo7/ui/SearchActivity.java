package br.com.andrepbap.testebuscaelo7.ui;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.RecyclerView;

import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.SearchView;

import java.util.List;

import br.com.andrepbap.testebuscaelo7.R;
import br.com.andrepbap.testebuscaelo7.model.ProductCardModel;
import br.com.andrepbap.testebuscaelo7.network.search.SearchClient;

public class SearchActivity extends AppCompatActivity implements SearchClient.SearchCallback {

    private SearchView searchView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        setupToolbar();
        setupSearchView();
        setupBackButton();
        handleSearchIntent();
    }

    private void handleSearchIntent() {
        Intent intent = getIntent();
        if (Intent.ACTION_SEARCH.equals(intent.getAction())) {
            keepSearchFocusCleared();

            String query = intent.getStringExtra(SearchManager.QUERY);
            searchView.setQuery(query, false);
            new SearchClient().search("", this);
        }
    }

    private void keepSearchFocusCleared() {
        if(searchView != null) {
            searchView.setFocusable(false);
        }
    }

    private void setupBackButton() {
        ImageView toolbarBackButton = findViewById(R.id.toolbar_back_button);
        toolbarBackButton.setOnClickListener(view -> finish());
    }

    private void setupSearchView() {
        SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        searchView = (SearchView) findViewById(R.id.search_view);
        searchView.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));
    }

    private void setupToolbar() {
        Toolbar appToolbar = findViewById(R.id.search_toolbar);
        setSupportActionBar(appToolbar);
    }

    @Override
    public void success(List<ProductCardModel> productCardModelList) {
        RecyclerView productListRecyclerView = findViewById(R.id.product_list_recycler_view);
        productListRecyclerView.setAdapter(new ProductListAdapter(this, productCardModelList));
    }

    @Override
    public void error() {

    }
}