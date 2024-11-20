package com.example.studentman

import android.app.Dialog
import android.os.Bundle
import android.util.Log
import android.view.ViewGroup.LayoutParams
import android.widget.Button
import android.widget.EditText
import androidx.fragment.app.DialogFragment

class DialogFragment(val students: List<ItemModel>, val position: Int): DialogFragment() {
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val dialog = Dialog(requireContext())
        dialog.setContentView(R.layout.layout_dialog)
        val editHoten = dialog.findViewById<EditText>(R.id.edit_hoten)
        val editMssv = dialog.findViewById<EditText>(R.id.edit_mssv)

//        val btn_ok = dialog.findViewById<Button>(R.id.button_ok)
//
//        if (position < students.size) {
//            //thay doi thong tin
//            editHoten.setText(students[position].name)
//            editMssv.setText(students[position].mssv)
//            btn_ok.setOnClickListener({
//                val hoten = editHoten.text.toString()
//                val mssv = editMssv.text.toString()
//                students[position].name = hoten
//                students[position].mssv = mssv
//
//            })
//        } else {
//            //them sinh vien
//        }
//        dialog.findViewById<Button>(R.id.button_ok).setOnClickListener {
//            val hoten = editHoten.text.toString()
//            val mssv = editMssv.text.toString()
//            Log.v("TAG", "$hoten - $mssv")
//            dialog.dismiss()
//        }
//        dialog.findViewById<Button>(R.id.button_cancel).setOnClickListener {
//            dialog.dismiss()
//        }
        dialog.window?.setLayout(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT)
        return dialog
    }
}