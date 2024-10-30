package com.example.gmail

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity(), ItemSelect {
    lateinit var mails: MutableList<MailItemModel>
    lateinit var adapter: MailAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        mails = mutableListOf(
            MailItemModel("Albert Einstein", "12/03/1879"),
            MailItemModel("Nelson Mandela", "18/07/1918"),
            MailItemModel("Mahatma Gandhi", "02/10/1869"),
            MailItemModel("Martin Luther King Jr.", "15/01/1929"),
            MailItemModel("Leonardo da Vinci", "15/04/1452"),
            MailItemModel("Isaac Newton", "25/12/1642"),
            MailItemModel("Winston Churchill", "30/11/1874"),
            MailItemModel("Charles Darwin", "12/02/1809"),
            MailItemModel("Abraham Lincoln", "12/02/1809"),
            MailItemModel("Steve Jobs", "24/02/1955"),
            MailItemModel("William Shakespeare", "26/04/1564"),
            MailItemModel("Muhammad Ali", "17/01/1942"),
            MailItemModel("Mother Teresa", "26/08/1910"),
            MailItemModel("Albert Schweitzer", "14/01/1875"),
            MailItemModel("Marie Curie", "07/11/1867"),
            MailItemModel("Elvis Presley", "08/01/1935"),
            MailItemModel("John F. Kennedy", "29/05/1917")
        )

        adapter = MailAdapter(mails, this)

        val listMail = findViewById<RecyclerView>(R.id.list_mail)
        listMail.adapter = adapter
        listMail.layoutManager = LinearLayoutManager(this)
    }

    override fun CheckedMail(position: Int) {
        mails[position].isChecked = !mails[position].isChecked
        adapter.notifyItemChanged(position)
    }
}