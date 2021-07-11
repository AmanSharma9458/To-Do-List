package com.example.roomdatabaseexample.ui

import android.app.Application
import androidx.lifecycle.*
import com.example.roomdatabaseexample.data.Task
import com.example.roomdatabaseexample.data.TaskDetailRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class TaskDetailViewModel(application: Application): AndroidViewModel(application){
    private val repo: TaskDetailRepository =
        TaskDetailRepository(application)

    private val _taskId = MutableLiveData<Long>(0)

    val taskId: LiveData<Long>
        get() = _taskId

    val task: LiveData<Task> = Transformations
        .switchMap(_taskId) { id ->
            repo.getTask(id)
        }

    fun setTaskId(id: Long){
        if(_taskId.value != id ) {
            _taskId.value = id
        }
    }

    fun saveTask(task: Task){
        viewModelScope.launch {
            if (_taskId.value == 0L) {
                _taskId.value = repo.insertTask(task)
            } else {
                repo.updateTask(task)
            }
        }
    }

    fun deleteTask(){
        viewModelScope.launch {
            task.value?.let { repo.deleteTask(it) }
        }
    }
}