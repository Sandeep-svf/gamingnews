package com.bb.gamingnews.koin


import com.bb.gamingnews.api.WebServiceRequests
import com.bb.gamingnews.ui.Comments.CommnetsVM
import com.bb.gamingnews.ui.HomeVM
import com.bb.gamingnews.ui.account.Signup.SignuptVM
import com.bb.gamingnews.ui.account.login.LoginVM
import com.bb.gamingnews.ui.account.otpbottomsheet.OTPbottomVM
import com.bb.gamingnews.ui.account.preferences.PreferencesVM
import com.bb.gamingnews.ui.account.resetpassword.ChangePaswordVM
import com.bb.gamingnews.ui.account.signin.SignInFragmentVM
import com.bb.gamingnews.ui.allnews.NewsAllVM
import com.bb.gamingnews.ui.allvideo.AllVideoVM
import com.bb.gamingnews.ui.dashboard.DashBoardShareVm
import com.bb.gamingnews.ui.dashboard.DashboardVM
import com.bb.gamingnews.ui.faq.FaqVM
import com.bb.gamingnews.ui.feed.article.ArticalAllVM
import com.bb.gamingnews.ui.feed.event.EventVM
import com.bb.gamingnews.ui.feed.interview.InterviewAllVM
import com.bb.gamingnews.ui.gametips.AllGametipsVM
import com.bb.gamingnews.ui.notification.NotificationVM
import com.bb.gamingnews.ui.privacypolicy.PrivacyPolicyVM
import com.bb.gamingnews.ui.tearmandcondi.TearmConditionVM
import com.bb.gamingnews.ui.userprofile.ProfileVM
import com.bb.gamingnews.ui.userprofile.addArticals.AddArticalUserProfileVM
import com.bb.gamingnews.ui.userprofile.addArticals.AddArticalesVM.AddArticalVM
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {

    single { WebServiceRequests() }

//    factory { (mListener: MyClickListener) -> AdapterPassCode(mListener) }

    viewModel { HomeVM(get()) }
    viewModel { DashboardVM(get()) }
    viewModel { SignInFragmentVM(get()) }
    viewModel { OTPbottomVM(get()) }
    viewModel { SignuptVM(get()) }
    viewModel { LoginVM(get()) }
    viewModel { PreferencesVM(get()) }
    viewModel { ProfileVM(get()) }
    viewModel { AddArticalUserProfileVM(get()) }
    viewModel { NewsAllVM(get()) }
    viewModel { AllVideoVM(get()) }
    viewModel { ArticalAllVM(get()) }
    viewModel { InterviewAllVM(get()) }
    viewModel { FaqVM(get()) }
    viewModel { TearmConditionVM(get()) }
    viewModel { AddArticalVM(get()) }
    viewModel { EventVM(get()) }
    viewModel { PrivacyPolicyVM(get()) }
    viewModel { CommnetsVM(get()) }
    viewModel { AllGametipsVM(get()) }
    viewModel { DashBoardShareVm() }
    viewModel { ChangePaswordVM(get ()) }
    viewModel { NotificationVM(get ()) }



}