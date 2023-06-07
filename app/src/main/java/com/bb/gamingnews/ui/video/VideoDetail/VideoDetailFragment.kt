package com.bb.gamingnews.ui.video.VideoDetail

import android.net.Uri
import android.util.Log
import android.view.View
import android.widget.MediaController
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import com.bb.gamingnews.R
import com.bb.gamingnews.base.BaseFragment
import com.bb.gamingnews.databinding.FragmentVideoDetailBinding
import com.bb.gamingnews.extentions.showToast
import com.bb.gamingnews.ui.account.preferences.PreferencesVM
import com.bb.gamingnews.ui.userprofile.addArticals.ArticalsCategoriesAdapter
import com.bb.gamingnews.utils.ErrorUtil
import com.bb.gamingnews.utils.PreferenceManager
import com.google.android.exoplayer2.source.MediaSource
import com.google.android.exoplayer2.source.ProgressiveMediaSource
import com.google.android.exoplayer2.source.dash.DashMediaSource
import com.google.android.exoplayer2.upstream.DataSource
import com.google.android.exoplayer2.upstream.DefaultDataSourceFactory
import org.koin.androidx.viewmodel.ext.android.viewModel
import at.huber.youtubeExtractor.VideoMeta

import at.huber.youtubeExtractor.YtFile

import android.util.SparseArray
import android.webkit.URLUtil
import android.widget.ImageView

import at.huber.youtubeExtractor.YouTubeExtractor
import com.bb.gamingnews.ui.allvideo.AllVideoVM
import com.bb.gamingnews.ui.dashboard.DashboardVM
import com.bb.gamingnews.utils.RemoveHtmlTags
import com.bb.gamingnews.utils.share.ShareUrl
import com.bumptech.glide.Glide
import com.google.android.exoplayer2.*
import com.google.android.exoplayer2.source.MergingMediaSource
import com.google.android.exoplayer2.upstream.DefaultHttpDataSource
import com.google.android.exoplayer2.PlaybackParameters

import com.google.android.exoplayer2.ExoPlaybackException

import com.google.android.exoplayer2.ExoPlayer

import com.google.android.exoplayer2.trackselection.TrackSelectionArray

import com.google.android.exoplayer2.source.TrackGroupArray

import com.google.android.exoplayer2.Timeline





class VideoDetailFragment : BaseFragment<FragmentVideoDetailBinding>(),  ArticalsCategoriesAdapter.CallBack_pref,Player.EventListener {
    lateinit var list:ArrayList<String>
    private val mPrefVM: PreferencesVM by viewModel()
    private val mVideoDetailsVM: AllVideoVM by viewModel()
    private val mDashboardVM: DashboardVM by viewModel()
    lateinit var adapters: ArticalsCategoriesAdapter
    var ThumbnailImageUrl=""
//......................player .....................

    // ............................test.....................
  var youtubeLink: String=""
    var player: ExoPlayer? = null
    private var playWhenReady = true
    private var currentWindow = 0
    private var playbackPosition = 0L
    var views:String=""
    var videoId:String=""
    var type:String=""
    var title:String=""


    //.....................................................
private lateinit var simpleExoplayer: SimpleExoPlayer
//    private var playbackPosition: Long = 0
    private var video = ""
    private var videoUUU = ""
    private var videoPlayURL = ""
            val videoUrl = "https://media.geeksforgeeks.org/wp-content/uploads/20201217192146/Screenrecorder-2020-12-17-19-17-36-828.mp4?_=1"
//
        private val dashUrl = "https://storage.googleapis.com/wvmedia/clear/vp9/tears/tears_uhd.mpd"
    private var urlList = listOf(video to "default", dashUrl to "dash")

    private val dataSourceFactory: DataSource.Factory by lazy {
        DefaultDataSourceFactory(requireContext(), "exoplayer-sample")
    }

    companion object {
        /*val gg = object : PassNavControlCallBack {
            override fun onItemClick(bundle: Bundle) {
                Log.e("AAAA","AWCC")
            }
        }*/

        fun newInstance(): VideoDetailFragment =
            VideoDetailFragment().apply {
//                setStyle(DialogFragment.STYLE_NORMAL, R.style.ThemeOverlay_App_BottomSheetDialog)

            }

    }


    override fun mLayoutRes(): Int {
      return  R.layout.fragment_video_detail
    }

