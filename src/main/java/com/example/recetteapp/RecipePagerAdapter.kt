package com.example.recetteapp

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class RecipePagerAdapter(
    private val recipes: List<Triple<Int, String, String>>, // Image, Name, Description
    private val onRecipeClick: (String, Int, String) -> Unit
) : RecyclerView.Adapter<RecipePagerAdapter.RecipeViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecipeViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.recipe_bf, parent, false)
        return RecipeViewHolder(view)
    }

    override fun onBindViewHolder(holder: RecipeViewHolder, position: Int) {
        val recipe = recipes[position]
        holder.bind(recipe)
        holder.itemView.setOnClickListener { onRecipeClick(recipe.second, recipe.first, recipe.third) }
    }

    override fun getItemCount(): Int = recipes.size

    class RecipeViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val imageView: ImageView = view.findViewById(R.id.slideImage)
        private val textView: TextView = view.findViewById(R.id.slideText)

        fun bind(recipe: Triple<Int, String, String>) {
            imageView.setImageResource(recipe.first)
            textView.text = recipe.second
        }
    }
}

