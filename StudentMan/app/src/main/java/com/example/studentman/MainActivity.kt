package com.example.studentman

import android.app.AlertDialog
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.widget.Button
import android.widget.EditText
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.snackbar.Snackbar

class MainActivity : AppCompatActivity(), ItemClickListener {

    lateinit var students: MutableList<ItemModel>
    lateinit var adapter: ItemAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        students = mutableListOf(
        ItemModel("Vu Van Hao", "20215572"),
        ItemModel("Quach Dinh Duong", "20215572"),
        ItemModel("Tu Van An", "20215572"),
        ItemModel("Do Thanh Dat", "20215572"),
        ItemModel("Nguyen Thanh Ha", "20215572"),
        ItemModel("Bui Viet Lang", "20215572"),
        ItemModel("Nguyen Dinh Tung", "20215572"),
        ItemModel("Ha Van Tang", "20215572"),
        ItemModel("Ha Son Duong", "20215572"),
        ItemModel("Nguyen Quang Thuan", "20215572")
        )

        adapter = ItemAdapter(students, this)

        val recycleView = findViewById<RecyclerView>(R.id.recycler_view)
        recycleView.adapter = adapter
        recycleView.layoutManager = LinearLayoutManager(this)

        findViewById<Button>(R.id.button_add).setOnClickListener({

            val dialogView = LayoutInflater.from(this)
            .inflate(R.layout.layout_dialog, null)

            val editHoten = dialogView.findViewById<EditText>(R.id.edit_hoten)
            val editMssv = dialogView.findViewById<EditText>(R.id.edit_mssv)

            AlertDialog.Builder(this)
            .setTitle("Nhap thong tin sinh vien")
            .setView(dialogView)
            .setPositiveButton("OK", { _, _ ->
                val hoten = editHoten.text.toString()
                val mssv = editMssv.text.toString()
                students.add(0, ItemModel(hoten, mssv))
                adapter.notifyItemInserted(0)
            })
            .setNegativeButton("Cancel", null)
            .show()
        })

    }

    override fun remove(position: Int, view: View) {
        //Log.v("TAG", "remove ${students[position].name} - ${students[position].mssv}"
        val rm_student = students[position]
        AlertDialog.Builder(this)
            .setTitle("Delete Student")
            .setMessage("Ban co muon xoa sinh vien: ${rm_student.name}-${rm_student.mssv}")
            .setPositiveButton("Yes",
                { _, _ ->
                    students.removeAt(position)
                    adapter.notifyItemRemoved(position)
                    Snackbar.make(view, "Remove ${rm_student.name} - ${rm_student.mssv}", Snackbar.LENGTH_LONG)
                        .setAction("UNDO", {
                            students.add(position, rm_student)
                            adapter.notifyItemInserted(position)
                        })
                        .show()
                })
            .setNegativeButton("No", null)
            .show()
            .setCanceledOnTouchOutside(true)
    }

    override fun edit(position: Int) {
        val dialogView = LayoutInflater.from(this)
            .inflate(R.layout.layout_dialog, null)

        val editHoten = dialogView.findViewById<EditText>(R.id.edit_hoten)
        val editMssv = dialogView.findViewById<EditText>(R.id.edit_mssv)

        editHoten.setText(students[position].name)
        editMssv.setText(students[position].mssv)

        AlertDialog.Builder(this)
            .setTitle("Thay doi thong tin sinh vien")
            .setView(dialogView)
            .setPositiveButton("OK", { _, _ ->
                val hoten = editHoten.text.toString()
                val mssv = editMssv.text.toString()
                students[position].name = hoten
                students[position].mssv = mssv
                adapter.notifyItemChanged(position)
            })
            .setNegativeButton("Cancel", null)
            .show()
    }
}