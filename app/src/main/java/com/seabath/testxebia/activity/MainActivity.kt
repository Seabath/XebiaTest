package com.seabath.testxebia.activity

import android.app.ProgressDialog
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.widget.ListView
import com.seabath.testxebia.R
import com.seabath.testxebia.adapter.BookAdapter
import com.seabath.testxebia.interfaces.BooksAPI
import com.seabath.testxebia.model.Book
import retrofit.Callback
import retrofit.RestAdapter
import retrofit.RetrofitError
import retrofit.client.Response


class MainActivity : AppCompatActivity() {

    companion object {
        private val URL_SERVER: String = "http://henri-potier.xebia.fr"
    }

    private var mListBooks: ListView? = null
    private var mBooks: List<Book>? = null
    private var mCheckBooks: List<Book>? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mCheckBooks = listOf();
        mListBooks = findViewById(R.id.book_list) as ListView?



        getBooks()
    }

    private fun getBooks() {
        val loading = ProgressDialog.show(this, getString(R.string.progress_dialog_title), getString(R.string.progress_dialog_text), false, false)

        val adapter = RestAdapter.Builder()
                .setEndpoint(URL_SERVER)
                .build()

        val api = adapter.create<BooksAPI>(BooksAPI::class.java)
        api.getBooks(object : Callback<List<Book>> {
            override fun failure(error: RetrofitError?) {
                Log.e(this.toString(), error.toString())
                loading.dismiss()
            }

            override fun success(list: List<Book>, response: Response) {
                loading.dismiss()
                mBooks = list
                showList()
            }
        })
    }

    private fun showList() {
        val adapter = BookAdapter(this, mBooks!!)
        mListBooks!!.adapter = adapter
    }
}