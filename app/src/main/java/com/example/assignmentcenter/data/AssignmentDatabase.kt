package com.example.assignmentcenter.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.assignmentcenter.data.model.AssignmentEntity
import com.example.assignmentcenter.model.Assignment

@Database(
    entities = [AssignmentEntity::class],
    version = 1
)
abstract class AssignmentDatabase: RoomDatabase() {
    abstract val assignments: AssignmentDao

    companion object {
        fun open(context: Context): AssignmentDatabase = Room.databaseBuilder(
            context, AssignmentDatabase::class.java, "assignments.db"
        ).build()
    }
}