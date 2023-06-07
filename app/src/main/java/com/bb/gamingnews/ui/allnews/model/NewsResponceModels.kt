package com.bb.gamingnews.ui.allnews.model


import com.google.gson.annotations.SerializedName

data class NewsResponceModels(
    @SerializedName("Data")
    val `data`: List<Data>?,
    @SerializedName("Message")
    val message: String?
) {
    data class Data(
        @SerializedName("Desc")
        val desc: String?,
        @SerializedName("lstNews")
        val lstNews: List<LstNew>?,
        @SerializedName("Tag")
        val tag: String?,
        @SerializedName("TagId")
        val tagId: Int?
    ) {
        data class LstNew(
            @SerializedName("CreatedBy")
            val createdBy: String?,
            @SerializedName("CreatedDate")
            val createdDate: String?,
            @SerializedName("Game")
            val game: String?,
            @SerializedName("GameId")
            val gameId: Int?,
            @SerializedName("Headline")
            val headline: String?,
            @SerializedName("Id")
            val id: Int?,
            @SerializedName("ImageUrl")
            val imageUrl: String?,
            @SerializedName("NewsDescription")
            val newsDescription: String?,
            @SerializedName("Tag")
            val tag: String?,
            @SerializedName("TagId")
            val tagId: Int?
        )
    }
}