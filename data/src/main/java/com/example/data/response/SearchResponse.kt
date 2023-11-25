package com.example.data.response

import com.example.data.entity.BookEntity
import com.example.data.entity.BookMapper
import com.example.domain.model.Search
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class SearchResponse(
    val documents: List<BookEntity>,
    val meta: MetaResponse,
) {
    fun toSearch() = Search(
        documents = documents.map { it.toBook() },
        meta = meta.toMeta(),
    )
}

object SearchMapper {
    fun toSearchResponse(search: Search) = SearchResponse(
        documents = search.documents.map { BookMapper.toBookEntity(it) },
        meta = MetaMapper.toMetaResponse(search.meta),
    )
}
