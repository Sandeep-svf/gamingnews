package com.bb.gamingnews.ui.account.login

import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.bb.gamingnews.api.WebServiceRequests
import com.bb.gamingnews.base.Event
import com.bb.gamingnews.extentions.hideProgressDialog
import com.bb.gamingnews.extentions.showProgressDialog
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.observers.DisposableObserver

import io.reactivex.schedulers.Schedulers

class LoginVM(private val webServiceRequests: WebServiceRequests) : ViewModel() {

    val progressIndicator = MutableLiveData<Boolean>()
    val errorResponse = MutableLiveData<Throwable>()
    val mLoginResponse = MutableLiveData<Event<LoginResponceModel>>()
    var context: Context? =null

    fun login(
        Username: String,
        Password: String,
        ClientIPAddress: String,
        ClientMachineName: String,
        FacebookId: String,
        GoogleId: String
    ) {
        showProgressDialog(context!!,"Please wait..")
        progressIndicator.value = true
        webServiceRequests.login(
            Username,
            Password,
            ClientIPAddress,
            ClientMachineName,
            FacebookId,
            GoogleId
        )
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : DisposableObserver<LoginResponceModel>() {
                override fun onNext(value: LoginResponceModel) {
                    progressIndicator.value = false
                    hideProgressDialog(context!!)
                    mLoginResponse.value = Event(value)
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
