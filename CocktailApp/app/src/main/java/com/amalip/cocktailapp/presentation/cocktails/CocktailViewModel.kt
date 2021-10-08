package com.amalip.cocktailapp.presentation.cocktails

import com.amalip.cocktailapp.core.presentation.BaseViewModel
import com.amalip.cocktailapp.domain.model.Cocktail
import com.amalip.cocktailapp.domain.usecase.GetCocktailsByName
import com.amalip.cocktailapp.domain.usecase.SaveCocktails
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.DelicateCoroutinesApi
import javax.inject.Inject

@DelicateCoroutinesApi
@HiltViewModel
class CocktailViewModel @Inject constructor(
    private val getCocktailsByName: GetCocktailsByName,
    private val saveCocktails: SaveCocktails
) :
    BaseViewModel() {

    fun doGetCocktailsByName(name: String) {
        getCocktailsByName(name) {
            it.fold(::handleFailure) {
                state.value = CocktailViewState.CocktailsReceived(it.drinks ?: listOf())

                saveCocktails(it.drinks ?: listOf())

                true
            }
        }
    }

    private fun saveCocktails(cocktails: List<Cocktail>) {
        saveCocktails(cocktails) {
            it.fold(::handleFailure) {
                it
            }
        }
    }

}