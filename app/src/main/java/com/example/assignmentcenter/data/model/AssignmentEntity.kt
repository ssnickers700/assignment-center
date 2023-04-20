package com.example.assignmentcenter.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "assignment")
data class AssignmentEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val name: String,
    val note: String,
    val priority: Int,
    val icon: String
)
