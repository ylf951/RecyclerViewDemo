package com.recyclerviewdemo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.webkit.WebView;

public class RecipeDetailActivity extends AppCompatActivity {
    private WebView mWebView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe_detail);

        //Retrieve the recipe data from the Intent passed by MainActivity by using the getExtras() method
        String title = this.getIntent().getExtras().getString("title");
        String url = this.getIntent().getExtras().getString("url");

        //Set the title on the action bar of this activity
        setTitle(title);

        //Initialize mWebView to the web view defined in the XML layout
        mWebView = (WebView) findViewById(R.id.detail_web_view);

        //Load the recipe web page by calling loadUrl() with the corresponding recipe's URL on the web view object
        mWebView.loadUrl(url);
    }
}
