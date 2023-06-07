package com.bb.gamingnews.ui.account.preferences


import com.google.gson.annotations.SerializedName

data class GetPrefrencesResponceModel(
    @SerializedName("Data")
    val `data`: List<Data>?,
    @SerializedName("Message")
    val message: String?
) {
    data class Data(
        @SerializedName("Desc")
        val desc: String?,
        @SerializedName("Id")
        val id: Int?,
        @SerializedName("ImageUrl")
        val imageUrl: String?,
        @SerializedName("Prefrence")
        val prefrence: String?

    )
}