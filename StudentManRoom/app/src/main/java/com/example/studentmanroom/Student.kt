package com.example.studentmanroom

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "students_room")
data class Student(
    @PrimaryKey(autoGenerate = true)
    val _id: Int = 0,
    val hoten: String,
    val mssv: String
)