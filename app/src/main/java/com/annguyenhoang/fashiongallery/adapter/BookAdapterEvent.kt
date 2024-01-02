package com.annguyenhoang.fashiongallery.adapter

import com.annguyenhoang.fashiongallery.model.Book

sealed class BookAdapterEvent {

    data class BookItemClicked(val bookClicked: Book) : BookAdapterEvent()

}
