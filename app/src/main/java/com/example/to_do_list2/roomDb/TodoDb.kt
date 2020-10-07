package com.example.to_do_list2.roomDb

import android.app.Application
import androidx.room.Database
import androidx.room.Room
import java.lang.Appendable

@Database(version = 1, entities = [Todo::class])
abstract class TodoDb {
    companion object {
        fun get(application: Application) : TodoDb {
            return Room.databaseBuilder(application, TodoDb::class.java, "Todos").build()
        }
    }

    abstract fun getTodoDao(): TodoDao
}