package com.inad.audiolibros.v1.app.fragments

import android.app.Activity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.inad.audiolibros.v1.app.BooksAdapter
import com.inad.audiolibros.v1.app.BookApp
import com.inad.audiolibros.v1.app.Book
import java.util.*
import android.widget.Toast
import androidx.recyclerview.widget.GridLayoutManager
import com.inad.audiolibros.v1.app.R

class SelectorFragment : Fragment() {

    private var actividad: Activity? = null
    private var recyclerView: RecyclerView? = null
    private var adaptador: BooksAdapter? = null
    private var vectorBooks: Vector<Book>? = null

    override fun onAttach(activity: Activity) {
        super.onAttach(activity)
        actividad = activity
        val app : BookApp = actividad!!.application as BookApp
        adaptador = app.adaptador
        vectorBooks = app.vectorBooks
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val vista: View = inflater.inflate(R.layout.fragment_selector, container, false)
        recyclerView = vista.findViewById<View>(R.id.recycler_view) as RecyclerView
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