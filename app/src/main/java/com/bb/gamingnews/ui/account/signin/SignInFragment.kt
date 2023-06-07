package com.bb.gamingnews.ui.account.signin

import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.bb.gamingnews.R
import com.bb.gamingnews.base.BaseFragment
import com.bb.gamingnews.databinding.FragmentSignInBinding
import com.bb.gamingnews.extentions.showToast
import com.bb.gamingnews.ui.account.otpbottomsheet.BottomSheetOTP
import com.bb.gamingnews.utils.ErrorUtil
import org.koin.androidx.viewmodel.ext.android.viewModel
import android.text.Editable

import android.text.TextWatcher
import android.view.View


class SignInFragment : BaseFragment<FragmentSignInBinding>() {

    private val mSignInFragmentVM: SignInFragmentVM by viewModel()
    var valuess:String=""
    lateinit var fragment:BottomSheetOTP
    override fun mLayoutRes(): Int {
        return R.layout.fragment_sign_in
    }

    override fun onViewReady() {

//        mBinding.ccp.isEnabled=false
        mSignInFragmentVM.context=requireContext()

        mBinding.ccp.isClickable=false

        mBinding.editTextTextPhonenumber.addTextChangedListener(object : TextWatcher {
            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {

                if(s.length>=10)
                {
//                    showToast("ok"+count,requireContext())
                    mBinding.imageView4.visibility=View.VISIBLE
                }
                else
                {
//                    showToast("no"+count,requireContext())
                    mBinding.imageView4.visibility=View.GONE
                }
            }

            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {

                // TODO Auto-generated method stub
            }

            override fun afterTextChanged(s: Editable) {

                // TODO Auto-generated method stub
            }
        })


        mBinding.textView9.setOnClickListener {
            val args = Bundle()
            args.putString("value","Login")
            findNavController().navigate(R.id.action_signInFragment_to_privacyPolicyFragment,args)
        }

        valuess= requireArguments().getString("checkPage", "")
        mBinding.alreadyMemberBtn.setOnClickListener {
            activity?.onBackPressed()

        }

        mBinding.tvSendcode.setOnClickListener {
            validation()
        }
        observer()
    }

        private fun observer() {

//.....................................otp check.....................................
            mSignInFragmentVM.progressIndicator.observe(this, Observer {
    //            toggleLoader(requireContext(), it)
            })
            mSignInFragmentVM.mSendOTpResponse.observe(this,
            {
                showToast(it.getContentIfNotHandled()?.message.toString())
                fragment= BottomSheetOTP.newInstance()
                fragment.isCancelable=false

                val otp=it.peekContent().data.oTP
    //            showToast(""+otp,requireContext())
                val args = Bundle()
                args.putString("OTP",otp)
                args.putString("checkPage",valuess)
                args.putString("moblile", mBinding.editTextTextPhonenumber.text.toString())
                fragment.setArguments(args)
                fragment.show(childFragmentManager,"")

            })

            mSignInFragmentVM.errorResponse.observe(this, {
                ErrorUtil.handlerGeneralError(requireContext(), mBinding.editTextTextPhonenumber, it)
            })
//...................................check mobile no .................................................

            mSignInFragmentVM.progressIndicator.observe(this, Observer {

                //            toggleLoader(requireContext(), it)
            })
            mSignInFragmentVM.mIsPhoneCheckResponse.observe(this,

                {

                    if(valuess=="reset")
                    {
                        if(it.peekContent().data==false)
                        {
                            var phoneNumber=mBinding.editTextTextPhonenumber.text.toString()
                            mSignInFragmentVM.sendOtp("",phoneNumber)
                        }
                        else
                        {
                            showToast("Mobile no not registered !")
                        }


                    }
                    else
                    {
                        if(it.peekContent().data==true)
                        {
                            var phoneNumber=mBinding.editTextTextPhonenumber.text.toString()
                            mSignInFragmentVM.sendOtp("",phoneNumber)
                        }
                        else
                        {
                            showToast("Mobile no already exist !")
                        }

                    }


                })

            mSignInFragmentVM.errorResponseCheckMobile.observe(this, {
//                hideProgressDialog(requireContext())
//                showToast(it.message.toString())
                 ErrorUtil.handlerGeneralErrorForCheckMobile(requireContext(), mBinding.editTextTextPhonenumber, it)
//               var error = ErrorUtil.handlerGeneralErrorForCheckMobile(requireContext(), mBinding.editTextTextPhonenumber, it)
//                if(error.equals("Phone number already exists")){
//
//                    var phoneNumber=mBinding.editTextTextPhonenumber.text.toString()
//                    mSignInFragmentVM.sendOtp("",phoneNumber)
//
//                }
//                else
//                {
//
//                }

//                showToast("ooooooooooppppp")

            })




        }

    private fun validation() {
        var phoneNumber=mBinding.editTextTextPhonenumber.text.toString()
        if (phoneNumber.isEmpty())
        {
            showToast("Please Enter Your Phone Number")
        }
        else
        {
            if(valuess=="reset"){

                mSignInFragmentVM.isCheckMobile("",phoneNumber,"reset")

            }else{

                mSignInFragmentVM.isCheckMobile("",phoneNumber,"")

            }
        }
    }

}