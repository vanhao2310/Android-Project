package com.example.studentmanfragment

import android.content.DialogInterface
import android.os.Bundle
import android.view.ContextMenu
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView.AdapterContextMenuInfo
import android.widget.ListView
import androidx.appcompat.app.AlertDialog
import androidx.navigation.fragment.findNavController
import com.google.android.material.snackbar.Snackbar


class ListFragment : Fragment() {

     lateinit var students: MutableList<StudentModel>

     lateinit var studentAdapter: StudentAdapter


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_list, container, false)


        if (activity is StudentAction)
            students = (activity as StudentAction).getList()
        else
            students = mutableListOf()

        studentAdapter = StudentAdapter(students)

        val listView = view.findViewById<ListView>(R.id.list_view_student)
        listView.adapter = studentAdapter

        registerForContextMenu(listView)

        return view
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
                args.putInt("pos", pos)
                args.putString("name", students[pos].name)
                args.putString("mssv", students[pos].mssv)
                findNavController().navigate(R.id.action_listFragment_to_studentFragment, args)
            }
            R.id.action_delete -> {
                val deleteStd = students[pos]
                context?.let {
                    AlertDialog.Builder(it)
                        .setTitle("Are you sure to delete ?")
                        .setMessage(students[pos].name)
                        .setPositiveButton("YES"){ dialogInterface: DialogInterface, i: Int ->
                            students.removeAt(pos)
                            studentAdapter.notifyDataSetChanged()
                            (activity as StudentAction).updateList(pos, null)
                            view?.let { it1 ->
                                Snackbar.make(it1.findViewById(R.id.list_view_student),"Student removed", Snackbar.LENGTH_LONG)
                                    .setAction("UNDO"){
                                        students.add(pos, deleteStd)
                                        studentAdapter.notifyDataSetChanged()
                                        if (activity is StudentAction) {
                                            (activity as StudentAction).saveStudent(pos, deleteStd)
                                        }
                                    }
                                    .show()
                            }

                        }
                        .setNegativeButton("CANCEL",null)
                        .show()
                }
            }
        }
        return super.onContextItemSelected(item)
    }
}