package com.bb.gamingnews.ui.notification.model


import com.google.gson.annotations.SerializedName

data class DeleteNotificationModel(
    @SerializedName("Data")
    val `data`: Boolean,
    @SerializedName("Message")
    val message: String
)