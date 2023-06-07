package com.bb.gamingnews.ui.notification.model


import com.google.gson.annotations.SerializedName

data class GetSentNotification(
    @SerializedName("Data")
    val `data`: List<Data>,
    @SerializedName("Message")
    val message: String
) {
    data class Data(
        @SerializedName("Body")
        val body: String,
        @SerializedName("CreatedDate")
        val createdDate: String,
        @SerializedName("Id")
        val id: Int,
        @SerializedName("ImageUrl")
        val imageUrl: String,
        @SerializedName("IsSentToAll")
        val isSentToAll: Boolean,
        @SerializedName("Title")
        val title: String,
        @SerializedName("Topic")
        val topic: String,
        @SerializedName("Type")
        val type: String
    )
}