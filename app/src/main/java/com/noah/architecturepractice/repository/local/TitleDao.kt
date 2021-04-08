package com.noah.architecturepractice.repository.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy.REPLACE
import androidx.room.Query
import com.noah.architecturepractice.model.Title

@Dao
interface TitleDao {
    @Insert( onConflict = REPLACE )
    fun save(titles: List<Title>)

    @Query("SELECT * FROM title WHERE authorid = :authorId")
    fun load(authorId: String?): List<Title>

}