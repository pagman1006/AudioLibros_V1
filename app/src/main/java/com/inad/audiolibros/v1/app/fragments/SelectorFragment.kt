package com.inad.audiolibros.v1.app.fragments

import android.app.Activity
import android.app.AlertDialog
import android.content.Intent
import android.os.Bundle
import android.view.*
import android.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.snackbar.Snackbar
import com.inad.audiolibros.v1.app.*
import com.inad.audiolibros.v1.app.utils.Constants
import java.util.*


class SelectorFragment : Fragment() {

    private var actividad: Activity? = null
    private var recyclerView: RecyclerView? = null
    private var adapter: AdapterBooksFilter? = null
    private var vectorBooks: Vector<Book>? = null

    override fun onAttach(activity: Activity) {
        super.onAttach(activity)
        actividad = activity
        val app: App = actividad!!.application as App
        adapter = app.adapter
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
        recyclerView!!.adapter = adapter

        adapter!!.setOnItemClickListener { v ->
            (actividad as MainActivity).showDetail(
                adapter!!.getItemId(recyclerView!!.getChildAdapterPosition(v))
                    .toInt()
            )
        }
        adapter!!.setOnItemLongClickListener(object : View.OnLongClickListener {
            override fun onLongClick(v: View?): Boolean {
                val id = recyclerView!!.getChildAdapterPosition(v!!)
                val menu: AlertDialog.Builder = AlertDialog.Builder(activity)
                val options = arrayOf("Compartir", "Borrar", "Insertar")
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
                            Snackbar.make(v, "¿Estás seguro?", Snackbar.LENGTH_LONG)
                                .setAction("SI", View.OnClickListener { v ->
                                    adapter!!.delete(id)
                                    adapter!!.notifyDataSetChanged()
                                }).show()
                        }
                        2 -> { // Insertar
                            var position: Int = recyclerView!!.getChildLayoutPosition(v)
                            adapter!!.insert(adapter!!.getItem(position))
                            adapter!!.notifyDataSetChanged()
                            Snackbar.make(v, "Libro insertado", Snackbar.LENGTH_INDEFINITE)
                                .setAction("OK", View.OnClickListener { v -> }).show()
                        }
                    }
                }
                menu.create().show()
                return true
            }
        })
        setHasOptionsMenu(true)
        return vista
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu_selector, menu)
        super.onCreateOptionsMenu(menu, inflater)
        var searchItem : MenuItem = menu.findItem(R.id.menu_buscar)
        var searchView : SearchView = searchItem.actionView as SearchView
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                if (newText!!.isBlank()) {
                    adapter!!.setGender(Constants.G_TODOS)
                    adapter!!.notifyDataSetChanged()
                } else {
                    adapter!!.setSearch(newText)
                    adapter!!.notifyDataSetChanged()
                }
                return false
            }

        })
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.menu_ultimo -> {
                (actividad as MainActivity).goToLastVisited()
                return true
            }
            R.id.menu_buscar -> {
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }

}


