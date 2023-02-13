package com.example.mamamboga.data.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize


@Parcelize
data class OrderGrocery(
    val grocery: Grocery,
    val count: Int
) : Parcelable






