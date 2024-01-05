package com.annguyenhoang.fashiongallery.model

import com.annguyenhoang.fashiongallery.enums.ItemType
import java.util.UUID

data class Book2(
    val id: String = UUID.randomUUID().toString(),
    val bookName: String = "",
    val authorName: String = "",
    val viewType: ItemType = ItemType.BOOK,
)

