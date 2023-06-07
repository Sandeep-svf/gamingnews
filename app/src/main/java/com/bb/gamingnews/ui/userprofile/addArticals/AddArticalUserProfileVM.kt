package com.bb.gamingnews.ui.userprofile.addArticals

import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.bb.gamingnews.api.WebServiceRequests
import com.bb.gamingnews.base.Event
import com.bb.gamingnews.extentions.hideProgressDialog
import com.bb.gamingnews.extentions.showProgressDialog
import com.bb.gamingnews.ui.userprofile.addArticals.models.GetUserProfileResponceModels
import com.bb.gamingnews.ui.userprofile.addArticals.models.UpdateProfileResponceModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.observers.DisposableObserver
import io.reactivex.schedulers.Schedulers
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody
import java.io.File

class AddArticalUserProfileVM(private val webServiceRequests: WebServiceRequests) : ViewModel() {
    val progressIndicator = MutableLiveData<Boolean>()
    val errorResponse = MutableLiveData<Throwable>()
    val mProfileInfoResponse = MutableLiveData<Event<GetUserProfileResponceModels>>()
    val mUpdateProfResponse = MutableLiveData<Event<UpdateProfileResponceModel>>()
    var context: Context? =null


    fun uploadFile(Image: File,username:String,userId:String,Address:String,PlayerDesc:String) {
//        showProgressDialog(context!!,"Please wait..")
//        progressIndicator.value = true
//        lottieAnimationView2.visibility = View.VISIBLE
        val Username = RequestBody.create(MultipartBody.FORM, username)
        val LoggedInUserId = RequestBody.create(MultipartBody.FORM, userId)
        val Address = RequestBody.create(MultipartBody.FORM, Address)
        val PlayerDesc = RequestBody.create(MultipartBody.FORM, PlayerDesc)
        val img = MultipartBody.Part.createFormData(
            "Image",
            Image?.name,
            RequestBody.create("image/*".toMediaTypeOrNull(), Image!!)
        )
        webServiceRequests.updloadImg(img,Username,LoggedInUserId,Address,PlayerDesc)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : DisposableObserver<UpdateProfileResponceModel>() {
                override fun onNext(t: UpdateProfileResponceModel) {
//                    progressIndicator.value = false
//                    hideProgressDialog(context!!)
                    mUpdateProfResponse.value=Event(t)
                }

                override fun onError(e: Throwable) {
//                    hideProgressDialog(context!!)
//                    progressIndicator.value = false
                }

                override fun onComplete() {
//                    hideProgressDialog(context!!)
//                    progressIndicator.value = false
                }
            })
    }
    fun profileInfo(
        Username: String,
        ClientIPAddress: String,
//        Id: String

    ) {
        progressIndicator.value = true
        showProgressDialog(context!!,"Please wait..")
        webServiceRequests.getUserProfile(Username,ClientIPAddress)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : DisposableObserver<GetUserProfileResponceModels>(){
                override fun onNext(value: GetUserProfileResponceModels) {
                    progressIndicator.value = false
                    hideProgressDialog(context!!)
                    mProfileInfoResponse.value = Event(value)
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