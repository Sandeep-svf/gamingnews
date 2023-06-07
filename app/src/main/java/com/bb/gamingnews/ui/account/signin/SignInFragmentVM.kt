package com.bb.gamingnews.ui.account.signin

import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.bb.gamingnews.api.WebServiceRequests
import com.bb.gamingnews.base.Event
import com.bb.gamingnews.extentions.hideProgressDialog
import com.bb.gamingnews.extentions.showProgressDialog
import com.bb.gamingnews.ui.account.Model.SendOtpResponse
import com.bb.gamingnews.ui.account.login.IsCheckMobileResponceModels
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.observers.DisposableObserver

import io.reactivex.schedulers.Schedulers

class SignInFragmentVM(private val webServiceRequests: WebServiceRequests) : ViewModel() {

    val progressIndicator = MutableLiveData<Boolean>()
    val errorResponse = MutableLiveData<Throwable>()
    val errorResponseCheckMobile= MutableLiveData<Throwable>()
    val mSendOTpResponse = MutableLiveData<Event<SendOtpResponse>>()
    val mIsPhoneCheckResponse = MutableLiveData<Event<IsCheckMobileResponceModels>>()
    var context: Context? =null


    fun sendOtp(UserName: String, phoneNumber: String) {
        progressIndicator.value = true
        showProgressDialog(context!!,"Please wait..")
        webServiceRequests.sendOtp(UserName,phoneNumber)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object :DisposableObserver<SendOtpResponse>(){
                override fun onNext(value: SendOtpResponse) {
                    progressIndicator.value = false
                    hideProgressDialog(context!!)
                    mSendOTpResponse.value = Event(value)
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


    fun isCheckMobile(
        Username: String,
        PhoneNumber: String,
        screenType:String
    ) {

        progressIndicator.value = true
        showProgressDialog(context!!,"Please wait..")
        webServiceRequests.isCheckMobile(
            Username,
            PhoneNumber
        )
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : DisposableObserver<IsCheckMobileResponceModels>() {
                override fun onNext(value: IsCheckMobileResponceModels) {
                    progressIndicator.value = false
                    hideProgressDialog(context!!)
                    mIsPhoneCheckResponse.value = Event(value)
                }


                override fun onError(e: Throwable) {
                    progressIndicator.value = false
                    hideProgressDialog(context!!)
                    if(screenType=="reset"){

                        errorResponseCheckMobile.value = e

                    }else{
                        errorResponse.value = e

                    }
                }

                override fun onComplete() {
                    hideProgressDialog(context!!)
                    progressIndicator.value = false
                }


            })


    }

}