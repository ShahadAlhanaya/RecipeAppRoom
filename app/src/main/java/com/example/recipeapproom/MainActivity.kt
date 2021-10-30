package com.example.recipeapproom

import android.app.Dialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.method.ScrollingMovementMethod
import android.view.Window
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.recipeapproom.data.Recipe
import com.example.recipeapproom.data.RecipesViewModel
import com.google.android.material.bottomsheet.BottomSheetDialog
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private lateinit var recipesViewModel: RecipesViewModel
    lateinit var adapter: RecipesAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        tv_addFirstRecipe.isVisible = false

        //initialize recyclerView
        adapter = RecipesAdapter(this)
        rv_recipes.layoutManager = LinearLayoutManager(this)
        rv_recipes.adapter = adapter

        //initialize view model
        recipesViewModel = ViewModelProvider(this).get(RecipesViewModel::class.java)
        getAllRecipes()

        floatingActionButton.setOnClickListener {
            showAddDialog()
        }
    }

    private fun insertRecipe(
        title: String,
        ingredients: String,
        instructions: String,
        author: String
    ) {
        recipesViewModel.addRecipe(Recipe(0, title, ingredients, instructions, author))
        Toast.makeText(applicationContext, "Added Successfully!", Toast.LENGTH_SHORT).show()
    }

    private fun updateRecipe(
        id: Int,
        title: String,
        ingredients: String,
        instructions: String,
        author: String
    ) {
        recipesViewModel.updateRecipe(Recipe(id, title, ingredients, instructions, author))
        Toast.makeText(applicationContext, "Updated Successfully!", Toast.LENGTH_SHORT).show()
    }

    private fun deleteRecipe(
        id: Int,
        title: String,
        ingredients: String,
        instructions: String,
        author: String
    ) {
        recipesViewModel.deleteRecipe(Recipe(id, title, ingredients, instructions, author))
        Toast.makeText(applicationContext, "Deleted Successfully!", Toast.LENGTH_SHORT).show()
    }

    private fun getAllRecipes() {
        recipesViewModel.getAllRecipes.observe(this, { recipes ->
            tv_addFirstRecipe.isVisible = recipes.isEmpty()
            adapter.setData(recipes)
        })
    }

    private fun showAddDialog() {
        val dialog = Dialog(this)
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.setContentView(R.layout.add_recipe_dialog)
        dialog.window?.setBackgroundDrawableResource(android.R.color.transparent)

        //views
        val titleEditText = dialog.findViewById<EditText>(R.id.edt_add_title)
        val authorEditText = dialog.findViewById<EditText>(R.id.edt_add_author)
        val ingredientsEditText = dialog.findViewById<EditText>(R.id.edt_add_ingredients)
        val instructionsEditText = dialog.findViewById<EditText>(R.id.edt_add_instructions)
        val saveButton = dialog.findViewById<Button>(R.id.btn_add_save)

        saveButton!!.setOnClickListener {
            val title = titleEditText!!.text.trim().toString()
            val author = authorEditText!!.text.trim().toString()
            val ingredients = ingredientsEditText!!.text.trim().toString()
            val instructions = instructionsEditText!!.text.trim().toString()

            if (title.isNotEmpty() && author.isNotEmpty() && ingredients.isNotEmpty() && instructions.isNotEmpty()) {
                insertRecipe(title, ingredients, instructions, author)
                dialog.dismiss()
            } else {
                Toast.makeText(this, "All fields are required", Toast.LENGTH_SHORT).show()
            }

        }
        dialog.show()
    }

    fun showEditDialog(
        id: Int,
        title: String,
        ingredients: String,
        instructions: String,
        author: String
    ) {
        val dialog = Dialog(this)
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.setContentView(R.layout.add_recipe_dialog)
        dialog.window?.setBackgroundDrawableResource(android.R.color.transparent)

        //views
        val titleEditText = dialog.findViewById<EditText>(R.id.edt_add_title)
        val authorEditText = dialog.findViewById<EditText>(R.id.edt_add_author)
        val ingredientsEditText = dialog.findViewById<EditText>(R.id.edt_add_ingredients)
        val instructionsEditText = dialog.findViewById<EditText>(R.id.edt_add_instructions)
        val saveButton = dialog.findViewById<Button>(R.id.btn_add_save)
        saveButton.text = "Save"
        titleEditText.setText(title)
        ingredientsEditText.setText(ingredients)
        instructionsEditText.setText(instructions)
        authorEditText.setText(author)

        saveButton!!.setOnClickListener {
            val updatedTitle = titleEditText!!.text.trim().toString()
            val updatedAuthor = authorEditText!!.text.trim().toString()
            val updatedIngredients = ingredientsEditText!!.text.trim().toString()
            val updatedInstructions = instructionsEditText!!.text.trim().toString()

            if (title.isNotEmpty() && author.isNotEmpty() && ingredients.isNotEmpty() && instructions.isNotEmpty()) {
                if (title != updatedTitle || author != updatedAuthor || ingredients != updatedIngredients || instructions != updatedInstructions) {
                    updateRecipe(id,updatedTitle, updatedIngredients, updatedInstructions, updatedAuthor)
                }
                dialog.dismiss()
            } else {
                Toast.makeText(this, "All fields are required", Toast.LENGTH_SHORT).show()
            }

        }
        dialog.show()
    }

    fun showDeleteDialog(
        id: Int,
        title: String,
        ingredients: String,
        instructions: String,
        author: String
    ) {
        val dialog = Dialog(this)
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.setContentView(R.layout.delete_dialog)
        dialog.window?.setBackgroundDrawableResource(android.R.color.transparent)

        //views
        val deleteButton = dialog.findViewById<Button>(R.id.btn_delete_delete)
        val cancelButton = dialog.findViewById<Button>(R.id.btn_delete_cancel)

        deleteButton!!.setOnClickListener {
            deleteRecipe(id, title, ingredients, instructions, author)
            dialog.dismiss()
        }
        cancelButton.setOnClickListener { dialog.dismiss() }
        dialog.show()
    }

}