package com.bb.gamingnews.api

import com.bb.gamingnews.ui.Comments.model.GetArticleCommentsResponceModel
import com.bb.gamingnews.ui.Comments.model.PostCommetResponceModel
import com.bb.gamingnews.ui.account.Model.SendOtpResponse
import com.bb.gamingnews.ui.account.Signup.models.SignUpResponceModel
import com.bb.gamingnews.ui.account.login.IsCheckMobileResponceModels
import com.bb.gamingnews.ui.account.login.LoginResponceModel
import com.bb.gamingnews.ui.account.otpbottomsheet.models.VerifyOTPResponceModel
import com.bb.gamingnews.ui.account.preferences.GetPrefrencesResponceModel
import com.bb.gamingnews.ui.account.preferences.SetPrefrencesResponceModel
import com.bb.gamingnews.ui.account.resetpassword.model.ChangePasswordResponceModel
import com.bb.gamingnews.ui.allnews.model.NewsResponceModels
import com.bb.gamingnews.ui.allvideo.models.AllVideoResponceResult
import com.bb.gamingnews.ui.dashboard.Models.DashboardResponceModel
import com.bb.gamingnews.ui.dashboard.Models.GetAdsResponceModels
import com.bb.gamingnews.ui.faq.model.GetFaqByCategoryResponceModel
import com.bb.gamingnews.ui.faq.model.GetFaqCategoryResponceModel
import com.bb.gamingnews.ui.feed.article.models.GetAllArticlesResponceModel
import com.bb.gamingnews.ui.feed.article.models.GetArticlesModel
import com.bb.gamingnews.ui.feed.article.models.LikeArticleModel
import com.bb.gamingnews.ui.feed.event.Model.AllEventResponceModel
import com.bb.gamingnews.ui.feed.interview.model.GetAllInterviewResponceModel
import com.bb.gamingnews.ui.feed.interview.model.GetInterviewModel
import com.bb.gamingnews.ui.feed.news.newsDetails.GetnewsModel
import com.bb.gamingnews.ui.gametips.model.GametipCategoryResponceModel
import com.bb.gamingnews.ui.gametips.model.GetGameTipsResponceModel
import com.bb.gamingnews.ui.notification.model.DeleteNotificationModel
import com.bb.gamingnews.ui.notification.model.GetSentNotification
import com.bb.gamingnews.ui.tearmandcondi.model.PrivacyPolicyResponceModel
import com.bb.gamingnews.ui.privacypolicy.model.TermsAndConditionResponceModel
import com.bb.gamingnews.ui.userprofile.addArticals.models.GetAllArticleTagsResponceModel
import com.bb.gamingnews.ui.userprofile.addArticals.models.GetUserProfileResponceModels
import com.bb.gamingnews.ui.userprofile.addArticals.models.PostArticleResponceModel
import com.bb.gamingnews.ui.userprofile.addArticals.models.UpdateProfileResponceModel
import com.bb.gamingnews.ui.userprofile.models.LogoutResponceModels
import com.bb.gamingnews.ui.userprofile.models.SetCommunicationRespomceModel
import com.bb.gamingnews.ui.video.VideoDetail.UpdateVideoViewCountResponceModel
import com.bb.gamingnews.ui.video.models.GetvideoModel
import io.reactivex.Observable
import okhttp3.MultipartBody
import okhttp3.RequestBody

class WebServiceRequests {

    val apiService by lazy { ApiClient.getClient()!!.create(ApiService::class.java) }

//    suspend fun mRequestPayment(
//        mRequestPayment: RequestPayment
//    ) = apiService.signUp(mRequestPayment)
        fun sendOtp(
            Username: String,
            PhoneNumber: String
        ): Observable<SendOtpResponse> {
            val params = HashMap<String, Any>()
            params[Constants.Keys.Username] = Username
            params[Constants.Keys.PhoneNumber] = PhoneNumber
            return apiService!!.sendOTP(params)
        }

    fun getallGetGameTipsTitles(
            Username: String,
            LoggedInUserId: String
        ): Observable<GametipCategoryResponceModel> {
            val params = HashMap<String, Any>()
            params[Constants.Keys.Username] = Username
            params[Constants.Keys.LoggedInUserId] = LoggedInUserId
            return apiService!!.getallGetGameTipsTitles(params)
        }
 fun getGameTips(
            Username: String,
            TitleId: String,
            LoggedInUserId: String
        ): Observable<GetGameTipsResponceModel> {
            val params = HashMap<String, Any>()
            params[Constants.Keys.Username] = Username
            params[Constants.Keys.TitleId] = TitleId
            params[Constants.Keys.LoggedInUserId] = LoggedInUserId
            return apiService!!.getGameTips(params)
        }
    fun changePassword(
            Username: String,
            Password: String,
            PhoneNumber: String
        ): Observable<ChangePasswordResponceModel> {
            val params = HashMap<String, Any>()
            params[Constants.Keys.Username] = Username
            params[Constants.Keys.Password] = Password
            params[Constants.Keys.PhoneNumber] = PhoneNumber
            return apiService!!.changePassword(params)
        }

