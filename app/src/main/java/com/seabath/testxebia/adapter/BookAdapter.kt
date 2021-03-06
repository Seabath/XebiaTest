package com.seabath.testxebia.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import com.seabath.testxebia.R
import com.seabath.testxebia.activity.BookActivity
import com.seabath.testxebia.activity.MainActivity
import com.seabath.testxebia.model.Book
import com.squareup.picasso.Picasso

class BookAdapter(var mContext: Context, var mBooks: List<Book>, var mHasPanier: Boolean) : BaseAdapter() {

    var mPanier: MutableList<Book> = mutableListOf()

    companion object Static {
        val EXTRA_BOOK: String = "EXTRA_BOOK"
    }

    private val mInflator: LayoutInflater = LayoutInflater.from(mContext)

    override fun getItem(position: Int): Any = mBooks.get(position)

    override fun getItemId(position: Int): Long = position.toLong()

    override fun getCount(): Int = mBooks.size

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View? {
        val view: View
        val vh: CellBookRowHolder
        if (convertView == null) {
            view = this.mInflator.inflate(R.layout.cell_book, parent, false)
            vh = CellBookRowHolder(view)
        } else {
            view = convertView
            vh = CellBookRowHolder(view)
        }
        val book = mBooks[position];

        vh.title.text = book.title
        Picasso.with(mContext).load(book.cover).into(vh.cover)
        if (mHasPanier) {
            vh.buttonAdd.visibility = View.VISIBLE
            vh.buttonAdd.setOnClickListener(View.OnClickListener {
                mPanier.add(book)
                Toast.makeText(mContext, book.title + " " + mContext.getText(R.string.added), Toast.LENGTH_SHORT).show()
            })
        }
        vh.price.text = String.format("%d%s", book.price, mContext.getString(R.string.currency))

        view.setOnClickListener(View.OnClickListener {
            val intent = Intent(mContext, BookActivity::class.java)
            intent.putExtra(EXTRA_BOOK, book)
            mContext.startActivity(intent)
        })

        return view
    }


    private class CellBookRowHolder(row: View?) {
        val cover: ImageView
        val title: TextView
        var buttonAdd: Button
        val price: TextView

        init {
            this.title = row?.findViewById(R.id.title_book) as TextView
            this.cover = row.findViewById(R.id.cover_book) as ImageView
            this.price = row.findViewById(R.id.tv_price) as TextView
            this.buttonAdd = row.findViewById(R.id.btn_add) as Button
        }
    }
}
