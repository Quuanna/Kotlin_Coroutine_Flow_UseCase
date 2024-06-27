package com.example.usecase_coroutine_and_test.data

import androidx.annotation.ColorRes
import androidx.annotation.DrawableRes

data class GridItemDataView(
    val title: String,
    @DrawableRes
    val imageRes: Int,
    @ColorRes
    val backgroundColor: Int
)