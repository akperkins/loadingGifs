package com.example.data

import android.content.Context

/**
 * This class is responsible for reading in the contents of a file located in the Assets folder as
 * a [String].
 */
open class AssetsReader(val context:Context) {
    open fun readFileAsString(fileName: String): String? {
        val inputStream = context.assets.open(fileName)
        val size = inputStream.available()
        val buffer = ByteArray(size)
        inputStream.read(buffer)
        inputStream.close()
        return String(buffer, Charsets.UTF_8)
    }
}
