package com.noah.architecturepractice.ui.authorlist

import android.content.Context
import android.content.Intent
import androidx.lifecycle.*
import com.noah.architecturepractice.model.Author
import com.noah.architecturepractice.repository.Repository
import com.noah.architecturepractice.ui.booklist.BookListActivity
import com.noah.architecturepractice.utils.Constants

class AuthorListViewModel(
    repository: Repository,
    handle: SavedStateHandle) : ViewModel() {

    var authors: LiveData<List<Author>> = repository.getAuthors(handle[Constants.FIRST_NAME], handle[Constants.LAST_NAME])

    fun navigateToAuthor(position: Int, context: Context) {
        val intent: Intent = Intent(context, BookListActivity::class.java).apply {
            putExtra(Constants.AUTHOR_ID, authors.value?.getOrNull(position)?.authorid)
        }

        context.startActivity(intent)
    }
}