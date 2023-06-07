package com.bb.gamingnews.ui.feed.interview.model


import com.google.gson.annotations.SerializedName

data class GetAllInterviewResponceModel(
    @SerializedName("Data")
    val `data`: List<Data>,
    @SerializedName("Message")
    val message: String
) {
    data class Data(
        @SerializedName("CreatedBy")
        val createdBy: String,
        @SerializedName("CreatedDate")
        val createdDate: String,
        @SerializedName("Description")
        val description: String,
        @SerializedName("Game")
        val game: String,
        @SerializedName("GameId")
        val gameId: Int,
        @SerializedName("Id")
        val id: Int,
        @SerializedName("ImageUrl")
        val imageUrl: String,
        @SerializedName("InterviewContent")
        val interviewContent: String,
        @SerializedName("IsPostedByMe")
        val isPostedByMe: Boolean,
        @SerializedName("Title")
        val title: String,
        @SerializedName("VideoThumbnailUrl")
        val videoThumbnailUrl: String,
        @SerializedName("VideoUrl")
        val videoUrl: String
    )
}