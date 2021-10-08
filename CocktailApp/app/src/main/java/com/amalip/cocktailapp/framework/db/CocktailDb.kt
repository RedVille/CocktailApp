package com.amalip.cocktailapp.framework.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.amalip.cocktailapp.data.dao.CocktailDao
import com.amalip.cocktailapp.domain.model.Cocktail

@Database(entities = [Cocktail::class], version = 3)
abstract class CocktailDb : RoomDatabase() {

    abstract fun cocktailDao(): CocktailDao

}