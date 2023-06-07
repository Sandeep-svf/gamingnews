package com.bb.gamingnews.ui.notification

import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.bb.gamingnews.api.WebServiceRequests
import com.bb.gamingnews.base.Event
import com.bb.gamingnews.extentions.hideProgressDialog
import com.bb.gamingnews.extentions.showProgressDialog
import com.bb.gamingnews.ui.notification.model.DeleteNotificationModel
import com.bb.gamingnews.ui.notification.model.GetSentNotification
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.observers.DisposableObserver
import io.reactivex.schedulers.Schedulers

class NotificationVM(private val webServiceRequests: WebServiceRequests) : ViewModel() {
    val progressIndicator = MutableLiveData<Boolean>()
    val errorResponse = MutableLiveData<Throwable>()
//    val mAddArticalResponse = MutableLiveData<Event<PostArticleResponceModel>>()
    val mNotificationResponse = MutableLiveData<GetSentNotification>()
    val mDeleteNotificationResponse = MutableLiveData<Event<DeleteNotificationModel>>()
    var context: Context? =null




    fun getSentNotification(
        Username: String,
        LoggedInUserId: String

    ) {
        progressIndicator.value = true
        showProgressDialog(context!!, "Please wait..")
        webServiceRequests.getSentNotification(Username, LoggedInUserId)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : DisposableObserver<GetSentNotification>() {
                override fun onNext(value: GetSentNotification) {
                    progressIndicator.value = false
                    hideProgressDialog(context!!)
                    mNotificationResponse.value = value
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
    fun deleteNotification(
        Username: String,
        Id: String,
        LoggedInUserId: String

    ) {
        progressIndicator.value = true
        showProgressDialog(context!!, "Please wait..")
        webServiceRequests.deleteNotification(Username, Id, LoggedInUserId)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : DisposableObserver<DeleteNotificationModel>() {
                override fun onNext(value: DeleteNotificationModel) {
                    progressIndicator.value = false
                    hideProgressDialog(context!!)
                    mDeleteNotificationResponse.value = Event(value)
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