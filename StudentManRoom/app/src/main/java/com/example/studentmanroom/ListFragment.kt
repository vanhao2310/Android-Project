package com.example.studentmanroom

import android.content.DialogInterface
import android.os.Bundle
import android.view.ContextMenu
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView.AdapterContextMenuInfo
import androidx.appcompat.app.AlertDialog
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.studentmanroom.databinding.FragmentListBinding
import com.google.android.material.snackbar.Snackbar
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


class ListFragment : Fragment() {

    lateinit var binding: FragmentListBinding

    lateinit var students: MutableList<Student>
    lateinit var studentAdapter: StudentAdapter


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentListBinding.inflate(layoutInflater)

        students = mutableListOf()
        val studentDao = (activity as MainActivity).studentDao
        studentAdapter = StudentAdapter(students)

        val listView = binding.listViewStudent
        listView.adapter = studentAdapter

        registerForContextMenu(listView)

        //lay danh sach student tu database
        lifecycleScope.launch(Dispatchers.IO) {
            val loadedStudents = studentDao.getAllStudents()
            withContext(Dispatchers.Main) {
                students.clear()
                students.addAll(loadedStudents)
                studentAdapter.notifyDataSetChanged()
            }
        }

        return binding.root
    }

    override fun onResume() {
        val studentDao = (activity as MainActivity).studentDao
        lifecycleScope.launch(Dispatchers.IO) {
            val loadedStudent = studentDao.getAllStudents()
            withContext(Dispatchers.Main) {
                students.clear()
                students.addAll(loadedStudent)
                studentAdapter.notifyDataSetChanged()
            }
        }
        super.onResume()
    }


    override fun onCreateContextMenu(
        menu: ContextMenu,
        v: View,
        menuInfo: ContextMenu.ContextMenuInfo?
    ) {
        requireActivity().menuInflater.inflate(R.menu.menu_context, menu)
        super.onCreateContextMenu(menu, v, menuInfo)
    }

    override fun onContextItemSelected(item: MenuItem): Boolean {
        val pos = (item.menuInfo as AdapterContextMenuInfo).position

        //xac dinh xem action nao duoc chon
        when (item.itemId) {
            R.id.action_edit -> {
                val args = Bundle()
                args.putInt("id", students[pos]._id)
                args.putString("name", students[pos].hoten)
                args.putString("mssv", students[pos].mssv)
                findNavController().navigate(R.id.action_listFragment_to_studentFragment, args)
            }

            R.id.action_delete -> {
                val deleteStd = students[pos]
                context?.let {
                    AlertDialog.Builder(it)
                        .setTitle("Are you sure to delete ?")
                        .setMessage(students[pos].hoten)
                        .setPositiveButton("YES") { dialogInterface: DialogInterface, i: Int ->
                            students.removeAt(pos)
                            studentAdapter.notifyDataSetChanged()

                            var isUndo = false
                            view?.let { it1 ->
                                Snackbar.make(
                                    it1.findViewById(R.id.list_view_student),
                                    "Student removed",
                                    Snackbar.LENGTH_LONG
                                )
                                    .setAction("UNDO") {
                                        isUndo = true
                                        students.add(pos, deleteStd)
                                        studentAdapter.notifyDataSetChanged()
                                    }.addCallback(object : Snackbar.Callback() {
                                        override fun onDismissed(
                                            transientBottomBar: Snackbar?,
                                            event: Int
                                        ) {
                                            if (!isUndo) {
                                                //xoa sinh vien khoi database
                                                lifecycleScope.launch(Dispatchers.IO) {
                                                    (activity as MainActivity).studentDao.deleteStudent(
                                                        deleteStd
                                                    )
                                                }
                                            }
                                        }
                                    }).show()
                            }
                        }
                        .setNegativeButton("CANCEL", null)
                        .show()
                }
            }
        }
        return super.onContextItemSelected(item)
    }
}