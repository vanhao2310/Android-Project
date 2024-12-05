package com.example.studentmanfragment

interface StudentAction {
    fun saveStudent(pos: Int, student: StudentModel)
    fun updateList(pos: Int, student: StudentModel?)
    fun getList(): MutableList<StudentModel>
}