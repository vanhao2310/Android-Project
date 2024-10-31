
package com.example.bai2liststudent

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.ArrayAdapter
import android.widget.EditText
import android.widget.ListView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    data class Student(
        val name: String,
        val mssv: String
    ) {
        override fun toString(): String {
            return "$name - $mssv"
        }
    }

    private lateinit var studentList: List<Student>
    private lateinit var filteredList: MutableList<Student>
    private lateinit var arrayAdapter: ArrayAdapter<Student>
    private lateinit var searchEditText: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        searchEditText = findViewById(R.id.searchEditText)
        val listView = findViewById<ListView>(R.id.listView)

        // Danh sách sinh viên ban đầu
        studentList = listOf(
            Student("Vu Van Hao", "20210001"),
            Student("Quach Dinh Duong", "20210023"),
            Student("Do Thanh Dat", "20210045"),
            Student("Nguyen Quang Thuan", "20210067"),
            Student("Nguyen Thanh ha", "20210089"),
            Student("Las Duong", "20210111"),
            Student("Nguyen Van A", "20210133"),
            Student("Vu Van B", "20210155"),
            Student("Pham Thi L", "20210177"),
            Student("Nguyen Thi X", "20210199"),
            Student("Do Duc M", "20210221"),
            Student("Le Huu H", "20210243"),
            Student("Pham Van B", "20210265"),
            Student("Nguyen Thi X", "20210287"),
            Student("Bui Van C", "20210309"),
            Student("E Van Luyen", "20210331"),
            Student("Nguyen Thanh G", "20210353"),
            Student("Pham Dang D", "20210375"),
            Student("Nguyen COng M", "20210397"),
            Student("Sandra Bullock", "20210419")
        )

        // Sao chép danh sách ban đầu vào filteredList
        filteredList = studentList.toMutableList()

        // Thiết lập adapter cho ListView
        arrayAdapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, filteredList)
        listView.adapter = arrayAdapter

        // Thêm TextWatcher để lọc danh sách khi nhập từ khóa
        searchEditText.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                filterList(s.toString())
            }

            override fun afterTextChanged(s: Editable?) {}
        })
    }

    // Hàm lọc danh sách sinh viên
    private fun filterList(query: String) {
        filteredList.clear()
        if (query.length > 2) {
            val lowerCaseQuery = query.lowercase()
            filteredList.addAll(studentList.filter {
                it.name.lowercase().contains(lowerCaseQuery) || it.mssv.contains(query)
            })
        } else {
            filteredList.addAll(studentList)
        }
        arrayAdapter.notifyDataSetChanged()
    }
}

