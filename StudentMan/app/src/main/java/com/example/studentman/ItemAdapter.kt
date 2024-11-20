package com.example.studentman

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.lifecycle.findViewTreeViewModelStoreOwner
import androidx.recyclerview.widget.RecyclerView

class ItemAdapter(val items: List<ItemModel>, val listener: ItemClickListener? = null):
    RecyclerView.Adapter<ItemAdapter.ItemViewHolder>() {
    class ItemViewHolder(itemView: View, listener: ItemClickListener?): RecyclerView.ViewHolder(itemView) {
        val text_name: TextView
        val text_mssv: TextView
        val btn_edit: Button
        val btn_remove: Button
        init {
            text_name = itemView.findViewById(R.id.text_name)
            text_mssv = itemView.findViewById(R.id.text_mssv)
            btn_edit = itemView.findViewById(R.id.btn_edit)
            btn_remove = itemView.findViewById(R.id.btn_remove)
            btn_edit.setOnClickListener({
                listener?.edit(adapterPosition)
            })
            btn_remove.setOnClickListener({
                listener?.remove(adapterPosition, it)
            })
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.layout_item, parent, false)
        return ItemViewHolder(itemView, listener)
    }

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        val item = items[position]
        holder.text_name.text = item.name
        holder.text_mssv.text = item.mssv
    }

}