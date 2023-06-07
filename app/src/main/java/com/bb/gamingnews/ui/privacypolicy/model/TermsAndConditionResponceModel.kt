package com.bb.gamingnews.ui.privacypolicy.model


import com.google.gson.annotations.SerializedName

data class TermsAndConditionResponceModel(
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
        @SerializedName("TermsAndCondition")
        val termsAndCondition: String,
        @SerializedName("Title")
        val title: String
    )
}