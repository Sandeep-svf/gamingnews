package com.bb.gamingnews.ui.faq

import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.bb.gamingnews.api.WebServiceRequests
import com.bb.gamingnews.base.Event
import com.bb.gamingnews.extentions.hideProgressDialog
import com.bb.gamingnews.extentions.showProgressDialog
import com.bb.gamingnews.ui.faq.model.GetFaqByCategoryResponceModel
import com.bb.gamingnews.ui.faq.model.GetFaqCategoryResponceModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.observers.DisposableObserver

import io.reactivex.schedulers.Schedulers

class FaqVM(private val webServiceRequests: WebServiceRequests) : ViewModel() {

    val progressIndicator = MutableLiveData<Boolean>()
    val errorResponse = MutableLiveData<Throwable>()
    val mFaqVMResponse = MutableLiveData<Event<GetFaqCategoryResponceModel>>()
    val mFaqByCategoryVMResponse = MutableLiveData<Event<GetFaqByCategoryResponceModel>>()

    var context: Context? =null




    fun getallCategory(
        Username: String,
        LoggedInUserId: String

               ) {
        showProgressDialog(context!!,"Please wait..")
        progressIndicator.value = true
        webServiceRequests.getallCategory(Username,LoggedInUserId)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object :DisposableObserver<GetFaqCategoryResponceModel>(){
                override fun onNext(value: GetFaqCategoryResponceModel) {
                    progressIndicator.value = false
                    hideProgressDialog(context!!)
                    mFaqVMResponse.value = Event(value)
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

  fun getallFaqByCategory(
        Username: String,
        CategoryId: String,
        LoggedInUserId: String

               ) {
        progressIndicator.value = true
      showProgressDialog(context!!,"Please wait..")
        webServiceRequests.getallFaqByCategory(Username,CategoryId,LoggedInUserId)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object :DisposableObserver<GetFaqByCategoryResponceModel>(){
                override fun onNext(value: GetFaqByCategoryResponceModel) {
                    progressIndicator.value = false
                    hideProgressDialog(context!!)
                    mFaqByCategoryVMResponse.value = Event(value)
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