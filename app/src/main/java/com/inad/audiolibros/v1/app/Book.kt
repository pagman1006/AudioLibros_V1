package com.inad.audiolibros.v1.app


class Book(
    var title: String?,
    var author: String?,
    var imageResource: Int,
    var urlAudio: String?,
    var gender: String?,
    var novelty: Boolean?,
    var read: Boolean?
)