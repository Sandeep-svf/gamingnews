package com.bb.gamingnews.ui.account

import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.util.Base64
import android.util.Log
import android.view.WindowManager
import com.bb.gamingnews.R
import com.bb.gamingnews.ui.MainActivity
import com.bb.gamingnews.utils.PreferenceManager
import java.security.MessageDigest
import java.security.NoSuchAlgorithmException

class SpleshActivity : AppCompatActivity() {
    var context: Context? = null
    var handler = Handler()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splesh)
        window.addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
        generateHasCode()
//        throw RuntimeException("Test Crash") // Force a crash
        context = this
        handler.postDelayed({
         val useid=   PreferenceManager.getInstance(this).getUserId
            if(!(useid==0))
            {
                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
                finish()
            }
            else
            {
                val intent = Intent(this, HoastAccountActivity::class.java)
                startActivity(intent)
                finish()
            }

        }, 3000)

    }

    fun generateHasCode(){
        try {
            val info = this.packageManager.getPackageInfo(
                "com.bb.gamingnews",
                PackageManager.GET_SIGNATURES
            )
            for (signature in info.signatures) {
                val md = MessageDigest.getInstance("SHA")
                md.update(signature.toByteArray())
                Log.e("KeyHash:", Base64.encodeToString(md.digest(), Base64.DEFAULT))


            }
        } catch (e: PackageManager.NameNotFoundException) {

//            snackbar(e.message.toString(), mBinding.imageView)

        } catch (e: NoSuchAlgorithmException) {

//            snackbar(e.message.toString(), mBinding.imageView)


        }
    }
}