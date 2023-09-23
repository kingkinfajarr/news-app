package com.kingkinfajarr.news_app.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class News(
    val title: String,
    val description: String,
    val date: String,
    val link: String,
    val image: Int,
): Parcelable
