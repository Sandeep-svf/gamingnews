package com.bb.gamingnews.ui.video.models


import com.google.gson.annotations.SerializedName

data class AllVideoResponceModel(
    @SerializedName("Data")
    val `data`: List<Data?>?,
    @SerializedName("Message")
    val message: String?
) {
    data class Data(
        @SerializedName("Category")
        val category: String?,
        @SerializedName("CategoryId")
        val categoryId: Int?,
        @SerializedName("lstVideos")
        val lstVideos: List<LstVideo?>?
    ) {
        data class LstVideo(
            @SerializedName("Category")
            val category: String?,
            @SerializedName("CategoryId")
            val categoryId: Int?,
            @SerializedName("CreatedBy")
            val createdBy: String?,
            @SerializedName("CreatedDate")
            val createdDate: String?,
            @SerializedName("Desc")
            val desc: String?,
            @SerializedName("Game")
            val game: String?,
            @SerializedName("GameId")
            val gameId: Int?,
            @SerializedName("Id")
            val id: Int?,
            @SerializedName("LikeCount")
            val likeCount: Int?,
            @SerializedName("ThumbnailImageUrl")
            val thumbnailImageUrl: String?,
            @SerializedName("Title")
            val title: String?,
            @SerializedName("VideoLength")
            val videoLength: String?,
            @SerializedName("VideoUrl")
            val videoUrl: String?,
            @SerializedName("ViewCount")
            val viewCount: Int?
        )
    }
}