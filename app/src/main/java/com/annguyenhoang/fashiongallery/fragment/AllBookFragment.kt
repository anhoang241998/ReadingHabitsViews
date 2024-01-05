package com.annguyenhoang.fashiongallery.fragment

import android.annotation.SuppressLint
import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.annguyenhoang.fashiongallery.ListBookViewModel
import com.annguyenhoang.fashiongallery.OnLoadMoreListener
import com.annguyenhoang.fashiongallery.R
import com.annguyenhoang.fashiongallery.RecyclerViewLoadMoreScroll
import com.annguyenhoang.fashiongallery.adapter.BookAdapter
import com.annguyenhoang.fashiongallery.adapter.BookAdapterEvent
import com.annguyenhoang.fashiongallery.enums.FetchingStatus
import com.annguyenhoang.fashiongallery.model.Book
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe
import org.greenrobot.eventbus.ThreadMode

class AllBookFragment : TabFragment(R.layout.recycleview_book_layout) {

    private val viewModel: ListBookViewModel by activityViewModels()
    private var bookType: Int = 0
    private val bookAdapter by lazy(mode = LazyThreadSafetyMode.SYNCHRONIZED) {
        BookAdapter()
    }
    private lateinit var loadMoreBooks: ArrayList<Book?>
    private lateinit var scrollListener: RecyclerViewLoadMoreScroll
    private lateinit var recyclerView: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            bookType = it.getInt(BOOK_TYPE)
        }
    }

    override fun viewsControl() {

        when (bookType) {
            1 -> {
                viewModel.wordBooks.observe(viewLifecycleOwner) { uiState ->
                    uiState?.data?.let { dataBooks ->
//                                bookAdapter.setBooksToList(dataBooks)
                        Log.d("TAG", "viewsControl: ${dataBooks.size}")
                        bookAdapter.submitList(dataBooks.toList())
                    }
                }

                viewModel.fetchWordBooks()
            }

            2 -> {
                viewModel.imageBooks.observe(viewLifecycleOwner) { uiState ->
                    when (uiState.fetchingStatus) {
                        FetchingStatus.SUCCESS -> {
                            uiState?.data?.let { dataBooks ->
                                bookAdapter.setBooksToList(dataBooks)
                            }
                        }
                        else -> {}
                    }
                }

                viewModel.fetchImageBooks()
            }

            else -> {
                viewModel.allBooks.observe(viewLifecycleOwner) { uiState ->
                    when (uiState.fetchingStatus) {
                        FetchingStatus.SUCCESS -> {
                            uiState?.data?.let { dataBooks ->
                                bookAdapter.setBooksToList(dataBooks)
                            }
                        }
                        else -> {}
                    }
                }

                viewModel.fetchAllBooks()
            }
        }

        bookAdapter.setOnButtonItemClickListener { book ->
            viewModel.updateProgressBar(book = book)
            Log.d("TAG", "viewsControl: btn click")
        }
    }

    override fun initViews(view: View) {
        recyclerView = view.findViewById(R.id.recycleView)

        recyclerView.apply {
            layoutManager = LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false)
            adapter = bookAdapter
            itemAnimator = DefaultItemAnimator()
            addItemDecoration(
                DividerItemDecoration(requireContext(), LinearLayoutManager.VERTICAL)
            )

//            setHasFixedSize(true)
//            setItemViewCacheSize(10)

            scrollListener = RecyclerViewLoadMoreScroll(LinearLayoutManager(requireContext()))
            scrollListener.setOnLoadMoreListener(object : OnLoadMoreListener {
                override fun onLoadMore() {
                    loadMoreData()
                }
            })
            addOnScrollListener(scrollListener)
        }
    }

    override fun onStart() {
        super.onStart()
        EventBus.getDefault().register(this)
    }

    override fun onStop() {
        super.onStop()
        EventBus.getDefault().unregister(this)
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    fun onAdapterEventsSubscribe(bookAdapterEvent: BookAdapterEvent) {
        when (bookAdapterEvent) {
            is BookAdapterEvent.BookItemClicked -> {
                val bookSelected = bookAdapterEvent.bookClicked
                Log.d("ANDEBUGGGGG", "${bookSelected.name}")
            }
        }
    }

    companion object {
        const val BOOK_TYPE = "BOOK_TYPE"

        @JvmStatic
        fun newInstance(bookType: Int): AllBookFragment = AllBookFragment().apply {
            arguments = Bundle().apply {
                putInt(BOOK_TYPE, bookType)
            }
        }
    }

    @SuppressLint("NotifyDataSetChanged")
    private fun loadMoreData() {
        bookAdapter.addLoadingView()
        loadMoreBooks = ArrayList()
        val start = bookAdapter.itemCount
        val end = start + 3 // 3 is the amount you want to add
        Handler().postDelayed({
            for (i in start..end) {
                loadMoreBooks.add(Book.mock().first())
            }
            bookAdapter.removeLoadingView()
            bookAdapter.addData(loadMoreBooks)
            scrollListener.setLoaded()
            recyclerView.post {
                bookAdapter.notifyDataSetChanged()
            }
        }, 2000)
    }
}