package com.inad.audiolibros.v1.app

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {

    private lateinit var recyclerView : RecyclerView
    private lateinit var layoutManager : RecyclerView.LayoutManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val app : BookApp = application as BookApp
        recyclerView = findViewById(R.id.recycler_view)
        recyclerView.adapter = app.adaptador
        layoutManager = GridLayoutManager(this, 2)
        recyclerView.layoutManager = layoutManager

        app.adaptador?.setOnItemClickListener {
            Toast.makeText(this, "Seleccionado el elemento: "
                + recyclerView.getChildAdapterPosition(it), Toast.LENGTH_SHORT).show()
        }
    }
}