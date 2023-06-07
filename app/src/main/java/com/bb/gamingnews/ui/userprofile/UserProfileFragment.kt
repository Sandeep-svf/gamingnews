package com.bb.gamingnews.ui.userprofile

import android.annotation.SuppressLint
import android.app.AlertDialog
import android.content.Intent
import android.os.Bundle
import android.provider.Settings
import androidx.navigation.fragment.findNavController
import com.bb.gamingnews.R
import com.bb.gamingnews.api.Constants
import com.bb.gamingnews.base.BaseFragment
import com.bb.gamingnews.databinding.FragmentUserProfileBinding
import com.bb.gamingnews.utils.ErrorUtil
import com.bb.gamingnews.utils.PreferenceManager
import org.koin.androidx.viewmodel.ext.android.viewModel
import android.content.DialogInterface
import android.widget.CompoundButton
import com.bb.gamingnews.ui.account.HoastAccountActivity
import com.bb.gamingnews.ui.userprofile.addArticals.AddArticalUserProfileVM
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.activity_main.*


class UserProfileFragment : BaseFragment<FragmentUserProfileBinding>() {

    private val mprofileInfoVM: AddArticalUserProfileVM by viewModel()
    private val mlogoutVM: ProfileVM by viewModel()
    var livegameStatus:Boolean=false
    var NotifactionStatus:Boolean=false


    override fun mLayoutRes(): Int {
        return R.layout.fragment_user_profile
    }

