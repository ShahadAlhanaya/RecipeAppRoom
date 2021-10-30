package com.example.recipeapproom.data


import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Recipe::class], version = 1, exportSchema = false)
abstract class RecipesDB : RoomDatabase(){

    abstract fun  recipesDao(): RecipesDao

    companion object{
        @Volatile
        private var INSTANCE: RecipesDB? = null

        fun getDatabase(context: Context): RecipesDB{
            val tempInstance = INSTANCE
            if(tempInstance != null){
                return tempInstance
            }
            synchronized(this){
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    RecipesDB::class.java,
                    "recipes_database"
                ).build()
                INSTANCE = instance
                return instance
            }
        }
    }
}