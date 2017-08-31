package com.seabath.testxebia.activity

import android.app.ProgressDialog
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ListView

import com.seabath.testxebia.R
import com.seabath.testxebia.adapter.BookAdapter
import com.seabath.testxebia.adapter.BookPanierAdapter
import com.seabath.testxebia.interfaces.BooksAPI
import com.seabath.testxebia.model.Book
import com.seabath.testxebia.model.Offer
import retrofit.Callback
import retrofit.RestAdapter
import retrofit.RetrofitError
import retrofit.client.Response

class PanierActivity : AppCompatActivity() {

    var mListViewBook: ListView? = null
    var mListBook: List<Book>? = null
    var mOffers: List<Offer>? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_panier)

        mListBook = intent.getParcelableArrayExtra(MainActivity.Static.EXTRA_LIST_BOOK).toList() as List<Book>
        mListViewBook = findViewById(R.id.lv_book_list) as ListView?
        mListViewBook?.adapter = BookPanierAdapter(this, mListBook!!)

        getOffers()
    }


    private fun getOffers() {
        val loading = ProgressDialog.show(this, getString(R.string.progress_dialog_title), getString(R.string.progress_dialog_text), false, false)

        val adapter = RestAdapter.Builder()
                .setEndpoint(MainActivity.Static.URL_SERVER)
                .build()

        val api = adapter.create<BooksAPI>(BooksAPI::class.java)
        api.getOffer(object : Callback<List<Offer>> {
            override fun failure(error: RetrofitError?) {
                Log.e(this.toString(), error.toString())
                loading.dismiss()
            }

            override fun success(list: List<Offer>, response: Response) {
                loading.dismiss()
                mOffers = list
                calculateOffer()
            }
        })
    }

    private fun calculateOffer() {
        mOffers?.forEach {
            when (it.type) {
                "percentage" -> {

                }
                "minus" -> {

                }
                "slice" -> {

                }
            }
        }
    }
}
