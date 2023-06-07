package com.bb.gamingnews.ui.userprofile.addArticals.AddArticalesVM

import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.bb.gamingnews.api.WebServiceRequests
import com.bb.gamingnews.base.Event
import com.bb.gamingnews.extentions.hideProgressDialog
import com.bb.gamingnews.extentions.showProgressDialog
import com.bb.gamingnews.ui.userprofile.addArticals.models.GetAllArticleTagsResponceModel
import com.bb.gamingnews.ui.userprofile.addArticals.models.PostArticleResponceModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.observers.DisposableObserver
import io.reactivex.schedulers.Schedulers
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody
import java.io.File

class AddArticalVM(private val webServiceRequests: WebServiceRequests) : ViewModel() {
    val progressIndicator = MutableLiveData<Boolean>()
    val errorResponse = MutableLiveData<Throwable>()
    val mAddArticalResponse = MutableLiveData<Event<PostArticleResponceModel>>()
    val mAllatrticalTagResponse = MutableLiveData<Event<GetAllArticleTagsResponceModel>>()
    var context: Context? =null




    fun getAllArticleTags(
        Username: String,
        LoggedInUserId: String

    ) {
        progressIndicator.value = true
        showProgressDialog(context!!,"Please wait..")
        webServiceRequests.getAllArticleTags(Username,LoggedInUserId)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object :DisposableObserver<GetAllArticleTagsResponceModel>(){
                override fun onNext(value: GetAllArticleTagsResponceModel) {
                    progressIndicator.value = false
                    hideProgressDialog(context!!)
                    mAllatrticalTagResponse.value = Event(value)
                }


                override fun onError(e: Throwable) {
                    progressIndicator.value = false
                    hideProgressDialog(context!!)
                    errorResponse.value = e
                }

                override fun onComplete() {
                    hideProgressDialog(context!!)
                    progressIndicator.value = false
                }



            })


    }

    fun postArticals(Image: File,
                   username:String,
                   Id:String,
                   TagId:String,
                   Title:String,
                   Content:String,
                   Desc:String,
                   userId:String) {
//        progressIndicator.value = true
//        lottieAnimationView2.visibility = View.VISIBLE
        showProgressDialog(context!!,"Please wait..")
        val Username = RequestBody.create(MultipartBody.FORM, username)
        val Id = RequestBody.create(MultipartBody.FORM, Id)
        val TagId = RequestBody.create(MultipartBody.FORM, TagId)
        val Title = RequestBody.create(MultipartBody.FORM, Title)
        val Content = RequestBody.create(MultipartBody.FORM, Content)
        val Desc = RequestBody.create(MultipartBody.FORM, Desc)
        val LoggedInUserId = RequestBody.create(MultipartBody.FORM, userId)
        val img = MultipartBody.Part.createFormData(
            "Image",
            Image?.name,
            RequestBody.create("image/*".toMediaTypeOrNull(), Image!!)
        )
        webServiceRequests.postArticle(img,
            Username,
            Id,
            TagId,
            Title,
            Content,
            Desc,
            LoggedInUserId)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : DisposableObserver<PostArticleResponceModel>() {
                override fun onNext(t: PostArticleResponceModel) {
                    progressIndicator.value = false
                    hideProgressDialog(context!!)
                    mAddArticalResponse.value=Event(t)
                }

                override fun onError(e: Throwable) {
                    hideProgressDialog(context!!)
                    progressIndicator.value = false
                }

                override fun onComplete() {
                    hideProgressDialog(context!!)
                    progressIndicator.value = false
                }
            })
    }

}