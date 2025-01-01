package com.example.studentmanroom

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

@Dao
interface StudentDao {
    @Query("select * from students_room")
    suspend fun getAllStudents(): MutableList<Student>

    @Query("select * from students_room where mssv = :ms")
    suspend fun getStudentByMssv(ms: String): Array<Student>

    @Query("select * from students_room where hoten like '%' || :name || '%'")
    suspend fun getStudentsByName(name: String): Array<Student>

    @Insert
    suspend fun insertStudent(student: Student): Long

    @Update
    suspend fun updateStudent(student: Student): Int

    @Delete
    suspend fun deleteStudent(student: Student): Int

    @Query("delete from students_room where mssv = :mssv")
    suspend fun deleteByMssv(mssv: String): Int

    @Query("update students_room set hoten = :hoten, mssv = :mssv where _id = :id")
    suspend fun updateById(id: Int, hoten: String, mssv: String): Int
}