    override fun onViewReady() {

//        val videoUrl = "https://media.geeksforgeeks.org/wp-content/uploads/20201217192146/Screenrecorder-2020-12-17-19-17-36-828.mp4?_=1"
        // resource from the videoUrl
//        val uri: Uri = Uri.parse(videoUrl)
//        mBinding.videoView.videoUrl(videoUrl).enableAutoStart().play()


        mPrefVM.context=requireContext()
        mDashboardVM.context=requireContext()
        mVideoDetailsVM.context=requireContext()
        mBinding.imageView20.setOnClickListener {

            if(videoId.isNotEmpty())
            {
                Log.v("vscsdss",">>>>>"+ThumbnailImageUrl)
                Glide.with(requireContext()).load(ThumbnailImageUrl).into(mBinding.videoUrl)
                mDashboardVM.Id=videoId
                mDashboardVM.type=type
                mDashboardVM.Title=title
                mDashboardVM.onCreateLinkClick(mBinding.videoUrl)

//                ShareUrl.deeplinkingUrl(requireContext(),title,mBinding.videoUrl,mDashboardVM.shortLink.toString())

            }
//            else
//            {
//
//            }
        }




        val mediaController = MediaController(requireContext())
//        mediaController.setAnchorView(mBinding.videoView);
//        mediaController.setMediaPlayer(mBinding.videoView);
//        mBinding.videoView.setMediaController(mediaController);
//        mBinding.videoView.start();



//        list= ArrayList()
//        list.add("Football")
//        list.add("Golf")
//        list.add("Chess")
//        list.add("Poker")
//        list.add("Rummy")
//        list.add("Fantast Game")
//        list.add("Ludo")
//        mBinding.categoryRecycler.layoutManager=LinearLayoutManager(requireContext(),LinearLayoutManager.HORIZONTAL,false)
//        mBinding.categoryRecycler.adapter= ArticalsCategoriesAdapter(requireContext(),list)
//        mBinding.recentVideoRecycler.adapter=VideoAdapter(this)

        if (arguments!=null)
        {
//            Type = requireArguments()!!.getString("type", "")
//            Id = requireArguments()!!.getString("id", "")

//            val Title = requireArguments()!!.getString("Title", "")
             videoId = requireArguments()!!.getString("videoId", "")
            type = requireArguments()!!.getString("type", "")
//            val createby = requireArguments()!!.getString("createby", "")
//             views = requireArguments()!!.getString("views", "")
//            val like = requireArguments()!!.getString("like", "")
//            val desc = requireArguments()!!.getString("desc", "")

//            val VideoLength = requireArguments()!!.getString("VideoLength", "")

            mVideoDetailsVM.GetVideo(type,videoId)



//           val  isValide:Boolean
//           val urldd:String="^(http(s)?:\\\\/\\\\/)?((w){3}.)?youtu(be|.be)?(\\\\.com)?\\\\/.+"

//            if(youtubeLink.matches(urldd))
//            {
//
//            }


//            val yesNo= Patterns.WEB_URL.matcher(youtubeLink).matches()


//            Log.v("CheckYotubUrl","CheckYotubUrl:  "+value)

            //......................youtub video convertor .............................

//            object : YouTubeExtractor(requireContext()) {
//                override fun onExtractionComplete(
//                    ytFiles: SparseArray<YtFile>?,
//                    videoMeta: VideoMeta?
//                ) {
//                    if (ytFiles != null) {
//
//                        val iTag = 137//tag of video 1080
//                        val audioTag = 140 //tag m4a audio
//                        // 720, 1080, 480
//                        var videoUrl = ""
//                        val iTags: List<Int> = listOf(22, 137, 18)
//                        for (i in iTags) {
//                            val ytFile = ytFiles.get(i)
//                            if (ytFile != null) {
//                                val downloadUrl = ytFile.url
//                                if (downloadUrl != null && downloadUrl.isNotEmpty()) {
//                                    videoUrl = downloadUrl
//                                }
//                            }
//                        }
//                        if (videoUrl == "")
//                            videoUrl = ytFiles[iTag].url
//                        val audioUrl = ytFiles[audioTag].url
//                        val audioSource: MediaSource = ProgressiveMediaSource
//                            .Factory(DefaultHttpDataSource.Factory())
//                            .createMediaSource(MediaItem.fromUri(audioUrl))
//                        val videoSource: MediaSource = ProgressiveMediaSource
//                            .Factory(DefaultHttpDataSource.Factory())
//                            .createMediaSource(MediaItem.fromUri(videoUrl))
//                        player?.setMediaSource(
//                            MergingMediaSource(true, videoSource, audioSource), true
//                        )
//                    }
//                }
//
//            }.extract(youtubeLink)
//            //........................................................................
//               object : YouTubeExtractor(requireContext()) {
//                override fun onExtractionComplete(ytFiles: SparseArray<YtFile>?, vMeta: VideoMeta?) {
//                    if (ytFiles != null) {
//                        val itag = 22
//                        videoPlayURL = ytFiles[itag].url
//                    }
//                }
//            }.extract(videoUUU)
//            Log.v("valuurrrrrrrr  ","Api Url : "+videoUUU+"   Origanal:  "+videoPlayURL)
//            initializePlayer(videoPlayURL)
            initializePlayer()
        }




        val emailId= PreferenceManager.getInstance(requireContext()).getEmail
        val userid= PreferenceManager.getInstance(requireContext()).getUserId

        adapters= ArticalsCategoriesAdapter(requireContext(),this)
        mVideoDetailsVM.updateVideoViewCount(emailId,videoId,userid.toString())


        mPrefVM.getPrefrences(emailId,userid.toString())
        observer()
    }

//    override fun videoonItemClicked(favouriteId: Int?) {
//        findNavController().navigate(R.id.videoDetailFragment)
//    }

