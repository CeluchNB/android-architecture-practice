package com.noah.architecturepractice.model

import com.google.gson.annotations.SerializedName

// TODO::Get Rid of this class
data class Authors(
    @SerializedName("@uri")
    var uri: String,
    var author: List<Author>)
