package br.com.andrepbap.testebuscaelo7.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.webkit.WebView;

public class ProductActivity extends AppCompatActivity {

    public final static String URL = "url";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setupWebView();
    }

    private void setupWebView() {
        Intent intent = getIntent();

        if(intent.hasExtra(URL)) {
            WebView webView = new WebView(this);
            setContentView(webView);

            webView.loadUrl(intent.getStringExtra(URL));
        }
    }
}