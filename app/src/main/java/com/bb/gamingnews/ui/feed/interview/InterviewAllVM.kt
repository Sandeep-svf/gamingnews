package com.bb.gamingnews.ui.feed.interview

import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.bb.gamingnews.api.WebServiceRequests
import com.bb.gamingnews.base.Event
import com.bb.gamingnews.extentions.hideProgressDialog
import com.bb.gamingnews.extentions.showProgressDialog
import com.bb.gamingnews.ui.feed.interview.model.GetAllInterviewResponceModel
import com.bb.gamingnews.ui.feed.interview.model.GetInterviewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.observers.DisposableObserver

import io.reactivex.schedulers.Schedulers

class InterviewAllVM(private val webServiceRequests: WebServiceRequests) : ViewModel() {

    val progressIndicator = MutableLiveData<Boolean>()
    val errorResponse = MutableLiveData<Throwable>()
    val mInterviewAllVMResponse = MutableLiveData<Event<GetAllInterviewResponceModel>>()
    val mGetInterviewModelResponse = MutableLiveData<Event<GetInterviewModel>>()
    var context: Context? =null





    fun getallInterviews(
        Username: String,
        LoggedInUserId: String

               ) {
        progressIndicator.value = true
        showProgressDialog(context!!,"Please wait..")

        webServiceRequests.getallInterviews(Username,LoggedInUserId)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object :DisposableObserver<GetAllInterviewResponceModel>(){
                override fun onNext(value: GetAllInterviewResponceModel) {
                    progressIndicator.value = false
                    hideProgressDialog(context!!)
                    mInterviewAllVMResponse.value = Event(value)
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

    fun GetInterview(
        Type: String,
        Id: String,
        Username: String,
        LoggedInUserId: String,

               ) {
        progressIndicator.value = true
        showProgressDialog(context!!,"Please wait..")

        webServiceRequests.GetInterview(Type,Id,Username,LoggedInUserId)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object :DisposableObserver<GetInterviewModel>(){
                override fun onNext(value: GetInterviewModel) {
                    progressIndicator.value = false
                    hideProgressDialog(context!!)
                    mGetInterviewModelResponse.value = Event(value)
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