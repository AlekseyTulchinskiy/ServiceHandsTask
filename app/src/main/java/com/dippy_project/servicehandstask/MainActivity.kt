package com.dippy_project.servicehandstask

import android.widget.Button
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: MyAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recyclerView = findViewById(R.id.recyclerView)
        adapter = MyAdapter(mutableListOf())

        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(this)

        val buttonAddData = findViewById<Button>(R.id.button)
        buttonAddData.setOnClickListener {
            val random = (0..1).random()

            if (random == 0) {
                adapter.addData(MyAdapter.TYPE_CARD1)
            } else {
                adapter.addData(MyAdapter.TYPE_CARD2)
            }
        }
    }
}