    override fun onItemClick_pref(posi: Int, strPrefrenceIds: String?) {
    }

    private fun observer() {
        //...........................................get prifernaces......................
        mPrefVM.progressIndicator.observe(this, {
//            toggleLoader(requireContext(), it)
        })
        mPrefVM.mGerPrefResponse.observe(this,
            {
                mBinding.categoryRecycler.layoutManager=LinearLayoutManager(requireContext(),LinearLayoutManager.HORIZONTAL,false)

                adapters.setData(it.peekContent().data!!)
                mBinding.categoryRecycler.adapter = adapters

                mBinding.categoryRecycler.itemAnimator = DefaultItemAnimator()
            })
        mPrefVM.errorResponse.observe(this, {
            ErrorUtil.handlerGeneralError(requireContext(), mBinding.imageView18, it)
        })



        //.............................video ViewCont......................
        mVideoDetailsVM.progressIndicator.observe(this, {
//            toggleLoader(requireContext(), it)
        })
        mVideoDetailsVM.mupdateVideoViewCountResponse.observe(this,
            {
                if(it.data==true)

                {
//                    showToast(it.data.toString())
                    if(views.isNotEmpty())
                    {
                        mBinding.txtviews.setText((views.toInt()+1).toString())

                    }
                    else{
                        mBinding.txtviews.setText((1).toString())

                    }

                }
//                else
//                {
//                    mBinding.txtviews.setText((views.toInt()+1).toString())
//                    showToast(it.data.toString())
//                }
            })
        mVideoDetailsVM.errorResponse.observe(this, {
            ErrorUtil.handlerGeneralError(requireContext(), mBinding.imageView18, it)
        })






        //.............................video details......................
        mVideoDetailsVM.progressIndicator.observe(this, {
//            toggleLoader(requireContext(), it)
        })
        mVideoDetailsVM.mVideosResponse.observe(this,
            {
                if(it.peekContent().data!!.isNotEmpty())
                {

                    mBinding.txttitle.setText(it.peekContent().data!![0]!!.title)
                    mBinding.create.setText(it.peekContent().data!![0]!!.createdBy)
                    mBinding.txtviews.setText(it.peekContent().data!![0]!!.viewCount.toString())
                    mBinding.txtLike.setText(it.peekContent().data!![0]!!.likeCount.toString())
                    mBinding.txtdesc.setText(RemoveHtmlTags.stripHtml(it.peekContent().data!![0]!!.desc))
                    title= it.peekContent().data!![0]!!.title.toString()
                    ThumbnailImageUrl = it.peekContent().data!![0]!!.thumbnailImageUrl.toString()
                    Glide.with(requireContext()).load(ThumbnailImageUrl).into(mBinding.videoUrl)
                    youtubeLink  = it.peekContent().data!![0]!!.videoUrl.toString()
                    val value= checkYT(youtubeLink)
                    if(value==true)
                    {
                        initializePlayer()
                    }
                    else
                    {
                        initializePlayer(youtubeLink)
                    }

                }
            })
        mVideoDetailsVM.errorResponse.observe(this, {
            ErrorUtil.handlerGeneralError(requireContext(), mBinding.imageView18, it)
        })

        //...............................................................
    }
    private fun initializePlayer( videoUrls:String) {
        simpleExoplayer = SimpleExoPlayer.Builder(requireContext()).build()
        val randomUrl = urlList.random()
        preparePlayer(videoUrls, "dash")
        mBinding.videoView.player = simpleExoplayer
        simpleExoplayer.seekTo(playbackPosition)
        simpleExoplayer.playWhenReady = true
        simpleExoplayer.addListener(this)
    }

