package com.equipouno.app.view.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.equipouno.app.R
import com.equipouno.app.model.Recipe

class ListRecipesAdapater(private val recipes: List<Recipe>, private val onItemClick: (Recipe) -> Unit) :
    RecyclerView.Adapter<ListRecipesAdapater.RecipeViewHolder>() {

    inner class RecipeViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val recipeName: TextView = itemView.findViewById(R.id.recipeName)
        val recipeDescription: TextView = itemView.findViewById(R.id.recipeDescription)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecipeViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.recipe_item_layout, parent, false)
        return RecipeViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: RecipeViewHolder, position: Int) {
        val currentRecipe = recipes[position]
        holder.recipeName.text = currentRecipe.name
        holder.recipeDescription.text = currentRecipe.description

        holder.itemView.setOnClickListener {
            onItemClick(currentRecipe)
        }
    }

    override fun getItemCount(): Int {
        return recipes.size
    }
}
