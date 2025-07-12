package com.edaakyil.android.kotlin.app.basicviews

import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.databinding.DataBindingUtil
import com.edaakyil.android.kotlin.app.basicviews.databinding.ActivitySpinnerWithBindingBinding

class SpinnerWithBindingActivity : AppCompatActivity() {
    private lateinit var mBinding: ActivitySpinnerWithBindingBinding

    private fun initMaritalStatusSpinner() {
        val maritalStatus = arrayOf(
            resources.getString(R.string.single),
            resources.getString(R.string.married),
            resources.getString(R.string.divorced)
        )

        mBinding.adapter = ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, maritalStatus)
    }

    private fun initModels() {
        mBinding.activity = this
        initMaritalStatusSpinner()
    }

    private fun initBinding() {
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_spinner_with_binding)
        initModels()
    }

    private fun initialize() {
        enableEdgeToEdge()
        initBinding()
        ViewCompat.setOnApplyWindowInsetsListener(mBinding.spinnerWithBindingActivityMainLayout) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initialize()
    }

    fun onItemSelected(position: Int) {
        Toast.makeText(this, mBinding.adapter!!.getItem(position), Toast.LENGTH_SHORT).show()
    }
}