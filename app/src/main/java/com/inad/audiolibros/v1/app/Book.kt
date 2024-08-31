package com.inad.audiolibros.v1.app


class Book(
    var title: String?,
    private var author: String?,
    var imageResource: Int,
    private var urlAudio: String?,
    private var genre: String?,
    private var novelty: Boolean?,
    private var isRead: Boolean?
) {

}