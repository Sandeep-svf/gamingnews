package com.bb.gamingnews.ui.account.login


import com.google.gson.annotations.SerializedName

data class IsCheckMobileResponceModels(
    @SerializedName("Data")
    val `data`: Boolean,
    @SerializedName("Message")
    val message: String
)