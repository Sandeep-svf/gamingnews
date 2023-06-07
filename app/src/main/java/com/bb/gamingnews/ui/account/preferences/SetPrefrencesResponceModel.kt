package com.bb.gamingnews.ui.account.preferences


import com.google.gson.annotations.SerializedName

data class SetPrefrencesResponceModel(
    @SerializedName("Data")
    val `data`: String?,
    @SerializedName("Message")
    val message: String?
)