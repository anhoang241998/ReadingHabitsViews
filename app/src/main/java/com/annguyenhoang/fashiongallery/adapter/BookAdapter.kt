package com.annguyenhoang.fashiongallery.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.annguyenhoang.fashiongallery.R
import com.annguyenhoang.fashiongallery.adapter.BookAdapterEvent.BookItemClicked
import com.annguyenhoang.fashiongallery.model.Book
import org.greenrobot.eventbus.EventBus

class BookAdapter : RecyclerView.Adapter<BookAdapter.MyViewHolder>() {
    private val books: MutableList<Book> = mutableListOf()


    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        books.getOrNull(position)?.let { currentBook ->
            holder.bind(currentBook)

            holder.itemView.rootView.setOnClickListener {
                EventBus.getDefault().post(BookItemClicked(currentBook))
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_card_book_layout, parent, false)
        return MyViewHolder(itemView)
    }

    override fun getItemCount(): Int = books.size

    fun setBooksToList(books: List<Book>) {
        if (this.books.isNotEmpty()) {
            this.books.clear()
        }

        this.books.addAll(books)
        notifyItemRangeInserted(0, books.count())
    }

    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        //        private val bookContainer: ConstraintLayout = itemView.findViewById(R.id.book_container)
        private val bookType: TextView = itemView.findViewById(R.id.typeBook)
        private val bookTitle: TextView = itemView.findViewById(R.id.titleBook)
        private val bookAuthor: TextView = itemView.findViewById(R.id.authorBook)
        private val bookView: TextView = itemView.findViewById(R.id.viewBook)

        fun bind(book: Book) {
            bookType.text = book.bookType?.typeName ?: "N/a"
            bookTitle.text = book.name
            bookAuthor.text = book.author
            bookView.text = book.totalOfViews.toString()
        }
    }
}