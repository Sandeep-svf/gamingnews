package com.bb.gamingnews.ui.account.Signup

import android.util.Log
import androidx.navigation.fragment.findNavController
import com.bb.gamingnews.R
import com.bb.gamingnews.base.BaseFragment
import com.bb.gamingnews.databinding.FragmentSignupBinding
import com.bb.gamingnews.extentions.showToast
import com.bb.gamingnews.utils.ErrorUtil

import com.bb.gamingnews.utils.PreferenceManager
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.util.*
import android.provider.Settings.Secure
import com.bb.gamingnews.api.Constants
import com.bb.gamingnews.base.callback.ManageBack
import com.google.firebase.messaging.FirebaseMessaging
import java.security.MessageDigest


class SignupFragment : BaseFragment<FragmentSignupBinding>() {
    private val mSignupVM: SignuptVM by viewModel()
    override fun mLayoutRes(): Int {
        return R.layout.fragment_signup
    }
    companion object{
        var manageBack:ManageBack?=null
    }
    override fun onViewReady() {
        mSignupVM.context = requireContext()
        manageBack!!.OnBack("signup")

        mBinding.imgBack.setOnClickListener {

            findNavController().popBackStack(R.id.loginFragment,false)
        }
            mBinding.signUpTxt.setOnClickListener {
//                Crypto.encryptAndEncode("String_to_encrypt");


                val android_id = Secure.getString(
                    requireContext()!!.contentResolver,
                    Secure.ANDROID_ID
                )
                val args = arguments
                val MobileNo = PreferenceManager.getInstance(requireContext()).getPhone
                val pass=getSha1Hex(mBinding.etConfPassword.text.toString());
                Log.v("asssssssssas","pass: "+pass+" id:"+android_id)
                if(Isvalidete())
                {
                    mSignupVM.signUp(
                        mBinding.etEmal.text.toString(),
                        MobileNo,
                        pass,
                        mBinding.etFullName.text.toString(),"","",android_id,"","",""
                    )
                }
            }

        observer()
    }
    private fun observer() {
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
            ErrorUtil.handlerGeneralError(requireContext(), mBinding.etEmal, it)
        })

    }
    fun  Isvalidete() : Boolean
    {
        if(mBinding.etEmal.text.toString().equals(""))
        {
            showToast("Enter email !",requireContext())
            return false
        }
//        else if((isValidPhoneNumber(mBinding.etEmal.text.toString())))
//        {
//            showToast("Enter valid email !",requireContext())
//            return false
//        }
        else if(mBinding.etPassword.text.toString().equals(""))
        {
            showToast("Enter password !",requireContext())
            return false
        }
        else if(mBinding.etConfPassword.text.toString().equals(""))
        {
            showToast("Enter confirm password !",requireContext())
            return false
        }
        else if(!(mBinding.etPassword.text.toString().equals(mBinding.etConfPassword.text.toString())))
        {
            showToast("Password does not match !",requireContext())
            return false
        }
        else if(!(mBinding.checkBox.isChecked==true))
        {
            showToast("Select Terms & Conditions !",requireContext())
            return false
        }
        return true
    }
//    fun isValidPhoneNumber( email:String) : Boolean {
//
//
//        return Patterns.EMAIL_ADDRESS.matcher(email).matches()
////        val patterns =  "^\\s*(?:\\+?(\\d{1,3}))?[-. (]*(\\d{3})[-. )]*(\\d{3})[-. ]*(\\d{4})(?: *x(\\d+))?\\s*$"
////        return Pattern.compile(patterns).matcher(email).matches()
//    }


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



}