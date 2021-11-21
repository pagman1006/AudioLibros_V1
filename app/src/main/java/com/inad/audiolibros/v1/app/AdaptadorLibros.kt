package com.inad.audiolibros.v1.app

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import java.util.*

class AdaptadorLibros : RecyclerView.Adapter<AdaptadorLibros.ViewHolder>() {

    private var inflador: LayoutInflater? = null
    protected var vectorLibros: Vector<Libro>? = null
    private var contexto: Context? = null

    private var onClickListener: View.OnClickListener? = null

    fun adaptadorLibrosConstructor(contexto: Context, vectorLibros: Vector<Libro>?) {
        this.contexto = contexto
        this.vectorLibros = vectorLibros
        inflador = contexto.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
    }



    // Creamos nuestro ViewHolder, con los tipos de elementos a modificar
    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var portada: ImageView
        var titulo: TextView

        init {
            portada = itemView.findViewById<View>(R.id.portada) as ImageView
            titulo = itemView.findViewById<View>(R.id.titulo) as TextView
        }
    }

    // Creamos el ViewHolder con la vista de un elemento sin personalizar
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        // Inflamos la vista desde el xml
        var v : View = inflador!!.inflate(R.layout.elemento_selector, null)
        v.setOnClickListener(onClickListener)
        return ViewHolder(v);
    }

    // Usamos como base el ViewHolder y lo personalizamos
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val libro = vectorLibros!!.elementAt(position)
        holder.portada.setImageResource(libro.recursoImagen)
        holder.titulo.text = libro.titulo
    }

    // Indicamos el número de elementos de la lista
    override fun getItemCount(): Int {
        return vectorLibros!!.size
    }

    fun setOnItemClickListener(onClickListener: View.OnClickListener?) {
        this.onClickListener = onClickListener
    }
}