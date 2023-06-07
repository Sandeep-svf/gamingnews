package com.bb.gamingnews.ui.gametips

import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.bb.gamingnews.api.WebServiceRequests
import com.bb.gamingnews.base.Event
import com.bb.gamingnews.extentions.hideProgressDialog
import com.bb.gamingnews.extentions.showProgressDialog
import com.bb.gamingnews.ui.gametips.model.GametipCategoryResponceModel
import com.bb.gamingnews.ui.gametips.model.GetGameTipsResponceModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.observers.DisposableObserver
import io.reactivex.schedulers.Schedulers

class AllGametipsVM(private val webServiceRequests: WebServiceRequests) : ViewModel() {

    val progressIndicator = MutableLiveData<Boolean>()
    val errorResponse = MutableLiveData<Throwable>()
    val mAllGametipResponse = MutableLiveData<Event<GametipCategoryResponceModel>>()
    val mGametipResponse = MutableLiveData<Event<GetGameTipsResponceModel>>()
    var context: Context? =null

    fun getGetGameTips(
        Username: String,
        TitleId: String,

        LoggedInUserId: String

    ) {
        progressIndicator.value = true
        showProgressDialog(context!!,"Please wait..")
        webServiceRequests.getGameTips(Username,TitleId, LoggedInUserId)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : DisposableObserver<GetGameTipsResponceModel>() {
                override fun onNext(value: GetGameTipsResponceModel) {
                    progressIndicator.value = false
                    hideProgressDialog(context!!)
                    mGametipResponse.value = Event(value)
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




    fun getallGetGameTipsTitles(
        Username: String,

        LoggedInUserId: String

    ) {
        progressIndicator.value = true
        showProgressDialog(context!!,"Please wait..")
        webServiceRequests.getallGetGameTipsTitles(Username, LoggedInUserId)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : DisposableObserver<GametipCategoryResponceModel>() {
                override fun onNext(value: GametipCategoryResponceModel) {
                    progressIndicator.value = false
                    hideProgressDialog(context!!)
                    mAllGametipResponse.value = Event(value)
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