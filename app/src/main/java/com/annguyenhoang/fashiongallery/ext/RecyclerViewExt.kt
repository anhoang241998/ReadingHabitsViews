package com.annguyenhoang.fashiongallery.ext

import androidx.recyclerview.widget.RecyclerView

val RecyclerView.MAX_CACHED_VALUE: Int
    get() = 20

fun <VH : RecyclerView.ViewHolder> RecyclerView.setUp(
    fixedSize: Boolean = true,
    cachedValue: Int = 20,
    adapter: RecyclerView.Adapter<VH>
) {
    setHasFixedSize(true)
    if (cachedValue <= MAX_CACHED_VALUE) {
        setItemViewCacheSize(cachedValue)
    }

    this.adapter = adapter
}