package com.example.domain.model

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Parcelize
@Entity
data class Inform(
    @PrimaryKey(autoGenerate = true)
    val roomId: Int = 0,
    val title: String,
    val content: String,
    val url: String? = null,
    val isSaved: Boolean = false
): Parcelable
