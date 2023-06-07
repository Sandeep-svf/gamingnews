package com.bb.gamingnews.ui.userprofile.models


import com.google.gson.annotations.SerializedName

data class SetCommunicationRespomceModel(
    @SerializedName("Data")
    val `data`: String,
    @SerializedName("Message")
    val message: String
)