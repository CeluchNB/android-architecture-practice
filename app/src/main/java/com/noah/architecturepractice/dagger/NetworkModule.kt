package com.noah.architecturepractice.dagger

import android.content.Context
import androidx.room.Room
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.google.gson.reflect.TypeToken
import com.noah.architecturepractice.model.Author
import com.noah.architecturepractice.repository.Webservice
import com.noah.architecturepractice.repository.local.AuthorDao
import com.noah.architecturepractice.repository.local.Database
import com.noah.architecturepractice.repository.local.TitleDao
import com.noah.architecturepractice.utils.AuthorTypeAdapter
import com.noah.architecturepractice.utils.Constants
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory
import java.lang.reflect.Type
import javax.inject.Named
import javax.inject.Singleton

@Module
class NetworkModule {
    companion object {
        private const val BASE_URL = "BASE_URL"
    }


    @Provides
    @Named(BASE_URL)
    fun provideBaseUrlString() = "${Constants.PROTOCOL}${Constants.URL}"


    @Provides
    fun provideTypeAdapter() = AuthorTypeAdapter()

    @Provides
    fun provideAuthorType() = object : TypeToken<List<Author>>() {}.type

    @Provides
    @Singleton
    fun provideGsonBuilder(typeAdapter: AuthorTypeAdapter, type: Type): Gson =
        GsonBuilder()
            .setLenient()
            .registerTypeAdapter(type, typeAdapter)
            .create()

    @Provides
    @Singleton
    fun provideRetrofit(@Named(BASE_URL) url: String, gson: Gson): Retrofit =
        Retrofit
            .Builder()
            .baseUrl(url)
            .addConverterFactory(ScalarsConverterFactory.create() )
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()

    @Provides
    @Singleton
    fun provideWebservice(retrofit: Retrofit): Webservice =
        retrofit.create(Webservice::class.java)


    @Provides
    @Singleton
    fun provideDatabase(context: Context) =
        Room.databaseBuilder(context, Database::class.java, "app-database.db").build()

    @Provides
    @Singleton
    fun provideAuthorDao(db: Database): AuthorDao = db.authorDao()

    @Provides
    @Singleton
    fun provideTitleDao(db: Database): TitleDao = db.titleDao()

}