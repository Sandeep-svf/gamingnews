package com.bb.gamingnews.ui.faq.model


import com.google.gson.annotations.SerializedName

data class GetFaqCategoryResponceModel(
    @SerializedName("Data")
    val `data`: List<Data>,
    @SerializedName("Message")
    val message: String
) {
    data class Data(
        @SerializedName("Category")
        val category: String,
        @SerializedName("Desc")
        val desc: String,
        @SerializedName("Id")
        val id: Int
    )
}