package com.annguyenhoang.fashiongallery

import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class RecyclerViewLoadMoreScroll(layoutManager: LinearLayoutManager) :
    RecyclerView.OnScrollListener() {

    private lateinit var onLoadMoreListener: OnLoadMoreListener
    private var isLoading: Boolean = false
    private var lastVisibleItem: Int = 0
    private var totalItemCount: Int = 0
    private var layoutManager: RecyclerView.LayoutManager = layoutManager

    fun setLoaded() {
        isLoading = false
    }

    fun setOnLoadMoreListener(onLoadMoreListener: Any) {
        this.onLoadMoreListener = onLoadMoreListener as OnLoadMoreListener
    }

    override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
        super.onScrolled(recyclerView, dx, dy)

        if (dy <= 0) return
        totalItemCount = layoutManager.itemCount
        lastVisibleItem = (layoutManager as LinearLayoutManager).findLastVisibleItemPosition()
        if (!isLoading && totalItemCount <= lastVisibleItem + 1) {
            onLoadMoreListener.onLoadMore()
            isLoading = true
        }
    }
}