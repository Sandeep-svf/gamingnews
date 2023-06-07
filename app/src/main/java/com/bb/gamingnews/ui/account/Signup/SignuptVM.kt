package com.bb.gamingnews.ui.account.Signup

import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.bb.gamingnews.api.WebServiceRequests
import com.bb.gamingnews.base.Event
import com.bb.gamingnews.extentions.hideProgressDialog
import com.bb.gamingnews.extentions.showProgressDialog
import com.bb.gamingnews.ui.account.Signup.models.SignUpResponceModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.observers.DisposableObserver

import io.reactivex.schedulers.Schedulers

class SignuptVM(private val webServiceRequests: WebServiceRequests) : ViewModel() {

    val progressIndicator = MutableLiveData<Boolean>()
    val errorResponse = MutableLiveData<Throwable>()
    val mSignupResponse = MutableLiveData<Event<SignUpResponceModel>>()
    var context:Context?=null


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
        FacebookId: String,

               ) {
        if (context != null) {
            showProgressDialog(context!!,"")
        }
        webServiceRequests.signUp(Username,
            PhoneNumber,
            Password,
            FullName,
            UserImageName,
            ImageBase64,
            ClientIPAddress,
            ClientMachineName,
            GoogleId,
            FacebookId
        )
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object :DisposableObserver<SignUpResponceModel>(){
                override fun onNext(value: SignUpResponceModel) {
                    if (context != null) {
                        hideProgressDialog(context!!)
                    }
                    progressIndicator.value = false
                    mSignupResponse.value = Event(value)
                }


                override fun onError(e: Throwable) {
                    if (context != null) {
                        hideProgressDialog(context!!)
                    }
                    progressIndicator.value = false
                    errorResponse.value = e
                }

                override fun onComplete() {
                    if (context != null) {
                        hideProgressDialog(context!!)
                    }
                    progressIndicator.value = false
                }



            })


    }


}