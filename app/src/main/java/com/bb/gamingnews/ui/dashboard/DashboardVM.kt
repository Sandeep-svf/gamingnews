package com.bb.gamingnews.ui.dashboard

import android.content.Context
import android.net.Uri
import android.util.Log
import android.widget.ImageView
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.bb.gamingnews.api.WebServiceRequests
import com.bb.gamingnews.base.Event
import com.bb.gamingnews.extentions.hideProgressDialog
import com.bb.gamingnews.extentions.showProgressDialog
import com.bb.gamingnews.ui.dashboard.Models.DashboardResponceModel
import com.bb.gamingnews.ui.dashboard.Models.GetAdsResponceModels
import com.bb.gamingnews.utils.share.ShareUrl
import com.google.firebase.dynamiclinks.ktx.*
import com.google.firebase.ktx.Firebase
import com.google.firebase.ktx.app
import com.google.firebase.ktx.options

import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.observers.DisposableObserver

import io.reactivex.schedulers.Schedulers

class DashboardVM(private val webServiceRequests: WebServiceRequests) : ViewModel() {

    val progressIndicator = MutableLiveData<Boolean>()
    val errorResponse = MutableLiveData<Throwable>()
    val mDashboardResponse = MutableLiveData<DashboardResponceModel>()
    val mAddsResponse = MutableLiveData<GetAdsResponceModels>()
    var context: Context? =null
    var type=""
    var Id=""
    var Title=""
//    var image= ImageView(context)

    //.......................dy`namic link ...............

    val refferCode by lazy { MutableLiveData<String>() }
    val shortLink by lazy { MutableLiveData<String>() }
    val dynamicLink by lazy { MutableLiveData<String>() }



    fun onCreateLinkClick(img:ImageView) {

//        val invitationLink =
//            "https://gamingnews.page.link/?invitedby=${refferCode.value}"
//        val dynamicLink = Firebase.dynamicLinks.dynamicLink {
//            link =
//                Uri.parse(invitationLink)
//            domainUriPrefix = "https://gamingnews.page.link"
//            // Open links with this app on Android
//            androidParameters {
//                minimumVersion = 1
//            }
//            // Open links with com.example.ios on iOS
////            iosParameters("com.example.ios") { }
//        }
//
//        val dynamicLinkUri = dynamicLink.uri
//        this.dynamicLink.value = dynamicLinkUri.toString()

        val shortLinkTask = Firebase.dynamicLinks.shortLinkAsync {



            val invitationLinks="https://gamingnews.page.links/?type=${type}&id=$Id"
            link = Uri.parse(invitationLinks)
            domainUriPrefix = "https://gamingnews.page.links"

            androidParameters {
                minimumVersion = 1
            }

            iosParameters("com.bizbrolly.GN.GamingNews") { }
            // Set parameters
            // ...
        }.addOnSuccessListener { result ->
            // Short link created
            val shortLink = result.shortLink
//            val flowchartLink = result.previewLink
            this.shortLink.value = shortLink.toString()
            ShareUrl.deeplinkingUrl(context!!,Title,img,shortLink.toString())
            Log.v("hhgvjv",">>>>>"+shortLink)

        }.addOnFailureListener {
            // Error
            // ...
            Log.d("log_tag", "==> ${it.localizedMessage}", it)
            this.shortLink.value = it.localizedMessage
        }

        Log.v("nbfjhghsd",">>>>"+shortLinkTask.toString())

    }
    //.........................................

    fun dashboard(
        Username: String,
        LoggedInUserId: String

               ) {
        showProgressDialog(context!!,"Please wait..")
        progressIndicator.value = true
        webServiceRequests.dashboard(Username,LoggedInUserId)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object :DisposableObserver<DashboardResponceModel>(){
                override fun onNext(value: DashboardResponceModel) {
                    progressIndicator.value = false
                    hideProgressDialog(context!!)
                    mDashboardResponse.value = (value)
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


    fun getadds(
        Username: String,
        LoggedInUserId: String
               ) {
        progressIndicator.value = true
        webServiceRequests.getadds(Username,LoggedInUserId)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object :DisposableObserver<GetAdsResponceModels>(){
                override fun onNext(value: GetAdsResponceModels) {
                    progressIndicator.value = false
                    mAddsResponse.value = (value)
                }
                override fun onError(e: Throwable) {
                    progressIndicator.value = false
                    errorResponse.value = e
                }
                override fun onComplete() {
                    progressIndicator.value = false
                }
            })
    }


}