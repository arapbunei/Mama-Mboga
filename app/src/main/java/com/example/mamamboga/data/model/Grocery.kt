package com.example.mamamboga.data.model

import android.os.Parcelable
import androidx.annotation.DrawableRes
import kotlinx.parcelize.Parcelize
import java.math.BigDecimal


@Parcelize
data class Grocery(val id: Long,
                   val name: String,
                   @DrawableRes val image: Int,
                   val description: String,
                   val size: String,
                   val price: BigDecimal
): Parcelable
