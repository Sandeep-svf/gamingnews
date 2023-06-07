package com.bb.gamingnews.ui

import android.graphics.Color
import android.graphics.Paint
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.core.view.GravityCompat
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.NavigationUI
import androidx.navigation.ui.setupWithNavController
import com.bb.gamingnews.R
import com.bb.gamingnews.base.BaseActivity
import com.bb.gamingnews.databinding.ActivityMainBinding
import com.bb.gamingnews.extentions.doBack
import com.bb.gamingnews.utils.KeyboardUtil
import com.bb.gamingnews.utils.PreferenceManager
import com.bumptech.glide.Glide
import com.google.firebase.dynamiclinks.ktx.dynamicLinks
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.preferences_item.view.*
import org.koin.androidx.viewmodel.ext.android.viewModel


class MainActivity() : BaseActivity<ActivityMainBinding>() {

    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var navController: NavController
    private lateinit var inflateHeaderView: View

    private val mHomeVM: HomeVM by viewModel()

    override fun mLayoutRes(): Int {
        return R.layout.activity_main
    }

    override fun onViewReady() {



        mBinding.imageView4.doBack()


        //.................firebase dynamic link ..................
        Firebase.dynamicLinks
            .getDynamicLink(intent)
            .addOnSuccessListener(this) { pendingDynamicLinkData ->
                // Get deep link from result (may be null if no link is found)
                var deepLink: Uri? = null
                if (pendingDynamicLinkData != null) {
                    deepLink = pendingDynamicLinkData.link
                    Log.d("TAGSSSSSSSSSSS", "==> ${deepLink.toString()}")
                    if (deepLink?.getBooleanQueryParameter("type", false) == true) {
                        val  type = deepLink!!.getQueryParameter("type")
                        val  id = deepLink!!.getQueryParameter("id")

                        if(type.equals("News"))
                        {
                            val arg=Bundle()
                            arg.putString("type",type)
                            arg.putString("Id",id)
                            navController.navigate(R.id.feedFragment,arg)

                        }
                        else if(type.equals("Article"))
                        {
                            val arg=Bundle()
                            arg.putString("type",type)
                            arg.putString("articalId",id)
                            navController.navigate(R.id.articleDetailsFragment,arg)
                        }
                        else if(type.equals("Interview"))
                        {
                            val arg=Bundle()
                            arg.putString("type",type)
                            arg.putString("id",id)
                            navController.navigate(R.id.newsDetailFragment,arg)
                        }
//                        intent.getStringExtra("type").let {
//                            when(it){
//                                "New Article"->{
//                                    navController.navigate(R.id.navigation_home)
//                                }
//                            }
//                        }
                        Log.d("TAGSSSSSSSSSSS", "==> "+type+">>>"+id)
                    }
                }

                // Handle the deep link. For example, open the linked
                // content, or apply promotional credit to the user's
                // account.
                // ...

                // ...
            }
            .addOnFailureListener(this) {
                    e -> Log.w("TAG", "getDynamicLink:onFailure", e)
            }

//        val data: Uri? = this.intent.data
//        if (data != null && data.isHierarchical()) {
//            val uri = this.intent.dataString
//            Log.i("MyApp", "Deep link clicked $uri")
//        }

        window.decorView.systemUiVisibility = (
                View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                        or View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN)
        window.statusBarColor = Color.TRANSPARENT;

        KeyboardUtil(this, mBinding.container)
//        setSupportActionBar(mBinding.toolbar)
        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        navController = navHostFragment.navController
        navController.handleDeepLink(intent)

        mBinding.navView.setupWithNavController(navController)
//        mBinding.navDrawer.setupWithNavController(navController)
//        appBarConfiguration = AppBarConfiguration(navController.graph, mBinding.drawerLayout)

        mBinding.txtname.setText(PreferenceManager.getInstance(this).getName)


        Glide.with(this).load(PreferenceManager.getInstance(this).getImage).placeholder(R.drawable.placeholder).circleCrop().into(mBinding.userProfile)
        Glide.with(this).load(PreferenceManager.getInstance(this).getImage).placeholder(R.drawable.placeholder).circleCrop().into(mBinding.imageView21)
//        showToast("image"+PreferenceManager.getInstance(this).getImage,this)


        mBinding.notificationIconSecond.setOnClickListener {
           navController.navigate(R.id.notificationFragment)
        }
        mBinding.notificationIcon.setOnClickListener {
            navController.navigate(R.id.notificationFragment)
        }

        mBinding.userProfile.setOnClickListener {
            navController.navigate(R.id.userProfileFragment)

        }
        mBinding.imageView21.setOnClickListener {
            navController.navigate(R.id.userProfileFragment)

        }
//        NavigationUI.setupActionBarWithNavController(this, navController, mBinding.drawerLayout)
//        fullScreen()
        navigation()
    }

