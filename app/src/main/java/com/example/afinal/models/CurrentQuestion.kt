package com.example.afinal.models

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize

data class CurrentQuestion(
    val score: Int,
    val total: Int,
    val current: Int,
    val result: List<Question>
): Parcelable
