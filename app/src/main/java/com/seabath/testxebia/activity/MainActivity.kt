package com.seabath.testxebia.activity

import android.app.ProgressDialog
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.ListView
import android.widget.TextView
import com.seabath.testxebia.R
import com.seabath.testxebia.adapter.BookAdapter
import com.seabath.testxebia.interfaces.BooksAPI
import com.seabath.testxebia.model.Book
import retrofit.Callback
import retrofit.RestAdapter
import retrofit.RetrofitError
import retrofit.client.Response


class MainActivity : AppCompatActivity() {

    companion object Static{
        val URL_SERVER: String = "http://henri-potier.xebia.fr"
        val EXTRA_LIST_BOOK: String = "EXTRA_LIST_BOOK"
    }

    private var mListBooks: ListView? = null
    private var mTvNbObject: TextView? = null
    private var mButtonValider: Button? = null

    private var mBooks: List<Book>? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mListBooks = findViewById(R.id.lv_book_list) as ListView?

        mTvNbObject = findViewById(R.id.tv_nb_object) as TextView?
        mTvNbObject?.text = getString(R.string.zero);

        mButtonValider = findViewById(R.id.btn_validate_main) as Button?
        mButtonValider?.setOnClickListener(View.OnClickListener {
            val intent: Intent = Intent(baseContext, PanierActivity::class.java)

            val panier = (mListBooks?.adapter as BookAdapter).mPanier.toTypedArray()
            intent.putExtra(EXTRA_LIST_BOOK, panier)
            startActivity(intent)
        })

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