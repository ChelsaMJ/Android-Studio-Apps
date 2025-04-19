package com.example.recetteapp

import android.content.Intent
import android.os.Bundle
import android.view.Gravity
import android.view.Menu
import android.view.MenuItem
import android.view.WindowManager
import android.widget.Button
import android.widget.PopupWindow
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager2.widget.ViewPager2

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Set up the toolbar
        val toolbar = findViewById<androidx.appcompat.widget.Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayShowTitleEnabled(false) // Hides the title

        val breakfastButton = findViewById<Button>(R.id.b1)
        val lunchButton = findViewById<Button>(R.id.b2)
        val snacksButton = findViewById<Button>(R.id.b3)
        val dinnerButton = findViewById<Button>(R.id.b4)

        breakfastButton.setOnClickListener { showFullscreenPopup("Breakfast") }
        lunchButton.setOnClickListener { showFullscreenPopup("Lunch") }
        snacksButton.setOnClickListener { showFullscreenPopup("Snacks") }
        dinnerButton.setOnClickListener { showFullscreenPopup("Dinner") }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.i1 -> {
                Toast.makeText(this, "Get idea clicked", Toast.LENGTH_SHORT).show()
                true
            }
            R.id.i2 -> {
                Toast.makeText(this, "Recipe of the day clicked", Toast.LENGTH_SHORT).show()
                true
            }
            R.id.i3 -> {
                Toast.makeText(this, "Popular clicked", Toast.LENGTH_SHORT).show()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }






    private fun showFullscreenPopup(category: String) {
        val inflater = layoutInflater
        val popupView = inflater.inflate(R.layout.popup_fullscreen, null)

        val popupWindow = PopupWindow(
            popupView,
            WindowManager.LayoutParams.MATCH_PARENT,
            WindowManager.LayoutParams.MATCH_PARENT,
            true
        )

        val viewPager = popupView.findViewById<ViewPager2>(R.id.viewPager)
        val closeButton = popupView.findViewById<Button>(R.id.closePopup)

        // Recipe data based on category
        val recipes = when (category) {
            "Lunch" -> listOf(
                Triple(R.drawable.img_4, "Grilled Sandwich", "Delicious sandwich with melted cheese and veggies."),
                Triple(R.drawable.img_5, "Caesar Salad", "Crispy romaine lettuce with creamy Caesar dressing."),
                Triple(R.drawable.img_6, "Pasta", "Rich and creamy pasta with a hint of garlic."),
                Triple(R.drawable.img_7, "Rice Bowl", "A healthy mix of rice, veggies, and protein.")
            )
            "Snacks" -> listOf(
                Triple(R.drawable.img_8, "Spring Rolls", "Crispy rolls filled with veggies and spices."),
                Triple(R.drawable.img_9, "Wraps", "Tortilla wraps with a savory filling."),
                Triple(R.drawable.img_10, "Garlic Bread", "Toasted bread with garlic and herb butter."),
                Triple(R.drawable.img_11, "Mini Pizzas", "Bite-sized pizzas topped with cheese and veggies.")
            )
            "Dinner" -> listOf(
                Triple(R.drawable.img_12, "Steak", "Juicy steak grilled to perfection."),
                Triple(R.drawable.img_13, "Grilled Salmon", "Tender salmon fillet with a lemon glaze."),
                Triple(R.drawable.img_14, "Tacos", "Soft tacos filled with spicy meat and veggies."),
                Triple(R.drawable.img_15, "Veg Curry", "Aromatic curry with mixed vegetables and spices.")
            )
            else -> listOf(
                Triple(R.drawable.img_1, "Pancakes", "Fluffy pancakes with syrup and berries."),
                Triple(R.drawable.img, "Omelette", "Classic omelette with cheese and veggies."),
                Triple(R.drawable.img_2, "Smoothie", "Refreshing fruit smoothie with yogurt."),
                Triple(R.drawable.img_3, "French Toast", "Golden brown toast with a hint of cinnamon.")
            )
        }


        viewPager.adapter = RecipePagerAdapter(recipes) { recipeName, recipeImage, recipeDescription ->
            popupWindow.dismiss()
            navigateToRecipe(recipeName, recipeImage, recipeDescription)
        }



        closeButton.setOnClickListener { popupWindow.dismiss() }
        popupWindow.showAtLocation(findViewById(android.R.id.content), Gravity.CENTER, 0, 0)
    }


    private fun navigateToRecipe(recipeName: String, recipeImage: Int, recipeDescription: String) {
        val intent = Intent(this, RecipeActivity::class.java)
        intent.putExtra("RECIPE_NAME", recipeName)
        intent.putExtra("RECIPE_IMAGE", recipeImage)
        intent.putExtra("RECIPE_DESCRIPTION", recipeDescription)
        startActivity(intent)
    }


}
