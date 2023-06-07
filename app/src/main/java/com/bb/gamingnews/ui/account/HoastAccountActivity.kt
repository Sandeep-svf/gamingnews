package com.bb.gamingnews.ui.account

import android.graphics.Color
import android.view.View
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import com.bb.gamingnews.R
import com.bb.gamingnews.base.BaseActivity
import com.bb.gamingnews.base.callback.ManageBack
import com.bb.gamingnews.databinding.ActivityHoastAccountBinding
import com.bb.gamingnews.ui.account.Signup.SignupFragment
import com.bb.gamingnews.utils.KeyboardUtil

class HoastAccountActivity : BaseActivity<ActivityHoastAccountBinding>() ,ManageBack{
    private lateinit var navController: NavController
    var fromScreen:String=""
    override fun mLayoutRes(): Int {
        return R.layout.activity_hoast_account
    }

    override fun onViewReady() {
        SignupFragment.manageBack=this

        window.decorView.systemUiVisibility = (
                View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                        or View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN)
        window.statusBarColor = Color.BLACK;

        KeyboardUtil(this, mBinding.container)

        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.fragment) as NavHostFragment
        navController = navHostFragment.navController


    }

    override fun onBackPressed() {
        if(fromScreen=="signup"){
            navController.navigate(R.id.loginFragment)
        }else{
            super.onBackPressed()

        }
    }


    override fun OnBack(fromScreen: String) {
        this.fromScreen = fromScreen

    }


}