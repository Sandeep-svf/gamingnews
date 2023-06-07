package com.bb.gamingnews.ui.privacypolicy

import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.bb.gamingnews.api.WebServiceRequests
import com.bb.gamingnews.extentions.hideProgressDialog
import com.bb.gamingnews.extentions.showProgressDialog
import com.bb.gamingnews.ui.privacypolicy.model.TermsAndConditionResponceModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.observers.DisposableObserver
import io.reactivex.schedulers.Schedulers

class PrivacyPolicyVM(private val webServiceRequests: WebServiceRequests) : ViewModel() {
    val progressIndicator = MutableLiveData<Boolean>()
    val errorResponse = MutableLiveData<Throwable>()
    val mTearmConditionResponse = MutableLiveData<TermsAndConditionResponceModel>()
    var context: Context? =null

    fun getTearmCondition(
        Username: String,
        ClientIPAddress: String

    ) {
        progressIndicator.value = true
        showProgressDialog(context!!,"Please wait..")
        webServiceRequests.getTearmCondition(Username,ClientIPAddress)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : DisposableObserver<TermsAndConditionResponceModel>(){
                override fun onNext(value: TermsAndConditionResponceModel) {
                    progressIndicator.value = false
                    hideProgressDialog(context!!)
                    mTearmConditionResponse.value = value
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



}