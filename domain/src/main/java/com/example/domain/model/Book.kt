package com.example.domain.model

import kotlinx.serialization.Serializable
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

@Serializable
data class Book(
    val authors: List<String>,
    val contents: String,
    val datetime: String,
    val isbn: String,
    val price: Int,
    val publisher: String,
    val salePrice: Int,
    val status: String,
    val thumbnail: String,
    val title: String,
    val translators: List<String>,
    val url: String,
) {
    // 객체를 JSON 형식의 문자열로 직렬화하는 함수
    fun toJson(): String {
        return json.encodeToString(this)
    }

    companion object {
        private val json = Json { prettyPrint = true }

        // JSON 형식의 문자열을 Book 객체로 역직렬화하는 함수
        fun fromJson(jsonString: String): Book {
            return json.decodeFromString(jsonString)
        }
    }
}
