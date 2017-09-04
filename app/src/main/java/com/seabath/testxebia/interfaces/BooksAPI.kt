package com.seabath.testxebia.interfaces

import com.seabath.testxebia.model.Book
import com.seabath.testxebia.model.OfferList
import retrofit.Callback
import retrofit.http.GET
import retrofit.http.Path

interface BooksAPI {

    @GET("/books")
    fun getBooks(response: Callback<List<Book>>)

    @GET("/books/{url}/commercialOffers")
    fun getOffer(@Path("url") url: String, response: Callback<OfferList>)

}