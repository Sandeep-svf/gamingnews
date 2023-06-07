package com.bb.gamingnews.ui.dashboard

import android.net.Uri
import android.os.Bundle
import android.util.DisplayMetrics
import android.util.Log
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.viewpager2.widget.CompositePageTransformer
import androidx.viewpager2.widget.MarginPageTransformer
import androidx.viewpager2.widget.ViewPager2
import com.bb.gamingnews.R
import com.bb.gamingnews.api.Constants
import com.bb.gamingnews.base.BaseFragment
import com.bb.gamingnews.databinding.DashBoardFragmentBinding
import com.bb.gamingnews.ui.dashboard.Models.DashboardResponceModel
import com.bb.gamingnews.ui.dashboard.adapter.*
import com.bb.gamingnews.ui.video.VideoDetail.VideoDetailFragment
import com.bb.gamingnews.utils.DateUtil
import com.bb.gamingnews.utils.ErrorUtil
import com.bb.gamingnews.utils.PreferenceManager
import com.bb.gamingnews.utils.dialog.GeneralDialog
import com.bizbrolly.wayja.fcmNotification.FBMessaging
import com.bumptech.glide.Glide
import com.google.firebase.FirebaseApp
import com.google.firebase.dynamiclinks.ktx.dynamicLinks
import com.google.firebase.ktx.Firebase
import com.google.firebase.messaging.FirebaseMessaging
import org.koin.androidx.viewmodel.ext.android.sharedViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel


