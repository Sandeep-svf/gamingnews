package com.bb.gamingnews.ui.userprofile.addArticals.models


import com.google.gson.annotations.SerializedName

data class UpdateProfileResponceModel(
    @SerializedName("Data")
    val `data`: Data,
    @SerializedName("Message")
    val message: String
) {
    data class Data(
        @SerializedName("Address")
        val address: String,
        @SerializedName("ClientIPAddress")
        val clientIPAddress: Any,
        @SerializedName("ClientMachineName")
        val clientMachineName: Any,
        @SerializedName("FacebookId")
        val facebookId: Any,
        @SerializedName("FullName")
        val fullName: String,
        @SerializedName("GoogleId")
        val googleId: Any,
        @SerializedName("ImageBase64")
        val imageBase64: Any,
        @SerializedName("LoggedInUserId")
        val loggedInUserId: Int,
        @SerializedName("Password")
        val password: Any,
        @SerializedName("PhoneNumber")
        val phoneNumber: Any,
        @SerializedName("PlayerDesc")
        val playerDesc: String,
        @SerializedName("RoleId")
        val roleId: Int,
        @SerializedName("UserImageName")
        val userImageName: Any,
        @SerializedName("UserProfilePic")
        val userProfilePic: String,
        @SerializedName("Username")
        val username: String
    )
}