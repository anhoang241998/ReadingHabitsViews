package com.annguyenhoang.fashiongallery.adapter

import android.annotation.SuppressLint
import android.os.Handler
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.annguyenhoang.fashiongallery.BookDiffUtil
import com.annguyenhoang.fashiongallery.R
import com.annguyenhoang.fashiongallery.databinding.ItemCardBookLayoutBinding
import com.annguyenhoang.fashiongallery.enums.ViewType
import com.annguyenhoang.fashiongallery.model.Book


class BookAdapter : RecyclerView.Adapter<
        /** BookAdapter.MyViewHolder **/
        RecyclerView.ViewHolder>() {

    private var books: MutableList<Book?> = mutableListOf()

    class LoadingViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val currentBook = books.getOrNull(position)
        when (holder) {
            is ItemViewHolder -> currentBook?.let { holder.bind(it) }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return if (viewType == ViewType.VIEW_TYPE_ITEM.value) {
            ItemViewHolder(parent)
        } else {
            val view = LayoutInflater.from(parent.context).inflate(R.layout.item_load_more, parent, false)
            LoadingViewHolder(view)
        }
    }

    override fun getItemViewType(position: Int): Int {
        return if (books[position] == null) {
            ViewType.VIEW_TYPE_LOADING.value
        } else {
            ViewType.VIEW_TYPE_ITEM.value
        }
    }

    /** using it for BookAdapter.MyViewHolder **/
//    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
//        books.getOrNull(position)?.let { currentBook ->
//            holder.bind(currentBook)
//
//            holder.itemView.rootView.setOnClickListener {
//                EventBus.getDefault().post(BookItemClicked(currentBook))
//            }
//        }
//    }

    /** using it for BookAdapter.MyViewHolder **/
//    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
//        val itemView = LayoutInflater.from(parent.context)
//            .inflate(R.layout.item_card_book_layout, parent, false)
//        return MyViewHolder(itemView)
//    }

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

    inner class ItemViewHolder private constructor(
        private val binding: ItemCardBookLayoutBinding,
    ) : RecyclerView.ViewHolder(binding.root) {

        constructor(parent: ViewGroup) : this(
            ItemCardBookLayoutBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )

        fun bind(book: Book) {
            binding.book = book
        }
    }

    fun setDataBooks(newBooks: List<Book>) {
        val diffUtil = BookDiffUtil(oldBooks = books, newBooks = newBooks)
        val diffUtilResult = DiffUtil.calculateDiff(diffUtil)
        diffUtilResult.dispatchUpdatesTo(this)
        books.clear()
        this.books.addAll(newBooks.toMutableList())
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
}