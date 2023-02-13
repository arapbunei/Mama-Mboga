package com.example.mamamboga.data.model

import androidx.annotation.DrawableRes

data class ProfileItem(
    val id: Long,
    val title: String,
    @DrawableRes val icon: Int
)