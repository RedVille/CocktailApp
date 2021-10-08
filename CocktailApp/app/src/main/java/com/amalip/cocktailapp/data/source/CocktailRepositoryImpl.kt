package com.amalip.cocktailapp.data.source

import com.amalip.cocktailapp.core.exception.Failure
import com.amalip.cocktailapp.core.functional.Either
import com.amalip.cocktailapp.core.plataform.NetworkHandler
import com.amalip.cocktailapp.data.api.CocktailApi
import com.amalip.cocktailapp.data.dao.CocktailDao
import com.amalip.cocktailapp.data.dto.CocktailsResponse
import com.amalip.cocktailapp.domain.model.Cocktail
import com.amalip.cocktailapp.domain.repository.CocktailRepository
import com.amalip.cocktailapp.framework.api.ApiRequest
import javax.inject.Inject

class CocktailRepositoryImpl @Inject constructor(
    private val cocktailApi: CocktailApi,
    private val cocktailDao: CocktailDao,
    private val networkHandler: NetworkHandler
) :
    CocktailRepository, ApiRequest {

    override fun getCocktailsByName(name: String) : Either<Failure, CocktailsResponse> {
        val result = makeRequest(networkHandler, cocktailApi.getCocktailsByName(name), { it }, CocktailsResponse(emptyList()))

        return if (result.isLeft) {

            val localResult = cocktailDao.getCocktailsByName("%$name%")

            if (localResult.isEmpty()) result
            else Either.Right(CocktailsResponse(localResult))

        } else result

    }

    override fun saveCocktail(cocktails: List<Cocktail>): Either<Failure, Boolean> {
        val result = cocktailDao.saveCocktails(cocktails)
        return if (result.size == cocktails.size) Either.Right(true)
        else Either.Left(Failure.DatabaseError)
    }

    override fun updateCocktail(cocktail: Cocktail): Either<Failure, Boolean> {
        TODO("Not yet implemented")
    }


}