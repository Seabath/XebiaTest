package com.seabath.testxebia.interfaces

import com.seabath.testxebia.model.Book
import retrofit.http.GET
import retrofit.Callback

interface BooksAPI {

    @GET("/books")
    fun getBooks(response: Callback<List<Book>>)

}