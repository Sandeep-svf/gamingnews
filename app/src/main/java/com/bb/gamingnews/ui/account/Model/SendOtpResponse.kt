package com.bb.gamingnews.ui.account.Model
import com.google.gson.annotations.SerializedName


data class SendOtpResponse(
    @SerializedName("Data")
    val `data`: Data,
    @SerializedName("Message")
    val message: String
) {
    data class Data(
        @SerializedName("LoggedInUserId")
        val loggedInUserId: Int,
        @SerializedName("OTP")
        val oTP: String,
        @SerializedName("PhoneNumber")
        val phoneNumber: String,
        @SerializedName("Username")
        val username: String
    )
}