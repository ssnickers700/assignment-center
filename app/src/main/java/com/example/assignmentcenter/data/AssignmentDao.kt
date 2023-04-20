package com.example.assignmentcenter.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.assignmentcenter.data.model.AssignmentEntity
import com.example.assignmentcenter.model.Assignment

@Dao
interface AssignmentDao {
    @Query("SELECT * FROM assignment")
    fun getAll(): List<AssignmentEntity>

    @Query("SELECT * FROM assignment ORDER BY priority DESC")
    fun getAllSortedByPriority(): List<AssignmentEntity>

    @Insert
    fun addAssignment(newAssignment: AssignmentEntity)

    @Update
    fun updateAssignment(newAssignment: AssignmentEntity)
}