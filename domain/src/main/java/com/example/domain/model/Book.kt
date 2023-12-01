package com.example.domain.model

import java.io.Serializable

data class Book(
    val serialVersionUID: Long = 1L,
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
) : Serializable
