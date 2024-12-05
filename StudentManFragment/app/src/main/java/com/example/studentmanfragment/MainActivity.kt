package com.example.studentmanfragment

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController

class MainActivity : AppCompatActivity(), StudentAction {

    var students = mutableListOf(
        StudentModel("Vu Van Hao", "20215572"),
        StudentModel("Quach Dinh Duong", "20210000"),
        StudentModel("Nguyen Thanh Ha", "20210000"),
        StudentModel("Do Thanh Dat", "20210000"),
        StudentModel("Bui Viet Lang", "20210000"),
        StudentModel("Nguyen Dinh Tung", "20210000"),
        StudentModel("Tu Van An", "20210000"),
        StudentModel("Nguyen Quang Thuan", "20210000"),
        StudentModel("Ha Son Duong", "20210000"),
        StudentModel("Ha Van Tang", "20210000")
    )

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_option, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.action_add -> {
                val args = Bundle()
                args.putInt("pos", -1)
                findNavController(R.id.container).navigate(R.id.action_listFragment_to_studentFragment, args)
            }
        }
        return super.onOptionsItemSelected(item)
    }

    override fun saveStudent(pos: Int, student: StudentModel) {
        students.add(pos, student)
    }

    override fun updateList(pos: Int, student: StudentModel?) {
        if (student != null) {
            students[pos].name = student.name
            students[pos].mssv = student.mssv
        } else {
            students.removeAt(pos)
        }
    }

    override fun getList(): MutableList<StudentModel> {
        return students
    }
}