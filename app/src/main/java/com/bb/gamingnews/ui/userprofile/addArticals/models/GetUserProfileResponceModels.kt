package com.bb.gamingnews.ui.userprofile.addArticals.models


import com.google.gson.annotations.SerializedName

data class GetUserProfileResponceModels(
    @SerializedName("Data")
    val `data`: Data?,
    @SerializedName("Message")
    val message: String?
) {
    data class Data(
        @SerializedName("Address")
        val address: String?,
        @SerializedName("CurrentRanking")
        val currentRanking: Int?,
        @SerializedName("Email")
        val email: String?,
        @SerializedName("FollowerCount")
        val followerCount: Int?,
        @SerializedName("FullName")
        val fullName: String?,
        @SerializedName("Id")
        val id: Int?,
        @SerializedName("MaxBuy")
        val maxBuy: Double?,
        @SerializedName("MemberSince")
        val memberSince: String?,
        @SerializedName("MinBuy")
        val minBuy: Double?,
        @SerializedName("PhoneNumber")
        val phoneNumber: String?,
        @SerializedName("PlayerDesc")
        val playerDesc: Any?,
        @SerializedName("PlayerTitle")
        val playerTitle: String?,
        @SerializedName("UserProfilePic")
        val userProfilePic: String?,
        @SerializedName("Winnings")
        val winnings: Double?
,
        @SerializedName("IsNotification")
    val IsNotification: Boolean,
    @SerializedName("IsLiveGameUpdates")
    val IsLiveGameUpdates: Boolean
    )
}