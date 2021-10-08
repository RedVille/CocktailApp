package com.amalip.cocktailapp.core.di

import android.content.Context
import androidx.room.Room
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import com.amalip.cocktailapp.framework.db.CocktailDb
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    private val MIGRATION_1_2 = object : Migration(1, 2) {
        override fun migrate(database: SupportSQLiteDatabase) {
            database.execSQL("ALTER TABLE Cocktail ADD COLUMN alcoholic TEXT")
        }
    }

    @Provides
    @Singleton
    fun provideCocktailDb(@ApplicationContext context: Context) =
        Room.databaseBuilder(context, CocktailDb::class.java, "cocktails").addMigrations(
            MIGRATION_1_2
        ).build()

}