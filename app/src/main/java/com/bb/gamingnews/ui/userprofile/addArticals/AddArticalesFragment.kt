package com.bb.gamingnews.ui.userprofile.addArticals

import android.Manifest
import android.app.Activity
import android.app.AlertDialog
import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Environment
import android.provider.MediaStore
import android.util.Log
import androidx.core.content.ContextCompat
import androidx.core.content.FileProvider
import androidx.navigation.fragment.findNavController
import com.bb.gamingnews.R
import com.bb.gamingnews.base.BaseFragment
import com.bb.gamingnews.databinding.FragmentAddArticalesBinding
import com.bb.gamingnews.extentions.showToast
import com.bb.gamingnews.ui.userprofile.addArticals.AddArticalesVM.AddArticalVM
import com.bb.gamingnews.utils.ErrorUtil
import com.bb.gamingnews.utils.PreferenceManager
import id.zelory.compressor.Compressor
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.io.File
import java.io.IOException
import java.text.SimpleDateFormat
import java.util.*


class AddArticales : BaseFragment<FragmentAddArticalesBinding>(),AttachFilesAdapter.ArticalsTagsCallback {
//    lateinit var list:ArrayList<String>
    private val addArticalVM:AddArticalVM by viewModel()
    private var profileImage: File? = null
    private val REQUEST_CAMERA = 793
    private val SELECT_FILE = 794
    private val PERMISSION_REQUEST_CODE = 790
    var compressedImageFile:File?=null
    var tagId=0
    override fun mLayoutRes(): Int {
        return R.layout.fragment_add_articales

    }

    override fun onViewReady() {
//        list=ArrayList()
//        list.add("Football")
//        list.add("Golf")
//        list.add("Chess")
//        list.add("Poker")
//        list.add("Rummy")
//        list.add("Fantast Game")
//        list.add("Ludo")


        addArticalVM.context=requireContext()
        val username=PreferenceManager.getInstance(requireContext()).getEmail
        val userid=PreferenceManager.getInstance(requireContext()).getUserId
        addArticalVM.getAllArticleTags(username,userid.toString())
        mBinding.cv.setOnClickListener {
            askPermissions()
        }

        mBinding.Cvsubmit.setOnClickListener {

            if(mBinding.editTextTextPersonName2.text.toString().isEmpty())
            {
             showToast("Enter article header !")
            }
            else if(mBinding.descriptionTxt.text.toString().isEmpty())
            {
                showToast("Enter article description !")
            }
            else if(compressedImageFile==null)
            {
                showToast("Attach file !")
            }
            else
            {
                val tital=mBinding.editTextTextPersonName2.text.toString()
                val desc=mBinding.descriptionTxt.text.toString()
                val username=PreferenceManager.getInstance(requireContext()).getEmail
                val userid=PreferenceManager.getInstance(requireContext()).getUserId.toString()
                Log.v("hjsdsbcjdssd",">>>>>>>>>>>>>>>>>"+desc)
                Log.v("hjsdsbcjdssd",">>>>>>>>>>>>>>>>>"+username)
                Log.v("hjsdsbcjdssd",">>>>>>>>>>>>>>>>>"+userid)
                Log.v("hjsdsbcjdssd",">>>>>>>>>>>>>>>>>"+tital)
                Log.v("hjsdsbcjdssd",">>>>>>>>>>>>>>>>>"+desc)
//                addArticalVM.postArticals(compressedImageFile!!,username,"0","1",tital ,desc,"",userid.toString())
                addArticalVM.postArticals(compressedImageFile!!,username,"0",tagId.toString(),tital ,desc,"",userid.toString())

            }

//            addArticalVM.postArticals(compressedImageFile!!,username,"0","1","tital" ,"desc","","9")
        }
        observer()
//        mBinding.categoriesRecyclwer.adapter=ArticalsCategoriesAdapter(requireContext(),list)
    }
    private fun observer() {

        addArticalVM.progressIndicator.observe(this,  {
//            toggleLoader(requireContext(), it)
        })
        addArticalVM.mAllatrticalTagResponse.observe(this,
            {


                Log.v("hjsdsbcjdssdsssss",">>>>>>>>>>>>>>>>>"+it.peekContent().data)

                mBinding.categoriesRecyclwer.adapter=AttachFilesAdapter(requireContext(),this,it.peekContent().data)



            })

        addArticalVM.errorResponse.observe(this, {
            ErrorUtil.handlerGeneralError(requireContext(), mBinding.textView10, it)
        })

        //....................................artical post .............
        addArticalVM.progressIndicator.observe(this,  {
//            toggleLoader(requireContext(), it)
        })
        addArticalVM.mAddArticalResponse.observe(this,
            {

                findNavController().popBackStack(R.id.addArticleUserProfileFragment,false)
//                mBinding.editTextTextPersonName2.setText("")
//                mBinding.descriptionTxt.setText("")
            showToast(""+it.peekContent().message,requireContext())

//                mBinding.categoriesRecyclwer.adapter=AttachFilesAdapter(requireContext(),this,it.peekContent().data)



            })

        addArticalVM.errorResponse.observe(this, {
            ErrorUtil.handlerGeneralError(requireContext(), mBinding.textView10, it)
        })




    }

    override fun onArticalTagItemClick(favouriteId: Int?) {

        tagId=favouriteId!!
        Log.v("hjsdsbcjdssd",">>>>>>>>>>>>>>>>>"+tagId)

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
                profileImage = null
                try {
                    profileImage = createImageFile()
                }
                catch (ex: IOException) {
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


                GlobalScope.launch {
                    compressedImageFile = profileImage?.let {
                        Compressor.compress(
                            requireContext(),
                            it
                        )
                    }


                }
            } else if (requestCode == SELECT_FILE) {
                val selectedImageURI = data!!.data
                val path: String = getPathFromURI(selectedImageURI!!)!!
                mBinding.imgarticals.setImageURI(selectedImageURI)
                profileImage = File(path)

                if (path != null) {
                    //api call
                    GlobalScope.launch {
                        compressedImageFile = profileImage?.let {
                            Compressor.compress(
                                requireContext(),
                                it
                            )
                        }
//                        var tital=mBinding.editTextTextPersonName2.text.toString()
//                        var desc=mBinding.descriptionTxt.text.toString()
//                        val username=PreferenceManager.getInstance(requireContext()).getEmail
//                        val userid=PreferenceManager.getInstance(requireContext()).getUserId.toString()
//                        addArticalVM.postArticals(compressedImageFile!!,username,"0",tagId.toString(),tital ,desc,"",userid.toString())


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


}