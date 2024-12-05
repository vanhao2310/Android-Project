package com.example.studentmanfragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.navigation.fragment.findNavController


class StudentFragment : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_student, container, false)

        val editName = view.findViewById<EditText>(R.id.edit_name)
        val editMssv = view.findViewById<EditText>(R.id.edit_mssv)

        //lay data tu arguments
        var pos = arguments?.getInt("pos", -1)
        val name = arguments?.getString("name")
        val mssv = arguments?.getString("mssv")

        if (pos != -1) {
            //chinh sua thong tin sinh vien
            editName.setText(name)
            editMssv.setText(mssv)
        }

        view.findViewById<Button>(R.id.btn_save).setOnClickListener({
            if (editName.text.isNotEmpty() && editMssv.text.isNotEmpty()) {
                val name = editName.text.toString()
                val mssv = editMssv.text.toString()
                //gui du lieu ve listFragment
                val args = Bundle()
                if (pos == null)
                    pos = -1

                if (activity is StudentAction) {
                    if (pos == -1) {
                        //them moi sinh vien
                        (activity as StudentAction).saveStudent(0, StudentModel(name, mssv))
                    } else {
                        (activity as StudentAction).updateList(pos!!, StudentModel(name, mssv))
                    }
                }

                findNavController().navigate(R.id.action_studentFragment_to_listFragment, args)
            }
        })

        view.findViewById<Button>(R.id.btn_cancel).setOnClickListener({
            findNavController().navigate(R.id.action_listFragment_to_studentFragment)
        })

        return view
    }


}