package com.noah.architecturepractice.utils

import com.google.gson.Gson
import com.google.gson.JsonParseException
import com.google.gson.TypeAdapter
import com.google.gson.reflect.TypeToken
import com.google.gson.stream.JsonReader
import com.google.gson.stream.JsonToken
import com.google.gson.stream.JsonWriter
import com.noah.architecturepractice.model.Author
import java.lang.reflect.Type

/**
 * If there is only one author, it is returned as object
 * Not array, hence the custom adapter
 */
class AuthorTypeAdapter : TypeAdapter<List<Author>>() {

    val gson: Gson = Gson()

    override fun write(jsonWriter: JsonWriter?, items: List<Author>?) {
        val type = object : TypeToken<List<Author>>() {}.type
        gson.toJson(items, type, jsonWriter)
    }

    override fun read(jsonReader: JsonReader?): List<Author> {
        var authors = ArrayList<Author>()

        jsonReader?.beginObject()
        jsonReader?.nextName()
        jsonReader?.nextName()

        if (jsonReader?.peek() == JsonToken.BEGIN_OBJECT) {
            authors.add(0, gson.fromJson(jsonReader, Author::class.java) as Author)
        } else if (jsonReader?.peek() == JsonToken.BEGIN_ARRAY) {
            val type: Type = object : TypeToken<ArrayList<Author>>(){}.type
            authors = gson.fromJson(jsonReader, type)
        } else {
            throw JsonParseException("Unexpected token " + jsonReader?.peek())
        }

        jsonReader.endObject()
        return authors
    }
}