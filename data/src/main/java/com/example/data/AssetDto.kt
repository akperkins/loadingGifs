package com.example.data

/**
 * This class is responsible for representing the data format that we will receive the Asset class
 * in, irregardless of how we will use the class in terms of the app business logic.
 *
 */
data class AssetDto(val asset_id: String, val url: String, val file_extension: String, val size: String)