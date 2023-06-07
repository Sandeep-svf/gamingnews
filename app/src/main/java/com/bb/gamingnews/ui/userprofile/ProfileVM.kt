package com.bb.gamingnews.ui.userprofile

import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.bb.gamingnews.api.WebServiceRequests
import com.bb.gamingnews.base.Event
import com.bb.gamingnews.extentions.hideProgressDialog
import com.bb.gamingnews.extentions.showProgressDialog
import com.bb.gamingnews.ui.userprofile.models.LogoutResponceModels
import com.bb.gamingnews.ui.userprofile.models.SetCommunicationRespomceModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.observers.DisposableObserver
import io.reactivex.schedulers.Schedulers

class ProfileVM(private val webServiceRequests: WebServiceRequests) : ViewModel() {
    val progressIndicator = MutableLiveData<Boolean>()
    val errorResponse = MutableLiveData<Throwable>()
    val mProfileResponse = MutableLiveData<Event<LogoutResponceModels>>()
    val mSetCommuntionResponse = MutableLiveData<Event<SetCommunicationRespomceModel>>()
    var context: Context? =null

    fun logout(
        Username: String,
        ClientIPAddress: String

    ) {
            progressIndicator.value = true
        showProgressDialog(context!!,"Please wait..")
        webServiceRequests.logOut(Username,ClientIPAddress)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : DisposableObserver<LogoutResponceModels>(){
                override fun onNext(value: LogoutResponceModels) {
                    progressIndicator.value = false
                    hideProgressDialog(context!!)
                    mProfileResponse.value = Event(value)
                }


                override fun onError(e: Throwable) {
                    hideProgressDialog(context!!)
                    progressIndicator.value = false
                    errorResponse.value = e
                }

                override fun onComplete() {
                    hideProgressDialog(context!!)
                    progressIndicator.value = false
                }



            })


    }

    fun setCommunicationPrefrences(
        Username: String,
        LiveGameUpdateStatus: String,
        NotificationStatus: String,
        LoggedInUserId: String

    ) {
        progressIndicator.value = true
        showProgressDialog(context!!,"Please wait..")
        webServiceRequests.setCommunicationPrefrences(Username,LiveGameUpdateStatus,NotificationStatus,LoggedInUserId)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : DisposableObserver<SetCommunicationRespomceModel>(){
                override fun onNext(value: SetCommunicationRespomceModel) {
                    progressIndicator.value = false
                    mSetCommuntionResponse.value = Event(value)
                    hideProgressDialog(context!!)
                }


                override fun onError(e: Throwable) {
                    progressIndicator.value = false
                    errorResponse.value = e
                    hideProgressDialog(context!!)
                }

                override fun onComplete() {
                    hideProgressDialog(context!!)
                    progressIndicator.value = false
                }



            })


    }

}