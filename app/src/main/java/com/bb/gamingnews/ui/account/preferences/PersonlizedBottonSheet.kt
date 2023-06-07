package com.bb.gamingnews.ui.account.preferences

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import com.bb.gamingnews.R
import com.bb.gamingnews.ui.MainActivity
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import kotlinx.android.synthetic.main.fragment_preferences.view.*

class PersonlizedBottonSheet  :BottomSheetDialogFragment() {

    companion object {
        /*val gg = object : PassNavControlCallBack {
            override fun onItemClick(bundle: Bundle) {
                Log.e("AAAA","AWCC")
            }
        }*/
        fun newInstance(): PersonlizedBottonSheet =
            PersonlizedBottonSheet().apply {
                setStyle(DialogFragment.STYLE_NORMAL, R.style.bottomDialog)

            }

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        var view = inflater.inflate(R.layout.personlized_bottom_sheet, container, false)
//        mMessageListVM.customerId = getPreferences(requireContext()).getCustomerId.toString()
        view.continueBtn.setOnClickListener {
            val intent = Intent(requireContext(), MainActivity::class.java)
            startActivity(intent)
        }
        return view

    }
}