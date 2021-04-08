package com.noah.architecturepractice.repository

import com.noah.architecturepractice.model.Authors
import com.noah.architecturepractice.utils.Constants
import com.noah.architecturepractice.model.Titles
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

interface Webservice {

    @Headers(Constants.ACCEPT_JSON)
    @GET("authors?max=0")
    fun getAuthors(@Query("firstName") first: String?, @Query("lastName") last: String?): Call<Authors>

    @Headers(Constants.ACCEPT_JSON)
    @GET("titles?expandLevel=1")
    fun getTitles(@Query("authorid") authorId: String?): Call<Titles>
}