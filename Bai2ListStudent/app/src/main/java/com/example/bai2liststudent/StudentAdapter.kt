package com.example.bai2liststudent

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.CheckBox
import android.widget.ImageView
import android.widget.TextView

class StudentAdapter(val students: List<StudentModel>): BaseAdapter() {
    override fun getCount(): Int = students.size

    override fun getItem(p0: Int): Any = students[p0]

    override fun getItemId(p0: Int): Long = p0.toLong()

    //duoc goi moi khi the item di vao khung nhin
    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val itemView: View
        val viewHolder: ViewHolder

        if (convertView == null) {  //neu itemview chua duoc khoi tao thi duoc khoi tao moi
            itemView = LayoutInflater.from(parent?.context)
                .inflate(R.layout.layout_item, parent, false)
            viewHolder = ViewHolder(itemView)
            itemView.tag = viewHolder
        } else {
            //tai su dung itemview
            itemView = convertView
            viewHolder = itemView.tag as ViewHolder //=> khong can goi lai findViewById
        }

        val student = students[position]
        viewHolder.name.text = student.name
        viewHolder.mssv.text = student.mssv
        return itemView
    }

    //su dung viewHolder de giam so lan goi findViewById -> tang hieu nang cua ung dung
    class ViewHolder(itemView: View) {
        val name: TextView
        val mssv: TextView
        init {
            name = itemView.findViewById(R.id.name)
            mssv = itemView.findViewById(R.id.mssv)
        }
    }
}