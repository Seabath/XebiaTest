package com.seabath.testxebia.activity

import android.app.ProgressDialog
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.widget.ListView
import android.widget.TextView
import com.seabath.testxebia.R
import com.seabath.testxebia.adapter.BookAdapter
import com.seabath.testxebia.interfaces.BooksAPI
import com.seabath.testxebia.model.Book
import com.seabath.testxebia.model.OfferList
import retrofit.Callback
import retrofit.RestAdapter
import retrofit.RetrofitError
import retrofit.client.Response

class PanierActivity : AppCompatActivity() {

    var mListViewBook: ListView? = null
    var mTextViewPrice: TextView? = null
    var mListBook: List<Book>? = null
    var mOffers: OfferList? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_panier)

        mListBook = intent.getParcelableArrayExtra(MainActivity.Static.EXTRA_LIST_BOOK).toList() as List<Book>
        mListViewBook = findViewById(R.id.lv_book_list) as ListView?
        mListViewBook?.adapter = BookAdapter(this, mListBook!!, false)

        mTextViewPrice = findViewById(R.id.tv_price) as TextView?

        getOffers()
    }


    private fun getOffers() {
        val loading = ProgressDialog.show(this, getString(R.string.progress_dialog_title), getString(R.string.progress_dialog_text), false, false)

        val adapter = RestAdapter.Builder()
                .setEndpoint(MainActivity.Static.URL_SERVER)
                .build()

        val api = adapter.create<BooksAPI>(BooksAPI::class.java)
        var string: String = ""
        mListBook?.forEach {
            string += it.isbn + ","
        }
        string = string.subSequence(0, string.length - 1).toString()
        api.getOffer(string, object : Callback<OfferList> {
            override fun failure(error: RetrofitError?) {
                Log.e(this.toString(), error.toString())
                loading.dismiss()
            }

            override fun success(list: OfferList, response: Response) {
                loading.dismiss()
                mOffers = list
                calculateOffer()
            }
        })
    }

    private fun calculateOffer() {
        var totalPrice: Int = 0
        mListBook?.forEach {
            totalPrice += it.price
        }
        var bestPrice: Int = totalPrice
        mOffers?.offers?.forEach {
            when (it.type) {
                "percentage" -> {
                    val tmpPrice = totalPrice - totalPrice * it.value!! / 100
                    if (tmpPrice <= bestPrice)
                        bestPrice = tmpPrice
                }
                "minus" -> {
                    val tmpPrice = totalPrice - it.value!!
                    if (tmpPrice <= bestPrice)
                        bestPrice = tmpPrice
                }
                "slice" -> {
                    val tmpPrice = totalPrice - (totalPrice / it.sliceValue!!) * it.value!!
                    if (tmpPrice <= bestPrice)
                        bestPrice = tmpPrice
                }
            }
        }

        mTextViewPrice?.text = String.format(getString(R.string.price_text_view), bestPrice, getString(R.string.currency))
    }
}
