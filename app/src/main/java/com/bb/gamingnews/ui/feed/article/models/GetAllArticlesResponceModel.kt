package com.bb.gamingnews.ui.feed.article.models


import com.google.gson.annotations.SerializedName

data class GetAllArticlesResponceModel(
    @SerializedName("Data")
    val `data`: List<Data>,
    @SerializedName("Message")
    val message: String
) {
    data class Data(
        @SerializedName("lstArticles")
        val lstArticles: List<LstArticle>,
        @SerializedName("Tag")
        val tag: String,
        @SerializedName("TagId")
        val tagId: Int
    ) {
        data class LstArticle(
            @SerializedName("ArticleContent")
            val articleContent: String,
            @SerializedName("AttachmentUrl")
            val attachmentUrl: String,
            @SerializedName("CreatedBy")
            val createdBy: String,
            @SerializedName("CreatedDate")
            val createdDate: String,
            @SerializedName("Description")
            val description: String,
            @SerializedName("Id")
            val id: Int,
            @SerializedName("IsPostedByMe")
            val isPostedByMe: Boolean,
            @SerializedName("Tag")
            val tag: String,
            @SerializedName("TagId")
            val tagId: Int,

            @SerializedName("IsApproved")
            val IsApproved: String,

            @SerializedName("IsLikedByMe")
            val IsLikedByMe: Boolean,

            @SerializedName("LikeCount")
            val LikeCount: String,

            @SerializedName("CommentCount")
            val CommentCount: String,

            @SerializedName("Title")
            val title: String
        )
    }
}