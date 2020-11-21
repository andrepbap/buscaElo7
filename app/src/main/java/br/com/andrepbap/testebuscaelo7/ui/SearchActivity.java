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

public class SearchActivity extends AppCompatActivity implements SearchClient.SearchCallback {

    private SearchView searchView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

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
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                new SearchClient().search("", SearchActivity.this);
                searchView.clearFocus();
                return true;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                return false;
            }
        });
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