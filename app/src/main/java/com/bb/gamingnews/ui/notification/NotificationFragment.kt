package com.bb.gamingnews.ui.notification

import com.bb.gamingnews.R
import com.bb.gamingnews.base.BaseFragment
import com.bb.gamingnews.databinding.NotificationFragmentBinding
import com.bb.gamingnews.ui.dashboard.adapter.*
import com.bb.gamingnews.ui.notification.adapter.NotificationAdapter
import com.bb.gamingnews.ui.notification.model.GetSentNotification
import com.bb.gamingnews.utils.ErrorUtil
import com.bb.gamingnews.utils.PreferenceManager
import org.koin.androidx.viewmodel.ext.android.viewModel

class NotificationFragment : BaseFragment<NotificationFragmentBinding>(),NotificationAdapter.DeleteNotiCallback {

    private val mnotificationVM: NotificationVM by viewModel()
    lateinit var listInterviewTexts:List<GetSentNotification.Data>

    override fun mLayoutRes(): Int {
        return R.layout.notification_fragment
    }

    override fun onViewReady() {

        mnotificationVM.context=requireContext()
        var email= PreferenceManager.getInstance(requireContext()).getEmail
        var userid= PreferenceManager.getInstance(requireContext()).getUserId
        mnotificationVM.getSentNotification(email,userid.toString())

        observer()
    }

    private fun observer() {


//...............................GetNotification.....................................................


        mnotificationVM.progressIndicator.observe(this,  {
//            toggleLoader(requireContext(), it)
        })
        mnotificationVM.mNotificationResponse.observe(this,
            {

                mBinding.notificationRecycler.adapter=NotificationAdapter(requireContext(),it.data,this)



            })

        mnotificationVM.errorResponse.observe(this, {
            ErrorUtil.handlerGeneralError(requireContext(), mBinding.imageView29, it)

        })
//......................................delete notification................................................
        mnotificationVM.progressIndicator.observe(this,  {
//            toggleLoader(requireContext(), it)
        })
        mnotificationVM.mDeleteNotificationResponse.observe(this,
            {
                var email= PreferenceManager.getInstance(requireContext()).getEmail
                var userid= PreferenceManager.getInstance(requireContext()).getUserId
                mnotificationVM.getSentNotification(email,userid.toString())


            })

        mnotificationVM.errorResponse.observe(this, {
            ErrorUtil.handlerGeneralError(requireContext(), mBinding.imageView29, it)

        })

    }

    override fun onNotificationClick(NotifiacationId: Int?) {
        var email= PreferenceManager.getInstance(requireContext()).getEmail
        var userid= PreferenceManager.getInstance(requireContext()).getUserId
        mnotificationVM.deleteNotification(email,NotifiacationId.toString(),userid.toString())
//        showToast("id"+NotifiacationId,requireContext())
    }

}