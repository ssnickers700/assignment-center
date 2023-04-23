package com.example.assignmentcenter.data

import androidx.room.*
import com.example.assignmentcenter.data.model.AssignmentEntity
import com.example.assignmentcenter.model.Assignment

@Dao
interface AssignmentDao {
    @Query("SELECT * FROM assignment")
    fun getAll(): List<AssignmentEntity>

    @Query("SELECT * FROM assignment ORDER BY priority ASC")
    fun getAllSortedByPriority(): List<AssignmentEntity>

    @Delete
    fun delete(assignment: AssignmentEntity)

    @Insert
    fun addAssignment(newAssignment: AssignmentEntity)

    @Update
    fun updateAssignment(newAssignment: AssignmentEntity)
}