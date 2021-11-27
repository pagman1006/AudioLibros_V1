package com.inad.audiolibros.v1.app.fragments

import android.app.Activity
import android.app.AlertDialog
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.inad.audiolibros.v1.app.*
import java.util.*


class SelectorFragment : Fragment() {

    private var actividad: Activity? = null
    private var recyclerView: RecyclerView? = null
    private var adaptador: AdapterBooks? = null
    private var vectorBooks: Vector<Book>? = null

    override fun onAttach(activity: Activity) {
        super.onAttach(activity)
        actividad = activity
        val app: App = actividad!!.application as App
        adaptador = app.adapter
        vectorBooks = app.vectorBooks
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        val vista: View = inflater.inflate(R.layout.fragment_selector, container, false)
        recyclerView = vista.findViewById(R.id.fragment_selector) as RecyclerView
        recyclerView!!.layoutManager = GridLayoutManager(actividad, 2)
        recyclerView!!.adapter = adaptador

        adaptador!!.setOnItemClickListener { v ->
            (actividad as MainActivity).showDetail(recyclerView!!.getChildAdapterPosition(v))
        }
        adaptador!!.setOnItemLongClickListener(object : View.OnLongClickListener {
            override fun onLongClick(v: View?): Boolean {
                val id = recyclerView!!.getChildAdapterPosition(v!!)
                val menu : AlertDialog.Builder = AlertDialog.Builder(activity)
                val options = arrayOf("Compartir", "Borrar", "Insertar")
                /*
                Forma normal y lambda abajo
                menu.setItems(opciones, object : DialogInterface.OnClickListener {
                    override fun onClick(dialog: DialogInterface?, opcion: Int) {
                        when (opcion) {
                            0 -> { // Compartir
                                val libro : Libro = vectorLibros!!.elementAt(id)
                                val intent : Intent = Intent(Intent.ACTION_SEND)
                                intent.type = "text/plain"
                                intent.putExtra(Intent.EXTRA_SUBJECT, libro.titulo)
                                intent.putExtra(Intent.EXTRA_TEXT, libro.urlAudio)
                                startActivity(Intent.createChooser(intent, "Compartir"))
                            }
                            1 -> { // Borrar
                                vectorLibros!!.removeAt(id)
                                adaptador!!.notifyDataSetChanged()
                            }
                            2 -> { // Insertar
                                vectorLibros!!.add(vectorLibros!!.elementAt(id))
                                adaptador!!.notifyDataSetChanged()
                            }
                        }
                    }
                })
                 */
                menu.setItems(options) { dialog, option ->
                    when (option) {
                        0 -> { // Compartir
                            val book: Book = vectorBooks!!.elementAt(id)
                            val intent = Intent(Intent.ACTION_SEND)
                            intent.type = "text/plain"
                            intent.putExtra(Intent.EXTRA_SUBJECT, book.title)
                            intent.putExtra(Intent.EXTRA_TEXT, book.urlAudio)
                            startActivity(Intent.createChooser(intent, "Compartir"))
                        }
                        1 -> { // Borrar
                            vectorBooks!!.removeAt(id)
                            adaptador!!.notifyDataSetChanged()
                        }
                        2 -> { // Insertar
                            vectorBooks!!.add(vectorBooks!!.elementAt(id))
                            adaptador!!.notifyDataSetChanged()
                        }
                    }
                }
                menu.create().show()
                return true
            }
        })
        return vista
    }

}