package com.noah.architecturepractice.ui.booklist

import android.content.Context
import android.content.Intent
import androidx.lifecycle.LiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.noah.architecturepractice.model.Title
import com.noah.architecturepractice.repository.Repository
import com.noah.architecturepractice.ui.bookdetails.BookDetailActivity
import com.noah.architecturepractice.utils.Constants
import javax.inject.Inject

class BookListViewModel @Inject constructor(
    repository: Repository,
    handle: SavedStateHandle) : ViewModel() {

    var titles: LiveData<List<Title>> = repository.getTitles(handle[Constants.AUTHOR_ID])

    fun navigateToBookList(position: Int, context: Context) {
        val intent: Intent = Intent(context, BookDetailActivity::class.java).apply {
            putExtra(Constants.TITLE, titles.value?.get(position))
        }
        context.startActivity(intent)
    }
}