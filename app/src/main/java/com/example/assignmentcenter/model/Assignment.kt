package com.example.assignmentcenter.model

import android.os.Parcelable
import androidx.annotation.DrawableRes
import kotlinx.parcelize.Parcelize

@Parcelize
data class Assignment(
    val id: Long,
    @DrawableRes
    val resId: Int,
    val name: String,
    val note: String,
    val priority: Int,
): Parcelable