package com.noah.architecturepractice.ui.main

import android.content.Context

interface IMainViewModel {
    fun goToAuthorsList(first: String, last: String, context: Context)
}