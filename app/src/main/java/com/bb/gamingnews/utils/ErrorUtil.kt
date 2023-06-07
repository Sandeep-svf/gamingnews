package com.bb.gamingnews.utils

import android.content.Context
import android.util.Log
import android.view.View
import android.widget.TextView
import android.widget.Toast
import com.bb.gamingnews.R
import com.bb.gamingnews.api.baseModel.ErrorBean
import com.bb.gamingnews.extentions.getGsonInstance


import retrofit2.HttpException
import java.net.ConnectException
import java.net.SocketTimeoutException
import java.net.UnknownHostException

object ErrorUtil {
    var error:String=""
    fun handlerGeneralErrorForCheckMobile(context: Context?, view: View?, throwable: Throwable):String{
        // No need to pass the view. i'll remove later

        if (context == null) return error

        when (throwable) {
            //For Display Toast

            is ConnectException -> Toast.makeText(
                context,
                "Unable to connect to the internet please try again",
                Toast.LENGTH_SHORT
            ).show()
            is SocketTimeoutException -> Toast.makeText(
                context,
                "Unable to connect to the internet please try again",
                Toast.LENGTH_SHORT
            ).show()
            is UnknownHostException -> Toast.makeText(
                context,
                "Unable to connect to the internet please try again",
                Toast.LENGTH_SHORT
            ).show()
            is InternalError -> Toast.makeText(context, "Server Error", Toast.LENGTH_SHORT).show()

            is HttpException -> {
                try {
                    when (throwable.code()) {
//                        401 -> forceLogout(context,view, throwable)
                        403 -> displayErrorCheckMobile(context, throwable)
                        400 -> displayErrorCheckMobile(context, throwable)
                        else -> displayErrorCheckMobile(context, throwable)
                    }
                } catch (exception: Exception) {

                }
            }
            else -> {
                Log.e("Other Exception", "Maybe wrong response model is set")
                // Toast.makeText(context, "Something Went Wrong", Toast.LENGTH_LONG).show()
            }
        }
        return error
    }
    fun handlerGeneralError(context: Context?, view: View?, throwable: Throwable):String{
        // No need to pass the view. i'll remove later

        if (context == null) return error

        when (throwable) {
            //For Display Toast

            is ConnectException -> Toast.makeText(
                context,
                "Unable to connect to the internet please try again",
                Toast.LENGTH_SHORT
            ).show()
            is SocketTimeoutException -> Toast.makeText(
                context,
                "Unable to connect to the internet please try again",
                Toast.LENGTH_SHORT
            ).show()
            is UnknownHostException -> Toast.makeText(
                context,
                "Unable to connect to the internet please try again",
                Toast.LENGTH_SHORT
            ).show()
            is InternalError -> Toast.makeText(context, "Server Error", Toast.LENGTH_SHORT).show()

            is HttpException -> {
                try {
                    when (throwable.code()) {
//                        401 -> forceLogout(context,view, throwable)
                        403 -> displayError(context, throwable)
                        400 -> displayError(context, throwable)
                        else -> displayError(context, throwable)
                    }
                } catch (exception: Exception) {

                }
            }
            else -> {
                Log.e("Other Exception", "Maybe wrong response model is set")
               // Toast.makeText(context, "Something Went Wrong", Toast.LENGTH_LONG).show()
            }
        }
        return error
    }

    fun displayErrorCheckMobile(context: Context, exception: HttpException):String {
        val errorBody = getGsonInstance().fromJson(
            exception.response()?.errorBody()?.charStream(),
            ErrorBean::class.java
        )
        try {
            error = errorBody.ErrorDetails

            //Toast.makeText(context, errorBody.ErrorDetails, Toast.LENGTH_SHORT).show()

        } catch (e: Exception) {
            Log.e("Error Utils", e.message + "")
//            Toast.makeText(
//                context, context.getString(R.string.error_exception), Toast.LENGTH_SHORT
//            ).show()
        }
        return errorBody.ErrorDetails
    }

     fun displayError(context: Context, exception: HttpException):String {
        val errorBody = getGsonInstance().fromJson(
            exception.response()?.errorBody()?.charStream(),
            ErrorBean::class.java
        )
        try {
            error = errorBody.ErrorDetails

            Toast.makeText(context, errorBody.ErrorDetails, Toast.LENGTH_SHORT).show()

        } catch (e: Exception) {
            Log.e("Error Utils", e.message + "")
//            Toast.makeText(
//                context, context.getString(R.string.error_exception), Toast.LENGTH_SHORT
//            ).show()
        }
        return errorBody.ErrorDetails
    }

