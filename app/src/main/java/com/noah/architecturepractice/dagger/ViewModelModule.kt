package com.noah.architecturepractice.dagger

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.noah.architecturepractice.ui.authorlist.AuthorListViewModel
import com.noah.architecturepractice.ui.booklist.BookListViewModel
import com.noah.architecturepractice.utils.ViewModelFactory
import dagger.Binds
import dagger.MapKey
import dagger.Module
import dagger.multibindings.IntoMap
import kotlin.reflect.KClass

@Module
abstract class ViewModelModule {

    @Binds
    internal abstract fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory

    @Binds
    @IntoMap
    @ViewModelKey(AuthorListViewModel::class)
    internal abstract fun provideAuthorListViewModel(viewModel: AuthorListViewModel): AuthorListViewModel

    @Binds
    @IntoMap
    @ViewModelKey(BookListViewModel::class)
    internal abstract fun provideBookListViewModel(viewModel: BookListViewModel): BookListViewModel

    @Target(AnnotationTarget.FUNCTION, AnnotationTarget.PROPERTY_GETTER, AnnotationTarget.PROPERTY_SETTER)
    @kotlin.annotation.Retention(AnnotationRetention.RUNTIME)
    @MapKey
    internal annotation class ViewModelKey(val value: KClass<out ViewModel>)

}

