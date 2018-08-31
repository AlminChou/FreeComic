package com.almin.library.network.retrofitlibrary.gson

import com.google.gson.TypeAdapter
import com.google.gson.stream.JsonReader
import com.google.gson.stream.JsonToken
import com.google.gson.stream.JsonWriter

import java.io.IOException

class NullStringToEmptyStringAdapter : TypeAdapter<String>() {

    @Throws(IOException::class)
    override fun read(`in`: JsonReader): String {
        val peek = `in`.peek()
        if (peek == JsonToken.NULL) {
            `in`.nextNull()
            return ""
        }
        /* coerce booleans to strings for backwards compatibility */
        return if (peek == JsonToken.BOOLEAN) {
            java.lang.Boolean.toString(`in`.nextBoolean())
        } else `in`.nextString()
    }

    @Throws(IOException::class)
    override fun write(out: JsonWriter, value: String) {
        out.value(value)
    }
}