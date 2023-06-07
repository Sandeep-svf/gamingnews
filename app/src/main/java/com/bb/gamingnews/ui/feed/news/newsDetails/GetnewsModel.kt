package com.bb.gamingnews.ui.feed.news.newsDetails


import com.google.gson.annotations.SerializedName

data class GetnewsModel(
    @SerializedName("Data")
    val `data`: List<Data?>?,
    @SerializedName("Message")
    val message: String?
) {
    data class Data(
        @SerializedName("BannerImageUrl")
        val bannerImageUrl: String?,
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
        val imageUrl: Any?,
        @SerializedName("IsPostedByMe")
        val isPostedByMe: Boolean?,
        @SerializedName("LoggedInUserId")
        val loggedInUserId: Int?,
        @SerializedName("lstTags")
        val lstTags: Any?,
        @SerializedName("NewsDescription")
        val newsDescription: String?,
        @SerializedName("Tag")
        val tag: Any?,
        @SerializedName("TagId")
        val tagId: Int?
    )
}