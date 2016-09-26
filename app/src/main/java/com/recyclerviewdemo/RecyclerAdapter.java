package com.recyclerviewdemo;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by ylf951 on 16/9/26.
 */
public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.ItemHolder>{
    //Member variable declaration: An arraylist of recipes to display
    private ArrayList<Recipe> mRecipes;


    //Create static inner class ItemHolder for view references
    //Make the class extend RecyclerView.ViewHolder, allowing it to be used as a ViewHolder for the adapter
    public static class ItemHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        //Add a list of references to the lifecycle of the object, to avoid querying the same information repeatedly
        private ImageView mItemImage;
        private TextView mItemTitle;
        private TextView mItemSubtitle;
        private TextView mItemDetail;
        private Recipe mRecipe;

        //Constructor for ItemHolder
        public ItemHolder(View v) {
            super(v);
            //Handle references to various subviews of the recipe item layout, which are defined in recyclerview_item_row.xml
            mItemImage = (ImageView) v.findViewById(R.id.recipe_list_thumbnail);
            mItemTitle = (TextView) v.findViewById(R.id.recipe_list_title);
            mItemSubtitle = (TextView) v.findViewById(R.id.recipe_list_subtitle);
            mItemDetail = (TextView) v.findViewById(R.id.recipe_list_detail);
            v.setOnClickListener(this);
        }

        //Bind the recipe to the ItemHolder
        public void bindRecipe(Recipe recipe){
            mRecipe = recipe;
            Picasso.with(mItemImage.getContext()).load(recipe.imageUrl).placeholder(R.mipmap.ic_launcher).into(mItemImage);
            mItemTitle.setText(recipe.title);
            mItemSubtitle.setText(recipe.description);
            mItemDetail.setText(recipe.label);

        }

        //Implement the required method for View.OnClickListener
        @Override
        public void onClick(View view) {

            //Get current context of the item view
            Context context = itemView.getContext();

            //Create an intent to show a new activity
            Intent showRecipeIntent = new Intent(context, RecipeDetailActivity.class);

            //Let the RecipeDetailActivity know the title and URL of the recipe
            showRecipeIntent.putExtra("title", mRecipe.title);
            showRecipeIntent.putExtra("url", mRecipe.instructionUrl);

            //Launch the RecipeDetailActivity by passing the intent object to the startActivity() method
            context.startActivity(showRecipeIntent);
        }
    }

    //Constructor
    public RecyclerAdapter(ArrayList<Recipe> recipes) {
        mRecipes = recipes;
    }


    //Three required methods for RecyclerView.Adapter<ItemHolder>

    //1st required method: onCreateViewHolder()
    //Since sometimes there are no ViewHolders available, RecyclerView will ask this method from RecyclerView.Adapter to make a new ViewHolder.
    @Override
    public ItemHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View inflatedView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.recyclerview_item_row, parent, false);
        return new ItemHolder(inflatedView);
    }

    //2nd required method: onBindViewHolder()
    //Use the parameter position to look up for the recipe from the list, and bind the recipe to ItemHolder
    @Override
    public void onBindViewHolder(ItemHolder holder, int position) {
        Recipe itemRecipe = mRecipes.get(position);
        holder.bindRecipe(itemRecipe);
    }
    //3rd required method: getItemCount()
    //Get the total number of items to display
    @Override
    public int getItemCount() {
        return mRecipes.size();
    }


}