class DashBoardFragment : BaseFragment<DashBoardFragmentBinding>(),
    InterviewAdapter.Callback,
    OnGoingAdapter.Callback,
    ViewPagerAdapter.CallbackTrending,
    SubEventVideoInnerDashboardAdapter.Callbacks,
    LeatestArticleAdapter.LatArticalsCallback ,
    VideoAdapter.Callback{
    private lateinit var dialogGeneral: GeneralDialog
    lateinit var listInterviewTexts:List<DashboardResponceModel.Data.LstInterview>
    private val mDashBoardViewModel: DashboardVM by viewModel()
    private val mDashBoardShareVm: DashBoardShareVm by sharedViewModel()
    lateinit var fragment: VideoDetailFragment
    lateinit var listTextsNews:List<DashboardResponceModel.Data.LstNew>
    var ads1:String=""
    var ads2:String=""


    private var listTexts = listOf<Int>(
        R.drawable.banner, R.drawable.img_video_icons,  R.drawable.imgbanner
    )
    override fun mLayoutRes(): Int {
        return R.layout.dash_board_fragment
    }

    override fun onViewReady() {
        mDashBoardViewModel.context=requireContext()
        FirebaseApp.initializeApp(requireContext())
        FBMessaging.subscribeForTopic("AllUser")

        Log.v("Tokenss",""+PreferenceManager.getInstance(requireContext()).getAuthToken)
//        //.................firebase dynamic link ..................
//
////        mDashBoardViewModel.onCreateLinkClick()
//
//
//        Firebase.dynamicLinks
//            .getDynamicLink(activity?.intent)
//            .addOnSuccessListener(requireActivity()) { pendingDynamicLinkData ->
//                // Get deep link from result (may be null if no link is found)
//                var deepLink: Uri? = null
//                if (pendingDynamicLinkData != null) {
//                    deepLink = pendingDynamicLinkData.link
//                    Log.d("TAGSSSSSSSSSSS", "==> ${deepLink.toString()}")
//                    if (deepLink?.getBooleanQueryParameter("invitedby", false) == true) {
//                       var  linkData = deepLink!!.getQueryParameter("invitedby")
//                        Log.d("TAGSSSSSSSSSSS", "==> ${linkData.toString()}")
//                    }
//                }
//
//                // Handle the deep link. For example, open the linked
//                // content, or apply promotional credit to the user's
//                // account.
//                // ...
//
//                // ...
//            }
//            .addOnFailureListener(requireActivity()) {
//                    e -> Log.w("TAG", "getDynamicLink:onFailure", e)
//            }

        //............................................................
        mBinding.myBanner.setOnClickListener {
            val args = Bundle()
            args.putString("ads",ads1)
            findNavController().navigate(R.id.adsDetailsFragment,args)
        }
        mBinding.imgthird.setOnClickListener {
            val args = Bundle()
            args.putString("ads",ads2)
            findNavController().navigate(R.id.adsDetailsFragment,args)
        }
        mBinding.textView17.setOnClickListener {
            findNavController().navigate(R.id.newFeedFragment)
        }
        var email= PreferenceManager.getInstance(requireContext()).getEmail
        var userid= PreferenceManager.getInstance(requireContext()).getUserId
        mDashBoardViewModel.getadds(email,userid.toString())

        mBinding.constraintLayout6.setOnClickListener {
            findNavController().navigate(R.id.gameTipFragment)
        }
        mBinding.txtallArical.setOnClickListener {
            val args = Bundle()
            args.putString("values","dashboaed")
            findNavController().navigate(R.id.articleFragment,args)
        }
        mBinding.imgallArtical.setOnClickListener {
            val args = Bundle()
            args.putString("values","dashboaed")
            findNavController().navigate(R.id.articleFragment,args)
        }
        mBinding.txtWatchAll.setOnClickListener {
            findNavController().navigate(R.id.videoFragment)
        }
//        mDashBoardViewModel.getadds("kshitij.mudgal@bizbrolly.com","1")
        mBinding.constrantArticalsLayout.setOnClickListener {

            val args = Bundle()
            args.putString("values","dashboaed")
            findNavController().navigate(R.id.articleFragment,args)
        }

        mBinding.textView28.setOnClickListener {
            findNavController().navigate(R.id.interViewFragment)
        }
        mBinding.interviewLayout.setOnClickListener {
            findNavController().navigate(R.id.interViewFragment)
//            findNavController().navigate(R.id.newFeedFragment)
        }

        mBinding.imageView24.setOnClickListener {
            findNavController().navigate(R.id.eventFragment)
        }
        mBinding.textView28.setOnClickListener {
            findNavController().navigate(R.id.eventFragment)
        }

        mDashBoardViewModel.dashboard(email,userid.toString())
//        mDashBoardViewModel.dashboard("kshitij.mudgal@bizbrolly.com","1")
        mBinding.viewPager.registerOnPageChangeCallback(object :ViewPager2.OnPageChangeCallback() {
            override fun onPageScrolled(
                position: Int,
                positionOffset: Float,
                positionOffsetPixels: Int
            ) { /*empty*/
            }

            override fun onPageSelected(position: Int) {
                //mBinding.pageindicator.
                mBinding.pageindicator.selection = position
            }

            override fun onPageScrollStateChanged(state: Int) { /*empty*/
            }
        })
        observer()
    }

    private fun Int.dpToPx(displayMetrics: DisplayMetrics): Int =
        (this * displayMetrics.density).toInt()

    override fun onItemClicked(favouriteId: Int?) {
        val args = Bundle()
        args.putString("type","Interview")
        args.putString("id",listInterviewTexts[favouriteId!!].id.toString())
        args.putString("Image",listInterviewTexts[favouriteId!!].imageUrl)
        args.putString("Headline",listInterviewTexts[favouriteId!!].title)
        args.putString("Creted",listInterviewTexts[favouriteId!!].createdBy)
        args.putString("date", DateUtil.getApiDateFromCalender(listInterviewTexts[favouriteId!!].createdDate))
        args.putString("desc",listInterviewTexts[favouriteId!!].interviewContent)
        findNavController().navigate(R.id.newsDetailFragment,args)

    }





    private fun showAlertDialog(type:String?,addtext:String?) {
        dialogGeneral = GeneralDialog(requireContext(),type,addtext)

        dialogGeneral.show()


    }

    private fun observer() {

        mDashBoardViewModel.progressIndicator.observe(this,  {
//            toggleLoader(requireContext(), it)
        })
        mDashBoardViewModel.mDashboardResponse.observe(this,
            {

//                showToast("mDashboardResponse",requireContext())

                //..............................LstMyPrefrence.................................

                if(it.data?.lstMyPrefrences?.isNotEmpty() == true)
                {
                    FBMessaging.subscribeForTopic("Pref_"+ it.data!!.lstMyPrefrences?.get(0)!!.id)

                }
                //............................................trending.............................

                if(it.data?.lstNews !=null)
                {
                    listTextsNews= it.data.lstNews
                    mBinding.viewPager.adapter = ViewPagerAdapter(requireContext(),this,it.data.lstNews)
//                mBinding.viewPager.adapter = ViewPagerAdapter(listTexts)
                    mBinding.viewPager.clipToPadding = false
                    mBinding.viewPager.clipChildren = false
                    mBinding.viewPager.offscreenPageLimit = 3
                    mBinding.pageindicator.count = it.data.lstNews.size
                    mBinding.viewPager.setPadding(50, 0, 130, 0);
                    val animTransformer = ViewPager2.PageTransformer { page, position ->
                        page.apply {
                            val r = 1 - Math.abs(position)
                            page.alpha = 0.25f + r
                            page.scaleY = 0.85f + r * 0.15f
                        }
                    }
                    val pageMarginPx = 10.dpToPx(resources.displayMetrics)
                    val marginTransformer = MarginPageTransformer(pageMarginPx)
                    val transformer = CompositePageTransformer().apply {
                        addTransformer(animTransformer)
                        addTransformer(marginTransformer)
                    }
                    mBinding.viewPager.setPageTransformer(transformer)
                }


    //.................................................video.........................................

                if(it.data?.lstVideos !=null)
                {
                    mBinding.videoRecycler.layoutManager = LinearLayoutManager(requireContext(),LinearLayoutManager.HORIZONTAL,false)
                    mBinding.videoRecycler.adapter = VideoAdapter(requireContext(),this,it.data.lstVideos)
                }

//............................................interviews.......................................................................

                if(it.data?.lstInterviews!=null)
                {
                    mBinding.interviewRecycler.layoutManager=LinearLayoutManager(requireContext())
                    listInterviewTexts=it.data.lstInterviews
                    mBinding.interviewRecycler.adapter=InterviewAdapter(requireContext(),this,it.data.lstInterviews)
                }

            //................................................game... changes to events........................

                if(it.data?.lstEvent!=null)
                {
                    mBinding.ongoinggame.layoutManager=LinearLayoutManager(requireContext())
                    mBinding.ongoinggame.adapter=OnGoingAdapter(requireContext(),this,
                        it.data?.lstEvent)
                    Log.v("hchsvchcv",">>>>"+it.data.lstEvent)
                }


//                mBinding.ongoinggame.adapter= SubEventVideoAdapter(requireContext(),this,it.data)
//...........................articals..........
// ..................................................................


                if(it.data?.lstArticle!=null)
                {
                    mBinding.articlerecycler.layoutManager=LinearLayoutManager(requireContext(),LinearLayoutManager.HORIZONTAL,false)
                    mBinding.articlerecycler.adapter= LeatestArticleAdapter(requireContext(),this,it.data?.lstArticle!!)
                    Log.v("ncjdnjkdjd",">>>>>>"+it.data.lstArticle)
                }

//................................................................................................................
            })

        mDashBoardViewModel.errorResponse.observe(this, {
            ErrorUtil.handlerGeneralError(requireContext(), mBinding.cardView3, it)
        })
//...............................GetAdds.....................................................


        mDashBoardViewModel.progressIndicator.observe(this,  {
//            toggleLoader(requireContext(), it)
        })
        mDashBoardViewModel.mAddsResponse.observe(this,
            {

//               showToast(it.message.toString(),requireContext())
//                mBinding.myBanner.
//                showToast("mAddsResponse",requireContext())

                if(it.data!=null)

                {
                    var type=it.data[0].adsType
                    var addtext=it.data[0].headline
                    if(Constants.PopUpCheck==false)

                    {
                        Constants.PopUpCheck=true
                        showAlertDialog(type,addtext)

                    }

//                    if()
                    ads1=it.data[0].redirectUrl!!
                    ads2=it.data[1].redirectUrl!!
                    Glide.with(requireContext()).load(it.data[0].imageUrl).placeholder(R.drawable.space_transparent).into(mBinding.myBanner)
                    Glide.with(requireContext()).load(it.data[1].imageUrl).placeholder(R.drawable.space_transparent).into(mBinding.imgthird)

                }


            })

        mDashBoardViewModel.errorResponse.observe(this, {
            ErrorUtil.handlerGeneralError(requireContext(), mBinding.cardView3, it)




        })

    }

    override fun videoonItemClicked(
        favouriteId: Int?,
        title: String?,
        createby: String?,
        views: String,
        like: String,
        desc: String?,
        ThumbnailImageUrl: String?,
        VideoUrl: String?,
        VideoLength: String?
    ) {
        val args = Bundle()
        args.putString("type","Video")
        args.putString("videoId",favouriteId.toString())
        args.putString("Title",title)
//        args.putString("createby",createby)
//        args.putString("views",views)
//        args.putString("like",like)
//        args.putString("desc",desc)
//        args.putString("ThumbnailImageUrl",ThumbnailImageUrl)
//        args.putString("VideoUrl",VideoUrl)
//        args.putString("VideoLength",VideoLength)
//        fragment.setArguments(args)
//        findNavController().navigate(R.id.videoDetailFragment)
        findNavController().navigate(R.id.action_navigation_home_to_videoDetailFragment,args)
    }

    override fun onLatArticalItemClick(
        favouriteId: Int?,
        articalId: String,
        image: String,
        desc: String,
        createdBy: String,
        date: String,
        title: String
    ) {
        val args = Bundle()

        args.putString("Image",image)
        args.putString("type","Article")
        args.putString("id",listTextsNews[favouriteId!!].id.toString())
        args.putString("articalId",articalId)
        args.putString("Headline",title)
        args.putString("Creted",createdBy)
        args.putString("date", date)
        args.putString("desc",desc)
        findNavController().navigate(R.id.articleDetailsFragment,args)
    }

//    override fun subeventonItemClicked(favouriteId: Int?) {
//        showToast("llooolll",requireContext())
//    }

    override fun onItemInnerClicked(listTexts: List<DashboardResponceModel.Data.LstEvent.LstSubEvent.LstReporting>) {
//        findNavController().navigate(R.id.eventDetailsNew)
//        showToast("lllll",requireContext())
    }

    override fun subeventonItemClicked(listTexts: List<DashboardResponceModel.Data.LstEvent.LstSubEvent.LstReporting>) {
//        showToast("llllppppppl"+listTexts.size,requireContext())

        mDashBoardShareVm.list.value = listTexts
//        val bundle = Bundle()
//        bundle.putSerializable("bankDetails", Gson(d).toJson(listTexts))
        findNavController().navigate(R.id.eventDetailsNew)
    }

    override fun onItemClickedTreding(favouriteId: Int?) {

        val args = Bundle()
        args.putString("Image",listTextsNews[favouriteId!!].imageUrl)
        args.putString("type","News")
        args.putString("id",listTextsNews[favouriteId!!].id.toString())
        args.putString("articalId",listTextsNews[favouriteId!!].id.toString())
        args.putString("Headline",listTextsNews[favouriteId!!].headline)
        args.putString("Creted",listTextsNews[favouriteId!!].createdBy)
        args.putString("date",DateUtil.getApiDateFromCalender(listTextsNews[favouriteId!!].createdDate))
        args.putString("desc",listTextsNews[favouriteId!!].newsDescription)
//        findNavController().navigate(R.id.articleDetailsFragment,args)
        findNavController().navigate(R.id.newsDetailFragment,args)
    }



}