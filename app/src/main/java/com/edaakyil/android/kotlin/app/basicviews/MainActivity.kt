package com.edaakyil.android.kotlin.app.basicviews

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.databinding.DataBindingUtil
import com.edaakyil.android.kotlin.app.basicviews.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var mBinding: ActivityMainBinding

    private fun initModels() {
        mBinding.activity = this
    }

    private fun initBinding() {
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        initModels()
    }

    private fun initialize() {
        enableEdgeToEdge()
        initBinding()
        ViewCompat.setOnApplyWindowInsetsListener(mBinding.mainActivityMainLayout) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        initialize()
    }

    fun onSpinnerWithLegacyPageButtonClicked() {
        Intent(this, SpinnerWithLegacyActivity::class.java).apply { startActivity(this) }
    }

    fun onSpinnerWithBindingPageButtonClicked() {
        Intent(this, SpinnerWithBindingActivity::class.java).apply { startActivity(this) }
    }

    fun onListViewWithLegacyPageButtonClicked() {
        Intent(this, ListViewWithLegacyActivity::class.java).apply { startActivity(this) }
    }

    fun onListViewWithBindingPageButtonClicked() {
        Intent(this, ListViewWithBindingActivity::class.java).apply { startActivity(this) }
    }
}