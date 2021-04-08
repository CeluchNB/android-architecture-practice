package com.noah.architecturepractice.repository.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.noah.architecturepractice.model.Author

@Dao
interface AuthorDao {

    @Insert
    fun save(authors: List<Author>?)

    @Query("SELECT * FROM author WHERE (:lastName = '' OR authorlast = :lastName) AND (:firstName = '' OR authorfirst = :firstName)")
    fun load(firstName: String?, lastName: String?): List<Author>
}