    private fun displaySnackbarError(context: Context, view: View, exception: HttpException) {
        try {
            val errorBody = getGsonInstance().fromJson(
                exception.response()?.errorBody()?.charStream(),
                ErrorBean::class.java
            )

            val toast = Toast.makeText(
                context,
                errorBody.ErrorDetails.toString(),
                Toast.LENGTH_SHORT
            )
//            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
//                toast.view?.setBackgroundResource(R.drawable.ic_launcher_background)
//            }
            toast.view?.setBackgroundColor(context.resources.getColor(R.color.purple_200))
            val textView = toast.view?.findViewById<TextView>(R.id.message)
            textView?.setTextColor(context.resources.getColor(R.color.white))
            toast.show()

//            val snackbar = Snackbar.make(
//                view,
//                errorBody.errorMessage.toString(),
//                Snackbar.LENGTH_SHORT
//            )
//            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
//                snackbar.view.setBackgroundResource(R.drawable.button_bg_primary_snackbar)
//            }
//            val textView = snackbar.view.findViewById<TextView>(R.id.snackbar_text)
//            textView.setTextColor(context.resources.getColor(R.color.white))
//            snackbar.show()

//            Toast.makeText(context, errorBody.ErrorDetails, Toast.LENGTH_SHORT).show()
        } catch (e: Exception) {
            Log.e("Error Utils", e.message + "")



            val toast = Toast.makeText(
                context,
                R.string.error_exception,
                Toast.LENGTH_SHORT
            )
//            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
//                toast.view?.setBackgroundResource(R.drawable.ic_launcher_background)
//            }
            toast.view?.setBackgroundColor(context.resources.getColor(R.color.purple_200))
            val textView = toast.view?.findViewById<TextView>(R.id.message)
            textView?.setTextColor(context.resources.getColor(R.color.white))
            toast.show()

//            val snackbar = Snackbar.make(
//                view,
//                R.string.error_exception,
//                Snackbar.LENGTH_SHORT
//            )
//            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
//                snackbar.view.setBackgroundResource(R.drawable.button_bg_primary_snackbar)
//            }
//            val textView = snackbar.view.findViewById<TextView>(R.id.snackbar_text)
//            textView.setTextColor(context.resources.getColor(R.color.white))
//            snackbar.show()

//            Toast.makeText(
//                context, context.getString(R.string.error_exception), Toast.LENGTH_SHORT
//            ).show()
        }
    }

//    private fun forceLogout(view: View?, exception: HttpException) {
//        try {
//            val errorBody = getGsonInstance().fromJson(
//                exception.response()?.errorBody()?.charStream(),
//                ErrorBean::class.java
//            )
//            Log.e("ErrorMessage", errorBody.ErrorDetails + "")
//            Toast.makeText(view?.context, errorBody.ErrorDetails, Toast.LENGTH_SHORT).show()
//
//            val act = view?.getParentActivity()
//            act?.run {
//                val viewModelUser = ViewModelProvider(act).get(UserViewModel::class.java)
//                viewModelUser.deleteUserData()
//                startActivity(Intent(this, LoginActivity::class.java))
//                finishAffinity()
//            }
//        } catch (e: java.lang.Exception) {
//            Toast.makeText(
//                view?.context,
//                view?.context?.getString(R.string.error_exception),
//                Toast.LENGTH_SHORT
//            ).show()
//        }
//    }


//    private fun forceLogout(context: Context, view: View?, exception: HttpException) {
//        try {
//
//
//            Toast.makeText(context, "Your session has expired", Toast.LENGTH_LONG).show()
//
//            val dialog = Dialog(context)
//            dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
//
//            dialog.setCancelable(false)
//            dialog.setContentView(R.layout.alert_dilog)
////            val width = (context.getResources().getDisplayMetrics().widthPixels * 0.90) as Int
////            val height = (context.getResources().getDisplayMetrics().heightPixels * 0.90) as Int
////            dialog?.window?.setLayout(width, height)
//            val btnLogin = dialog.findViewById(R.id.btnLogin) as Button
//            dialog.show()
//            btnLogin.setOnClickListener {
//                PreferenceManager.getInstance(context).setAuthToken("Bearer HM-SPT-HAMBAAPP-BIZBRO-2021LLY")
//                Constants.HEADER_TOKEN="Bearer HM-SPT-HAMBAAPP-BIZBRO-2021LLY"
//
//                PreferenceManager.getInstance(context).setUserName("")
//                PreferenceManager.getInstance(context).setMobiileNo("")
//                PreferenceManager.getInstance(context).setUserId(0)
//                val mainIntent = Intent(context, LoginActivity::class.java)
//                context.startActivity(mainIntent)
//                (context as Activity).finishAffinity()
//                dialog.dismiss()
//            }
//
//        } catch (e: java.lang.Exception) {
//
//        }
//    }
}