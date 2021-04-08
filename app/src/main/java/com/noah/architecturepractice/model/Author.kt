package com.noah.architecturepractice.model

import android.os.Build
import android.text.Html
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Author(
    val authordisplay: String,
    val authorid: String,
    val spotlight: String?,
    val authorlast: String?,
    val authorfirst: String?,
    @PrimaryKey(autoGenerate = true)
    val id: Int) {



    fun getHtmlSpotlight(): String?  {
        return if (spotlight.isNullOrBlank()) {
            null
        } else {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                Html.fromHtml(spotlight, Html.FROM_HTML_MODE_LEGACY).toString()
            } else {
                Html.fromHtml(spotlight).toString()
            }
        }
    }
}