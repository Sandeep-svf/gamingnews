package com.bb.gamingnews.ui.allvideo

import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.bb.gamingnews.api.WebServiceRequests
import com.bb.gamingnews.base.Event
import com.bb.gamingnews.extentions.hideProgressDialog
import com.bb.gamingnews.extentions.showProgressDialog
import com.bb.gamingnews.ui.allvideo.models.AllVideoResponceResult
import com.bb.gamingnews.ui.feed.news.newsDetails.GetnewsModel
import com.bb.gamingnews.ui.video.VideoDetail.UpdateVideoViewCountResponceModel
import com.bb.gamingnews.ui.video.models.GetvideoModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.observers.DisposableObserver
import io.reactivex.schedulers.Schedulers

class AllVideoVM( private val webServiceRequests: WebServiceRequests) : ViewModel() {

    val progressIndicator = MutableLiveData<Boolean>()
    val errorResponse = MutableLiveData<Throwable>()
    val mAllVideoResponse = MutableLiveData<Event<AllVideoResponceResult>>()
    val mVideosResponse = MutableLiveData<Event<GetvideoModel>>()
    val mupdateVideoViewCountResponse = MutableLiveData<UpdateVideoViewCountResponceModel>()
    var context: Context? =null

    fun allVideo(
        Username: String,
        Id: String,
        LoggedInUserId: String

    ) {
        showProgressDialog(context!!,"Please wait..")
        progressIndicator.value = true
        webServiceRequests.allVideo(Username, Id, LoggedInUserId)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : DisposableObserver<AllVideoResponceResult>() {
                override fun onNext(value: AllVideoResponceResult) {
                    progressIndicator.value = false
                    hideProgressDialog(context!!)
                    mAllVideoResponse.value = Event(value)
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



    fun updateVideoViewCount(
        Username: String,
        Id: String,
        LoggedInUserId: String

    ) {
        showProgressDialog(context!!,"Please wait..")
        progressIndicator.value = true
        webServiceRequests.updateVideoViewCount(Username, Id, LoggedInUserId)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : DisposableObserver<UpdateVideoViewCountResponceModel>() {
                override fun onNext(value: UpdateVideoViewCountResponceModel) {
                    progressIndicator.value = false
                    hideProgressDialog(context!!)
                    mupdateVideoViewCountResponse.value = value
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

    fun GetVideo(
        Type: String,
        Id: String

    ) {
        showProgressDialog(context!!,"Please wait..")
        progressIndicator.value = true
        webServiceRequests.GetVideo(Type,Id,)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object :DisposableObserver<GetvideoModel>(){
                override fun onNext(value: GetvideoModel) {
                    progressIndicator.value = false
                    hideProgressDialog(context!!)
                    mVideosResponse.value = Event(value)
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

}