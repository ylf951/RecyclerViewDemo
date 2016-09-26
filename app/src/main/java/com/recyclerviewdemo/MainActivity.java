package com.recyclerviewdemo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    //Member variables declaration
    private RecyclerView mRecyclerView;
    private LinearLayoutManager mLinearLayoutManager;
    private RecyclerAdapter mAdapter;

    private ArrayList<Recipe> mRecipeList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Reference RecyclerView
        mRecyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        //LinearLayoutManager positions items to look like a standard ListView
        //Layout Managers comes in three default flavors: LinearLayoutManager, GridLayoutManager, and StaggerGridLayoutManager
        mLinearLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLinearLayoutManager);

        //Read recipes from file recipes.json
        mRecipeList = Recipe.getRecipesFromFile("recipes.json", this);

        //Create a RecyclerView Adapter
        mAdapter = new RecyclerAdapter(mRecipeList);
        //Attach the adapter to the RecyclerView
        mRecyclerView.setAdapter(mAdapter);
    }
}