        fun OTP(
        Username: String,
        PhoneNumber: String,
        OTP: String
    ): Observable<VerifyOTPResponceModel> {
        val params = HashMap<String, Any>()
        params[Constants.Keys.Username] = Username
        params[Constants.Keys.PhoneNumber] = PhoneNumber
        params[Constants.Keys.OTP] = OTP
        return apiService!!.verifyOTP(params)
    }

  fun signUp(
        Username: String,
        PhoneNumber: String,
        Password: String,
        FullName: String,
        UserImageName: String,
        ImageBase64: String,
        ClientIPAddress: String,
        ClientMachineName: String,
        GoogleId: String,
        FacebookId: String
    ): Observable<SignUpResponceModel> {
        val params = HashMap<String, Any>()
        params[Constants.Keys.Username] = Username
        params[Constants.Keys.PhoneNumber] = PhoneNumber
        params[Constants.Keys.Password] = Password
        params[Constants.Keys.FullName] = FullName
        params[Constants.Keys.UserImageName] = UserImageName
        params[Constants.Keys.ImageBase64] = ImageBase64
        params[Constants.Keys.ClientIPAddress] = ClientIPAddress
        params[Constants.Keys.ClientMachineName] = ClientMachineName
        params[Constants.Keys.GoogleId] = GoogleId
        params[Constants.Keys.FacebookId] = FacebookId
        return apiService!!.signUp(params)
    }

    fun login(
        Username: String,
        Password: String,
        ClientIPAddress: String,
        ClientMachineName: String,
        FacebookId: String,
        GoogleId: String
    ): Observable<LoginResponceModel> {
        val params = HashMap<String, Any>()
        params[Constants.Keys.Username] = Username
        params[Constants.Keys.Password] = Password
        params[Constants.Keys.ClientIPAddress] = ClientIPAddress
        params[Constants.Keys.ClientMachineName] = ClientMachineName
        params[Constants.Keys.GoogleId] = GoogleId
        params[Constants.Keys.FacebookId] = FacebookId
        return apiService!!.login(params)
    }

    fun getPrefrences(
        Username: String,
        LoggedInUserId: String
    ): Observable<GetPrefrencesResponceModel> {
        val params = HashMap<String, Any>()
        params[Constants.Keys.Username] = Username
        params[Constants.Keys.LoggedInUserId] = LoggedInUserId
        return apiService!!.getPrefrences(params)
    }
    fun setPrefrences(
        Username: String,
        LoggedInUserId: String,
        strPrefrenceIds: String
    ): Observable<SetPrefrencesResponceModel> {
        val params = HashMap<String, Any>()
        params[Constants.Keys.Username] = Username
        params[Constants.Keys.LoggedInUserId] = LoggedInUserId
        params[Constants.Keys.strPrefrenceIds] = strPrefrenceIds
        return apiService!!.setPrefrences(params)
    }
  fun dashboard(
        Username: String,
        LoggedInUserId: String
    ): Observable<DashboardResponceModel> {
        val params = HashMap<String, Any>()
        params[Constants.Keys.Username] = Username
        params[Constants.Keys.LoggedInUserId] = LoggedInUserId
        return apiService!!.dashboard(params)
    }
fun logOut(
        Username: String,
        ClientIPAddress: String
    ): Observable<LogoutResponceModels> {
        val params = HashMap<String, Any>()
        params[Constants.Keys.Username] = Username
        params[Constants.Keys.ClientIPAddress] = ClientIPAddress
        return apiService!!.logout(params)
    }
fun getadds(
        Username: String,
        LoggedInUserId: String
    ): Observable<GetAdsResponceModels> {
        val params = HashMap<String, Any>()
        params[Constants.Keys.Username] = Username
        params[Constants.Keys.LoggedInUserId] = LoggedInUserId
        return apiService!!.getadds(params)
    }
fun  getUserProfile(
        Username: String,
        LoggedInUserId: String
//        Id: String
    ): Observable<GetUserProfileResponceModels> {
        val params = HashMap<String, Any>()
        params[Constants.Keys.Username] = Username
        params[Constants.Keys.LoggedInUserId] = LoggedInUserId
//        params[Constants.Keys.Id] = Id
        return apiService!!.getUserProfile(params)
    }


