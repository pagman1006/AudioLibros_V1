package com.inad.audiolibros.v1.app

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import java.util.*

class BooksAdapter : RecyclerView.Adapter<BooksAdapter.ViewHolder>() {

    private var inflator: LayoutInflater? = null
    protected var vectorBooks: Vector<Book>? = null
    private var context: Context? = null

    private var onClickListener: View.OnClickListener? = null

    fun booksAdapterConstructor(context: Context, vectorBooks: Vector<Book>?) {
        this.context = context
        this.vectorBooks = vectorBooks
        inflator = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
    }

    // Creamos nuestro ViewHolder, con los tipos de elementos a modificar
    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var frontPage: ImageView = itemView.findViewById<View>(R.id.portada) as ImageView
        var title: TextView = itemView.findViewById<View>(R.id.titulo) as TextView
    }

    // Creamos el ViewHolder con la vista de un elemento sin personalizar
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        // Inflamos la vista desde el xml
        val v : View = inflator!!.inflate(R.layout.elemento_selector, null)
        v.setOnClickListener(onClickListener)
        return ViewHolder(v);
    }

    // Usamos como base el ViewHolder y lo personalizamos
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val book = vectorBooks!!.elementAt(position)
        holder.frontPage.setImageResource(book.imageResource)
        holder.title.text = book.title
    }

    // Indicamos el número de elementos de la lista
    override fun getItemCount(): Int {
        return vectorBooks!!.size
    }

    fun setOnItemClickListener(onClickListener: View.OnClickListener?) {
        this.onClickListener = onClickListener
    }
}