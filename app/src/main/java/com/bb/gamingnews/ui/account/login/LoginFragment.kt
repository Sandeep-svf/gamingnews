package com.bb.gamingnews.ui.account.login

import android.content.ContentValues.TAG
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import android.provider.ContactsContract.DisplayNameSources.EMAIL
import android.provider.Settings
import android.text.method.HideReturnsTransformationMethod
import android.text.method.PasswordTransformationMethod
import android.util.Base64
import android.util.Log
import androidx.navigation.fragment.findNavController
import com.bb.gamingnews.MyApplication
import com.bb.gamingnews.R
import com.bb.gamingnews.api.Constants
import com.bb.gamingnews.base.BaseFragment
import com.bb.gamingnews.databinding.FragmentLoginBinding
import com.bb.gamingnews.extentions.showToast
import com.bb.gamingnews.ui.MainActivity
import com.bb.gamingnews.ui.account.Signup.SignuptVM
import com.bb.gamingnews.utils.ErrorUtil
    import com.bb.gamingnews.utils.PreferenceManager
import com.facebook.*
import com.facebook.FacebookSdk.getApplicationContext
import com.facebook.appevents.AppEventsLogger
import com.facebook.login.LoginManager
import com.facebook.login.LoginResult
import com.facebook.login.widget.LoginButton
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.android.gms.tasks.Task
import com.google.firebase.messaging.FirebaseMessaging
import org.json.JSONException
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.security.MessageDigest
import java.security.NoSuchAlgorithmException


class LoginFragment : BaseFragment<FragmentLoginBinding>() {
    private val mSignupVM: SignuptVM by viewModel()
    private val RC_SIGN_IN =2
    private val mLoginVM: LoginVM by viewModel()
    private var callbackManager: CallbackManager? = null

    var social:Boolean=false
    var socialType:String=""
    var socialName:String=""
    var socialEmail:String=""
    var socialId:String=""


    override fun mLayoutRes(): Int {
        return R.layout.fragment_login
    }
    var checkView:Boolean=false



    override fun onViewReady() {

//        generateHasCode()


        FacebookSdk.sdkInitialize(getApplicationContext());
        AppEventsLogger.activateApp(requireActivity().application);
        mBinding.imageView8.setOnClickListener {
            social=true
            socialType="G"
            googleLogin()
        }

        mBinding.imageView7.setOnClickListener {
            social=true
            socialType="F"
            LoginManager.getInstance()
                .logInWithReadPermissions(this, listOf("public_profile", "email"))
            callbackManager = CallbackManager.Factory.create();
            mBinding.fblogin.registerCallback(
                callbackManager, object : FacebookCallback<LoginResult> {
                    override fun onCancel() {
                        Log.d("FACEBOOKDATA", "obj!!.getString()")

                    }

                    override fun onError(error: FacebookException) {
                        Log.d("FACEBOOKDATA", ">>>>>>>>>>"+error)

                    }

                    override fun onSuccess(result: LoginResult) {

                        val graphRequest =
                            GraphRequest.newMeRequest(result.accessToken) { obj, response ->                                try {
                                if (obj!!.has("id")) {
                                    Log.d("FACEBOOKDATA", obj!!.getString("name"))
                                    socialName=obj!!.getString("name")
//                                    Log.d("FACEBOOKDATA", obj.getString("email"))
//                                    Log.d("FACEBOOKDATA", obj.getString("picture"))
                                    val id=obj.getString("id")
                                    socialId=id
//                                    val email=obj.getString("email")
//                                    val email=obj.getString("email")


                                    val android_id = Settings.Secure.getString(
                                        requireContext()!!.contentResolver,
                                        Settings.Secure.ANDROID_ID
                                    )
                                    Log.v("jndsajkd",">>>>"+android_id+" FB> "+socialId)
                                    mLoginVM.login("",""
                                        ,android_id,"",id,"")
                                }
                            } catch (e: Exception) {
                                Log.e("AAA",e.message.toString())
                            }
                            }
                        val param = Bundle()
                        param.putString("field", "name , email ,id,picture.type(large)")
                        graphRequest.parameters = param
                        graphRequest.executeAsync()
                    }


//                        val accessToken = AccessToken.getCurrentAccessToken()
//                        val isLoggedIn = accessToken != null && !accessToken.isExpired
                })


        }
//        mBinding.imageView7.setOnClickListener {
//
//            faceBookLogin()
//        }
        mBinding.imageView6.setOnClickListener {

            if(checkView==true)
            {
//                showToast("show")
                mBinding.imageView6.setImageResource(R.drawable.ic_hidepswd)
                mBinding.etPassword.setTransformationMethod(PasswordTransformationMethod.getInstance())
                checkView=false
            }
            else if(checkView==false)
            {
//                showToast("hide")
                mBinding.imageView6.setImageResource(R.drawable.showpassword_icon)
                checkView=true
                mBinding.etPassword.setTransformationMethod(HideReturnsTransformationMethod.getInstance())

            }
        }
        mBinding.textView22.setOnClickListener {
            val args = Bundle()
            args.putString("value","Login")
            findNavController().navigate(R.id.action_loginFragment_to_privacyPolicyFragment,args)

        }

        mBinding.resetPassword.setOnClickListener {

//            findNavController().navigate(R.id.resetPasswordFragment)
            val args = Bundle()
            args.putString("checkPage","reset")
            findNavController().navigate(R.id.signInFragment,args)
//            findNavController().navigate(R.id.signInFragment)
        }

        mBinding.loginBtn.setOnClickListener {

            social=false
            if(mBinding.etEmail.text.toString().isNullOrBlank())
            {
                showToast("Enter email !",requireContext())

            }
            else if(mBinding.etPassword.text.toString().isNullOrBlank())
            {
                showToast("Enter password !",requireContext())
            }
            else
            {
                Constants.HEADER_TOKEN="EV-DEC21-OPN-BIZBRO-2021LLY"
                val android_id = Settings.Secure.getString(
                    requireContext()!!.contentResolver,
                    Settings.Secure.ANDROID_ID
                )
                val pass=getSha1Hex(mBinding.etPassword.text.toString())
                Log.v("asssssssssas","pass: "+pass+" id:"+android_id)
                mLoginVM.login(mBinding.etEmail.text.toString(),pass
                    ,android_id,"","","")
            }


        }
        mBinding.newUserBtn.setOnClickListener {
            val args = Bundle()
            args.putString("checkPage","")
            findNavController().navigate(R.id.signInFragment,args)
//            findNavController().navigate(R.id.signInFragment)
        }

        observer()
    }

