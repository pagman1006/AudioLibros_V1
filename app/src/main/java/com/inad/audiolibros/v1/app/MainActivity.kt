package com.inad.audiolibros.v1.app

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.LinearLayout
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.inad.audiolibros.v1.app.fragments.SelectorFragment
import kotlin.concurrent.fixedRateTimer

class MainActivity : AppCompatActivity() {

    var ARG_ID_LIBRO = "id_libro"

    lateinit var recyclerView : RecyclerView
    lateinit var layoutManager : RecyclerView.LayoutManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if ((findViewById<View>(R.id.contenedor_pequeno) != null) ) {
            val app : Aplicacion = application as Aplicacion
            recyclerView = findViewById(R.id.fragment_selector)
            recyclerView.adapter = app.adaptador
            layoutManager = GridLayoutManager(this, 2)
            recyclerView.layoutManager = layoutManager

            app.adaptador?.setOnItemClickListener {
                Toast.makeText(this, "Seleccionado el elemento: "
                        + recyclerView.getChildAdapterPosition(it), Toast.LENGTH_SHORT).show()
            }
        }
    }
}