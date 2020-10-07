package com.example.to_do_list2.roomDb

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface TodoDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addTodo(task: Todo)

    @Query("SELECT * FROM Todo")
    fun getTasks(): LiveData<List<Todo>>

    @Delete
    suspend fun deleteTodo(task: Todo): Void
}