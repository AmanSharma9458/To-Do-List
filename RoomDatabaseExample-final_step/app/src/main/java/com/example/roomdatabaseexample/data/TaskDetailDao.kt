package com.example.roomdatabaseexample.data

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.roomdatabaseexample.data.Task

@Dao
interface TaskDetailDao{
    @Query("SELECT * FROM task WHERE `id` = :id")
    fun getTask(id: Long): LiveData<Task>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertTask(task: Task): Long

    @Update
    suspend fun updateTask(task: Task)

    @Delete
    suspend fun deleteTask(task: Task)

}