package com.bb.gamingnews.ui.allnews

import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.bb.gamingnews.api.WebServiceRequests
import com.bb.gamingnews.base.Event
import com.bb.gamingnews.extentions.hideProgressDialog
import com.bb.gamingnews.extentions.showProgressDialog
import com.bb.gamingnews.ui.allnews.model.NewsResponceModels
import com.bb.gamingnews.ui.feed.news.newsDetails.GetnewsModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.observers.DisposableObserver

import io.reactivex.schedulers.Schedulers

class NewsAllVM(private val webServiceRequests: WebServiceRequests) : ViewModel() {

    val progressIndicator = MutableLiveData<Boolean>()
    val errorResponse = MutableLiveData<Throwable>()
    val mAllNewsResponse = MutableLiveData<Event<NewsResponceModels>>()
    val mNewsDetailsResponse = MutableLiveData<Event<GetnewsModel>>()
    var context: Context? =null

    fun allnews(
        Username: String,
        Id: String,
        LoggedInUserId: String

               ) {
        showProgressDialog(context!!,"Please wait..")
        progressIndicator.value = true
        webServiceRequests.allnews(Username,Id,LoggedInUserId)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object :DisposableObserver<NewsResponceModels>(){
                override fun onNext(value: NewsResponceModels) {
                    progressIndicator.value = false
                    hideProgressDialog(context!!)
                    mAllNewsResponse.value = Event(value)
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
 fun Getnews(
     Type: String,
     Id: String,
     Username: String,
     LoggedInUserId: String

               ) {
        showProgressDialog(context!!,"Please wait..")
        progressIndicator.value = true
        webServiceRequests.Getnews(Type,Id,Username,LoggedInUserId)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object :DisposableObserver<GetnewsModel>(){
                override fun onNext(value: GetnewsModel) {
                    progressIndicator.value = false
                    hideProgressDialog(context!!)
                    mNewsDetailsResponse.value = Event(value)
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