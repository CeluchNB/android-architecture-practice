package com.noah.architecturepractice.utils

import androidx.lifecycle.SavedStateHandle
import com.noah.architecturepractice.repository.Repository
import com.noah.architecturepractice.ui.booklist.BookListViewModel
import javax.inject.Inject

class BookListViewModelFactory @Inject constructor(
    private val repository: Repository
) : ViewModelAssistedFactory<BookListViewModel> {
    override fun create(handle: SavedStateHandle): BookListViewModel {
        return BookListViewModel(repository, handle)
    }
}