package com.example.afinal.models
import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class FinalResult(
    val totalQuestion: Int,
    val score: Int
) : Parcelable
