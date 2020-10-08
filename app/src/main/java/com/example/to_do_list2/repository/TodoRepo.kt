package com.example.to_do_list2.repository

import androidx.lifecycle.LiveData
import com.example.to_do_list2.roomDb.Todo
import com.example.to_do_list2.roomDb.TodoDao

class TodoRepo(private val todoDao: TodoDao) {
    val readAllTodos: LiveData<List<Todo>> = todoDao.getTasks()

    suspend fun addTodo(todo: Todo) {
        todoDao.addTodo(todo)
    }

    suspend fun deleteTodo(todo: Todo) {
        todoDao.deleteTodo(todo)
    }
}