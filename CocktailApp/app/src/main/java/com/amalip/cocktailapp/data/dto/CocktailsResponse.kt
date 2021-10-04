package com.amalip.cocktailapp.data.dto

import com.amalip.cocktailapp.domain.model.Cocktail
import com.squareup.moshi.JsonClass

// dto -> modelos de transferencia de datos

@JsonClass(generateAdapter = true)
data class CocktailsResponse(val drinks: List<Cocktail>? = listOf())
