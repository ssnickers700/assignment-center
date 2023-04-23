package com.example.assignmentcenter.model

import androidx.annotation.DrawableRes

data class Assignment(
    val id: Long,
    @DrawableRes
    val resId: Int,
    val name: String,
    val note: String,
    val priority: Int,
)