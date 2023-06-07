package com.bb.gamingnews.ui.userprofile.addArticals.models


import com.google.gson.annotations.SerializedName

data class PostArticleResponceModel(
    @SerializedName("Data")
    val `data`: Boolean,
    @SerializedName("Message")
    val message: String
)