    private fun observer() {


        //.............................signup..........................................

        mSignupVM.progressIndicator.observe(this,  {
//            toggleLoader(requireContext(), it)
        })
        mSignupVM.mSignupResponse.observe(this,
            {
                PreferenceManager.getInstance(requireContext()).setAuthToken(it.peekContent().data!!.authToken)
                PreferenceManager.getInstance(requireContext()).setEmail(it.peekContent().data!!.username)
                PreferenceManager.getInstance(requireContext()).setUserId(it.peekContent().data!!.userId)
                PreferenceManager.getInstance(requireContext()).setRoleId(it.peekContent().data!!.roleId!!)
                Constants.HEADER_TOKEN=it.peekContent().data!!.authToken.toString()
                FirebaseMessaging.getInstance().subscribeToTopic(it.peekContent().data!!.userId.toString())

                findNavController().navigate(R.id.preferencesFragment)
            })

        mSignupVM.errorResponse.observe(this, {
            ErrorUtil.handlerGeneralError(requireContext(), mBinding.etEmail, it)
        })

        //...............................................................................
        mLoginVM.progressIndicator.observe(this,  {
//            toggleLoader(requireContext(), it)
        })
        mLoginVM.mLoginResponse.observe(this,
            {



                PreferenceManager.getInstance(requireContext()).setAuthToken(it.peekContent().data!!.authToken)
                PreferenceManager.getInstance(requireContext()).setUserId(it.peekContent().data!!.userId)
                PreferenceManager.getInstance(requireContext()).setName(it.peekContent().data!!.fullName)
                PreferenceManager.getInstance(requireContext()).setEmail(it.peekContent().data!!.username!!)
                PreferenceManager.getInstance(requireContext()).setRoleId(it.peekContent().data!!.roleId!!)
                PreferenceManager.getInstance(requireContext()).setImage(it.peekContent().data!!.userProfilePic!!)
                Constants.HEADER_TOKEN=it.peekContent().data!!.authToken.toString()
                FirebaseMessaging.getInstance().subscribeToTopic(it.peekContent().data!!.userId.toString())

                val intent = Intent(requireContext(), MainActivity::class.java)
                startActivity(intent)

            })

        mLoginVM.errorResponse.observe(this, {

            if(social==true)
            {
                if(socialType.equals("F"))
                {
                    val android_id = Settings.Secure.getString(
                        requireContext()!!.contentResolver,
                        Settings.Secure.ANDROID_ID
                    )

                    mSignupVM.signUp(
                        "",
                        "",
                        "",
                        socialName,"","",android_id,"","",socialId
                    )
                }
                else if(socialType.equals("G"))
                {
//                    val android_id1 = Settings.Secure.getString(
//                        requireContext()!!.contentResolver,
//                        Settings.Secure.ANDROID_ID
//                    )
//                    Log.v("jndsajkd",">>>>"+android_id1+" FB> "+socialId)
                    val android_id = Settings.Secure.getString(
                        requireContext()!!.contentResolver,
                        Settings.Secure.ANDROID_ID
                    )
                    mSignupVM.signUp(
                        "",
                        "",
                        "",
                        socialName,"","",android_id,"",socialId,""
                    )
                }
                else
                {
                    showToast("Error",requireActivity())
                }

            }
            else
            {
                ErrorUtil.handlerGeneralError(requireContext(), mBinding.etEmail, it)

            }
        })

    }
    fun getSha1Hex(clearString: String): String {
        val HEX_CHARS = "0123456789ABCDEF"
        val bytes = MessageDigest
            .getInstance("SHA-1")
            .digest(clearString.toByteArray())
        val result = StringBuilder(bytes.size * 2)
        bytes.forEach {
            val i = it.toInt()
            result.append(HEX_CHARS[i shr 4 and 0x0f])
            result.append(HEX_CHARS[i and 0x0f])
        }
        return result.toString()
    }
        fun googleLogin(){
            val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build()
            val mGoogleSignInClient = GoogleSignIn.getClient(requireContext(), gso)
    //        Constants.bundelKey.isBack=true
            val signInIntent = mGoogleSignInClient.signInIntent
            startActivityForResult(signInIntent, RC_SIGN_IN)
        }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        callbackManager?.onActivityResult(requestCode, resultCode, data)
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == RC_SIGN_IN) {

            val task: Task<GoogleSignInAccount> = GoogleSignIn.getSignedInAccountFromIntent(data)
            handleSignInResult(task)
        }
    }

    private fun handleSignInResult(completedTask: Task<GoogleSignInAccount>) {
        try {
            val account = completedTask.getResult(ApiException::class.java)
            val splitname = account?.displayName.toString().split(" ").toTypedArray()

            val android_id = Settings.Secure.getString(
                requireContext()!!.contentResolver,
                Settings.Secure.ANDROID_ID
            )
//            val pass=getSha1Hex(mBinding.etPassword.text.toString())
//            Log.v("asssssssssas","pass: "+pass+" id:"+android_id)
            socialId=account?.id!!
            socialEmail=account?.email!!
            mLoginVM.login(socialEmail,""
                ,android_id,"","",socialId)
            Log.w(
                TAG,
                "signInResult:success data=" + "token =" + account?.idToken.toString() + "display name=" + account?.displayName + "name=" + account?.givenName + "id=" + account?.id + "email=" + account?.email
            )

        } catch (e: ApiException) {
            Log.w(TAG, "signInResult:failed code=" + e.message)
        }

    }
