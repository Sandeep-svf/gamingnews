package com.bb.gamingnews.base

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding


abstract class BaseActivity<DB : ViewDataBinding/*, VM: ViewModel*/> : AppCompatActivity() {

    abstract fun mLayoutRes(): Int

    abstract fun onViewReady()

    protected lateinit var mBinding: DB


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = DataBindingUtil.setContentView(this, mLayoutRes())
        onViewReady()
    }

}