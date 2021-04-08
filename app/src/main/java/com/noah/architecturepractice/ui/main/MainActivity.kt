package com.noah.architecturepractice.ui.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.noah.architecturepractice.R
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private val viewModel: IMainViewModel = MainViewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        searchButton.setOnClickListener {
            goToAuthorsList()
        }
    }

    fun goToAuthorsList() {
        viewModel.goToAuthorsList(searchFirst.text.toString(), searchLast.text.toString(), this)
    }
}
