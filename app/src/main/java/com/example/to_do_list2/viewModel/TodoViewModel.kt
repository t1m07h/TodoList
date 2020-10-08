package com.example.to_do_list2.viewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.to_do_list2.repository.TodoRepo
import com.example.to_do_list2.roomDb.Todo
import com.example.to_do_list2.roomDb.TodoDb
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class TodoViewModel(application: Application): AndroidViewModel(application) {
    val readAllTodo: LiveData<List<Todo>>
    private val repository: TodoRepo

    init {
        val todoDao = TodoDb.get(application).getTodoDao()
        repository = TodoRepo(todoDao)
        readAllTodo = repository.readAllTodos
    }

    fun addTodo(todo: Todo) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.addTodo(todo)
        }
    }

    fun deleteTodo(todo: Todo) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.deleteTodo(todo)
        }
    }
}