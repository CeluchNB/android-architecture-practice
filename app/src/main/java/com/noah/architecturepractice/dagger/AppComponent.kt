package com.noah.architecturepractice.dagger

import com.noah.architecturepractice.ui.authorlist.AuthorListActivity
import com.noah.architecturepractice.ui.booklist.BookListActivity
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [
    AppModule::class,
    ViewModelModule::class,
    NetworkModule::class
])
interface AppComponent {
    fun inject(target: AuthorListActivity)

    fun inject(target: BookListActivity)
}