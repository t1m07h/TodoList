package com.example.to_do_list2.roomDb

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity()
data class Todo (
    @PrimaryKey(autoGenerate = true) val id: Int,
    val task: String
)