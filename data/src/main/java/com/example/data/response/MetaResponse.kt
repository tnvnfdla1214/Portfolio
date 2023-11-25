package com.example.data.response

import com.example.domain.model.Meta
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class MetaResponse(
    @field:Json(name = "is_end")
    val isEnd: Boolean, // false
    @field:Json(name = "pageable_count")
    val pageableCount: Int, // 1000
    @field:Json(name = "total_count")
    val totalCount: Int, // 1075
) {
    fun toMeta() = Meta(
        isEnd = isEnd,
        pageableCount = pageableCount,
        totalCount = totalCount,
    )
}

object MetaMapper {
    fun toMetaResponse(meta: Meta) = MetaResponse(
        isEnd = meta.isEnd,
        pageableCount = meta.pageableCount,
        totalCount = meta.totalCount,
    )
}
