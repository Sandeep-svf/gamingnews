package com.bb.gamingnews.ui.userprofile.addArticals

import android.Manifest
import android.app.Activity
import android.app.AlertDialog
import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import android.os.Environment
import android.provider.MediaStore
import android.util.Log
import androidx.core.content.ContextCompat
import androidx.core.content.FileProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.bb.gamingnews.R
import com.bb.gamingnews.base.BaseFragment
import com.bb.gamingnews.databinding.FragmentAddArticleUserProfileBinding
import com.bb.gamingnews.extentions.showToast
import com.bb.gamingnews.ui.feed.article.ArticalAllVM
import com.bb.gamingnews.ui.feed.article.models.GetAllArticlesResponceModel
import com.bb.gamingnews.ui.teammemberprofile.MemberArticleAdapter
import com.bb.gamingnews.utils.DateUtil
import com.bb.gamingnews.utils.ErrorUtil
import com.bb.gamingnews.utils.PreferenceManager
import com.bumptech.glide.Glide
import id.zelory.compressor.Compressor
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.io.File
import java.io.IOException
import java.text.SimpleDateFormat
import java.util.*

class AddArticleUserProfileFragment : BaseFragment<FragmentAddArticleUserProfileBinding>(),
    MemberArticleAdapter.Callback {


    private val mprofileInfoVM: AddArticalUserProfileVM by viewModel()
    private val mArticalVM: ArticalAllVM by viewModel()
    lateinit var listTexts: List<GetAllArticlesResponceModel.Data>
    var listOwn = mutableListOf<GetAllArticlesResponceModel.Data.LstArticle>()
    private var profileImage: File? = null
    private val REQUEST_CAMERA = 793
    private val SELECT_FILE = 794
    private val PERMISSION_REQUEST_CODE = 790
    var imageUrl:String=""
    override fun mLayoutRes(): Int {
        return R.layout.fragment_add_article_user_profile
    }

    override fun onViewReady() {


        mprofileInfoVM.context=requireContext()
        mArticalVM.context=requireContext()

        mBinding.txtAdd.setOnClickListener {
            findNavController().navigate(R.id.addArticales)

        }


        val username = PreferenceManager.getInstance(requireContext()).getEmail
        val rolid = PreferenceManager.getInstance(requireContext()).getRoleId
        val userid = PreferenceManager.getInstance(requireContext()).getUserId

        mBinding.txtsave.setOnClickListener {
            try
            {


                if(profileImage!=null)
                {
                    mprofileInfoVM.uploadFile(profileImage!!, username, userid.toString()
                        ,mBinding.txtadd.text.toString(),mBinding.txdesc.text.toString())
                }
                else
                {
                    showToast("Please select Image !")

                }


                Log.v("hdfgsjdfj",">>"+profileImage)
                Log.v("hdfgsjdfj",">>"+username)
                Log.v("hdfgsjdfj",">>"+userid)
                Log.v("hdfgsjdfj",">>"+mBinding.txtadd.text.toString())
                Log.v("hdfgsjdfj",">>"+mBinding.txdesc.text.toString())
            }catch (e:Exception)
            {
                Log.v("hdfgsjdfj",">>"+e.toString())
            }

        }



        mArticalVM.getallArtical(username, userid.toString())
        mprofileInfoVM.profileInfo(username, userid.toString())
        mBinding.imgUpdate.setOnClickListener {
            askPermissions()
        }
        observer()
    }

    private fun observer() {
//...................Get My AllArtical.....................
        mArticalVM.progressIndicator.observe(this, {
            //            toggleLoader(requireContext(), it)
        })
        mArticalVM.mArticalAllVMResponse.observe(this,
            {


                if (it.peekContent().data.size > 0) {
                    listTexts = it.peekContent()!!.data
                    Log.v("eeeeeeeeee", listTexts.size.toString())
                    for (i in listTexts) {
                        for (obj in i.lstArticles) {
                            if (obj.isPostedByMe == true) {
                                listOwn.add(obj)
                                Log.v("qwertyu", "" + listOwn.size)
                            } else {
                                Log.v("qwertyu", "op" + listOwn.size.toByte())
                            }
                        }
                    }

                    mBinding.recyclerArticle.layoutManager = LinearLayoutManager(requireContext())
                    mBinding.recyclerArticle.adapter =
                        MemberArticleAdapter(requireContext(), this, listOwn)

                } else {
                    showToast("no data ", requireContext())
                }

            })

        mArticalVM.errorResponse.observe(this, {
            ErrorUtil.handlerGeneralError(requireContext(), mBinding.imgProf, it)
        })
        //...............................................................
        mprofileInfoVM.progressIndicator.observe(this, {
//            toggleLoader(requireContext(), it)
        })
        mprofileInfoVM.mProfileInfoResponse.observe(this,
            {

                Glide.with(requireContext()).load(it.peekContent().data!!.userProfilePic)
                    .placeholder(R.drawable.placeholder).circleCrop().into(mBinding.imgProf)
                imageUrl= it.peekContent().data!!.userProfilePic.toString()
                PreferenceManager.getInstance(requireContext())
                    .setImage(it.peekContent().data!!.userProfilePic)
                mBinding.txtname.setText(it.peekContent().data!!.fullName)
                mBinding.txtplayertitel.setText(it.peekContent().data!!.playerTitle)
                mBinding.txtadd.setText(it.peekContent().data!!.address)
                mBinding.txtfallowe.setText(it.peekContent().data!!.followerCount.toString())
                mBinding.txrank.setText(it.peekContent().data!!.currentRanking.toString())
                mBinding.txdesc.setText(it.peekContent().data!!.playerDesc.toString())
                mBinding.txtmembersicncess.setText(DateUtil.getApiDateFromCalender(it.peekContent().data!!.memberSince.toString()));
//                mBinding.txtmembersicncess.setText(it.peekContent().data!!.memberSince.toString())
                mBinding.txtwinningssss.setText(it.peekContent().data!!.winnings.toString())
                mBinding.txtMinamt.setText(" ₹ "+it.peekContent().data!!.minBuy.toString())
                mBinding.txtMaxamt.setText(" ₹ "+it.peekContent().data!!.maxBuy.toString())


            })

        mprofileInfoVM.errorResponse.observe(this, {
            ErrorUtil.handlerGeneralError(requireContext(), mBinding.imgProf, it)
        })

        //.........................................update profile .........................................
        mprofileInfoVM.progressIndicator.observe(this, {
//            toggleLoader(requireContext(), it)
        })
        mprofileInfoVM.mUpdateProfResponse.observe(this,
            {

                showToast("" + it.peekContent().message, requireContext())
                val username = PreferenceManager.getInstance(requireContext()).getEmail
                val rolid = PreferenceManager.getInstance(requireContext()).getRoleId
                val userid = PreferenceManager.getInstance(requireContext()).getUserId
                mprofileInfoVM.profileInfo(username, userid.toString())
//                Glide.with(requireContext()).load(it.peekContent().data!!.userProfilePic).placeholder(R.drawable.placeholder).into(mBinding.imgProf)
            })
        mprofileInfoVM.errorResponse.observe(this, {
            ErrorUtil.handlerGeneralError(requireContext(), mBinding.imgProf, it)
        })
    }

    private fun askPermissions() {
        val permissions = arrayOf(
            Manifest.permission.CAMERA,
            Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.WRITE_EXTERNAL_STORAGE
        )
        for (permission in permissions) {
            if (ContextCompat.checkSelfPermission(
                    Objects.requireNonNull<Any>(
                        requireContext()
                    ) as Context, permission
                ) == PackageManager.PERMISSION_DENIED
            ) {
                requestPermissions(
                    permissions,
                    PERMISSION_REQUEST_CODE
                )
                return
            }
        }
        selectImage()
    }

    private fun selectImage() {
        val options =
            arrayOf<CharSequence>("Take Photo", "Choose from Gallery", "Cancel")
        val builder =
            AlertDialog.Builder(requireContext())
        builder.setItems(
            options
        ) { dialog: DialogInterface, item: Int ->
            if (options[item] == "Take Photo") {

                val takePictureIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
                try {
                    profileImage = createImageFile()
                } catch (ex: IOException) {
                    Log.d(
                        "mylog",
                        "Exception while creating file: $ex"
                    )
                }
                if (profileImage != null) {
                    Log.d("mylog", "Image file not null")
                    val photoURI = FileProvider.getUriForFile(
                        requireContext(),
                        "com.bb.gamingnews",
                        profileImage!!
                    )
                    takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, photoURI)
                    startActivityForResult(takePictureIntent, REQUEST_CAMERA)
                }
            } else if (options[item] == "Choose from Gallery") {
                val photoPickerIntent = Intent(Intent.ACTION_PICK)
                photoPickerIntent.type = "image/*"
                startActivityForResult(
                    photoPickerIntent,
                    SELECT_FILE
                )
            } else if (options[item] == "Cancel") {
                dialog.dismiss()
            }
        }
        val cameradialog = builder.create()
        cameradialog.show()
    }

    @Throws(IOException::class)
    fun createImageFile(): File? {
        val timeStamp =
            SimpleDateFormat("yyyyMMdd_HHmmss").format(Date())
        val imageFileName = "JPEG_" + timeStamp + "_"
        val storageDir =
            Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES)
        val image = File.createTempFile(
            imageFileName,
            ".jpg",
            storageDir
        )
        val mCurrentPhotoPath = image.absolutePath
        Log.d("mylog", "Path: $mCurrentPhotoPath")
        return image
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == Activity.RESULT_OK) {
            if (requestCode == REQUEST_CAMERA) {

//               profileImage = File(data.pat)

                //  var compressedImageFile:File?=null
                GlobalScope.launch {
                    val compressedImageFile = profileImage?.let {
                        Compressor.compress(
                            requireContext(),
                            it
                        )
                    }
                    val username = PreferenceManager.getInstance(requireContext()).getEmail
                    val userid =
                        PreferenceManager.getInstance(requireContext()).getUserId.toString()
//                if(compressedImageFile!=null)
//                {
//
//                }
//                else
//                {
//
//                }
                    mprofileInfoVM.uploadFile(compressedImageFile!!, username, userid,mBinding.txtadd.text.toString(),mBinding.txdesc.text.toString())
                }


            } else if (requestCode == SELECT_FILE) {
                val selectedImageURI = data!!.data
                val path: String = getPathFromURI(selectedImageURI!!)!!


                Log.v("weeeeeee", "Uri :" + selectedImageURI + "path:  " + path)
                if (path != null) {
                    //api call
                    profileImage = File(path)

                    //   var compressedImageFile:File?=null
                    GlobalScope.launch {
                        val compressedImageFile = profileImage?.let {
                            Compressor.compress(
                                requireContext(),
                                it
                            )
                        }
                        val username = PreferenceManager.getInstance(requireContext()).getEmail
                        val userid =
                            PreferenceManager.getInstance(requireContext()).getUserId.toString()
                        mprofileInfoVM.uploadFile(compressedImageFile!!, username, userid,mBinding.txtadd.text.toString(),mBinding.txdesc.text.toString())

                    }

                    // sendMediaMessage(profileImage!!)
//                    uploadFile(profileImage!!)
                }
            }
        }
    }

    private fun getPathFromURI(contentUri: Uri): String? {
        var res: String? = null
        val proj = arrayOf(MediaStore.Images.Media.DATA)
        val cursor = requireContext().contentResolver.query(contentUri, proj, null, null, null)
        if (Objects.requireNonNull(cursor)!!.moveToFirst()) {
            val column_index = cursor!!.getColumnIndexOrThrow(MediaStore.Images.Media.DATA)
            res = cursor.getString(column_index)
        }
        cursor!!.close()
        return res
    }

    override fun onItemClicked(
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
        args.putString("articalId",articalId)
        args.putString("Headline",title)
        args.putString("Creted",createdBy)
        args.putString("date", date)
        args.putString("desc",desc)
        findNavController().navigate(R.id.articleDetailsFragment,args)
    }


}



