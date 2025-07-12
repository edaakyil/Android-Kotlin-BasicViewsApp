package com.edaakyil.android.kotlin.app.basicviews

import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import android.widget.EditText
import android.widget.ListView
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import org.csystem.kotlin.util.string.randomTextEN
import kotlin.random.Random

private const val DEFAULT_USER_COUNT = 20

class ListViewWithLegacyActivity : AppCompatActivity() {
    private lateinit var mRandomTextsListView: ListView
    private lateinit var mRandomTextTextView: TextView
    private lateinit var mRandomTextsCountEditText: EditText
    private lateinit var mRandomTextsArrayAdapter: ArrayAdapter<String>

    private fun itemClickListenerCallback(position: Int) {
        val text = mRandomTextsArrayAdapter.getItem(position)

        mRandomTextTextView.text = text

        Toast.makeText(this, text, Toast.LENGTH_SHORT).show()
    }

    private fun generateRandomTexts(count: Int): List<String> {
        return List(count) { i -> "$i. ${Random.randomTextEN(Random.nextInt(3, 16))}" }
        //return Random.randomTextsEN(count, Random.nextInt(5, 16)).toList()
    }

    private fun initRandomTextListView() {
        mRandomTextsListView = findViewById(R.id.randomTextsListView)
        mRandomTextsListView.setOnItemClickListener { _, _, position, _ -> itemClickListenerCallback(position) }
    }

    private fun initViews() {
        mRandomTextTextView = findViewById(R.id.randomTextTextView)
        mRandomTextsCountEditText = findViewById(R.id.randomTextsCountEditText)
        initRandomTextListView()
    }

    private fun initialize() {
        initViews()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_list_view_with_legacy)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.listViewWithLegacyActivityMainLayout)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        initialize()
    }

    fun onGenerateRandomTextsButtonClicked(view: View) {
        try {
            if (this::mRandomTextsArrayAdapter.isInitialized)
                mRandomTextsArrayAdapter.clear()

            val countStr = mRandomTextsCountEditText.text.toString().trim()
            val count = if (countStr.isNotBlank()) countStr.toInt() else DEFAULT_USER_COUNT

            if (count <= 0) {
                Toast.makeText(this, R.string.value_must_be_positive_prompt, Toast.LENGTH_LONG).show()
                return
            }

            val randomTexts = generateRandomTexts(count)

            mRandomTextsArrayAdapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, randomTexts)
                .apply { mRandomTextsListView.adapter = this }

            mRandomTextTextView.text = ""
            mRandomTextsCountEditText.text.clear()
        } catch (_: Exception) {
            Toast.makeText(this, "Problem occurred", Toast.LENGTH_SHORT).show()
        }
    }
}