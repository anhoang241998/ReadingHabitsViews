package com.annguyenhoang.fashiongallery.adapter

import android.annotation.SuppressLint
import android.os.Handler
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ProgressBar
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.annguyenhoang.fashiongallery.R
import com.annguyenhoang.fashiongallery.enums.ViewType
import com.annguyenhoang.fashiongallery.model.Book

class BookAdapter : ListAdapter<Book, RecyclerView.ViewHolder>(ITEM_VIEW_DIFFUTIL) {
    private var books: MutableList<Book?> = mutableListOf()
    private var onButtonItemClickListener: ((Book) -> Unit)?= null

    inner class ItemViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val bookType = view.findViewById<TextView>(R.id.typeBook)
        private val bookAuthor = view.findViewById<TextView>(R.id.authorBook)
        private val bookTitle = view.findViewById<TextView>(R.id.titleBook)
        private val bookView = view.findViewById<TextView>(R.id.viewBook)
        private val bookButton = view.findViewById<Button>(R.id.btnRead)
        private val progressBar = view.findViewById<ProgressBar>(R.id.progressBarHorizontal)

        fun bind(book: Book) {
            bookType.text = book.bookType?.typeName ?: "N/a"
            bookAuthor.text = book.author
            bookView.text = book.totalOfViews.toString()
            bookTitle.text = book.name
            progressBar.progress = book.duration ?: 0
            progressBar.visibility = if (progressBar.progress > 0) View.VISIBLE else View.GONE
            bookButton.text = when (progressBar.progress) {
                0 -> "Đọc ngay"
                100 -> "Đọc lại"
                else -> "Đọc tiếp"
            }
        }

        fun setOnButtonItemClickListener(onButtonItemClickListener: () -> Unit) {
            bookButton.setOnClickListener {
                onButtonItemClickListener()
            }
        }
    }

    inner class LoadingViewHolder(view: View) : RecyclerView.ViewHolder(view)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return if (viewType == ViewType.VIEW_TYPE_ITEM.value) {
            val view =
                LayoutInflater.from(parent.context)
                    .inflate(R.layout.item_card_book_layout, parent, false)
            ItemViewHolder(view)
        } else {
            val view =
                LayoutInflater.from(parent.context)
                    .inflate(R.layout.item_load_more, parent, false)
            LoadingViewHolder(view)
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val currentBook = books.getOrNull(position)
        when (holder) {
            is ItemViewHolder -> currentBook?.let { book ->
                holder.bind(book)
                holder.setOnButtonItemClickListener {
                    onButtonItemClickListener?.invoke(currentBook)
                }
            }
        }
    }

//    override fun submitList(list: MutableList<Book>?) {
//        Log.d("TAG", "submitList: ")
//        super.submitList(list?.let { ArrayList(it) })
//    }

    override fun getItemViewType(position: Int): Int {
        return if (books[position] == null) {
            ViewType.VIEW_TYPE_LOADING.value
        } else {
            ViewType.VIEW_TYPE_ITEM.value
        }
    }

    override fun getItemCount(): Int = books.size

    @SuppressLint("NotifyDataSetChanged")
    fun setBooksToList(books: List<Book>) {
        if (this.books.isNotEmpty()) {
            this.books.clear()
        }

        this.books.addAll(books)
        notifyDataSetChanged()
//        notifyItemRangeInserted(0, books.count())
    }

    @SuppressLint("NotifyDataSetChanged")
    fun addData(data: ArrayList<Book?>) {
        this.books.addAll(data)
        notifyDataSetChanged()
    }

    fun addLoadingView() {
        Handler().post {
            books.add(null)
            notifyItemInserted(books.size - 1)
        }
    }

    fun removeLoadingView() {
        if (books.size != 0) {
            books.removeAt(books.size - 1)
            notifyItemRemoved(books.size)
        }
    }

    fun setOnButtonItemClickListener(onButtonItemClickListener: (Book) -> Unit) {
        this.onButtonItemClickListener = onButtonItemClickListener
    }

    companion object {
        val ITEM_VIEW_DIFFUTIL = object : DiffUtil.ItemCallback<Book>() {
            override fun areItemsTheSame(oldItem: Book, newItem: Book): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: Book, newItem: Book): Boolean {
                return oldItem.name == newItem.name
            }
        }
    }
}