    fun  isCheckMobile(
        Username: String,
        PhoneNumber: String
    ): Observable<IsCheckMobileResponceModels> {
        val params = HashMap<String, Any>()
        params[Constants.Keys.Username] = Username
        params[Constants.Keys.PhoneNumber] = PhoneNumber
        return apiService!!.isCheckMobile(params)
    }

    fun  allVideo(
        Username: String,
        Id: String,
        LoggedInUserId: String,
    ): Observable<AllVideoResponceResult> {
        val params = HashMap<String, Any>()
        params[Constants.Keys.Username] = Username
        params[Constants.Keys.Id] = Id
        params[Constants.Keys.LoggedInUserId] = LoggedInUserId
        return apiService!!.allvideo(params)
    }
    fun  allnews(
        Username: String,
        Id: String,
        LoggedInUserId: String,
    ): Observable<NewsResponceModels> {
        val params = HashMap<String, Any>()
        params[Constants.Keys.Username] = Username
        params[Constants.Keys.Id] = Id
        params[Constants.Keys.LoggedInUserId] = LoggedInUserId
        return apiService!!.allnews(params)
    }


    fun  getallArtical(
        Username: String,
        LoggedInUserId: String,
    ): Observable<GetAllArticlesResponceModel> {
        val params = HashMap<String, Any>()
        params[Constants.Keys.Username] = Username
        params[Constants.Keys.LoggedInUserId] = LoggedInUserId
        return apiService!!.getallArtical(params)
    }
    fun  getallCategory(
        Username: String,
        LoggedInUserId: String,
    ): Observable<GetFaqCategoryResponceModel> {
        val params = HashMap<String, Any>()
        params[Constants.Keys.Username] = Username
        params[Constants.Keys.LoggedInUserId] = LoggedInUserId
        return apiService!!.getallCategory(params)
    }
    fun  getPrivacyPolicy(
        Username: String,
        LoggedInUserId: String,
    ): Observable<PrivacyPolicyResponceModel> {
        val params = HashMap<String, Any>()
        params[Constants.Keys.Username] = Username
        params[Constants.Keys.LoggedInUserId] = LoggedInUserId
        return apiService!!.getPrivacyPolicy(params)
    }
    fun  getTearmCondition(
        Username: String,
        LoggedInUserId: String,
    ): Observable<TermsAndConditionResponceModel> {
        val params = HashMap<String, Any>()
        params[Constants.Keys.Username] = Username
        params[Constants.Keys.LoggedInUserId] = LoggedInUserId
        return apiService!!.getTearmCondition(params)
    }
 fun  getallFaqByCategory(
        Username: String,
        CategoryId: String,
        LoggedInUserId: String,
    ): Observable<GetFaqByCategoryResponceModel> {
        val params = HashMap<String, Any>()
        params[Constants.Keys.Username] = Username
        params[Constants.Keys.CategoryId] = CategoryId
        params[Constants.Keys.LoggedInUserId] = LoggedInUserId
        return apiService!!.getallFaqByCategory(params)
    }

    fun  getAllEvent(
        Username: String,
        Id: String,
        LoggedInUserId: String
    ): Observable<AllEventResponceModel> {
        val params = HashMap<String, Any>()
        params[Constants.Keys.Username] = Username
        params[Constants.Keys.Id] = Id
        params[Constants.Keys.LoggedInUserId] = LoggedInUserId
        return apiService!!.getAllEvent(params)
    }


    fun  getArticalComment(
        Username: String,
        ArticleId: String,

        LoggedInUserId: String
    ): Observable<GetArticleCommentsResponceModel> {
        val params = HashMap<String, Any>()
        params[Constants.Keys.Username] = Username
        params[Constants.Keys.ArticleId] = ArticleId
        params[Constants.Keys.LoggedInUserId] = LoggedInUserId
        return apiService!!.getArticalComment(params)
    }  fun  postArticalComment(
        Username: String,
        ArticleId: String,
        Comment: String,

        LoggedInUserId: String
    ): Observable<PostCommetResponceModel> {
        val params = HashMap<String, Any>()
        params[Constants.Keys.Username] = Username
        params[Constants.Keys.ArticleId] = ArticleId
        params[Constants.Keys.Comment] = Comment
        params[Constants.Keys.LoggedInUserId] = LoggedInUserId
        return apiService!!.postArticalComment(params)
    }





    fun updloadImg(image: MultipartBody.Part, Username: RequestBody,LoggedInUserId: RequestBody,
                   Address: RequestBody,PlayerDesc: RequestBody
                  ): Observable<UpdateProfileResponceModel> {
        return apiService!!.UploadImage(image,Username,LoggedInUserId,Address,PlayerDesc)
    }
    fun postArticle(Attachment: MultipartBody.Part,
                    Username: RequestBody,
                    Id: RequestBody,
                    TagId: RequestBody,
                    Title: RequestBody,
                    Content: RequestBody,
                    Desc: RequestBody,
                    LoggedInUserId: RequestBody
                  ): Observable<PostArticleResponceModel> {
        return apiService!!.postArticle(Attachment,Username,Id,TagId,Title,Content,Desc,LoggedInUserId)
    }