//    fun generateHasCode(){
//        try {
//            val info = requireActivity().packageManager.getPackageInfo(
//                "com.bb.gamingnews",
//                PackageManager.GET_SIGNATURES
//            )
//            for (signature in info.signatures) {
//                val md = MessageDigest.getInstance("SHA")
//                md.update(signature.toByteArray())
//                Log.e("KeyHash:", Base64.encodeToString(md.digest(), Base64.DEFAULT))
//
//
//            }
//        } catch (e: PackageManager.NameNotFoundException) {
//
////            snackbar(e.message.toString(), mBinding.imageView)
//
//        } catch (e: NoSuchAlgorithmException) {
//
////            snackbar(e.message.toString(), mBinding.imageView)
//
//
//        }
//    }
    fun faceBookLogin(){
        val callbackManager = CallbackManager.Factory.create()
        LoginManager.getInstance()
            .logInWithReadPermissions(this, listOf("public_profile", "email"))
        LoginManager.getInstance().registerCallback(callbackManager,
            object : FacebookCallback<LoginResult> {
                override fun onSuccess(loginResult: LoginResult) {

                    // faceBookLogin= loginResult.accessToken.applicationId
                    setFacebookData(loginResult)




                }

                override fun onCancel() {
                    showToast("cancle")

                    Log.d("MainActivity", "Facebook onCancel.")

                }

                override fun onError(error: FacebookException) {
                    showToast(error.toString())
                    Log.d("MainActivity", "Facebook onError.")

                }
            })
    }
    private fun setFacebookData(loginResult: LoginResult) {
        val request = GraphRequest.newMeRequest(
            loginResult.accessToken
        ) { `object`, response ->
            // Application code
            try {
                Log.i("Response", response.toString())
              var  firstName = response?.jsonObject?.getString("first_name")
              var   lastName = response?.jsonObject?.getString("last_name")
               var  faceBookLogin = response?.jsonObject?.getString("id")
                if( response?.jsonObject?.getString("email").isNullOrEmpty())
                {
                    showToast("IfResponce"+firstName+" "+lastName,requireContext())
                 var   email=""
                }else{

                    showToast("ElseResponce"+firstName+" "+lastName,requireContext())
                  var   email =  response?.jsonObject?.getString("email")

                }




            } catch (e: JSONException) {
                e.printStackTrace()

            }
        }
        val parameters = Bundle()
        parameters.putString("fields", "id,first_name,last_name,gender")
        request.parameters = parameters
        request.executeAsync()
    }

    override fun onResume() {
        super.onResume()
        mLoginVM.context=requireContext()

    }



}