package com.noah.architecturepractice.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.liveData
import com.noah.architecturepractice.model.Author
import com.noah.architecturepractice.model.Title
import com.noah.architecturepractice.repository.local.AuthorDao
import com.noah.architecturepractice.repository.local.TitleDao
import kotlinx.coroutines.*
import javax.inject.Inject

class Repository @Inject constructor(
    private val webservice: Webservice,
    private val authorDao: AuthorDao,
    private val titleDao: TitleDao) {

    fun getAuthors(first: String?, last: String?): LiveData<List<Author>> {
        return liveData(Dispatchers.IO) {
            var authorList = authorDao.load(first, last)

            if (authorList.isNullOrEmpty()) {
                val authors = webservice.getAuthors(first, last).execute().body()
                authorDao.save(authors?.author)
                authorList = authorDao.load(first, last)
            }
            emit(authorList)
        }
    }

    fun getTitles(authorId: String?): LiveData<List<Title>> {
        return liveData(Dispatchers.IO) {
            var titleList = titleDao.load(authorId)

            if (titleList.isNullOrEmpty()) {
                webservice.getTitles(authorId).execute().body()?.title?.let{ titles ->
                    for (title in titles) {
                        title.authorid = authorId
                    }
                    titleDao.save(titles)
                }
            }

            titleList = titleDao.load(authorId)
            emit(titleList)
        }
    }

}