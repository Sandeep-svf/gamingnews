package com.bb.gamingnews.ui.account.preferences

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

class PreferencesVM(private val webServiceRequests: WebServiceRequests) : ViewModel() {

    val progressIndicator = MutableLiveData<Boolean>()
    val errorResponse = MutableLiveData<Throwable>()
    val mGerPrefResponse = MutableLiveData<Event<GetPrefrencesResponceModel>>()
    val mSetPrefResponse = MutableLiveData<Event<SetPrefrencesResponceModel>>()
    var context: Context? =null

    fun getPrefrences(UserName: String, LoggedInUserId: String) {
        progressIndicator.value = true
        showProgressDialog(context!!,"Please wait..")
        webServiceRequests.getPrefrences(UserName,LoggedInUserId)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object :DisposableObserver<GetPrefrencesResponceModel>(){
                override fun onNext(value: GetPrefrencesResponceModel) {
                    progressIndicator.value = false
                    hideProgressDialog(context!!)
                    mGerPrefResponse.value = Event(value)
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


    fun setPrefrences(UserName: String, LoggedInUserId: String,strPrefrenceIds: String) {
        progressIndicator.value = true
        showProgressDialog(context!!,"Please wait..")
        webServiceRequests.setPrefrences(UserName,LoggedInUserId,strPrefrenceIds)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object :DisposableObserver<SetPrefrencesResponceModel>(){
                override fun onNext(value: SetPrefrencesResponceModel) {
                    progressIndicator.value = false
                    hideProgressDialog(context!!)
                    mSetPrefResponse.value = Event(value)
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