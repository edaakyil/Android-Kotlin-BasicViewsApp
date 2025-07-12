package com.edaakyil.android.kotlin.app.basicviews

import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class SpinnerWithLegacyActivity : AppCompatActivity(), AdapterView.OnItemSelectedListener {
    private lateinit var mMaritalStatusSpinner: Spinner

    private fun initMaritalStatusSpinner() {
        val maritalStatus = arrayOf(
            resources.getString(R.string.single),
            resources.getString(R.string.married),
            resources.getString(R.string.divorced)
        )
        mMaritalStatusSpinner = findViewById<Spinner>(R.id.maritalStatusSpinner).apply {
            adapter = ArrayAdapter(this@SpinnerWithLegacyActivity, android.R.layout.simple_spinner_dropdown_item, maritalStatus)
            onItemSelectedListener = this@SpinnerWithLegacyActivity
        }

    }

    private fun initViews() {
        initMaritalStatusSpinner()
    }

    private fun initialize() {
        initViews()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_spinner_with_legacy)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.spinnerWithLegacyActivityMainLayout)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        initialize()
    }

    override fun onItemSelected(p0: AdapterView<*>?, p1: View?, position: Int, p3: Long) {
        Toast.makeText(this, "${mMaritalStatusSpinner.selectedItemPosition}: ${mMaritalStatusSpinner.selectedItem as String}", Toast.LENGTH_SHORT).show()
    }

    override fun onNothingSelected(p0: AdapterView<*>?) {
        TODO("Not yet implemented")
    }
}