package com.example.studentmansqlite

import android.content.Intent
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteException
import android.os.Build
import android.os.Bundle
import android.os.Environment
import android.provider.Settings
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import com.example.studentmansqlite.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    lateinit var db: SQLiteDatabase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.main)

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R)
            if (!Environment.isExternalStorageManager()) {
                val intent = Intent(Settings.ACTION_MANAGE_ALL_FILES_ACCESS_PERMISSION)
                startActivity(intent)
            }

        val path = filesDir.path + "/studentdb"
        db = SQLiteDatabase.openDatabase(path, null,
            SQLiteDatabase.CREATE_IF_NECESSARY)

        createTable()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_option, menu)
        return super.onCreateOptionsMenu(menu)
    }
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.action_add -> {
                val args = Bundle()
                args.putInt("id", -1)
                findNavController(R.id.container).navigate(R.id.action_listFragment_to_studentFragment, args)
            }
        }
        return super.onOptionsItemSelected(item)
    }

    fun createTable() {
        db.beginTransaction()
        try {
            db.execSQL("drop table if exists student")
            //tao bang student
            db.execSQL("create table if not exists student(" +
                    "id integer primary key autoincrement," +
                    "name text," +
                    "mssv text)")
            db.execSQL("insert into student(name, mssv) values ('Vu Van Hao', '20215572')")
            db.execSQL("insert into student(name, mssv) values ('Quach Dinh Duong', '20215572')")
            db.execSQL("insert into student(name, mssv) values ('Tu Van An', '20215572')")
            db.execSQL("insert into student(name, mssv) values ('Nguyen Thanh Ha', '20215572')")
            db.execSQL("insert into student(name, mssv) values ('Do Thanh Dat', '20215572')")

            //thong bao transaction thanh cong
            db.setTransactionSuccessful()
        } catch (e: Exception) {
            println(e.message)
        } finally {
            db.endTransaction()
        }
    }

    //dong ket noi db
    override fun onStop() {
        db.close()
        super.onStop()
    }
}