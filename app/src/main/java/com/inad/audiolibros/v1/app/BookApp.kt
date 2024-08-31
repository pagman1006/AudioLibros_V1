package com.inad.audiolibros.v1.app

import android.app.Application
import java.util.*

class BookApp : Application() {

    var vectorBooks: Vector<Book>? = null
    var adaptador: BooksAdapter? = null

    private val G_ALL = "Todos los géneros"
    private val G_EPICO = "Poema épico"
    private val G_S_XIX = "Literatura siglo XIX"
    private val G_SUSPENSE = "Suspense"
    //val G_ARRAY = arrayOf(G_ALL, G_EPICO, G_S_XIX, G_SUSPENSE)

    override fun onCreate() {
        super.onCreate()
        vectorBooks = exampleBooks()
        adaptador = BooksAdapter()
        adaptador!!.booksAdapterConstructor(this, vectorBooks)
    }

    private fun exampleBooks(): Vector<Book>? {
        val SERVER = "http://www.dcomg.upv.es/~jtomas/android/audiolibros/"
        val books = Vector<Book>()
        books.add(
            Book(
                "Kappa", "Akutagawa", R.mipmap.kappa,
                SERVER + "kappa.mp3", G_S_XIX, false, false
            )
        )
        books.add(
            Book(
                "Avecilla", "Alas Clarín, Leopoldo", R.mipmap.avecilla,
                SERVER + "avecilla.mp3", G_S_XIX, true, false
            )
        )
        books.add(
            Book(
                "Divina Comedia", "Dante", R.mipmap.divina_comedia,
                SERVER + "divina_comedia.mp3", G_EPICO, true, false
            )
        )
        books.add(
            Book(
                "Viejo Pancho, El", "Alonso y Trelles, José", R.mipmap.viejo_pancho,
                SERVER + "viejo_pancho.mp3", G_S_XIX, true, true
            )
        )
        books.add(
            Book(
                "Cancion de Rolando", "Anónimo", R.mipmap.cancion_rolando,
                SERVER + "cancion_rolando.mp3", G_EPICO, false, true
            )
        )
        books.add(
            Book(
                "Matrimonio de sabuesos", "Agata Christie", R.mipmap.matrimonio_sabuesos,
                SERVER + "matrim_sabuesos.mp3", G_SUSPENSE, false, true
            )
        )
        books.add(
            Book(
                "Iliada", "Homero", R.mipmap.iliada,
                SERVER + "la_iliada.mp3", G_EPICO, true, false
            )
        )
        return books
    }

}