package com.seabath.testxebia.interfaces

import com.seabath.testxebia.model.Book
import retrofit.http.GET
import retrofit.Callback
import retrofit.client.Response
import retrofit.http.Path

interface BooksAPI {

    @GET("/books")
    fun getBooks(response: Callback<List<Book>>)

}