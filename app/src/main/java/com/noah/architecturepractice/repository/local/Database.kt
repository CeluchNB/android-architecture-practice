package com.noah.architecturepractice.repository.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.noah.architecturepractice.model.Author
import com.noah.architecturepractice.model.Title

@Database(entities = [Author::class, Title::class], version = 1)
abstract class Database : RoomDatabase() {
    abstract fun authorDao(): AuthorDao

    abstract fun titleDao(): TitleDao
}