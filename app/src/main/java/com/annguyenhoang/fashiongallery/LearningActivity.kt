package com.annguyenhoang.fashiongallery

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.annguyenhoang.fashiongallery.model.Data
import com.annguyenhoang.fashiongallery.R
import com.annguyenhoang.fashiongallery.adapter.Books2Adapter
import com.annguyenhoang.fashiongallery.adapter.RecyclerViewAdapter
import com.google.android.material.floatingactionbutton.FloatingActionButton

class LearningActivity : AppCompatActivity() {

    private lateinit var rvBooks: RecyclerView
    private lateinit var btnAdd: FloatingActionButton

    private val booksAdapter by lazy {
        Books2Adapter()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_learning)
        initViews()
//        rvBooks.setUp(adapter = booksAdapter)
//
//        val data = mutableListOf(
//            Book(id = "1: Book An", bookName = "Book 1", viewType = ItemType.BOOK),
//            Book(id = "2: author An", authorName = "Author 1", viewType = ItemType.AUTHOR),
//            Book(id = "3: bookName An", bookName = "Book 2", viewType = ItemType.BOOK),
//        )
//
//        booksAdapter.addDataToList(data)
//
//        btnAdd.setOnClickListener {
//            val newBook = Book(bookName = "New Book ${data.lastIndex + 1}")
//            data.add(newBook)
//            booksAdapter.addDataToList(data)
//        }

        val dataList = ArrayList<Data>()
        dataList.add(Data(RecyclerViewAdapter.VIEW_TYPE_ONE, "1. Hi! I am in View 1"))
        dataList.add(Data(RecyclerViewAdapter.VIEW_TYPE_TWO, "2. Hi! I am in View 2"))
        dataList.add(Data(RecyclerViewAdapter.VIEW_TYPE_ONE, "3. Hi! I am in View 3"))
        dataList.add(Data(RecyclerViewAdapter.VIEW_TYPE_TWO, "4. Hi! I am in View 4"))
        dataList.add(Data(RecyclerViewAdapter.VIEW_TYPE_ONE, "5. Hi! I am in View 5"))
        dataList.add(Data(RecyclerViewAdapter.VIEW_TYPE_TWO, "6. Hi! I am in View 6"))
        dataList.add(Data(RecyclerViewAdapter.VIEW_TYPE_ONE, "7. Hi! I am in View 7"))
        dataList.add(Data(RecyclerViewAdapter.VIEW_TYPE_TWO, "8. Hi! I am in View 8"))
        dataList.add(Data(RecyclerViewAdapter.VIEW_TYPE_ONE, "9. Hi! I am in View 9"))
        dataList.add(Data(RecyclerViewAdapter.VIEW_TYPE_TWO, "10. Hi! I am in View 10"))
        dataList.add(Data(RecyclerViewAdapter.VIEW_TYPE_ONE, "11. Hi! I am in View 11"))
        dataList.add(Data(RecyclerViewAdapter.VIEW_TYPE_TWO, "12. Hi! I am in View 12"))
        dataList.add(Data(RecyclerViewAdapter.VIEW_TYPE_TWO, "12. Hi! I am in View 13"))

        val adapter = RecyclerViewAdapter(this, dataList)
        rvBooks.layoutManager = LinearLayoutManager(this)
        rvBooks.adapter = adapter
    }

    private fun initViews() {
        rvBooks = findViewById(R.id.rv_books)
        btnAdd = findViewById(R.id.btn_add)
    }

}

