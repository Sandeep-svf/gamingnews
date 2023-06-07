package com.bb.gamingnews.ui.Comments

import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.bb.gamingnews.api.WebServiceRequests
import com.bb.gamingnews.base.Event
import com.bb.gamingnews.extentions.hideProgressDialog
import com.bb.gamingnews.extentions.showProgressDialog
import com.bb.gamingnews.ui.Comments.model.GetArticleCommentsResponceModel
import com.bb.gamingnews.ui.Comments.model.PostCommetResponceModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.observers.DisposableObserver
import io.reactivex.schedulers.Schedulers

class CommnetsVM(private val webServiceRequests: WebServiceRequests) : ViewModel() {
    val progressIndicator = MutableLiveData<Boolean>()
    val errorResponse = MutableLiveData<Throwable>()
    val mCommentResponse = MutableLiveData<Event<GetArticleCommentsResponceModel>>()
    val mPostCommentResponse = MutableLiveData<Event<PostCommetResponceModel>>()
    var context: Context? =null

    fun postArticalComment(
        Username: String,
        ArticleId: String,
        Comment: String,
        userid: String

    ) {
        showProgressDialog(context!!,"Please wait..")
        progressIndicator.value = true
        webServiceRequests.postArticalComment(Username,ArticleId,Comment,userid)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : DisposableObserver<PostCommetResponceModel>(){
                override fun onNext(value: PostCommetResponceModel) {
                    progressIndicator.value = false
                    hideProgressDialog(context!!)
                    mPostCommentResponse.value = Event(value)
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




    fun getArticalComment(
        Username: String,
        ArticleId: String,
        userid: String

    ) {
        progressIndicator.value = true
        showProgressDialog(context!!,"Please wait..")
        webServiceRequests.getArticalComment(Username,ArticleId,userid)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : DisposableObserver<GetArticleCommentsResponceModel>(){
                override fun onNext(value: GetArticleCommentsResponceModel) {
                    progressIndicator.value = false
                    hideProgressDialog(context!!)
                    mCommentResponse.value = Event(value)
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