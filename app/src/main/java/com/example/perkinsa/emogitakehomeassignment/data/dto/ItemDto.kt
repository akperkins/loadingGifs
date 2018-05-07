package com.example.perkinsa.emogitakehomeassignment.data.dto

/**
 * This class is responsible for representing the data format that we will receive the Item class
 * in, irregardless of how we will use the class in terms of the app business logic.
 *
 */
data class ItemDto(val content_id: String, val assets: List<AssetDto>, val tags: List<String>)