package com.amalip.cocktailapp.domain.usecase

import com.amalip.cocktailapp.core.interactor.UseCase
import com.amalip.cocktailapp.domain.model.Cocktail
import com.amalip.cocktailapp.domain.repository.CocktailRepository
import javax.inject.Inject

class SaveCocktails @Inject constructor(private val cocktailRepository: CocktailRepository) :
    UseCase<Boolean, List<Cocktail>>() {

    override suspend fun run(params: List<Cocktail>) = cocktailRepository.saveCocktail(params)

}