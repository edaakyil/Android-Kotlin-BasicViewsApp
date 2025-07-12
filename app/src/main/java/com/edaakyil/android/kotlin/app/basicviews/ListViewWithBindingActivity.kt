package com.edaakyil.android.kotlin.app.basicviews

import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.databinding.DataBindingUtil
import com.edaakyil.android.kotlin.app.basicviews.databinding.ActivityListViewWithBindingBinding
import org.csystem.kotlin.util.string.randomTextEN
import kotlin.random.Random

private const val DEFAULT_USER_COUNT = 20

class ListViewWithBindingActivity : AppCompatActivity() {
    private lateinit var mBinding: ActivityListViewWithBindingBinding

    private fun getRandomTexts(count: Int): List<String> {
        return List(count) { i -> "$i. ${Random.randomTextEN(Random.nextInt(3, 16))}" }
        //return Random.randomTextsEN(count, Random.nextInt(5, 16)).toList()
    }

    private fun initModels() {
        mBinding.activity = this
        mBinding.countText = ""
        mBinding.selectedItem = ""
        mBinding.adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, ArrayList<String>())
    }

    private fun initBinding() {
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_list_view_with_binding)
        initModels()
    }

    private fun initialize() {
        enableEdgeToEdge()
        initBinding()
        ViewCompat.setOnApplyWindowInsetsListener(mBinding.listViewWithBindingActivityMainLayout) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        initialize()
    }

    fun onItemClicked(position: Int) {
        val text = mBinding.adapter!!.getItem(position)

        mBinding.selectedItem = text

        Toast.makeText(this, text, Toast.LENGTH_SHORT).show()
    }

    fun onGenerateRandomTextsButtonClicked() {
        try {
            mBinding.adapter!!.clear()

            val countStr = mBinding.countText!!.trim()
            val count = if (countStr.isNotBlank()) countStr.toInt() else DEFAULT_USER_COUNT

            if (count <= 0) {
                Toast.makeText(this, R.string.value_must_be_positive_prompt, Toast.LENGTH_LONG).show()
                mBinding.selectedItem = ""
                mBinding.countText = ""
                return
            }

            val randomTexts = getRandomTexts(count)

            mBinding.adapter!!.addAll(randomTexts)

            mBinding.selectedItem = ""
            mBinding.countText = ""
        } catch (_: Exception) {
            Toast.makeText(this, "Problem occurred", Toast.LENGTH_SHORT).show()
        }
    }
}