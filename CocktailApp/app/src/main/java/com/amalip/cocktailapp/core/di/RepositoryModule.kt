package com.amalip.cocktailapp.core.di

import com.amalip.cocktailapp.core.plataform.NetworkHandler
import com.amalip.cocktailapp.data.api.CocktailApi
import com.amalip.cocktailapp.data.dao.CocktailDao
import com.amalip.cocktailapp.data.source.CocktailRepositoryImpl
import com.amalip.cocktailapp.domain.repository.CocktailRepository
import com.amalip.cocktailapp.framework.api.ApiProvider
import com.amalip.cocktailapp.framework.db.CocktailDb
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Provides
    @Singleton
    fun provideCocktailRepository(
        apiProvider: ApiProvider,
        cocktailDb: CocktailDb,
        networkHandler: NetworkHandler
    ): CocktailRepository =
        CocktailRepositoryImpl(apiProvider.getEndpoint(CocktailApi::class.java), networkHandler = networkHandler, cocktailDao = cocktailDb.cocktailDao())

}