    override fun onViewReady() {

        mlogoutVM.context=requireContext()
        mprofileInfoVM.context=requireContext()
        mBinding.termsofUses.setOnClickListener {
            val args = Bundle()
            args.putString("value","1")
            findNavController().navigate(R.id.privacypolicysFragment,args)
        }
         mBinding.termsofUsesImg.setOnClickListener {
             val args = Bundle()
             args.putString("value","1")
             findNavController().navigate(R.id.privacypolicysFragment,args)
        }

         mBinding.privacyPolicy.setOnClickListener {
             val args = Bundle()

             args.putString("value","4")
             findNavController().navigate(R.id.privacypolicysFragment,args)

        }
        mBinding.contactSuppport.setOnClickListener {
             val args = Bundle()

             args.putString("value","3")
             findNavController().navigate(R.id.privacypolicysFragment,args)

        }

        mBinding.gamingNewsTxt.setOnClickListener {
            val args = Bundle()

            args.putString("value","2")
            findNavController().navigate(R.id.privacypolicysFragment,args)

        }
//     mBinding.privacyploicyImg.setOnClickListener {
//            findNavController().navigate(R.id.privacypolicysFragment)
//        }

        val username=PreferenceManager.getInstance(requireContext()).getEmail
        val userid=PreferenceManager.getInstance(requireContext()).getUserId
        mprofileInfoVM.profileInfo(username,userid.toString())
        Glide.with(this).load(PreferenceManager.getInstance(requireContext()).getImage).placeholder(R.drawable.placeholder).circleCrop().into(mBinding.imageView34)
        mBinding.textView66.setText(PreferenceManager.getInstance(requireContext()).getName)
        mBinding.textView69.setText(PreferenceManager.getInstance(requireContext()).getEmail)
      

        mBinding.switcLiveGame.setOnCheckedChangeListener(CompoundButton.OnCheckedChangeListener { buttonView, isChecked ->
//           showToast(isChecked.toString(),requireContext())
            livegameStatus=isChecked
            mlogoutVM.setCommunicationPrefrences(PreferenceManager.getInstance(requireContext()).getEmail
            ,livegameStatus.toString(),NotifactionStatus.toString(),
                PreferenceManager.getInstance(requireContext()).getUserId.toString())

        })
        mBinding.switchnotification.setOnCheckedChangeListener(CompoundButton.OnCheckedChangeListener { buttonView, isChecked ->
//           showToast(isChecked.toString(),requireContext())
            NotifactionStatus=isChecked

            mlogoutVM.setCommunicationPrefrences(PreferenceManager.getInstance(requireContext()).getEmail
                ,livegameStatus.toString(),NotifactionStatus.toString(),
                PreferenceManager.getInstance(requireContext()).getUserId.toString())
        })
      
//        mBinding.termsofUsesImg.setOnClickListener {
//            findNavController().navigate(R.id.termsConditionFragment)
//        }
//        mBinding.privacyploicyImg.setOnClickListener {
//
//        }
        mBinding.myArticlesClick.setOnClickListener {
            val args = Bundle()
            args.putString("values","myArtical")
            findNavController().navigate(R.id.articleFragment,args)
        }
        mBinding.faqCLick.setOnClickListener {
            findNavController().navigate(R.id.faqFragment)

        }
        mBinding.changePrefrences.setOnClickListener {
            findNavController().navigate(R.id.action_userProfileFragment_to_preferencesFragment2)
        }

            mBinding.changePreferencesTxt.setOnClickListener {
            findNavController().navigate(R.id.action_userProfileFragment_to_preferencesFragment2)
        }


        mBinding.profilelinearLayout.setOnClickListener{
            findNavController().navigate(R.id.addArticleUserProfileFragment)
        }

        mBinding.signOut.setOnClickListener {
            showPopup()
        }

        observer()

    }
    private fun observer() {
//...........................................profile details..........................

        mprofileInfoVM.progressIndicator.observe(this,  {
//            toggleLoader(requireContext(), it)
        })
        mprofileInfoVM.mProfileInfoResponse.observe(this,
            {

                if(it.peekContent().data!=null)
                {
                    if(it.peekContent().data!!.IsLiveGameUpdates==true)
                    {
                        mBinding.switcLiveGame.isChecked=true
                    }else
                    {
                        mBinding.switcLiveGame.isChecked=false

                    }
                    if(it.peekContent().data!!.IsNotification==true)
                    {
                        mBinding.switchnotification.isChecked=true
                    }else
                    {
                        mBinding.switchnotification.isChecked=false
                    }
                }




            })

        mprofileInfoVM.errorResponse.observe(this, {
            ErrorUtil.handlerGeneralError(requireContext(), mBinding.imageView34, it)
        })


        //.............................................................................



        mlogoutVM.progressIndicator.observe(this,  {
//            toggleLoader(requireContext(), it)
        })
        mlogoutVM.mProfileResponse.observe(this,
            {

                PreferenceManager.getInstance(requireContext()).setAuthToken("EV-DEC21-OPN-BIZBRO-2021LLY")
                PreferenceManager.getInstance(requireContext()).setUserId(0)
                PreferenceManager.getInstance(requireContext()).setName("")
                Constants.HEADER_TOKEN="EV-DEC21-OPN-BIZBRO-2021LLY"

//               findNavController().navigate(R.id.loginFragment)
                val intent = Intent(requireContext(), HoastAccountActivity::class.java)
                startActivity(intent)
                activity?.finish()

            })

        mlogoutVM.errorResponse.observe(this, {
            PreferenceManager.getInstance(requireContext()).setAuthToken("EV-DEC21-OPN-BIZBRO-2021LLY")
            PreferenceManager.getInstance(requireContext()).setUserId(0)
            PreferenceManager.getInstance(requireContext()).setName("")
            Constants.HEADER_TOKEN="EV-DEC21-OPN-BIZBRO-2021LLY"

            val intent = Intent(requireContext(), HoastAccountActivity::class.java)
            startActivity(intent)
            activity?.finish()
//            ErrorUtil.handlerGeneralError(requireContext(), mBinding.imageView34, it)
        })

        //......................................SetCommunicationPrefrences.........................

        mlogoutVM.progressIndicator.observe(this,  {
//            toggleLoader(requireContext(), it)
        })
        mlogoutVM.mSetCommuntionResponse.observe(this,
            {

//                showToast(it.peekContent().message!!,requireContext())
                if(livegameStatus==false)
                {
                    mBinding.switcLiveGame.isChecked=false
                }else
                {
                    mBinding.switcLiveGame.isChecked=true

                }
                if(NotifactionStatus==false)
                {
                    mBinding.switchnotification.isChecked=false
                }
                else

                {
                    mBinding.switchnotification.isChecked=true

                }

//                val intent = Intent(requireContext(), Log::class.java)
//                startActivity(intent)

            })

        mlogoutVM.errorResponse.observe(this, {
            ErrorUtil.handlerGeneralError(requireContext(), mBinding.imageView34, it)
        })

    }
    @SuppressLint("HardwareIds")
    private fun showPopup() {
        val alert: AlertDialog.Builder = AlertDialog.Builder(requireContext())
        alert.setMessage("Are you sure?")
            .setPositiveButton("Logout", DialogInterface.OnClickListener { dialog, which ->

                val android_id = Settings.Secure.getString(
                    requireContext().contentResolver,
                    Settings.Secure.ANDROID_ID
                )
                val username=PreferenceManager.getInstance(requireContext()).getUserId
                mlogoutVM.logout(username.toString(),android_id)

//                logout() // Last step. Logout function
            }).setNegativeButton("Cancel", null)
        val alert1: AlertDialog = alert.create()
        alert1.show()
    }

}