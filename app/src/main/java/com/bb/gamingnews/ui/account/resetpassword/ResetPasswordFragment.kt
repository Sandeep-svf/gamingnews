package com.bb.gamingnews.ui.account.resetpassword

import android.content.Intent
import android.os.Bundle
import android.text.method.HideReturnsTransformationMethod
import android.text.method.PasswordTransformationMethod
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.bb.gamingnews.R
import com.bb.gamingnews.base.BaseFragment
import com.bb.gamingnews.databinding.FragmentResetPasswordBinding
import com.bb.gamingnews.extentions.showToast
import com.bb.gamingnews.ui.account.HoastAccountActivity
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.security.MessageDigest

class ResetPasswordFragment : BaseFragment<FragmentResetPasswordBinding>() {


    private val mChaengePassVM:ChangePaswordVM by viewModel()
    var checkView:Boolean=false

    override fun mLayoutRes(): Int {
        return R.layout.fragment_reset_password
        }

    override fun onViewReady() {


        mBinding.textView22.setOnClickListener {
            val args = Bundle()
            args.putString("value","Login")
            findNavController().navigate(R.id.privacyPolicyFragment,args)

        }

            mBinding.imageView6.setOnClickListener {
                if(checkView==true)
                {
//                showToast("show")
                    mBinding.imageView6.setImageResource(R.drawable.ic_hidepswd)
                    mBinding.editTextTextPersonName3.setTransformationMethod(
                        PasswordTransformationMethod.getInstance())
                    checkView=false
                }
                else if(checkView==false)
                {
//                showToast("hide")
                    mBinding.imageView6.setImageResource(R.drawable.showpassword_icon)
                    checkView=true
                    mBinding.editTextTextPersonName3.setTransformationMethod(HideReturnsTransformationMethod.getInstance())

                }

        }
        mChaengePassVM.context=requireContext()
        mBinding.textView8.setOnClickListener {

            if(validate()==true)
            {
                val args = arguments
                var phon=args!!.getString("moblile", "")
                mChaengePassVM.changePassword("",getSha1Hex(mBinding.editTextTextPersonName3.text.toString()),phon)
            }

        }
        mBinding.backtoLogin.setOnClickListener {
            activity?.onBackPressed()
        }
        observer()
    }
    private fun observer() {

        mChaengePassVM.progressIndicator.observe(this, Observer {
//            toggleLoader(requireContext(), it)
        })
        mChaengePassVM.mCbangepassResponse.observe(this,
            {
//                fragment= SignupFragment.newInstance()
                showToast(""+it.peekContent().message)
                val intent = Intent(requireContext(), HoastAccountActivity::class.java)
                startActivity(intent)
                activity?.finish()

//               findNavController().navigate(R.id.loginFragment)
            })

        mChaengePassVM.errorResponse.observe(this, {
//            Error.handlerGeneralError(requireContext(), mBinding.editTextTextPhonenumber, it)
        })

    }
 fun validate():Boolean
 {
     if(mBinding.editTextTextPersonName3.text.toString().isNullOrBlank())
     {
         showToast("Enter new pass !")
         return false
     }
    else if(mBinding.editTextTextPersonName.text.toString().isNullOrBlank())
     {
         showToast("Enter confirm pass !")
         return false
     }
    else if(!(mBinding.editTextTextPersonName3.text.toString().equals(mBinding.editTextTextPersonName.text.toString())))
     {
         showToast("Enter does not matched !")
         return false
     }
     return true
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

}