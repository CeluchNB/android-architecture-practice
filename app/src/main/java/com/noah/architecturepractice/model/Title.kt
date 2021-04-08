package com.noah.architecturepractice.model

import android.os.Build
import android.os.Parcelable
import android.text.Html
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Entity
@Parcelize
data class Title(
    @SerializedName("authorweb") val authorName: String,
    val excerpt: String?,
    val formatname: String?,
    val imprint: String?,
    val pages: Int?,
    @SerializedName("titleweb") val title: String,
    @PrimaryKey val workid: String,
    var authorid: String?) : Parcelable {

    fun getHtmlExcerpt(): String? {
        return if (excerpt.isNullOrBlank()) {
            null
        } else {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                Html.fromHtml(excerpt, Html.FROM_HTML_MODE_LEGACY).toString()
            } else {
                Html.fromHtml(excerpt).toString()
            }
        }
    }
}
