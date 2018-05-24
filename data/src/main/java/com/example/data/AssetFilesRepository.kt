package com.example.data

import com.example.presentation.DataStore
import com.example.presentation.Item
import com.squareup.moshi.Moshi
import io.reactivex.Observable
import org.json.JSONObject

const val JSON_FILE_NAME = "contents.json"

/**
 * This [Repository] provides a list of [Item] objects by reading the local
 * JSON file stored at [JSON_FILE_NAME].
 *
 */
class AssetFilesRepository(private val assetsReader: AssetsReader?) : Repository, DataStore {
    private var testJson: String? = null

    /**
     * This method is a part of the Testing API. Should not be used for production code.
     */
    internal fun setJson(str: String){
        testJson = str
    }

    override fun loadItems(): Observable<List<Item>> {
        val jsonStr = testJson ?: assetsReader!!.readFileAsString(JSON_FILE_NAME)
        val contents = JSONObject(jsonStr)
        val itemsJson = JSONObject(contents["contents"].toString())
        val keys = itemsJson.keys()
        val itemsList = ArrayList<Item>()
        val moshi = Moshi.Builder().build()

        while(keys.hasNext()){
            val itemObject = itemsJson[keys.next()]
            val jsonAdapter = moshi.adapter(ItemDto::class.java)
            val item = jsonAdapter.fromJson(itemObject.toString())
            item?.let { itemsList.add(convertToItem(it)) }
        }

        return Observable.just(itemsList)
    }

    private fun convertToItem(itemDto: ItemDto): Item {
        var thumbNailUrl: String? = null
        var fullSizeUrl: String? = null
        itemDto.assets.forEach {
            when (it.size){
                "full" -> fullSizeUrl = it.url
                "thumb" -> thumbNailUrl = it.url
                else -> throw IllegalArgumentException("Unexpected asset size: $it")
            }
        }
        return Item(itemDto.content_id, thumbnailUrl = thumbNailUrl, fullScreenUrl = fullSizeUrl,
                tags = itemDto.tags)
    }
}

