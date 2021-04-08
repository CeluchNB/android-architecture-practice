package com.noah.architecturepractice.ui.main

import android.content.Context
import android.content.Intent
import androidx.lifecycle.ViewModel
import com.noah.architecturepractice.ui.authorlist.AuthorListActivity
import com.noah.architecturepractice.utils.Constants

class MainViewModel : ViewModel(), IMainViewModel {

    override fun goToAuthorsList(first: String, last: String, context: Context) {
        if (first.isEmpty() && last.isEmpty()) {
            return
        }

        val intent: Intent = Intent(context, AuthorListActivity::class.java).apply {
            putExtra(Constants.FIRST_NAME, first)
            putExtra(Constants.LAST_NAME, last)
        }

        context.startActivity(intent)
    }
}