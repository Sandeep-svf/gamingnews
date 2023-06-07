package com.bb.gamingnews.ui.feed.event

import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.navigation.fragment.findNavController
import com.bb.gamingnews.R
import com.bb.gamingnews.base.BaseFragment
import com.bb.gamingnews.databinding.FragmentEventBinding
import com.bb.gamingnews.extentions.showToast
import com.bb.gamingnews.ui.dashboard.DashBoardShareVm
import com.bb.gamingnews.ui.feed.event.Model.AllEventResponceModel
import com.bb.gamingnews.ui.feed.event.adapter.*
import com.bb.gamingnews.utils.ErrorUtil
import com.bb.gamingnews.utils.PreferenceManager
import kotlinx.android.synthetic.main.item_view_interview.view.*
import org.koin.androidx.viewmodel.ext.android.sharedViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.text.SimpleDateFormat
import java.time.LocalDateTime
import java.time.temporal.ChronoUnit
import java.util.*

class EventFragment : BaseFragment<FragmentEventBinding>(),
    EventSubCategoryAdapter.Callback,
    LiveEventAdapter.Callback,
    SubEventVideoAdapter.CallbackSub,
    SubEventVideoInnerAdapter.Callbackss{

   private val allEventVM:EventVM by viewModel()
    private val mDashBoardShareVm: DashBoardShareVm by sharedViewModel()
    lateinit var listInterviewTexts:List<AllEventResponceModel.Data.LstSubEvent.LstVideo>
    //lateinit var listCategory:List<AllEventResponceModel.Data>
    private var listCategory= mutableListOf<AllEventResponceModel.Data>()
    private var upComeLivePast:Int=0

    private var listTexts = listOf<String>(
        "Poker Event", "Rummy Events", "Fantasy Sport Event",   "Poker Event", "Rummy Events", "Fantasy Sport Event"
    )
    private var listSecond = listOf<String>(
        "Live Reporting", "Upcoming Events", "Past Events"
    )
    override fun mLayoutRes(): Int {
        return R.layout.fragment_event
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onViewReady() {

        allEventVM.context=requireContext()
        allEventVM.getAllEvent(
            PreferenceManager.getInstance(requireContext()).getEmail,"0",
            PreferenceManager.getInstance(requireContext()).getUserId.toString())
        mBinding.eventCategoryRecycler.adapter=EventCategoryAdapter(requireContext(),listTexts)
        mBinding.eventSubCategoryRecycler.adapter=EventSubCategoryAdapter(requireContext(),this, listSecond)
//        mBinding.liveeventRecycler.adapter=LiveEventAdapter(requireContext(),this)

        observer()
    }

    override fun onResume() {
        super.onResume()
     //   observer()

    }

    override fun onItemClicked(favouriteId: Int?) {
//        showToast("ooooook")
        findNavController().navigate(R.id.videoDetailFragment)
    }


    private fun observer() {

        allEventVM.progressIndicator.observe(this,  {
//            toggleLoader(requireContext(), it)
        })
        allEventVM.meventResponse.observe(this,
            {

            if(it.data!!.isNotEmpty())
            {
                if(it.data[0].lstSubEvents!!.isNotEmpty())
                {
                    Log.v("sbbcsbc",">>>>>"+it.data[0].lstSubEvents)

                    if(it.data[0].lstSubEvents.get(0).lstVideos!=null)
                    {
                        listInterviewTexts= (it.data[0].lstSubEvents?.get(0)!!.lstVideos!!)
                        if(listInterviewTexts.isNotEmpty())
                        {
                            listCategory.clear()
                            for(i in it.data)
                            {
                                var startDate =SimpleDateFormat("yyyy-MM-dd").parse(i.startDate)
                                var endDate =SimpleDateFormat("yyyy-MM-dd").parse(i.endDate)



                                if(upComeLivePast==0)
                                {

                                    //..........live data............
//                            showToast("Live click",requireContext())
                                    //System.currentTimeMillis()>=meetingTime.time
                                    //System.currentTimeMillis()<=meetingTime.time
                                    // var Sday=getDiffrentTwoDate(i.startDate)
                                    //  var Eday=getDiffrentTwoDate(i.endDate)
                                    //Log.v("okkkkkkk",""+Sday)


                                    if(System.currentTimeMillis()>= startDate.time && System.currentTimeMillis()<=endDate.time)
                                    {
                                        Log.v("okkkkkkk","${System.currentTimeMillis()} true")
                                        Log.v("okkkkkkk","${System.currentTimeMillis()} upComeLivePast>>>>>>"+endDate.time)
//                                    Log.v("okkkkkkk","upComeLivePast>>>>>>"+i)

                                        listCategory.add(i)

                                        if(listCategory.size>0)
                                        {
                                            mBinding.subEventsVideoRecycler.adapter=SubEventVideoAdapter(requireContext(),this,listCategory)

                                        }

                                    }
                                    else
                                    {
                                        Log.v("okkkkkkk","${System.currentTimeMillis()} false")
                                        mBinding.subEventsVideoRecycler.adapter=SubEventVideoAdapter(requireContext(),this,listCategory)
//                                showToast("No live data available !")
                                    }
                                }
                                else if(upComeLivePast==1)
                                {
                                    //........upcoming data............
                                    Log.v("okkkkkkk","upComeLivePast1   ")
//                            showToast("Upcomming click",requireContext())

                                    if(System.currentTimeMillis()<startDate.time)
                                    {
                                        listCategory.clear()

                                        listCategory.add(i)
                                        if(listCategory.size>0)
                                        {
                                            mBinding.subEventsVideoRecycler.adapter=SubEventVideoAdapter(requireContext(),this,listCategory)

                                        }
                                        else
                                        {

                                        }
                                    }else{
                                        mBinding.subEventsVideoRecycler.adapter=SubEventVideoAdapter(requireContext(),this,listCategory)
//                                showToast("No upcomming data available !")
                                    }

                                }else if(upComeLivePast==2)
                                {
                                    Log.v("okkkkkkk","upComeLivePast2")
                                    //..........past data...........
//                            showToast("Past click",requireContext())

                                    if(System.currentTimeMillis()>startDate.time && System.currentTimeMillis()>endDate.time

                                    )
                                    {
                                        listCategory.add(i)
                                        if(listCategory.size>0)
                                        {
                                            mBinding.subEventsVideoRecycler.adapter=SubEventVideoAdapter(requireContext(),this,listCategory)

                                        }

                                        Log.v("okkkkkkk","upcoming")
                                    }
                                    else
                                    {
//                                showToast("No past data available !")
                                        mBinding.subEventsVideoRecycler.adapter=SubEventVideoAdapter(requireContext(),this,listCategory)

                                    }

                                }
                            }
                            mBinding.liveeventRecycler.adapter=LiveEventAdapter(requireContext(),this,listInterviewTexts)


                        }
                        else
                        {
                            showToast("No data found !",requireContext())
                        }

//                for (i in 1..3)
//                {
//                    listInterviewTexts= listOf(it.peekContent().data[0].lstSubEvents[0].lstVideos[i])
//
//                }
                        Log.v("hedjehsbdhesd",">>>>"+listInterviewTexts)



                    }
                    else
                    {
                        showToast("No data found !",requireContext())
                    }
                }
                else
                {
                    showToast("No data found !",requireContext())

                }

            }


//                mBinding.eventSubCategoryRecycler.adapter=EventSubCategoryAdapter(requireContext(), listSecond)
            })

        allEventVM.errorResponse.observe(this, {
            ErrorUtil.handlerGeneralError(requireContext(), mBinding.eventCategoryRecycler, it)
        })




    }

    override fun onEventUpcomeLivePastItemClicked(favouriteId: Int?) {
        upComeLivePast=favouriteId!!


        allEventVM.getAllEvent(
            PreferenceManager.getInstance(requireContext()).getEmail,"0",
            PreferenceManager.getInstance(requireContext()).getUserId.toString())

    }

@RequiresApi(Build.VERSION_CODES.O)
private fun getDiffrentTwoDate(date:String):Long
{
//    val date=listTexts[position].createdDate

    val c = Calendar.getInstance().time
    val df = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss", Locale.getDefault())
    val formattedDate = df.format(c)
    val date1 = LocalDateTime.parse(date)
    val date2 = LocalDateTime.parse(formattedDate)

    val days = ChronoUnit.DAYS.between(date1, date2)
//    itemView.txtdays.setText(days.toString()+" "+"Days Ago")

    Log.d("rrrrrrrrrrrrrrrr", "" + days)
    return days
}

//    override fun onItemInnerClicked(favouriteId: Int?) {
//
//    }

        override fun subeventonItemClicked(listTexts: List<AllEventResponceModel.Data.LstSubEvent.LstReporting>) {

//            showToast("oooooooooooooooooosws",requireContext())
       mDashBoardShareVm.listEventList.value=listTexts
        findNavController().navigate(R.id.eventDetailsNew)
    }
}