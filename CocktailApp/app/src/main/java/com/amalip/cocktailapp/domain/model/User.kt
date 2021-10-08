package com.amalip.cocktailapp.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@Entity
@JsonClass(generateAdapter = true)
class User(
    @PrimaryKey(autoGenerate = false)
    val idUser: Int = 0,
    @Json(name = "strName") val name: String = "",
    @Json(name = "strEmail") val email: String = "",
    @Json(name = "strImage") val image: String? = "",
    @Json(name = "strToken") val token: String? = ""
) {
}