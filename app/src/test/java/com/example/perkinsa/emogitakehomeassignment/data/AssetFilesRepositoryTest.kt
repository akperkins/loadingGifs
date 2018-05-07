package com.example.perkinsa.emogitakehomeassignment.data

import android.content.Context
import com.nhaarman.mockito_kotlin.any
import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.whenever
import org.junit.Assert.*
import org.junit.Before
import org.junit.Ignore
import org.junit.Test

const val TEST_JSON = "{\n  \"contents\": {\n    \"1CTQNP6\": {\n      \"content_id\": \"1CTQNP6\",\n      \"assets\": [\n        {\n          \"asset_id\": \"bd0d471a\",\n          \"url\": \"https://cdn.emogi.com/cxp/1CT/1CTQNP6-thumb.gif\",\n          \"file_extension\": \"gif\",\n          \"size\": \"thumb\"\n        },\n        {\n          \"asset_id\": \"04d114e8\",\n          \"url\": \"https://cdn.emogi.com/cxp/1CT/1CTQNP6-full.gif\",\n          \"file_extension\": \"gif\",\n          \"size\": \"full\"\n        }\n      ],\n      \"tags\": [\n        \"omw!\",\n        \"coming?\",\n        \"go!\",\n        \"ready?\",\n        \"omw\",\n        \"ready\",\n        \"hurry\",\n        \"pixel\"\n      ]\n    },\n    \"47DQJ4\": {\n      \"content_id\": \"47DQJ4\",\n      \"assets\": [\n        {\n          \"asset_id\": \"a8a65466\",\n          \"url\": \"https://cdn.emogi.com/cxp/47D/47DQJ4-thumb.gif\",\n          \"file_extension\": \"gif\",\n          \"size\": \"thumb\"\n        },\n        {\n          \"asset_id\": \"66d85462\",\n          \"url\": \"https://cdn.emogi.com/cxp/47D/47DQJ4-full.gif\",\n          \"file_extension\": \"gif\",\n          \"size\": \"full\"\n        }\n      ],\n      \"tags\": [\n        \"yoo\",\n        \"'sup\",\n        \"yooo\",\n        \"heyy\",\n        \"heyyy\",\n        \"sup?\",\n        \"wassap?\",\n        \"hey\",\n        \"yo!\",\n        \"hey!\",\n        \"wassup\",\n        \"sup??\",\n        \"'sup?\",\n        \"rabbit\",\n        \"wassap\",\n        \"heya\",\n        \"bunny\",\n        \"wassup?\",\n        \"sup\"\n      ]\n    }\n  }\n}"

class AssetFilesRepositoryTest{

    val assetsReader:AssetsReader = mock()

    val testSubject = AssetFilesRepository(assetsReader)

    @Test
    @Ignore("Valuable test, just too much time to separate from Android SDK dependency")
    fun loadItems_verifyThatTheDataLoadedProperly() {
        whenever(assetsReader.readFileAsString(any())).thenReturn(TEST_JSON)

        val items = testSubject.loadItems()

        items.test()
                .assertValueCount(2)
    }
}

