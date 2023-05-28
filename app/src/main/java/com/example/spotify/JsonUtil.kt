package com.example.spotify

import android.content.Context
import com.google.gson.Gson
import java.io.IOException

object JsonUtil {

    inline fun <reified T> parseJson(context: Context, fileName: String): T? {
        val jsonString: String
        try {
            jsonString = context.assets.open(fileName).bufferedReader().use { it.readText() }
        } catch (ioException: IOException) {
            ioException.printStackTrace()
            return null
        }

        return Gson().fromJson(jsonString, T::class.java)
    }
}