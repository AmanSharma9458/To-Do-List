package com.example.roomdatabaseexample.data

import android.app.Application
import androidx.lifecycle.LiveData
import com.example.roomdatabaseexample.data.*


class TaskListRepository(context: Application){
    private val taskListDao: TaskListDao = TaskDatabase.getDatabase(context).taskListDao()

    fun getTasks(sort: SortColumn = SortColumn.Priority
    ): LiveData<List<Task>> {
        return if(sort == SortColumn.Priority){
            taskListDao.getTasksByPriority(TaskStatus.Open.ordinal)
        } else{
            taskListDao.getTasksByTitle(TaskStatus.Open.ordinal)
        }


    }
}