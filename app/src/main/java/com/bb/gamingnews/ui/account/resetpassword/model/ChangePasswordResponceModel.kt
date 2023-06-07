package com.bb.gamingnews.ui.account.resetpassword.model


import com.google.gson.annotations.SerializedName

data class ChangePasswordResponceModel(
    @SerializedName("Data")
    val `data`: String,
    @SerializedName("Message")
    val message: String
)