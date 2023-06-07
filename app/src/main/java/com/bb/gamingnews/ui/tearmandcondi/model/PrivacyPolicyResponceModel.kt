package com.bb.gamingnews.ui.tearmandcondi.model


import com.google.gson.annotations.SerializedName

data class PrivacyPolicyResponceModel(
    @SerializedName("Data")
    val `data`: List<Data>,
    @SerializedName("Message")
    val message: String
) {
    data class Data(
        @SerializedName("Desc")
        val desc: String,
        @SerializedName("Id")
        val id: Int,
        @SerializedName("PrivacyPolicy")
        val privacyPolicy: String,
        @SerializedName("Title")
        val title: String
    )
}