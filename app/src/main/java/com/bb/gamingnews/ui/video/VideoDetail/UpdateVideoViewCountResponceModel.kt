package com.bb.gamingnews.ui.video.VideoDetail


import com.google.gson.annotations.SerializedName

data class UpdateVideoViewCountResponceModel(
    @SerializedName("Data")
    val `data`: Boolean,
    @SerializedName("Message")
    val message: String
)