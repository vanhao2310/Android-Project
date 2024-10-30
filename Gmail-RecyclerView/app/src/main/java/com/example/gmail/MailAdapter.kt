package com.example.gmail

import android.graphics.Color
import android.graphics.drawable.GradientDrawable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import kotlin.random.Random

class MailAdapter(val mails: List<MailItemModel>, val listener: ItemSelect? = null): RecyclerView.Adapter<MailAdapter.MailViewHolder>() {

    class MailViewHolder(itemView: View, listener: ItemSelect?): RecyclerView.ViewHolder(itemView) {
        val textAvt: TextView
        val senderName: TextView
        val content: TextView
        val time: TextView
        var imageCheck: ImageView
        init {
            textAvt = itemView.findViewById(R.id.first_name_avt)
            senderName = itemView.findViewById(R.id.text_sender)
            content = itemView.findViewById(R.id.text_content)
            time = itemView.findViewById(R.id.text_time)
            imageCheck = itemView.findViewById(R.id.image_check)
            imageCheck.setOnClickListener({
                listener?.CheckedMail(adapterPosition)
            })
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MailViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.layout_mail_item, parent, false)
        return MailViewHolder(itemView, listener)
    }

    override fun getItemCount(): Int = mails.size

    override fun onBindViewHolder(holder: MailViewHolder, position: Int) {
        val mail = mails[position]
        holder.textAvt.text = "${mail.name[0]}"
        holder.senderName.text = mail.name
        holder.content.text = mail.content
        holder.time.text = mail.time

        if (mail.isChecked) {
            //neu duoc chon thi cho sao mau vang
            holder.imageCheck.setImageResource(R.drawable.baseline_star_24)
        } else {
            holder.imageCheck.setImageResource(R.drawable.baseline_star_border_24)
        }
        //lay mau ngau nhien va set hinh tron
        val colors = listOf(
            Color.RED, Color.GREEN, Color.YELLOW, Color.CYAN, Color.MAGENTA
        )
        val shapeDrawable = GradientDrawable().apply {
            shape = GradientDrawable.RECTANGLE
            setColor(colors[mail.colorId])
            cornerRadius = 1000f  // Bán kính lớn để tạo hiệu ứng tròn
        }
        holder.textAvt.background = shapeDrawable
    }
}