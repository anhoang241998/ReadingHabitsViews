package com.annguyenhoang.fashiongallery.model

import com.annguyenhoang.fashiongallery.enums.ItemType
import java.util.UUID

data class Author(
    val id: String = UUID.randomUUID().toString(),
    val authorName: String,
    val viewType: ItemType = ItemType.AUTHOR,
)