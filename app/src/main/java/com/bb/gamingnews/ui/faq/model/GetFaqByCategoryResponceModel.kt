package com.bb.gamingnews.ui.faq.model


import com.google.gson.annotations.SerializedName

data class GetFaqByCategoryResponceModel(
    @SerializedName("Data")
    val `data`: List<Data>,
    @SerializedName("Message")
    val message: String
) {
    data class Data(
        @SerializedName("Answer")
        val answer: String,
        @SerializedName("Category")
        val category: String,
        @SerializedName("CategoryId")
        val categoryId: Int,
        @SerializedName("Id")
        val id: Int,
        @SerializedName("Question")
        val question: String
    )
}