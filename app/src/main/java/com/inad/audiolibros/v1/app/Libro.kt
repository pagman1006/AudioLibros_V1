package com.inad.audiolibros.v1.app

import java.util.*


class Libro {

    var titulo : String? = null
    var autor : String? = null
    var recursoImagen : Int = 0
    var urlAudio: String? = null
    var gener: String? = null
    var novedad: Boolean? = null
    var leido: Boolean? = null

    constructor(
        titulo: String?,
        autor: String?,
        recursoImagen: Int,
        urlAudio: String?,
        gener: String?,
        novedad: Boolean?,
        leido: Boolean?
    ) {
        this.titulo = titulo
        this.autor = autor
        this.recursoImagen = recursoImagen
        this.urlAudio = urlAudio
        this.gener = gener
        this.novedad = novedad
        this.leido = leido
    }
}