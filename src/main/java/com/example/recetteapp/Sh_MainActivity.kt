package com.example.recetteapp

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar

class Sh_MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sh_main)

        val toolbar: Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)
        supportActionBar?.title = "Recette"

        val breakfastButton: Button = findViewById(R.id.button2)
        val lunchButton: Button = findViewById(R.id.button6)
        val snacksButton: Button = findViewById(R.id.button7)
        val dinnerButton: Button = findViewById(R.id.button8)
        val exitButton: Button = findViewById(R.id.button9)

        breakfastButton.setOnClickListener { showPopup(it, R.menu.sh_breakfast_menu) }
        lunchButton.setOnClickListener { showPopup(it, R.menu.sh_lunch_menu) }
        snacksButton.setOnClickListener { showPopup(it, R.menu.sh_snacks_menu) }
        dinnerButton.setOnClickListener { showPopup(it, R.menu.sh_dinner_menu) }

        exitButton.setOnClickListener {
            val exitDialog = AlertDialog.Builder(this)
                .setTitle("Exit")
                .setMessage("Do you want to exit?")
                .setPositiveButton("Yes") { _, _ -> finish() }
                .setNegativeButton("No") { dialog, _ -> dialog.dismiss() }
                .setCancelable(false)
                .create()
            exitDialog.show()
        }
    }

    private fun showPopup(view: View, menuRes: Int) {
        val popup = androidx.appcompat.widget.PopupMenu(this, view)
        popup.menuInflater.inflate(menuRes, popup.menu)
        popup.setOnMenuItemClickListener { item ->
            val intent = Intent(this, RecipeActivity::class.java)
            intent.putExtra("DISH_NAME", item.title.toString())
            startActivity(intent)
            true
        }
        popup.show()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.i1 -> Toast.makeText(this, "Get Ideas clicked", Toast.LENGTH_SHORT).show()
            R.id.i2 -> Toast.makeText(this, "Recipe of the Day clicked", Toast.LENGTH_SHORT).show()
            R.id.i3 -> Toast.makeText(this, "Popular clicked", Toast.LENGTH_SHORT).show()
        }
        return super.onOptionsItemSelected(item)
    }
}