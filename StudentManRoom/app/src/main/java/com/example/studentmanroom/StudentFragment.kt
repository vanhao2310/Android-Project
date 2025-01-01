package com.example.studentmanroom

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.studentmanroom.databinding.FragmentStudentBinding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


class StudentFragment : Fragment() {

    lateinit var binding: FragmentStudentBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentStudentBinding.inflate(layoutInflater)

        val editName = binding.editName
        val editMssv = binding.editMssv

        //lay data tu arguments
        val id = arguments?.getInt("id", -1)
        val name = arguments?.getString("name")
        val mssv = arguments?.getString("mssv")

        if (id != -1) {
            //chinh sua thong tin sinh vien
            editName.setText(name)
            editMssv.setText(mssv)
        }

        binding.btnSave.setOnClickListener({
            if (editName.text.isNotEmpty() && editMssv.text.isNotEmpty()) {
                val name = editName.text.toString()
                val mssv = editMssv.text.toString()

                //luu du lieu vao db
                if (id == -1) {
                    //them moi sinh vien
                    lifecycleScope.launch(Dispatchers.IO) {
                        (activity as MainActivity).studentDao.insertStudent(
                            Student(
                                hoten = name,
                                mssv = mssv
                            )
                        )
                    }
                } else {
                    //thay doi thong tin sinh vien
                    lifecycleScope.launch(Dispatchers.IO) {
                        (activity as MainActivity).studentDao.updateById(id!!, name, mssv)
                    }
                }


                findNavController().navigate(R.id.action_studentFragment_to_listFragment)
            }
        })

        binding.btnCancel.setOnClickListener({
            findNavController().navigate(R.id.action_studentFragment_to_listFragment)
        })

        return binding.root
    }
}