    fun  setCommunicationPrefrences(
        Username: String,
        LiveGameUpdateStatus: String,
        NotificationStatus: String,
        LoggedInUserId: String,
    ): Observable<SetCommunicationRespomceModel> {
        val params = HashMap<String, Any>()
        params[Constants.Keys.Username] = Username
        params[Constants.Keys.LiveGameUpdateStatus] = LiveGameUpdateStatus
        params[Constants.Keys.NotificationStatus] = NotificationStatus
        params[Constants.Keys.LoggedInUserId] = LoggedInUserId
        return apiService!!.setCommunicationPrefrences(params)
    }

    fun  getallInterviews(
        Username: String,
        LoggedInUserId: String
    ): Observable<GetAllInterviewResponceModel> {
        val params = HashMap<String, Any>()
        params[Constants.Keys.Username] = Username
        params[Constants.Keys.LoggedInUserId] = LoggedInUserId
        return apiService!!.getallInterviews(params)
    }

    fun  getAllArticleTags(
        Username: String,
        LoggedInUserId: String
    ): Observable<GetAllArticleTagsResponceModel> {
        val params = HashMap<String, Any>()
        params[Constants.Keys.Username] = Username
        params[Constants.Keys.LoggedInUserId] = LoggedInUserId
        return apiService!!.getAllArticleTags(params)
    }
    fun  updateVideoViewCount(
        Username: String,
        VideoId: String,
        LoggedInUserId: String
    ): Observable<UpdateVideoViewCountResponceModel> {
        val params = HashMap<String, Any>()
        params[Constants.Keys.Username] = Username
        params[Constants.Keys.VideoId] = VideoId
        params[Constants.Keys.LoggedInUserId] = LoggedInUserId
        return apiService!!.updateVideoViewCount(params)
    }

    fun  getSentNotification(
        Username: String,
        LoggedInUserId: String
    ): Observable<GetSentNotification> {
        val params = HashMap<String, Any>()
        params[Constants.Keys.Username] = Username
        params[Constants.Keys.LoggedInUserId] = LoggedInUserId
        return apiService!!.getSentNotification(params)
    }

    fun  deleteNotification(
        Username: String,
        Id: String,
        LoggedInUserId: String
    ): Observable<DeleteNotificationModel> {
        val params = HashMap<String, Any>()
        params[Constants.Keys.Username] = Username
        params[Constants.Keys.Id] = Id
        params[Constants.Keys.LoggedInUserId] = LoggedInUserId
        return apiService!!.deleteNotification(params)
    }

    fun  likeArticle(
        Username: String,
        ArticleId: String,
        LoggedInUserId: String,
        IsLike: String
    ): Observable<LikeArticleModel> {
        val params = HashMap<String, Any>()
        params[Constants.Keys.Username] = Username
        params[Constants.Keys.ArticleId] = ArticleId
        params[Constants.Keys.IsLike] = IsLike
        params[Constants.Keys.LoggedInUserId] = LoggedInUserId
        return apiService!!.likeArticle(params)
    }

  fun  Getnews(
      Type: String,
      Id: String,
      Username: String,
      LoggedInUserId: String
    ): Observable<GetnewsModel> {
        val params = HashMap<String, Any>()
        params[Constants.Keys.Type] = Type
        params[Constants.Keys.Id] = Id
        params[Constants.Keys.Username] = Username
        params[Constants.Keys.LoggedInUserId] = LoggedInUserId
        return apiService!!.Getnews(params)
    }

fun  GetArticles(
      Type: String,
      Id: String,
    ): Observable<GetArticlesModel> {
        val params = HashMap<String, Any>()
        params[Constants.Keys.Type] = Type
        params[Constants.Keys.Id] = Id
        return apiService!!.GetArticles(params)
    }


fun  GetVideo(
      Type: String,
      Id: String,
    ): Observable<GetvideoModel> {
        val params = HashMap<String, Any>()
        params[Constants.Keys.Type] = Type
        params[Constants.Keys.Id] = Id
        return apiService!!.GetVideo(params)
    }



fun GetInterview(
      Type: String,
      Id: String,
      Username: String,
      LoggedInUserId: String
    ): Observable<GetInterviewModel> {
        val params = HashMap<String, Any>()
        params[Constants.Keys.Type] = Type
        params[Constants.Keys.Id] = Id
         params[Constants.Keys.Username] = Username
        params[Constants.Keys.LoggedInUserId] = LoggedInUserId
        return apiService!!.GetInterview(params)
    }

}
