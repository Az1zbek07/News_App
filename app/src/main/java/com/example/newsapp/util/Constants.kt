package com.example.newsapp.util

import com.example.domain.model.SplScrButton
import com.example.domain.model.SplScrImage
import com.example.newsapp.R

object Constants {

    fun setSplScrImages(): List<SplScrImage>{
        return listOf(
            SplScrImage(R.drawable.img),
            SplScrImage(R.drawable.img_1),
            SplScrImage(R.drawable.img_2),
            SplScrImage(R.drawable.img_3)
        )
    }

    fun setSplScrButtons(): List<SplScrButton>{
        return listOf(
            SplScrButton("Sports", R.drawable.img_4),
            SplScrButton("Politics", R.drawable.img_5),
            SplScrButton("Life", R.drawable.img_6),
            SplScrButton("Gaming", R.drawable.img_7),
            SplScrButton("Animals", R.drawable.img_8),
            SplScrButton("Nature", R.drawable.img_9),
            SplScrButton("Food", R.drawable.img_10),
            SplScrButton("Art", R.drawable.img_11),
            SplScrButton("History", R.drawable.img_12),
            SplScrButton("Fashion", R.drawable.img_13)
        )
    }
}