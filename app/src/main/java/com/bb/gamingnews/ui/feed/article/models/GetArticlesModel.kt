package com.bb.gamingnews.ui.feed.article.models


import com.google.gson.annotations.SerializedName

data class GetArticlesModel(
    @SerializedName("Data")
    val `data`: List<Data>?,
    @SerializedName("Message")
    val message: String?
) {
    data class Data(
        @SerializedName("ArticleContent")
        val articleContent: String?,
        @SerializedName("AttachmentUrl")
        val attachmentUrl: Any?,
        @SerializedName("Author")
        val author: String?,
        @SerializedName("CommentCount")
        val commentCount: Int?,
        @SerializedName("CreatedBy")
        val createdBy: Any?,
        @SerializedName("CreatedById")
        val createdById: Int?,
        @SerializedName("CreatedDate")
        val createdDate: String?,
        @SerializedName("Description")
        val description: String?,
        @SerializedName("Id")
        val id: Int?,
        @SerializedName("IsApproved")
        val isApproved: Boolean?,
        @SerializedName("IsLikedByMe")
        val isLikedByMe: Boolean?,
        @SerializedName("IsPostedByMe")
        val isPostedByMe: Boolean?,
        @SerializedName("LikeCount")
        val likeCount: Int?,
        @SerializedName("metakeywords")
        val metakeywords: Any?,
        @SerializedName("metdescription")
        val metdescription: Any?,
        @SerializedName("Tag")
        val tag: Any?,
        @SerializedName("TagId")
        val tagId: Int?,
        @SerializedName("Title")
        val title: String?
    )
}