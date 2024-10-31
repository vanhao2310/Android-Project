package com.example.basiclist

import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.ListView
import android.widget.RadioButton
import android.widget.RadioGroup
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)



        val buttonShow: Button = findViewById(R.id.btn_show)
        val inputText: EditText = findViewById(R.id.text_input)
        val rdbChan: RadioButton = findViewById(R.id.rdb_chan)
        val rdbLe: RadioButton = findViewById(R.id.rdb_le)
        val rdbChinhPhuong: RadioButton = findViewById(R.id.rdb_chinhphuong)
        val listView: ListView = findViewById(R.id.list_number)

        buttonShow.setOnClickListener({
            if (inputText.text.isNotEmpty()) {
                val input = inputText.text.toString()
                val number = input.toInt()
                if (rdbLe.isChecked) {
                    val listNum = soLe(number)
                    val adapter: ArrayAdapter<Int> = ArrayAdapter(this, android.R.layout.simple_list_item_1, listNum)
                    listView.adapter = adapter
                } else if (rdbChan.isChecked) {
                    val listNum = soChan(number)
                    val adapter: ArrayAdapter<Int> = ArrayAdapter(this, android.R.layout.simple_list_item_1, listNum)
                    listView.adapter = adapter
                } else if (rdbChinhPhuong.isChecked) {
                    val listNum = soChinhPhuong(number)
                    val adapter: ArrayAdapter<Int> = ArrayAdapter(this, android.R.layout.simple_list_item_1, listNum)
                    listView.adapter = adapter
                } else {
                    val listNum = mutableListOf<Int>()
                    for(i in 0..number)
                        listNum.add(i)
                    val adapter: ArrayAdapter<Int> = ArrayAdapter(this, android.R.layout.simple_list_item_1, listNum)
                    listView.adapter = adapter
                }
            }
        })
    }
    fun soChan(n: Int): List<Int> {
        var list = mutableListOf<Int>()
        for (i in 0 .. n) {
            if (i % 2 == 0)
                list.add(i)
        }
        return list
    }

    fun soLe(n: Int): List<Int> {
        var list = mutableListOf<Int>()
        for (i in 0 .. n) {
            if (i % 2 == 1)
                list.add(i)
        }
        return list
    }

    fun checkChinhPhuong(n: Int): Boolean {
        if (n == 0)
            return true
        else {
            for (i in 1 ..Math.sqrt(n.toDouble()).toInt())
                if (i*i == n)
                    return true
            return false
        }
    }
    fun soChinhPhuong(n: Int): List<Int> {
        var list = mutableListOf<Int>()
        for (i in 0 .. n) {
            if (checkChinhPhuong(i))
                list.add(i)
        }
        return list
    }
}