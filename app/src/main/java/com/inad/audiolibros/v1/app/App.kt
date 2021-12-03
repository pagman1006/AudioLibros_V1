package com.inad.audiolibros.v1.app

import android.app.Application
import com.inad.audiolibros.v1.app.utils.Constants
import java.util.*

class App : Application() {

    var vectorBooks: Vector<Book>? = null
    var adapter: AdapterBooksFilter? = null

    override fun onCreate() {
        super.onCreate()
        vectorBooks = exampleBooks()
        adapter = AdapterBooksFilter()
        adapter!!.adapterBooksConstructor(this, vectorBooks)
    }

    private fun exampleBooks(): Vector<Book> {
        val books = Vector<Book>()
        books.add(
            Book(
                "Kappa", "Akutagawa", R.mipmap.kappa,
                Constants.SERVIDOR + "kappa.mp3", Constants.G_S_XIX, novelty = false, read = false
            )
        )
        books.add(
            Book(
                "Avecilla", "Alas Clarín, Leopoldo", R.mipmap.avecilla,
                Constants.SERVIDOR + "avecilla.mp3", Constants.G_S_XIX, novelty = true, read = false
            )
        )
        books.add(
            Book(
                "Divina Comedia", "Dante", R.mipmap.divina_comedia,
                Constants.SERVIDOR + "divina_comedia.mp3", Constants.G_EPICO, novelty = true, read = false
            )
        )
        books.add(
            Book(
                "Viejo Pancho, El", "Alonso y Trelles, José", R.mipmap.viejo_pancho,
                Constants.SERVIDOR + "viejo_pancho.mp3", Constants.G_S_XIX, novelty = true, read = true
            )
        )
        books.add(
            Book(
                "Cancion de Rolando", "Anónimo", R.mipmap.cancion_rolando,
                Constants.SERVIDOR + "cancion_rolando.mp3", Constants.G_EPICO, novelty = false, read = true
            )
        )
        books.add(
            Book(
                "Matrimonio de sabuesos", "Agata Christie", R.mipmap.matrimonio_sabuesos,
                Constants.SERVIDOR + "matrim_sabuesos.mp3", Constants.G_SUSPENSE, novelty = false, read = true
            )
        )
        books.add(
            Book(
                "Iliada", "Homero", R.mipmap.iliada,
                Constants.SERVIDOR + "la_iliada.mp3", Constants.G_EPICO, novelty = true, read = false
            )
        )
        return books
    }

}