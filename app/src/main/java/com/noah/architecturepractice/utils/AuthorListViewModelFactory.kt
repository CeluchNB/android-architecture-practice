package com.noah.architecturepractice.utils

import androidx.lifecycle.SavedStateHandle
import com.noah.architecturepractice.repository.Repository
import com.noah.architecturepractice.ui.authorlist.AuthorListViewModel
import javax.inject.Inject

class AuthorListViewModelFactory @Inject constructor(
    private val repository: Repository
) : ViewModelAssistedFactory<AuthorListViewModel> {

    override fun create(handle: SavedStateHandle) : AuthorListViewModel {
        return AuthorListViewModel(repository, handle)
    }

}