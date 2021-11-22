package com.inad.audiolibros.v1.app.fragments

import android.app.Activity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.inad.audiolibros.v1.app.AdaptadorLibros
import com.inad.audiolibros.v1.app.Aplicacion
import com.inad.audiolibros.v1.app.Libro
import java.util.*
import android.widget.Toast
import androidx.recyclerview.widget.GridLayoutManager
import com.inad.audiolibros.v1.app.R

class SelectorFragment : Fragment() {

    private var actividad: Activity? = null
    private var recyclerView: RecyclerView? = null
    private var adaptador: AdaptadorLibros? = null
    private var vectorLibros: Vector<Libro>? = null

    override fun onAttach(activity: Activity) {
        super.onAttach(activity)
        actividad = activity
        var app : Aplicacion = actividad!!.application as Aplicacion
        adaptador = app.adaptador
        vectorLibros = app.vectorLibros
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        var vista: View = inflater.inflate(R.layout.fragment_selector, container, false)
        recyclerView = vista.findViewById(R.id.fragment_selector) as RecyclerView
        recyclerView!!.layoutManager = GridLayoutManager(actividad, 2)
        recyclerView!!.adapter = adaptador
        adaptador!!.setOnItemClickListener { v ->
            Toast.makeText(
                actividad, "Seleccionado el elemento: " +
                        recyclerView!!.getChildAdapterPosition(v), Toast.LENGTH_SHORT
            ).show()
        }

        return vista
    }

}