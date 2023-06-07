package com.bb.gamingnews.ui.Comments.model


import com.google.gson.annotations.SerializedName

data class GetArticleCommentsResponceModel(
    @SerializedName("Data")
    val `data`: List<Data>,
    @SerializedName("Message")
    val message: String
) {
    data class Data(
        @SerializedName("ArticleId")
        val articleId: Int,
        @SerializedName("Comment")
        val comment: String,
        @SerializedName("CommentedBy")
        val commentedBy: String,
        @SerializedName("CommentedByUserId")
        val commentedByUserId: Int,
        @SerializedName("CommentedByUserImageUrl")
        val commentedByUserImageUrl: String,
        @SerializedName("CreatedDate")
        val createdDate: String,
        @SerializedName("Id")
        val id: Int
    )
}