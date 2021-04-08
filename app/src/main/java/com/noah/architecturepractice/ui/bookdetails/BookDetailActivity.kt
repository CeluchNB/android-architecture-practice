package com.noah.architecturepractice.ui.bookdetails


import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.noah.architecturepractice.BR
import com.noah.architecturepractice.R
import com.noah.architecturepractice.databinding.ActivityBookDetailsBinding
import com.noah.architecturepractice.model.Title
import com.noah.architecturepractice.utils.Constants

class BookDetailActivity : AppCompatActivity() {

    private var binding: ActivityBookDetailsBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_book_details)
    }

    override fun onResume() {
        super.onResume()
        val titleObject: Title? = intent.getParcelableExtra(Constants.TITLE)

        binding?.setVariable(BR.book, titleObject)
        binding?.book = titleObject
        binding?.executePendingBindings()
    }
}