package com.bb.gamingnews.ui.video

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.bb.gamingnews.api.WebServiceRequests
import com.bb.gamingnews.base.Event
import com.bb.gamingnews.ui.allvideo.models.AllVideoResponceResult
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.observers.DisposableObserver
import io.reactivex.schedulers.Schedulers

class VideoVM ( private val webServiceRequests: WebServiceRequests) : ViewModel() {

    val progressIndicator = MutableLiveData<Boolean>()
    val errorResponse = MutableLiveData<Throwable>()
    val mAllVideoResponse = MutableLiveData<Event<AllVideoResponceResult>>()


    fun allVideo(
        Username: String,
        Id: String,
        LoggedInUserId: String

    ) {
        progressIndicator.value = true
        webServiceRequests.allVideo(Username, Id, LoggedInUserId)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : DisposableObserver<AllVideoResponceResult>() {
                override fun onNext(value: AllVideoResponceResult) {
                    progressIndicator.value = false
                    mAllVideoResponse.value = Event(value)
                }


                override fun onError(e: Throwable) {
                    progressIndicator.value = false
                    errorResponse.value = e
                }

                override fun onComplete() {
                    progressIndicator.value = false
                }


            })


    }
}