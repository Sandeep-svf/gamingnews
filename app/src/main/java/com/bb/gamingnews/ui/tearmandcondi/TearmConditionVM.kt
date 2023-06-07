package com.bb.gamingnews.ui.tearmandcondi

import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.bb.gamingnews.api.WebServiceRequests
import com.bb.gamingnews.base.Event
import com.bb.gamingnews.extentions.hideProgressDialog
import com.bb.gamingnews.extentions.showProgressDialog
import com.bb.gamingnews.ui.tearmandcondi.model.PrivacyPolicyResponceModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.observers.DisposableObserver
import io.reactivex.schedulers.Schedulers

class TearmConditionVM(private val webServiceRequests: WebServiceRequests) : ViewModel() {
    val progressIndicator = MutableLiveData<Boolean>()
    val errorResponse = MutableLiveData<Throwable>()
    val mSetPrivacyPolicyResponse = MutableLiveData<Event<PrivacyPolicyResponceModel>>()
    var context: Context? =null

    fun getPrivacyPolicy(
        Username: String,
        ClientIPAddress: String

    ) {
        showProgressDialog(context!!,"Please wait..")
        progressIndicator.value = true
        webServiceRequests.getPrivacyPolicy(Username,ClientIPAddress)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : DisposableObserver<PrivacyPolicyResponceModel>(){
                override fun onNext(value: PrivacyPolicyResponceModel) {
                    progressIndicator.value = false
                    hideProgressDialog(context!!)
                    mSetPrivacyPolicyResponse.value = Event(value)
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