package com.bb.gamingnews.ui.gametips.model


import com.google.gson.annotations.SerializedName

data class GetGameTipsResponceModel(
    @SerializedName("Data")
    val `data`: List<Data>,
    @SerializedName("Message")
    val message: String
) {
    data class Data(
        @SerializedName("Content")
        val content: String,
        @SerializedName("CreatedBy")
        val createdBy: String,
        @SerializedName("CreatedDate")
        val createdDate: String,
        @SerializedName("Heading")
        val heading: String,
        @SerializedName("Id")
        val id: Int,
        @SerializedName("ImageUrl")
        val imageUrl: Any,
        @SerializedName("Title")
        val title: Any,
        @SerializedName("TitleId")
        val titleId: Int
    )
}