package com.amalip.cocktailapp.domain.repository

import com.amalip.cocktailapp.core.exception.Failure
import com.amalip.cocktailapp.core.functional.Either
import com.amalip.cocktailapp.data.dto.CocktailsResponse
import com.amalip.cocktailapp.domain.model.Cocktail

interface CocktailRepository {
    // regresa un fallo o la respuesta
    fun getCocktailsByName(name: String): Either<Failure, CocktailsResponse>

    fun saveCocktail(cocktails: List<Cocktail>): Either<Failure, Boolean>

    fun updateCocktail(cocktail: Cocktail): Either<Failure, Boolean>

}