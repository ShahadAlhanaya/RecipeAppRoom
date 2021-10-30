package com.example.recipeapproom

import android.app.Dialog
import android.text.method.ScrollingMovementMethod
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.Window
import android.widget.Button
import android.widget.ImageButton
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.recipeapproom.data.Recipe
import kotlinx.android.synthetic.main.recipe_item.view.*

class RecipesAdapter(private val context: MainActivity): RecyclerView.Adapter<RecipesAdapter.UserViewHolder>() {

    private var recipesList = emptyList<Recipe>()

    class UserViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val titleTextView: TextView = itemView.tv_title
        val ingredientsTextView: TextView = itemView.tv_ingredients
        val instructionsTextView: TextView = itemView.tv_instructions
        val authorTextView: TextView = itemView.tv_author
        val deleteButton: ImageButton = itemView.btn_delete
        val editButton: ImageButton = itemView.btn_edit
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(
            R.layout.recipe_item,
            parent,
            false
        )
        return UserViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        holder.titleTextView.text = recipesList[position].title
        holder.ingredientsTextView.text = recipesList[position].ingredients
        holder.ingredientsTextView.movementMethod = ScrollingMovementMethod()
        holder.instructionsTextView.text = recipesList[position].instructions
        holder.instructionsTextView.movementMethod = ScrollingMovementMethod()
        holder.authorTextView.text = recipesList[position].author

        holder.deleteButton.setOnClickListener {
            context.showDeleteDialog(recipesList[position].id,recipesList[position].title,recipesList[position].ingredients,recipesList[position].instructions,recipesList[position].author,)
        }

        holder.editButton.setOnClickListener {
            context.showEditDialog(recipesList[position].id,recipesList[position].title,recipesList[position].ingredients,recipesList[position].instructions,recipesList[position].author,)
        }
    }

    fun setData(notes: List<Recipe>){
        this.recipesList = notes
        notifyDataSetChanged()
    }

    override fun getItemCount() = recipesList.size

//    fun showEditDialog(position: Int) {
//        val dialog = Dialog(context)
//        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
//        dialog.setContentView(R.layout.edit_dialog_layout)
//        dialog.window?.setBackgroundDrawableResource(android.R.color.transparent)
//
//        //views
//        val titleEditText = dialog.findViewById<TextView>(R.id.edt_titleDialog)
//        val noteEditText = dialog.findViewById<TextView>(R.id.edt_noteDialog)
//        val saveButton = dialog.findViewById<Button>(R.id.btn_saveDialog)
//
//
//        val id = recipesList[position].id
//        val title = recipesList[position].title
//        val note = recipesList[position].note
//        titleEditText.text = title
//        noteEditText.text = note
//
//        saveButton.setOnClickListener {
//            val updatedTitle = titleEditText.text.toString()
//            val updatedNote = noteEditText.text.toString()
//            if (updatedTitle.trim().isNotEmpty() && updatedNote.trim().isNotEmpty()) {
//                if (updatedTitle != title || updatedNote != note) {
//                    context.updateNote(id, updatedTitle, updatedNote)
////                    context.getAllNotes()
//                }
//                dialog.dismiss()
//            }
//        }
//        dialog.show()
//    }
//
//    fun showDeleteDialog(position: Int) {
//        val dialog = Dialog(context)
//        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
//        dialog.setContentView(R.layout.delete_dialog_layout)
//        dialog.window?.setBackgroundDrawableResource(android.R.color.transparent)
//
//        //views
//        val deleteButton = dialog.findViewById<Button>(R.id.btn_deleteDialog)
//        val cancelButton = dialog.findViewById<Button>(R.id.btn_cancelDialog)
//        val titleTextView = dialog.findViewById<TextView>(R.id.tv_titleDialog)
//        val noteTextView = dialog.findViewById<TextView>(R.id.tv_noteDialog)
//
//        val id = recipesList[position].id
//        val title = recipesList[position].title
//        val note = recipesList[position].note
//
//        titleTextView.text = title
//        noteTextView.text = note
//
//        cancelButton.setOnClickListener { dialog.cancel() }
//
//        deleteButton.setOnClickListener {
//            context.deleteNote(id, title, note)
////            context.getAllNotes()
//            dialog.dismiss()
//        }
//
//        dialog.show()
//    }
}