package com.example.data.entity

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.domain.model.Book
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import kotlinx.parcelize.Parcelize

@Parcelize
@JsonClass(generateAdapter = true)
@Entity(tableName = "books")
data class BookEntity(
    val authors: List<String>,
    val contents: String,
    val datetime: String,
    @PrimaryKey(autoGenerate = false)
    val isbn: String,
    val price: Int,
    val publisher: String,
    @ColumnInfo(name = "sale_price")
    @field:Json(name = "sale_price")
    val salePrice: Int,
    val status: String,
    val thumbnail: String,
    val title: String,
    val translators: List<String>,
    val url: String,
) : Parcelable {
    fun toBook() = Book(
        authors = authors,
        contents = contents,
        datetime = datetime,
        isbn = isbn,
        price = price,
        publisher = publisher,
        salePrice = salePrice,
        status = status,
        thumbnail = thumbnail,
        title = title,
        translators = translators,
        url = url,
    )
}

object BookMapper {
    fun toBookEntity(book: Book) = BookEntity(
        authors = book.authors,
        contents = book.contents,
        datetime = book.datetime,
        isbn = book.isbn,
        price = book.price,
        publisher = book.publisher,
        salePrice = book.salePrice,
        status = book.status,
        thumbnail = book.thumbnail,
        title = book.title,
        translators = book.translators,
        url = book.url,
    )
}
