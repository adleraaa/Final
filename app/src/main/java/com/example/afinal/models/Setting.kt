package com.example.afinal.models


import android.os.Parcelable
import kotlinx.android.parcel.Parcelize
@Parcelize

data class Setting(
    val category: Int,
    val type: String,
    val difficulty: String,
): Parcelable
