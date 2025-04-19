package com.example.recetteapp

import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class RecipeActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recipe)

        // Get data passed via Intent
        val recipeName = intent.getStringExtra("RECIPE_NAME") ?: "Unknown Recipe"
        val recipeImage = intent.getIntExtra("RECIPE_IMAGE", R.drawable.img)
        val recipeDescription = intent.getStringExtra("RECIPE_DESCRIPTION") ?: "No description available."

        // Find views
        val recipeTitle = findViewById<TextView>(R.id.recipe_title)
        val recipeImageView = findViewById<ImageView>(R.id.recipe_image)
        val recipeDescriptionView = findViewById<TextView>(R.id.recipe_description)

        // Set data
        recipeTitle.text = recipeName
        recipeImageView.setImageResource(recipeImage)
        recipeDescriptionView.text = recipeDescription
    }
}
