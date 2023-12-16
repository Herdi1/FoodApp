package com.abid.submissionapp

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Food (
    val name : String,
    val description : String,
    val img: Int,
    val place: String,
    val ingredient: String
):Parcelable