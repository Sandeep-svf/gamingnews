package com.bb.gamingnews.ui.Comments.model


import com.google.gson.annotations.SerializedName

data class PostCommetResponceModel(
    @SerializedName("Data")
    val `data`: Boolean,
    @SerializedName("Message")
    val message: String
)