package com.example.recipeapproom.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "recipes_table")
data class Recipe(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "ID")
    val id: Int,
    @ColumnInfo(name = "Title")
    val title: String,
    @ColumnInfo(name = "Ingredients")
    val ingredients: String,
    @ColumnInfo(name = "Instructions")
    val instructions: String,
    @ColumnInfo(name = "Author")
    val author: String
)