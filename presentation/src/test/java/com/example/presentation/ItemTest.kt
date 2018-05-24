package com.example.presentation

import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotEquals
import org.junit.Test

class ItemTest{

    private val testSubject = Item("Andre", "google.com")

    @Test
    fun equals_objectsHaveSameId_returnTrue() {
        val other = Item("Andre")
        assertEquals(other, testSubject)
    }

    @Test
    fun equals_objectsHaveDifferentId_returnFalse() {
        val other = Item("Emogi")
        assertNotEquals(other, testSubject)
    }

    @Test
    fun equals_otherObjectIsNotInstanceOfItemHaveSameId_returnFalse() {
        val other = ""
        assertNotEquals(other, testSubject)
    }

    @Test
    fun hashcode_verifyItDelegatesToContentIdHashCode() {
        val actual = testSubject.hashCode()

        assertEquals("Andre".hashCode(), actual)
    }
}