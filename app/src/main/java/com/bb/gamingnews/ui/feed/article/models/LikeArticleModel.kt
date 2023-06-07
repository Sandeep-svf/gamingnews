package com.bb.gamingnews.ui.feed.article.models


import com.google.gson.annotations.SerializedName

data class LikeArticleModel(
    @SerializedName("Data")
    val `data`: Boolean?,
    @SerializedName("Message")
    val message: String?
)