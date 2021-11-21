package com.inad.audiolibros.v1.app

import android.app.Application
import java.util.*

class Aplicacion : Application() {

    var vectorLibros: Vector<Libro>? = null
    var adaptador: AdaptadorLibros? = null

    val G_TODOS = "Todos los géneros"
    val G_EPICO = "Poema épico"
    val G_S_XIX = "Literatura siglo XIX"
    val G_SUSPENSE = "Suspense"
    val G_ARRAY = arrayOf(G_TODOS, G_EPICO, G_S_XIX, G_SUSPENSE)

    override fun onCreate() {
        super.onCreate()
        vectorLibros = ejemploLibros()
        adaptador = AdaptadorLibros()
        adaptador!!.adaptadorLibrosConstructor(this, vectorLibros)
    }

    fun ejemploLibros(): Vector<Libro>? {
        val SERVIDOR = "http://www.dcomg.upv.es/~jtomas/android/audiolibros/"
        val libros = Vector<Libro>()
        libros.add(
            Libro(
                "Kappa", "Akutagawa", R.mipmap.kappa,
                SERVIDOR + "kappa.mp3", G_S_XIX, false, false
            )
        )
        libros.add(
            Libro(
                "Avecilla", "Alas Clarín, Leopoldo", R.mipmap.avecilla,
                SERVIDOR + "avecilla.mp3", G_S_XIX, true, false
            )
        )
        libros.add(
            Libro(
                "Divina Comedia", "Dante", R.mipmap.divina_comedia,
                SERVIDOR + "divina_comedia.mp3", G_EPICO, true, false
            )
        )
        libros.add(
            Libro(
                "Viejo Pancho, El", "Alonso y Trelles, José", R.mipmap.viejo_pancho,
                SERVIDOR + "viejo_pancho.mp3", G_S_XIX, true, true
            )
        )
        libros.add(
            Libro(
                "Cancion de Rolando", "Anónimo", R.mipmap.cancion_rolando,
                SERVIDOR + "cancion_rolando.mp3", G_EPICO, false, true
            )
        )
        libros.add(
            Libro(
                "Matrimonio de sabuesos", "Agata Christie", R.mipmap.matrimonio_sabuesos,
                SERVIDOR + "matrim_sabuesos.mp3", G_SUSPENSE, false, true
            )
        )
        libros.add(
            Libro(
                "Iliada", "Homero", R.mipmap.iliada,
                SERVIDOR + "la_iliada.mp3", G_EPICO, true, false
            )
        )
        return libros
    }

}