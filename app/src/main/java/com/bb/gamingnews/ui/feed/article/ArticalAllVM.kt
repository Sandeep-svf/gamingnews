package com.bb.gamingnews.ui.feed.article

import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.bb.gamingnews.api.WebServiceRequests
import com.bb.gamingnews.base.Event
import com.bb.gamingnews.extentions.hideProgressDialog
import com.bb.gamingnews.extentions.showProgressDialog
import com.bb.gamingnews.ui.feed.article.models.GetAllArticlesResponceModel
import com.bb.gamingnews.ui.feed.article.models.GetArticlesModel
import com.bb.gamingnews.ui.feed.article.models.LikeArticleModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.observers.DisposableObserver

import io.reactivex.schedulers.Schedulers

class ArticalAllVM(private val webServiceRequests: WebServiceRequests) : ViewModel() {

    val progressIndicator = MutableLiveData<Boolean>()
    val errorResponse = MutableLiveData<Throwable>()
    val mArticalAllVMResponse = MutableLiveData<Event<GetAllArticlesResponceModel>>()
    val mArticalLikeVMResponse = MutableLiveData<Event<LikeArticleModel>>()
    val mGetArticlesResponse = MutableLiveData<Event<GetArticlesModel>>()
    var context: Context? = null


    fun getallArtical(
        Username: String,
        LoggedInUserId: String

    ) {
        showProgressDialog(context!!, "Please wait..")
        progressIndicator.value = true
        webServiceRequests.getallArtical(Username, LoggedInUserId)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : DisposableObserver<GetAllArticlesResponceModel>() {
                override fun onNext(value: GetAllArticlesResponceModel) {
                    progressIndicator.value = false
                    hideProgressDialog(context!!)
                    mArticalAllVMResponse.value = Event(value)
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


    fun likeArticle(
        Username: String,
        ArticleId: String,
        LoggedInUserId: String,
        IsLike: String

    ) {
        showProgressDialog(context!!,"Please wait..")
        progressIndicator.value = true
        webServiceRequests.likeArticle(Username,ArticleId,LoggedInUserId,IsLike)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object :DisposableObserver<LikeArticleModel>(){
                override fun onNext(value: LikeArticleModel) {
                    progressIndicator.value = false
                    hideProgressDialog(context!!)
                    mArticalLikeVMResponse.value = Event(value)
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

    fun GetArticles(
        Type: String,
        Id: String

    ) {
        showProgressDialog(context!!,"Please wait..")
        progressIndicator.value = true
        webServiceRequests.GetArticles(Type,Id)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object :DisposableObserver<GetArticlesModel>(){
                override fun onNext(value: GetArticlesModel) {
                    progressIndicator.value = false
                    hideProgressDialog(context!!)
                    mGetArticlesResponse.value = Event(value)
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