package com.example.roomdatabaseexample.data

import android.app.Application
import androidx.lifecycle.LiveData
import com.example.roomdatabaseexample.data.Task
import com.example.roomdatabaseexample.data.TaskDatabase
import com.example.roomdatabaseexample.data.TaskDetailDao

class TaskDetailRepository(context: Application){
    private val taskDetailDao: TaskDetailDao = TaskDatabase.getDatabase(context).taskDetailDao()

    fun getTask(id: Long): LiveData<Task> {
        return taskDetailDao.getTask(id)
    }

    suspend fun insertTask(task: Task): Long{
        return taskDetailDao.insertTask(task)
    }

    suspend fun updateTask(task: Task){
        taskDetailDao.updateTask(task)
    }

    suspend fun deleteTask(task: Task){
        taskDetailDao.deleteTask(task)
    }
}