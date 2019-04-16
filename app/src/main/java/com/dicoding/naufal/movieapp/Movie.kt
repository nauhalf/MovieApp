package com.dicoding.naufal.movieapp

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
class Movie(
    var title: String? = null,
    var year: Int? = null
) : Parcelable