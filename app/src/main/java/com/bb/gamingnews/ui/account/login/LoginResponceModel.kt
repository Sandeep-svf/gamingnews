package com.bb.gamingnews.ui.account.login


import com.google.gson.annotations.SerializedName

data class LoginResponceModel(
    @SerializedName("Data")
    val `data`: Data?,
    @SerializedName("Message")
    val message: String?
) {
    data class Data(
        @SerializedName("AuthToken")
        val authToken: String?,
        @SerializedName("ClientIPAddress")
        val clientIPAddress: String?,
        @SerializedName("ClientMachineName")
        val clientMachineName: String?,
        @SerializedName("FullName")
        val fullName: String?,
        @SerializedName("Phone")
        val phone: String?,
        @SerializedName("Role")
        val role: String?,
        @SerializedName("RoleId")
        val roleId: Int?,
        @SerializedName("UserId")
        val userId: Int,
        @SerializedName("UserProfilePic")
        val userProfilePic: String?,
        @SerializedName("Username")
        val username: String?
    )
}