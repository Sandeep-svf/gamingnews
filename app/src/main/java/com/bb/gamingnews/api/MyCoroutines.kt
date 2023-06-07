package com.bb.gamingnews.api

import android.content.Context
import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.bb.gamingnews.base.Event
import com.bb.gamingnews.extentions.hideProgressDialog
import com.bb.gamingnews.extentions.mGetErrorMessage
import com.bb.gamingnews.extentions.showProgressDialog
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import retrofit2.HttpException
import retrofit2.Response
import java.net.ConnectException
import java.net.SocketTimeoutException
import java.net.UnknownHostException

object MyCoroutines {

    fun <T : Any> hitApi(
        mScope: CoroutineScope,
        work: suspend (() -> Response<T>),
        mContext: Context,
        mError: MutableLiveData<String>,
        mResponse: MutableLiveData<T>
    ) {
        val handler = CoroutineExceptionHandler { _, exception ->
            onError(exception, mContext, mError)
            Log.e("###", "CoroutineExceptionHandler got $exception")
        }

        showProgressDialog(mContext, "")
        mScope.launch(handler) {
            val asyncPost = async { work() }
            val response = asyncPost.await()
            if (response.isSuccessful) {
                hideProgressDialog(mContext)
                response.body()?.also { mResponse.value = it }
            } else {
                hideProgressDialog(mContext)
                val t = HttpException(response)
                onError(t, mContext, mError)
            }
        }


    }


    fun <T : Any> hitApiEvent(
        mScope: CoroutineScope,
        work: suspend (() -> Response<T>),
        mContext: Context,
        mError: MutableLiveData<String>,
        mResponse: MutableLiveData<Event<T>>
    ) {
        showProgressDialog(mContext, "")
        mScope.launch {
            val asyncPost = async { work() }
            val response = asyncPost.await()
            if (response.isSuccessful) {
                hideProgressDialog(mContext)
                mResponse.value = Event(response.body()!!)
            } else {
                hideProgressDialog(mContext)
                val t = HttpException(response)
                onError(t, mContext, mError)
            }
        }
    }


    private fun onError(
        throwable: Throwable,
        mContext: Context,
        mError: MutableLiveData<String>
    ) {
        hideProgressDialog(mContext)
        when (throwable) {
            is ConnectException -> mError.value = "No Internet"
            is SocketTimeoutException -> mError.value =
                "Unable to connect to the internet please try again"
            is UnknownHostException -> mError.value =
                "Unable to connect to the internet please try again"
            is InternalError -> mError.value = "Server Error"
            is HttpException -> {
                try {
                    when (throwable.code()) {
                        401 -> {
                        }/*forceLogout(throwable, mError)*/
                        else -> displayError(throwable, mError)
                    }
                } catch (exception: Exception) {
                    mError.value = "Something Went Wrong"
                }
            }
            else -> mError.value = "Something Went Wrong"
        }
    }


    private fun displayError(
        exception: HttpException,
        mError: MutableLiveData<String>
    ) {
        mError.value = mGetErrorMessage(exception)
    }
}