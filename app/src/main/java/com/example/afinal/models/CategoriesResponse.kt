package com.example.afinal.models

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class CategoriesResponse(
    val categories: List<Category>
) : Parcelable
