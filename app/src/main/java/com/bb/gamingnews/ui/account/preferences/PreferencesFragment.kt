package com.bb.gamingnews.ui.account.preferences

import android.util.TypedValue
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.GridLayoutManager
import com.bb.gamingnews.R
import com.bb.gamingnews.base.BaseFragment
import com.bb.gamingnews.databinding.FragmentPreferencesBinding
import com.bb.gamingnews.ui.account.preferences.adapter.PreferencesAdapter
import com.bb.gamingnews.utils.ErrorUtil
import com.bb.gamingnews.utils.GridSpacingItemDecoration
import com.bb.gamingnews.utils.PreferenceManager
import kotlinx.android.synthetic.main.activity_hoast_account.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class PreferencesFragment : BaseFragment<FragmentPreferencesBinding>(), PreferencesAdapter.CallBack_pref {
    lateinit var frament:PersonlizedBottonSheet
    private val mPrefVM: PreferencesVM by viewModel()
    lateinit var adapters: PreferencesAdapter
    var valueSelect="0"
    override fun mLayoutRes(): Int {
        return R.layout.fragment_preferences
    }

    override fun onViewReady() {
        mPrefVM.context=requireContext()
        var emailId=PreferenceManager.getInstance(requireContext()).getEmail
        var userid=PreferenceManager.getInstance(requireContext()).getUserId
        adapters=PreferencesAdapter(requireContext(),this)

        mBinding.continueBtn.setOnClickListener {

            mPrefVM.setPrefrences(emailId,userid.toString(),valueSelect)


        }

        mPrefVM.getPrefrences(emailId,userid.toString())

        observer()
    }
    private fun dpToPx(dp: Int): Int {
        val r = resources
        return Math.round(
            TypedValue.applyDimension(
                TypedValue.COMPLEX_UNIT_DIP,
                dp.toFloat(),
                r.displayMetrics
            )
        )
    }
    private fun observer() {
        //...........................................get prifernaces......................

        mPrefVM.progressIndicator.observe(this,  {
//            toggleLoader(requireContext(), it)
        })
        mPrefVM.mGerPrefResponse.observe(this,
            {
                mBinding.recyclerPreferences.layoutManager =
                    GridLayoutManager(requireContext(), 2, GridLayoutManager.VERTICAL, false)

                adapters.setData(it.peekContent().data!!)
                mBinding.recyclerPreferences.adapter = adapters



                mBinding.recyclerPreferences.addItemDecoration(
                    GridSpacingItemDecoration(2, dpToPx(11), true)
                )
                mBinding.recyclerPreferences.itemAnimator = DefaultItemAnimator()
            })

        mPrefVM.errorResponse.observe(this, {
            ErrorUtil.handlerGeneralError(requireContext(), mBinding.imageView3, it)
        })
 //..............................................................................................
        //...........................................set prifernaces...........

        mPrefVM.progressIndicator.observe(this,  {
//            toggleLoader(requireContext(), it)
        })
        mPrefVM.mSetPrefResponse.observe(this,
            {
                frament= PersonlizedBottonSheet.newInstance()
                frament.show(childFragmentManager,"")
            })
            mPrefVM.errorResponse.observe(this, {
            ErrorUtil.handlerGeneralError(requireContext(), mBinding.imageView3, it)
        })
//............................................................................................................
    }

    override fun onItemClick_pref(posi: Int, strPrefrenceIds: String?) {
//        showToast(""+strPrefrenceIds,requireContext())
        valueSelect= strPrefrenceIds!!

    }

}
