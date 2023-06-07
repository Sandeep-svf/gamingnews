package com.bb.gamingnews.ui.account.otpbottomsheet

import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.bb.gamingnews.api.WebServiceRequests
import com.bb.gamingnews.base.Event
import com.bb.gamingnews.extentions.hideProgressDialog
import com.bb.gamingnews.extentions.showProgressDialog
import com.bb.gamingnews.ui.account.otpbottomsheet.models.VerifyOTPResponceModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.observers.DisposableObserver

import io.reactivex.schedulers.Schedulers

class OTPbottomVM(private val webServiceRequests: WebServiceRequests) : ViewModel() {

    val progressIndicator = MutableLiveData<Boolean>()
    val errorResponse = MutableLiveData<Throwable>()
    val mOTpResponse = MutableLiveData<Event<VerifyOTPResponceModel>>()
    var context: Context? =null

    fun OTP(UserName: String, phoneNumber: String,otp: String) {
        progressIndicator.value = true
        showProgressDialog(context!!,"Please wait..")
        webServiceRequests.OTP(UserName,phoneNumber,otp)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object :DisposableObserver<VerifyOTPResponceModel>(){
                override fun onNext(value: VerifyOTPResponceModel) {
                    mOTpResponse.value = Event(value)
                    hideProgressDialog(context!!)
                }


                override fun onError(e: Throwable) {
//                    progressIndicator.value = false
                    hideProgressDialog(context!!)
                    errorResponse.value = e
                }

                override fun onComplete() {
                    hideProgressDialog(context!!)
//                    progressIndicator.value = false
                }



            })


    }


}