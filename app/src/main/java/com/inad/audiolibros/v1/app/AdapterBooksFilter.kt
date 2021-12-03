package com.inad.audiolibros.v1.app

import android.content.Context
import android.util.Log
import com.inad.audiolibros.v1.app.utils.Constants
import com.inad.audiolibros.v1.app.utils.Constants.*
import java.util.*

class AdapterBooksFilter : AdapterBooks() {

    private lateinit var bookWithOutFilter : Vector<Book>
    private lateinit var indexFilter : Vector<Int>

    private var search : CharSequence = ""
    private var gender : CharSequence = ""
    private var novelty : Boolean = false
    private var read : Boolean = false

    override fun adapterBooksConstructor(context: Context, vectorBooks: Vector<Book>?) {
        super.adapterBooksConstructor(context, vectorBooks)
        this.bookWithOutFilter = vectorBooks!!
        applyFilter(true)
    }

    fun applyFilter(start : Boolean) {
        vectorBooks = Vector<Book>()
        indexFilter = Vector<Int>()


        for (i in bookWithOutFilter.indices) {
            var book : Book = bookWithOutFilter[i]
            if (start) {
                vectorBooks!!.add(book)
                indexFilter.add(i)
            }
            if (gender.isNotEmpty()) {
                when(gender) {
                    G_TODOS -> {
                        vectorBooks!!.add(book)
                        indexFilter.add(i)
                    }
                    G_EPICO, G_S_XIX, G_SUSPENSE -> {
                        if (book.gender == gender){
                            vectorBooks!!.add(book)
                            indexFilter.add(i)
                        }
                    }
                }
            } else if (search.isNotEmpty()) {
                if ((book.title!!.contains(search, ignoreCase = true) ||
                            book.author!!.contains(search, ignoreCase = true))
                    && book.gender!!.startsWith(gender, ignoreCase = false)) {
                    vectorBooks!!.add(book)
                    indexFilter.add(i)
                }
            }

        }
    }

    fun setSearch(search : String) {
        this.gender = ""
        this.search = search
        applyFilter(false)
    }

    fun setGender(gender : String) {
        this.gender = gender
        applyFilter(false)
    }

    fun setNovelty(novelty : Boolean) {
        this.novelty = novelty
        applyFilter(false)
    }

    fun setRead(read : Boolean) {
        this.read = read
        applyFilter(false)
    }

    fun getItem(position : Int) : Book {
        return bookWithOutFilter.elementAt(indexFilter.elementAt(position))
    }

    override fun getItemId(position : Int) : Long {
        return indexFilter.elementAt(position).toLong()
    }

    fun delete(position : Int) {
        bookWithOutFilter.remove(getItem(position))
        applyFilter(false)
    }

    fun insert(book : Book) {
        bookWithOutFilter.add(book)
        applyFilter(false)
    }
}

















