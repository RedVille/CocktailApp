package com.amalip.cocktailapp.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy.IGNORE
import androidx.room.Query
import androidx.room.Update
import com.amalip.cocktailapp.domain.model.Cocktail

@Dao
interface CocktailDao {

    @Query("SELECT * FROM Cocktail WHERE name LIKE :filter")
    fun getCocktailsByName(filter: String): List<Cocktail>

    @Insert(onConflict = IGNORE)
    fun saveCocktails(cocktails: List<Cocktail>): List<Long>

    @Update
    fun updateCocktail(cocktail: Cocktail): Int

}