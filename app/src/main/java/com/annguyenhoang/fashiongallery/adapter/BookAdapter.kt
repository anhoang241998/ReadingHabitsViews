package com.annguyenhoang.fashiongallery.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.annguyenhoang.fashiongallery.R
import com.annguyenhoang.fashiongallery.model.Book

class BookAdapter(private val lstBook: List<Book>): RecyclerView.Adapter<BookAdapter.MyViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.item_card_book_layout, parent, false)
        return MyViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return lstBook.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val currentBook = lstBook[position]
        holder.bookType.text = currentBook.bookType?.typeName ?: "N/a"
        holder.bookTitle.text = currentBook.name
        holder.bookAuthor.text = currentBook.author
        holder.bookView.text = currentBook.totalOfViews.toString()
    }

    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val bookType: TextView = itemView.findViewById(R.id.typeBook)
        val bookTitle: TextView = itemView.findViewById(R.id.titleBook)
        val bookAuthor: TextView = itemView.findViewById(R.id.authorBook)
        val bookView: TextView = itemView.findViewById(R.id.viewBook)
    }
}