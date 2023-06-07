package com.bb.gamingnews.ui.gametips.model


import com.google.gson.annotations.SerializedName

data class GametipCategoryResponceModel(
    @SerializedName("Data")
    val `data`: List<Data>,
    @SerializedName("Message")
    val message: String
) {
    data class Data(
        @SerializedName("Id")
        val id: Int,
        @SerializedName("ImageUrl")
        val imageUrl: String,
        @SerializedName("Title")
        val title: String
    )
}