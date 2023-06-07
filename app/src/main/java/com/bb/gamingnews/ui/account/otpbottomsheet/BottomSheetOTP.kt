package com.bb.gamingnews.ui.account.otpbottomsheet

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.bb.gamingnews.R
import com.bb.gamingnews.api.Constants
import com.bb.gamingnews.ui.account.Signup.SignupFragment
import com.bb.gamingnews.utils.PreferenceManager
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import kotlinx.android.synthetic.main.bottom_sheet_otp.view.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class BottomSheetOTP :BottomSheetDialogFragment() {
    private val otpVm: OTPbottomVM by viewModel()
    lateinit var fragment:SignupFragment
    var MobileNo :String=""
    var valuess:String=""
    companion object {
        /*val gg = object : PassNavControlCallBack {
            override fun onItemClick(bundle: Bundle) {
                Log.e("AAAA","AWCC")
            }
        }*/
        fun newInstance(): BottomSheetOTP =
            BottomSheetOTP().apply {
                setStyle(DialogFragment.STYLE_NORMAL, R.style.bottomDialog)

            }

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        var view = inflater.inflate(R.layout.bottom_sheet_otp, container, false)
//        mMessageListVM.customerId = getPreferences(requireContext()).getCustomerId.toString()


        otpVm.context=requireContext()

        view.et1.addTextChangedListener(object:
            TextWatcher {override fun afterTextChanged(s: Editable?) {

        }
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                if(count==1)
                {

//                    showToast("1 is ok",requireContext())
                    view.et2.requestFocus()
//                    view.et1.setBackgroundResource(R.drawable.circle_green_bg)
                }
                else
                {
//                    view.et1.setBackgroundResource(R.drawable.circle_white_bg)

                }
            }


        })


        view.et2.addTextChangedListener(object:
            TextWatcher {override fun afterTextChanged(s: Editable?) {

        }
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                if(count==1)
                {
                    view.et3.requestFocus()
//                    view.et2.setBackgroundResource(R.drawable.circle_green_bg)
                }
                else
                {
//                    view.et2.setBackgroundResource(R.drawable.circle_white_bg)

                }
            }

        })


        view.et3.addTextChangedListener(object:
            TextWatcher {override fun afterTextChanged(s: Editable?) {

        }
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                if(count==1)
                {
                    view.et4.requestFocus()
//                    view.e3.setBackgroundResource(R.drawable.circle_green_bg)
                }
                else
                {
//                    view.e3.setBackgroundResource(R.drawable.circle_white_bg)

                }
            }

        })

        view.et4.addTextChangedListener(object:
            TextWatcher {override fun afterTextChanged(s: Editable?) {

        }
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                if(count==1)
                {
                    view.et5.requestFocus()
//                    view.et4.setBackgroundResource(R.drawable.circle_green_bg)
                }
                else
                {
//                    view.et4.setBackgroundResource(R.drawable.circle_white_bg)

                }
            }

        })

        view.et5.addTextChangedListener(object:
            TextWatcher {override fun afterTextChanged(s: Editable?) {

        }
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                if(count==1)
                {
//                    view.et5.setBackgroundResource(R.drawable.circle_green_bg)
                }
                else
                {
//                    view.et5.setBackgroundResource(R.drawable.circle_white_bg)

                }

            }

        })



        view.number.setOnClickListener {

            dismiss()
        }

        val args = arguments
        valuess= requireArguments().getString("checkPage", "")
        val OTP = args!!.getString("OTP", "")
         MobileNo = args!!.getString("moblile", "")

//        view.et1.setText(OTP[0].toString())
//        view.et2.setText(OTP[1].toString())
//        view.et3.setText(OTP[2].toString())
//        view.et4.setText(OTP[3].toString())
//        view.et5.setText(OTP[4].toString())
        view.number.setText(MobileNo.toString())


        view.verifyBtn.setOnClickListener {

            Constants.HEADER_TOKEN="EV-DEC21-OPN-BIZBRO-2021LLY"
        otpVm.OTP("",MobileNo, OTP)
        }
        observer()
        return view

    }
    private fun observer() {

        otpVm.progressIndicator.observe(this, Observer {
//            toggleLoader(requireContext(), it)
        })
        otpVm.mOTpResponse.observe(this,
            {
//                fragment= SignupFragment.newInstance()
                PreferenceManager.getInstance(requireContext()).setPhone(MobileNo)
//                val args = Bundle()
//                args.putString("moblile",MobileNo)
//                fragment.setArguments(args)
                if(valuess.equals("reset"))

                {
                    val args = Bundle()
                    args.putString("moblile", MobileNo)
//                    fragment.setArguments(args)
                    findNavController().navigate(R.id.resetPasswordFragment,args)

                }
                else
                {
                    findNavController().navigate(R.id.signupFragment)

                }

            })

        otpVm.errorResponse.observe(this, {
//            Error.handlerGeneralError(requireContext(), mBinding.editTextTextPhonenumber, it)
        })

    }

}