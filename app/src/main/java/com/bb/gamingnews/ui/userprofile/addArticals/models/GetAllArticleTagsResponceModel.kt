package com.bb.gamingnews.ui.userprofile.addArticals.models


import com.google.gson.annotations.SerializedName

data class GetAllArticleTagsResponceModel(
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
        @SerializedName("Tag")
        val tag: String
    )
}