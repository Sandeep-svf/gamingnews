package com.bb.gamingnews.api

class Constants {
    companion object {
        const val BASE_URL = "http://admin.gamingnews247.com/api/"
//        const val BASE_URL = "http://devbox.bizbrolly.com:8878/api/"
//        var DeviceId: String = ""
            var HEADER_TOKEN = "EV-DEC21-OPN-BIZBRO-2021LLY"
            var PopUpCheck = false
    }

    internal object Partials {
        const val SignUp = "Login/SignUp"
        const val SendOtp = "Login/SendOTP"
        const val VerifyOTP = "Login/VerifyOTP"
        const val Login = "Login/Login"
        const val SetPrefrences = "Common/SetPrefrences"
        const val GetPrefrences = "Common/GetPrefrences"
        const val Dashboard = "User/Dashboard"
        const val GetAds = "Common/GetAds"
        const val LogOut = "Login/LogOut"
        const val GetUserProfile = "User/GetUserProfile"
        const val IsPhoneExists = "Login/IsPhoneExists"
        const val GetAllNews = "News/GetAllNews"
        const val GetAllVideos = "Video/GetAllVideos"
        const val UpdateProfilePicture = "User/UpdateProfilePicture"
        const val SetCommunicationPrefrences = "User/SetCommunicationPrefrences"
        const val GetAllInterview = "Interview/GetAllInterview"
        const val GetAllArticles = "Article/GetAllArticles"
        const val GetFaqCategory = "Common/GetFaqCategory"
        const val GetFaqByCategory = "Common/GetFaqByCategory"
        const val PrivacyPolicy = "Common/PrivacyPolicy"
        const val TermsAndCondition = "Common/TermsAndCondition"
        const val PostArticle = "Article/PostArticle"
        const val GetAllArticleTags = "Common/GetAllArticleTags"
        const val GetAllEvents = "Event/GetAllEvents"
        const val GetArticleComments = "Article/GetArticleComments"
        const val AddCommentOnArticle = "Article/AddCommentOnArticle"
        const val GetGameTipsTitles = "Article/GetGameTipsTitles"
        const val GetGameTips = "Article/GetGameTips"
        const val ChangePassword = "Login/ChangePassword"
        const val UpdateVideoViewCount = "Video/UpdateVideoViewCount"
        const val GetSentNotification ="Common/GetSentNotification"
        const val DeleteNotification ="Common/DeleteNotification"
        const val LikeArticle= "Article/LikeArticle"
        //.................deeplinking api ...................

        const val Getnews= "Common/Getnews"
        const val GetArticles= "Common/GetArticles"
        const val GetInterview= "Common/GetInterview"
        const val GetVideo= "Common/GetVideo"


    }

    internal object Keys {
        const val Username = "Username"
        const val Id = "Id"
        const val Type = "Type"
        const val PhoneNumber = "PhoneNumber"
        const val Password = "Password"
        const val OTP = "OTP"
        const val FullName = "FullName"
        const val UserImageName = "UserImageName"
        const val ImageBase64 = "ImageBase64"
        const val ClientIPAddress = "ClientIPAddress"
        const val ClientMachineName = "ClientMachineName"
        const val FacebookId = "FacebookId"
        const val GoogleId = "GoogleId"
        const val LoggedInUserId = "LoggedInUserId"
        const val strPrefrenceIds = "strPrefrenceIds"
        const val LiveGameUpdateStatus ="LiveGameUpdateStatus"
        const val NotificationStatus = "NotificationStatus"
        const val CategoryId = "CategoryId"
        const val TagId = "TagId"
        const val Title = "Title"
        const val Content = "Content"
        const val Desc = "Desc"
        const val ArticleId = "ArticleId"
        const val Comment = "Comment"
        const val TitleId = "TitleId"
        const val VideoId = "VideoId"
        const val IsLike = "IsLike"
//        const val GetAllInterview = "Interview/GetAllInterview/"
    }
}