    private fun navigation() {
        navController.addOnDestinationChangedListener { _, destination, _ ->
            when (destination.id) {
                R.id.navigation_home -> {
                    mBinding.frodashboard.visibility=View.VISIBLE
                    mBinding.toolbarlight.visibility=View.GONE
                    mBinding.constraintLayout9.visibility=View.VISIBLE

                }
                R.id.newFeedFragment -> {
                    mBinding.frodashboard.visibility=View.GONE
                    mBinding.toolbarlight.setBackgroundColor(Color.parseColor("#262626"));
                    mBinding.notificationIconSecond.setImageDrawable(notificationIconSecond.resources.getDrawable(R.drawable.notification_icons))
                    mBinding.toolbarlight.visibility=View.VISIBLE
                    mBinding.tvTitle.text = "Feed"
                    mBinding.tvTitle.setTextColor(Color.parseColor("#ffffff"));
                    mBinding.constraintLayout9.visibility=View.VISIBLE
                    mBinding.userProfile.visibility=View.VISIBLE
                    mBinding.notificationIconSecond.visibility=View.VISIBLE

                }
                R.id.newsDetailFragment -> {
                    window.decorView.systemUiVisibility = (
                            View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                                    or View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN)
                    window.statusBarColor = Color.TRANSPARENT;

//                    mBinding.tvTitle.text = "Detalis"
                    mBinding.constraintLayout9.visibility=View.GONE
                }  R.id.articleDetailsFragment -> {
//                    mBinding.tvTitle.text = "Detalis"
                window.decorView.systemUiVisibility = (
                        View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                                or View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN)
                window.statusBarColor = Color.TRANSPARENT;

                mBinding.constraintLayout9.visibility=View.GONE
                }
                R.id.newFeedFragment -> {
                    mBinding.frodashboard.visibility=View.GONE
                    mBinding.toolbarlight.setBackgroundColor(Color.parseColor("#262626"));
                    mBinding.notificationIconSecond.setImageDrawable(notificationIconSecond.resources.getDrawable(R.drawable.notification_icons))
                    mBinding.toolbarlight.visibility=View.VISIBLE
                    mBinding.tvTitle.text = "Feed"
                    mBinding.tvTitle.setTextColor(Color.parseColor("#ffffff"));
                    mBinding.constraintLayout9.visibility=View.VISIBLE
                    mBinding.notificationIconSecond.visibility=View.VISIBLE

                    mBinding.userProfile.visibility=View.VISIBLE

                }
                R.id.articleFragment -> {
                    window.decorView.systemUiVisibility = (
                            View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                                    or View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN)
                    window.statusBarColor = Color.BLACK;

                    mBinding.frodashboard.visibility=View.GONE
                    mBinding.toolbarlight.visibility=View.VISIBLE
                    mBinding.tvTitle.text = "Articles"
                    mBinding.constraintLayout9.visibility=View.VISIBLE
                    mBinding.toolbarlight.setBackgroundColor(Color.parseColor("#ffffff"));
                    mBinding.tvTitle.setTextColor(Color.parseColor("#000000"));
                    mBinding.userProfile.visibility=View.VISIBLE

                    mBinding.notificationIconSecond.visibility=View.VISIBLE

                }

                R.id.interViewFragment -> {
                    window.decorView.systemUiVisibility = (
                            View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                                    or View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN)
                    window.statusBarColor = Color.BLACK;

                    mBinding.frodashboard.visibility=View.GONE
                    mBinding.toolbarlight.visibility=View.VISIBLE
                    mBinding.tvTitle.text = "Interviews"
                    mBinding.constraintLayout9.visibility=View.VISIBLE
                    mBinding.toolbarlight.setBackgroundColor(Color.parseColor("#ffffff"));
                    mBinding.tvTitle.setTextColor(Color.parseColor("#000000"));
                    mBinding.userProfile.visibility=View.VISIBLE

                    mBinding.notificationIconSecond.visibility=View.VISIBLE

                }



                R.id.eventFragment -> {
                window.decorView.systemUiVisibility = (
                        View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                                or View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN)
                window.statusBarColor = Color.BLACK;
                    mBinding.frodashboard.visibility=View.GONE
                    mBinding.toolbarlight.visibility=View.VISIBLE
                    mBinding.tvTitle.text = "Event"
                    mBinding.constraintLayout9.visibility=View.VISIBLE
                mBinding.toolbarlight.setBackgroundColor(Color.parseColor("#ffffff"));
                mBinding.tvTitle.setTextColor(Color.parseColor("#000000"));
                mBinding.userProfile.visibility=View.VISIBLE
                mBinding.notificationIconSecond.visibility=View.VISIBLE

                mBinding.notificationIconSecond.setImageDrawable(notificationIconSecond.resources.getDrawable(R.drawable.notifcation_icon_black))

                }

                R.id.gameTipFragment -> {
                    window.decorView.systemUiVisibility = (
                            View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                                    or View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN)
                    window.statusBarColor = Color.BLACK;
                    mBinding.frodashboard.visibility=View.GONE
                    mBinding.toolbarlight.visibility=View.VISIBLE
                    mBinding.tvTitle.text = "Game Tips"
                    mBinding.constraintLayout9.visibility=View.VISIBLE
                    mBinding.toolbarlight.setBackgroundColor(Color.parseColor("#ffffff"));
                    mBinding.tvTitle.setTextColor(Color.parseColor("#000000"));
                    mBinding.userProfile.visibility=View.VISIBLE
                    mBinding.notificationIconSecond.visibility=View.VISIBLE

                    mBinding.notificationIconSecond.setImageDrawable(notificationIconSecond.resources.getDrawable(R.drawable.notifcation_icon_black))

                }

                R.id.eventDetailsNew -> {
                    window.decorView.systemUiVisibility = (
                            View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                                    or View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN)
                    window.statusBarColor = Color.BLACK;
                    mBinding.frodashboard.visibility=View.GONE
                    mBinding.toolbarlight.visibility=View.VISIBLE
                    mBinding.tvTitle.text = "Event Details"
                    mBinding.constraintLayout9.visibility=View.VISIBLE
                    mBinding.toolbarlight.setBackgroundColor(Color.parseColor("#ffffff"));
                    mBinding.tvTitle.setTextColor(Color.parseColor("#000000"));
                    mBinding.userProfile.visibility=View.VISIBLE
                    mBinding.notificationIconSecond.visibility=View.VISIBLE

                    mBinding.notificationIconSecond.setImageDrawable(notificationIconSecond.resources.getDrawable(R.drawable.notifcation_icon_black))

                }

                R.id.videoFragment -> {
                window.decorView.systemUiVisibility = (
                        View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                                or View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN)
                window.statusBarColor = Color.BLACK;

//                window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR;//  set status text dark
                mBinding.frodashboard.visibility=View.GONE
                    mBinding.toolbarlight.visibility=View.VISIBLE
                    mBinding.tvTitle.text = "Video"
                    mBinding.constraintLayout9.visibility=View.VISIBLE
                mBinding.toolbarlight.setBackgroundColor(Color.parseColor("#ffffff"));
                mBinding.tvTitle.setTextColor(Color.parseColor("#000000"));
                mBinding.userProfile.visibility=View.VISIBLE
                mBinding.notificationIconSecond.visibility=View.VISIBLE

                mBinding.notificationIconSecond.setImageDrawable(notificationIconSecond.resources.getDrawable(R.drawable.notifcation_icon_black))

                } R.id.notificationFragment -> {
                window.decorView.systemUiVisibility = (
                        View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                                or View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN)
                window.statusBarColor = Color.BLACK;
                    mBinding.frodashboard.visibility=View.GONE
                    mBinding.toolbarlight.visibility=View.VISIBLE
                    mBinding.tvTitle.text = "Notification"
                    mBinding.constraintLayout9.visibility=View.VISIBLE
                mBinding.toolbarlight.setBackgroundColor(Color.parseColor("#ffffff"));
                mBinding.tvTitle.setTextColor(Color.parseColor("#000000"));
                mBinding.notificationIconSecond.setImageDrawable(notificationIconSecond.resources.getDrawable(R.drawable.notifcation_icon_black))
                mBinding.userProfile.visibility=View.VISIBLE
                mBinding.notificationIconSecond.visibility=View.GONE
                mBinding.userProfile.visibility=View.GONE

                }  R.id.videoDetailFragment -> {

                window.decorView.systemUiVisibility = (
                        View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                                or View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN)
                window.statusBarColor = Color.TRANSPARENT;

                mBinding.constraintLayout9.visibility=View.GONE

//                    mBinding.frodashboard.visibility=View.GONE
//                    mBinding.toolbarlight.visibility=View.VISIBLE
//                    mBinding.tvTitle.text = "Video Details"
//                    mBinding.constraintLayout9.visibility=View.VISIBLE
//                mBinding.toolbarlight.setBackgroundColor(Color.parseColor("#ffffff"));
//                mBinding.tvTitle.setTextColor(Color.parseColor("#000000"));
//                mBinding.notificationIconSecond.setImageDrawable(notificationIconSecond.resources.getDrawable(R.drawable.notifcation_icon_black))

                }
//                R.id.termsConditionFragment -> {
//                    window.decorView.systemUiVisibility = (
//                            View.SYSTEM_UI_FLAG_LAYOUT_STABLE
//                                    or View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN)
//                    window.statusBarColor = Color.BLACK;
////                window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR;//  set status text dark
//                    mBinding.frodashboard.visibility=View.GONE
//                    mBinding.toolbarlight.visibility=View.VISIBLE
//                    mBinding.tvTitle.text = "Terms & Conditions"
//                    mBinding.constraintLayout9.visibility=View.VISIBLE
//                    mBinding.toolbarlight.setBackgroundColor(Color.parseColor("#ffffff"));
//                    mBinding.tvTitle.setTextColor(Color.parseColor("#000000"));
//                    mBinding.userProfile.visibility=View.VISIBLE
//                    mBinding.notificationIconSecond.setImageDrawable(notificationIconSecond.resources.getDrawable(R.drawable.notifcation_icon_black))
//                    mBinding.notificationIconSecond.visibility=View.VISIBLE
//
//                }



                R.id.termsConditionFragment -> {
                    window.decorView.systemUiVisibility = (
                            View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                                    or View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN)
                    window.statusBarColor = Color.BLACK;
//                window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR;//  set status text dark
                    mBinding.frodashboard.visibility=View.GONE
                    mBinding.toolbarlight.visibility=View.VISIBLE
                    mBinding.tvTitle.text = "Terms & Conditions"
                    mBinding.constraintLayout9.visibility=View.VISIBLE
                    mBinding.toolbarlight.setBackgroundColor(Color.parseColor("#ffffff"));
                    mBinding.tvTitle.setTextColor(Color.parseColor("#000000"));
                    mBinding.userProfile.visibility=View.VISIBLE
                    mBinding.notificationIconSecond.setImageDrawable(notificationIconSecond.resources.getDrawable(R.drawable.notifcation_icon_black))
                    mBinding.notificationIconSecond.visibility=View.VISIBLE

                }

                R.id.privacypolicysFragment -> {
                    window.decorView.systemUiVisibility = (
                            View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                                    or View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN)
                    window.statusBarColor = Color.BLACK;
//                window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR;//  set status text dark
                    mBinding.frodashboard.visibility=View.GONE
                    mBinding.toolbarlight.visibility=View.VISIBLE
                    mBinding.tvTitle.text = "Privacy Policy"
                    mBinding.constraintLayout9.visibility=View.VISIBLE
                    mBinding.toolbarlight.setBackgroundColor(Color.parseColor("#ffffff"));
                    mBinding.tvTitle.setTextColor(Color.parseColor("#000000"));
                    mBinding.userProfile.visibility=View.VISIBLE
                    mBinding.notificationIconSecond.setImageDrawable(notificationIconSecond.resources.getDrawable(R.drawable.notifcation_icon_black))
                    mBinding.notificationIconSecond.visibility=View.VISIBLE

                }
   R.id.adsDetailsFragment -> {
                    window.decorView.systemUiVisibility = (
                            View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                                    or View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN)
                    window.statusBarColor = Color.BLACK;
//                window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR;//  set status text dark
                    mBinding.frodashboard.visibility=View.GONE
                    mBinding.toolbarlight.visibility=View.VISIBLE
                    mBinding.tvTitle.text = "Advertisement"
                    mBinding.constraintLayout9.visibility=View.VISIBLE
                    mBinding.toolbarlight.setBackgroundColor(Color.parseColor("#ffffff"));
                    mBinding.tvTitle.setTextColor(Color.parseColor("#000000"));
                    mBinding.userProfile.visibility=View.VISIBLE
                    mBinding.notificationIconSecond.setImageDrawable(notificationIconSecond.resources.getDrawable(R.drawable.notifcation_icon_black))
                    mBinding.notificationIconSecond.visibility=View.VISIBLE

                }

                R.id.userProfileFragment -> {
                    window.decorView.systemUiVisibility = (
                            View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                                    or View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN)
                    window.statusBarColor = Color.BLACK;
//                window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR;//  set status text dark
                    mBinding.frodashboard.visibility=View.GONE
                    mBinding.toolbarlight.visibility=View.VISIBLE
                    mBinding.tvTitle.text = "Profile"
                    mBinding.constraintLayout9.visibility=View.VISIBLE
                    mBinding.toolbarlight.setBackgroundColor(Color.parseColor("#ffffff"));
                    mBinding.tvTitle.setTextColor(Color.parseColor("#000000"));

                    mBinding.notificationIconSecond.setImageDrawable(notificationIconSecond.resources.getDrawable(R.drawable.notifcation_icon_black))
                    mBinding.notificationIconSecond.visibility=View.VISIBLE
                    mBinding.userProfile.visibility=View.GONE
                }
                R.id.commentFragmant -> {
                    window.decorView.systemUiVisibility = (
                            View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                                    or View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN)
                    window.statusBarColor = Color.BLACK;
//                window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR;//  set status text dark
                    mBinding.frodashboard.visibility=View.GONE
                    mBinding.toolbarlight.visibility=View.VISIBLE
                    mBinding.tvTitle.text = "Comments"
                    mBinding.constraintLayout9.visibility=View.VISIBLE
                    mBinding.toolbarlight.setBackgroundColor(Color.parseColor("#ffffff"));
                    mBinding.tvTitle.setTextColor(Color.parseColor("#000000"));

                    mBinding.notificationIconSecond.setImageDrawable(notificationIconSecond.resources.getDrawable(R.drawable.notifcation_icon_black))
                    mBinding.notificationIconSecond.visibility=View.GONE
                    mBinding.userProfile.visibility=View.GONE
                }
                R.id.addArticleUserProfileFragment -> {
                window.decorView.systemUiVisibility = (
                        View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                                or View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN)
                window.statusBarColor = Color.BLACK;
//                window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR;//  set status text dark
                    mBinding.frodashboard.visibility=View.GONE
                    mBinding.toolbarlight.visibility=View.VISIBLE
                    mBinding.tvTitle.text = "My Profile"
                    mBinding.constraintLayout9.visibility=View.VISIBLE
                    mBinding.toolbarlight.setBackgroundColor(Color.parseColor("#ffffff"));
                    mBinding.tvTitle.setTextColor(Color.parseColor("#000000"));
                mBinding.notificationIconSecond.visibility=View.VISIBLE

                    mBinding.notificationIconSecond.setImageDrawable(notificationIconSecond.resources.getDrawable(R.drawable.notifcation_icon_black))

                }
 R.id.preferencesFragment2 -> {
     window.decorView.systemUiVisibility = (
             View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                     or View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN)
     window.statusBarColor = Color.BLACK;
//                window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR;//  set status text dark
     mBinding.constraintLayout9.visibility=View.GONE


 } R.id.faqFragment -> {
                window.decorView.systemUiVisibility = (
                        View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                                or View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN)
                window.statusBarColor = Color.BLACK;
//                window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR;//  set status text dark
                mBinding.frodashboard.visibility=View.GONE
                mBinding.toolbarlight.visibility=View.VISIBLE
                mBinding.tvTitle.text = "FAQ"
                mBinding.constraintLayout9.visibility=View.VISIBLE
                mBinding.toolbarlight.setBackgroundColor(Color.parseColor("#ffffff"));
                mBinding.tvTitle.setTextColor(Color.parseColor("#000000"));
                mBinding.userProfile.visibility=View.VISIBLE
                mBinding.notificationIconSecond.visibility=View.VISIBLE

                mBinding.notificationIconSecond.setImageDrawable(notificationIconSecond.resources.getDrawable(R.drawable.notifcation_icon_black))


 } R.id.addArticales -> {
                window.decorView.systemUiVisibility = (
                        View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                                or View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN)
                window.statusBarColor = Color.BLACK;
//                window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR;//  set status text dark
                mBinding.frodashboard.visibility=View.GONE
                mBinding.toolbarlight.visibility=View.VISIBLE
                mBinding.tvTitle.text = "Articles"
                mBinding.constraintLayout9.visibility=View.VISIBLE
                mBinding.toolbarlight.setBackgroundColor(Color.parseColor("#ffffff"));
                mBinding.tvTitle.setTextColor(Color.parseColor("#000000"));
                mBinding.userProfile.visibility=View.VISIBLE
                mBinding.notificationIconSecond.visibility=View.VISIBLE

                mBinding.notificationIconSecond.setImageDrawable(notificationIconSecond.resources.getDrawable(R.drawable.notifcation_icon_black))


 }
                R.id.teamMemberProfileFragment -> {
//                window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR;//  set status text dark
                mBinding.frodashboard.visibility=View.GONE
                mBinding.toolbarlight.visibility=View.VISIBLE
                mBinding.tvTitle.text = "Team Member Profile"
                mBinding.constraintLayout9.visibility=View.VISIBLE
                mBinding.toolbarlight.setBackgroundColor(Color.parseColor("#ffffff"));
                mBinding.tvTitle.setTextColor(Color.parseColor("#000000"));
                mBinding.userProfile.visibility=View.VISIBLE
                mBinding.notificationIconSecond.visibility=View.VISIBLE

                mBinding.notificationIconSecond.setImageDrawable(notificationIconSecond.resources.getDrawable(R.drawable.notifcation_icon_black))


 }



            }


        }

    }


//    fun fullScreen(){
//        window.addFlags(WindowManager.LayoutParams.FLAG_LAYOUT_IN_SCREEN)
//        window.addFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS)
//    }

    override fun onSupportNavigateUp(): Boolean {
        return NavigationUI.navigateUp(navController, appBarConfiguration)
    }

    override fun onResume() {
        super.onResume()
//        mBinding.navDrawer.menu.clear()
////        Glide.with(this).load(PreferenceManager.getInstance(this).getImage).placeholder(R.drawable.placeholder).circleCrop().into(mBinding.userProfile)
////        Glide.with(this).load(PreferenceManager.getInstance(this).getImage).placeholder(R.drawable.placeholder).circleCrop().into(mBinding.imageView21)
//        if (::inflateHeaderView.isInitialized)
//            mBinding.navDrawer.removeHeaderView(inflateHeaderView)
//        mBinding.navDrawer.inflateMenu(R.menu.bottom_nav_menu)
//        inflateHeaderView = mBinding.navDrawer.inflateHeaderView(R.layout.drawer_header)
//
//        inflateHeaderView.editProfileTextView.paintFlags =
//            inflateHeaderView.editProfileTextView.paintFlags or Paint.UNDERLINE_TEXT_FLAG
//
//
//        inflateHeaderView.closeImageView.setOnClickListener {
//            mBinding.drawerLayout.closeDrawer(GravityCompat.START)
//        }


//            getProfile()
    }


}