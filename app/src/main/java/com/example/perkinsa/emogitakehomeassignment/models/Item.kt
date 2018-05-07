package com.example.perkinsa.emogitakehomeassignment.models

import java.io.Serializable

/**
 * This class is responsible for representing each piece of content in regards to how we need
 * it for business logic.
 */
data class Item(val content_id: String, val thumbnailUrl: String? = "",
                val fullScreenUrl: String? = "", val tags: List<String> = emptyList()) : Serializable {

    /**
     * Using a [HashSet] to make the look-up for any tag constant time search as this is
     * low-hanging fruit in terms of time optimization.
     */
    private val tagHashSet = tags.map { it.toLowerCase() }.toHashSet()

    override fun equals(other: Any?): Boolean {
        return other is Item && content_id == other.content_id
    }

    override fun hashCode(): Int {
        return content_id.hashCode()
    }

    fun hasTag(query: List<String>): Boolean {
        query.forEach {
            if (tagHashSet.contains(it)) {
                return true
            }
        }
        return false
    }
}