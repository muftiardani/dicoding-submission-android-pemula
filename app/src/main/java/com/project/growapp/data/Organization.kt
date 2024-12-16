package com.project.growapp.data

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Organization (
    val name: String,
    val description: String,
    val photo: Int,
    val title: String
): Parcelable