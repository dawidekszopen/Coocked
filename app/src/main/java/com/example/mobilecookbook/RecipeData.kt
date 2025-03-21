package com.example.mobilecookbook

import android.os.Parcelable

data class RecipeData(
    val nazwa: String,
    val opis: String,
    val skladniki: String,
    val intrukcja: String,
    val ocena: Float,
)