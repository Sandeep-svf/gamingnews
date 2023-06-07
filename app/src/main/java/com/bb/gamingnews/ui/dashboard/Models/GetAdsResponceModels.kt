package com.bb.gamingnews.ui.dashboard.Models


import com.google.gson.annotations.SerializedName

data class GetAdsResponceModels(
    @SerializedName("Data")
    val `data`: List<Data>?,
    @SerializedName("Message")
    val message: String?
) {
    data class Data(
        @SerializedName("AdsType")
        val adsType: String?,
        @SerializedName("AdsTypeId")
        val adsTypeId: Int?,
        @SerializedName("Description")
        val description: String?,
        @SerializedName("Headline")
        val headline: String?,
        @SerializedName("Id")
        val id: Int?,
        @SerializedName("ImageUrl")
        val imageUrl: String?,
        @SerializedName("RedirectUrl")
        val redirectUrl: String?
    )
}