    private fun preparePlayer(videoUrl: String, type: String) {
        val uri = Uri.parse(videoUrl)
        val mediaSource = buildMediaSource(uri, type)
        simpleExoplayer.prepare(mediaSource)
    }

    private fun releasePlayer() {
        playbackPosition = simpleExoplayer.currentPosition
        simpleExoplayer.release()
    }

    override fun onPlayerError(error: ExoPlaybackException) {
        // handle error
//        showToast("errr"+error.toString(),requireContext())
        Log.v("CheckYotubUrl","CheckYotubUrl:  "+error.toString())
    }
    private fun buildMediaSource(uri: Uri, type: String): MediaSource {
        return if (type == "dash") {
            DashMediaSource.Factory(dataSourceFactory)
                .createMediaSource(uri)
        } else {
            ProgressiveMediaSource.Factory(dataSourceFactory)
                .createMediaSource(uri)
        }
    }

    override fun onPlayerStateChanged(playWhenReady: Boolean, playbackState: Int) {
        if (playbackState == Player.STATE_BUFFERING)
            mBinding.progressBar.visibility = View.VISIBLE
        else if (playbackState == Player.STATE_READY || playbackState == Player.STATE_ENDED)
           mBinding.progressBar.visibility = View.INVISIBLE
    }


    private fun initializePlayer() {
//       val player = ExoPlayer.Builder(requireContext()).build()
        player = SimpleExoPlayer.Builder(requireContext()).build()
        mBinding.videoView.player = player
        object : YouTubeExtractor(requireContext()) {
            override fun onExtractionComplete(
                ytFiles: SparseArray<YtFile>?,
                videoMeta: VideoMeta?
            ) {
                if (ytFiles != null) {

                    val iTag = 137//tag of video 1080
                    val audioTag = 140 //tag m4a audio
                    // 720, 1080, 480
                    var videoUrl = ""
                    val iTags: List<Int> = listOf(22, 137, 18)
                    for (i in iTags) {
                        val ytFile = ytFiles.get(i)
                        if (ytFile != null) {
                            val downloadUrl = ytFile.url
                            if (downloadUrl != null && downloadUrl.isNotEmpty()) {
                                videoUrl = downloadUrl
//                                Log.v("valuurrrrrrrr  ","Api Url : "+youtubeLink+"   Origanal:  "+videoPlayURL)
                            }
                        }
                    }
                    if (videoUrl == "")
                        videoUrl = ytFiles[iTag].url
                    val audioUrl = ytFiles[audioTag].url
                    val audioSource: MediaSource = ProgressiveMediaSource
                        .Factory(DefaultHttpDataSource.Factory())
                        .createMediaSource(MediaItem.fromUri(audioUrl))
                    val videoSource: MediaSource = ProgressiveMediaSource
                        .Factory(DefaultHttpDataSource.Factory())
                        .createMediaSource(MediaItem.fromUri(videoUrl))
                    player?.setMediaSource(
                        MergingMediaSource(true, videoSource, audioSource), true
                    )
                    player?.prepare()
                    player?.playWhenReady = playWhenReady
                    player?.seekTo(currentWindow, playbackPosition)

                    player!!.addListener(object : Player.EventListener {
                        fun onTimelineChanged(timeline: Timeline?, manifest: Any?) {}
                        override fun onTracksChanged(
                            trackGroups: TrackGroupArray,
                            trackSelections: TrackSelectionArray
                        ) {
                        }

                        override fun onLoadingChanged(isLoading: Boolean) {
//                            Log.v("11111111111111111",isLoading.toString())
                        }
                        override fun onPlayerStateChanged(
                            playWhenReady: Boolean,
                            playbackState: Int
                        ) {
//                            Log.v("11111111111111111","ww:"+playbackState.toString())

                            if (playbackState == ExoPlayer.STATE_BUFFERING) {
                                mBinding.progressBar.setVisibility(View.VISIBLE)
                            } else {
                                mBinding.progressBar.setVisibility(View.GONE)
                            }
                        }

                        override fun onPlayerError(error: ExoPlaybackException) {}
                        fun onPositionDiscontinuity() {}
                        override fun onPlaybackParametersChanged(playbackParameters: PlaybackParameters) {}
                    })
                }
            }

        }.extract(youtubeLink)
    }


    override fun onDestroy() {
        super.onDestroy()
        player!!.pause()
//        showToast("ooooooooooojk",requireContext())
    }

fun checkYT(url :String):Boolean
{
    if(URLUtil.isValidUrl(youtubeLink))
    {
        val uri = Uri.parse(youtubeLink)
        if ("www.youtube.com".equals(uri.getHost())) {
            return true;
        }
    }
   return false
}


    }