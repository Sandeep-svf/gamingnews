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
import retrofit2.http.Body
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.Part

interface ApiService {

    @POST(Constants.Partials.SendOtp)
    fun sendOTP(@Body params:HashMap<String, Any>): Observable<SendOtpResponse>

     @POST(Constants.Partials.VerifyOTP)
    fun verifyOTP(@Body params:HashMap<String, Any>): Observable<VerifyOTPResponceModel>


     @POST(Constants.Partials.SignUp)
    fun signUp(@Body params:HashMap<String, Any>): Observable<SignUpResponceModel>

    @POST(Constants.Partials.Login)
    fun login(@Body params:HashMap<String, Any>): Observable<LoginResponceModel>


    @POST(Constants.Partials.GetPrefrences)
    fun getPrefrences(@Body params:HashMap<String, Any>): Observable<GetPrefrencesResponceModel>


    @POST(Constants.Partials.SetPrefrences)
    fun setPrefrences(@Body params:HashMap<String, Any>): Observable<SetPrefrencesResponceModel>

    @POST(Constants.Partials.Dashboard)
    fun dashboard(@Body params:HashMap<String, Any>): Observable<DashboardResponceModel>

    @POST(Constants.Partials.GetAds)
    fun getadds(@Body params:HashMap<String, Any>): Observable<GetAdsResponceModels>


    @POST(Constants.Partials.LogOut)
    fun logout(@Body params:HashMap<String, Any>): Observable<LogoutResponceModels>

    @POST(Constants.Partials.GetUserProfile)
    fun getUserProfile(@Body params:HashMap<String, Any>): Observable<GetUserProfileResponceModels>

   @POST(Constants.Partials.IsPhoneExists)
    fun isCheckMobile(@Body params:HashMap<String, Any>): Observable<IsCheckMobileResponceModels>


 @POST(Constants.Partials.GetAllVideos)
    fun allvideo(@Body params:HashMap<String, Any>): Observable<AllVideoResponceResult>

 @POST(Constants.Partials.GetAllNews)
    fun allnews(@Body params:HashMap<String, Any>): Observable<NewsResponceModels>

    @POST(Constants.Partials.SetCommunicationPrefrences)
    fun setCommunicationPrefrences(@Body params:HashMap<String, Any>): Observable<SetCommunicationRespomceModel>


    @POST(Constants.Partials.GetAllInterview)
    fun getallInterviews(@Body params:HashMap<String, Any>): Observable<GetAllInterviewResponceModel>

    @POST(Constants.Partials.GetAllArticles)
    fun getallArtical(@Body params:HashMap<String, Any>): Observable<GetAllArticlesResponceModel>

  @POST(Constants.Partials.GetFaqCategory)
    fun getallCategory(@Body params:HashMap<String, Any>): Observable<GetFaqCategoryResponceModel>

    @POST(Constants.Partials.GetFaqByCategory)
    fun getallFaqByCategory(@Body params:HashMap<String, Any>): Observable<GetFaqByCategoryResponceModel>


    @POST(Constants.Partials.GetGameTipsTitles)
    fun getallGetGameTipsTitles(@Body params:HashMap<String, Any>): Observable<GametipCategoryResponceModel>

    @POST(Constants.Partials.GetGameTips)
    fun getGameTips(@Body params:HashMap<String, Any>): Observable<GetGameTipsResponceModel>

    @POST(Constants.Partials.ChangePassword)
    fun changePassword(@Body params:HashMap<String, Any>): Observable<ChangePasswordResponceModel>

//    @POST(Constants.Partials.GetFaqByCategory)
//    fun getallFaqByCategory(@Body params:HashMap<String, Any>): Observable<GetFaqByCategoryResponceModel>



    @POST(Constants.Partials.PrivacyPolicy)
    fun getPrivacyPolicy(@Body params:HashMap<String, Any>): Observable<PrivacyPolicyResponceModel>

    @POST(Constants.Partials.TermsAndCondition)
    fun getTearmCondition(@Body params:HashMap<String, Any>): Observable<TermsAndConditionResponceModel>

    @Multipart()
    @POST(Constants.Partials.UpdateProfilePicture)
    fun UploadImage(@Part Attachment: MultipartBody.Part, @Part("Username") Username: RequestBody,
                    @Part("LoggedInUserId") LoggedInUserId: RequestBody,
                    @Part("Address") Address: RequestBody,
                    @Part("PlayerDesc") PlayerDesc: RequestBody
    ):Observable<UpdateProfileResponceModel>



    @POST(Constants.Partials.GetAllArticleTags)
    fun getAllArticleTags(@Body params:HashMap<String, Any>): Observable<GetAllArticleTagsResponceModel>

    @POST(Constants.Partials.GetAllEvents)
    fun getAllEvent(@Body params:HashMap<String, Any>): Observable<AllEventResponceModel>
  @POST(Constants.Partials.GetArticleComments)
    fun getArticalComment(@Body params:HashMap<String, Any>): Observable<GetArticleCommentsResponceModel>

    @POST(Constants.Partials.AddCommentOnArticle)
    fun postArticalComment(@Body params:HashMap<String, Any>): Observable<PostCommetResponceModel>


    @POST(Constants.Partials.GetSentNotification)
    fun getSentNotification(@Body params:HashMap<String, Any>): Observable<GetSentNotification>

    @POST(Constants.Partials.DeleteNotification)
    fun deleteNotification(@Body params:HashMap<String, Any>): Observable<DeleteNotificationModel>

  @POST(Constants.Partials.LikeArticle)
    fun likeArticle(@Body params:HashMap<String, Any>): Observable<LikeArticleModel>

    @POST(Constants.Partials.UpdateVideoViewCount)
    fun updateVideoViewCount(@Body params:HashMap<String, Any>): Observable<UpdateVideoViewCountResponceModel>

     @POST(Constants.Partials.Getnews)
    fun Getnews(@Body params:HashMap<String, Any>): Observable<GetnewsModel>

    @POST(Constants.Partials.GetArticles)
    fun GetArticles(@Body params:HashMap<String, Any>): Observable<GetArticlesModel>

    @POST(Constants.Partials.GetVideo)
    fun GetVideo(@Body params:HashMap<String, Any>): Observable<GetvideoModel>

    @POST(Constants.Partials.GetInterview)
    fun GetInterview(@Body params:HashMap<String, Any>): Observable<GetInterviewModel>

    @Multipart()
    @POST(Constants.Partials.PostArticle)
        fun postArticle(@Part Attachment: MultipartBody.Part,
                    @Part("Username") Username: RequestBody,
                    @Part("Id") Id: RequestBody,
                    @Part("TagId") TagId: RequestBody,
                    @Part("Title") Title: RequestBody,
                    @Part("Content") Content: RequestBody,
                    @Part("Desc") Desc: RequestBody,
                    @Part("LoggedInUserId") LoggedInUserId: RequestBody
    ):Observable<PostArticleResponceModel>

}


