package com.example.data

import android.support.test.InstrumentationRegistry
import android.support.test.runner.AndroidJUnit4
import org.junit.Assert.assertEquals
import org.junit.Ignore
import org.junit.Test
import org.junit.runner.RunWith

const val TEST_JSON = "{\n  \"contents\": {\n    \"1CTQNP6\": {\n      \"content_id\": \"1CTQNP6\",\n      \"assets\": [\n        {\n          \"asset_id\": \"bd0d471a\",\n          \"url\": \"https://cdn.emogi.com/cxp/1CT/1CTQNP6-thumb.gif\",\n          \"file_extension\": \"gif\",\n          \"size\": \"thumb\"\n        },\n        {\n          \"asset_id\": \"04d114e8\",\n          \"url\": \"https://cdn.emogi.com/cxp/1CT/1CTQNP6-full.gif\",\n          \"file_extension\": \"gif\",\n          \"size\": \"full\"\n        }\n      ],\n      \"tags\": [\n        \"omw!\",\n        \"coming?\",\n        \"go!\",\n        \"ready?\",\n        \"omw\",\n        \"ready\",\n        \"hurry\",\n        \"pixel\"\n      ]\n    },\n    \"47DQJ4\": {\n      \"content_id\": \"47DQJ4\",\n      \"assets\": [\n        {\n          \"asset_id\": \"a8a65466\",\n          \"url\": \"https://cdn.emogi.com/cxp/47D/47DQJ4-thumb.gif\",\n          \"file_extension\": \"gif\",\n          \"size\": \"thumb\"\n        },\n        {\n          \"asset_id\": \"66d85462\",\n          \"url\": \"https://cdn.emogi.com/cxp/47D/47DQJ4-full.gif\",\n          \"file_extension\": \"gif\",\n          \"size\": \"full\"\n        }\n      ],\n      \"tags\": [\n        \"yoo\",\n        \"'sup\",\n        \"yooo\",\n        \"heyy\",\n        \"heyyy\",\n        \"sup?\",\n        \"wassap?\",\n        \"hey\",\n        \"yo!\",\n        \"hey!\",\n        \"wassup\",\n        \"sup??\",\n        \"'sup?\",\n        \"rabbit\",\n        \"wassap\",\n        \"heya\",\n        \"bunny\",\n        \"wassup?\",\n        \"sup\"\n      ]\n    }\n  }\n}"

/**
 * Instrumented test, which will execute on an Android device.
 *
 * @see [Testing documentation](http://d.android.com/tools/testing)
 */
@RunWith(AndroidJUnit4::class)
class ExampleInstrumentedTest {
    @Test
    fun useAppContext() {
        // Context of the app under test.
        val appContext = InstrumentationRegistry.getTargetContext()

        assertEquals("com.example.data.test", appContext.packageName)
    }

    private val testSubject = AssetFilesRepository(null)

    @Test
    @Ignore("Fix Me")
    fun loadItems_verifyThatTheDataLoadedProperly() {
        testSubject.setJson(TEST_JSON)

        val items = testSubject.loadItems()

        items.test()
                .assertValueCount(2)
    }
}
