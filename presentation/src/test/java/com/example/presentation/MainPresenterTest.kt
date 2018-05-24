package com.example.presentation

import com.nhaarman.mockito_kotlin.any
import com.nhaarman.mockito_kotlin.capture
import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.never
import com.nhaarman.mockito_kotlin.verify
import com.nhaarman.mockito_kotlin.whenever
import io.reactivex.Observable
import io.reactivex.android.plugins.RxAndroidPlugins
import io.reactivex.plugins.RxJavaPlugins
import io.reactivex.schedulers.Schedulers
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule
import org.junit.runner.Description
import org.junit.runners.model.Statement
import org.mockito.ArgumentCaptor
import org.mockito.Captor
import org.mockito.MockitoAnnotations
import org.mockito.junit.MockitoJUnit


class MainPresenterTest {

    val view: Contract.View = mock()

    val repository: DataStore = mock()

    @Rule
    @JvmField
    val rule = MockitoJUnit.rule()!!

    @Rule
    @JvmField
    var testSchedulerRule = RxImmediateSchedulerRule()

    @Captor
    lateinit var captor: ArgumentCaptor<List<Item>>

    val testSubject = MainPresenter(view, repository)

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
    }

    @Test
    fun unBind_verifyViewIsNull() {

        testSubject.unbind()

        assertNull(testSubject.view)
    }

    @Test
    fun textChanged_whenAllItemsInWordsMatchTags_verifyNoItemsFiltered() {
        val list = listOf(
                Item("id1", "google.com", tags = listOf("joyful")),
                Item("id2", "google.com", tags = listOf("ghost", "joyful"))
        )
        testSubject.currentItems = list
        testSubject.textChanged("joyful")
        verify(view).displayItems(capture(captor))
        assertEquals(captor.value, list)
    }

    @Test
    fun textChanged_whenNoItemsInWordsMatchTags_verifyAllItemsFiltered() {
        val list = listOf(
                Item("id1", "google.com", tags = listOf("joyful")),
                Item("id2", "google.com", tags = listOf("ghost"))
        )
        testSubject.currentItems = list
        testSubject.textChanged("bun")
        verify(view).displayItems(capture(captor))
        assertEquals(captor.value, emptyList<Item>())
    }

    @Test
    fun textChanged_whenTextIsEmpty_verifyNoItemsFiltered() {
        val list = listOf(
                Item("id1", "google.com", tags = listOf("joyful")),
                Item("id2", "google.com", tags = listOf("ghost"))
        )
        testSubject.currentItems = list
        testSubject.textChanged("")
        verify(view).displayItems(capture(captor))
        assertEquals(captor.value, list)
    }

    @Test
    fun textChanged_whenTextIsBlackString_verifyNoItemsFiltered() {
        val list = listOf(
                Item("id1", "google.com", tags = listOf("joyful")),
                Item("id2", "google.com", tags = listOf("ghost"))
        )
        testSubject.currentItems = list
        testSubject.textChanged("   ")
        verify(view).displayItems(capture(captor))
        assertEquals(captor.value, list)
    }

    @Test
    fun loadData_whenNoTextHasAlreadyBeenSearched_verifyAppropriateItemsAreNotFiltered() {
        val list = listOf(
                Item("id1", "google.com", tags = listOf("joyful")),
                Item("id2", "google.com", tags = listOf("ghost"))
        )

        whenever(repository.loadItems()).thenReturn(Observable.just(list))

        testSubject.loadData()
        verify(view).displayItems(capture(captor))
        assertEquals(captor.value, list)
    }

    @Test
    fun loadData_whenTextGhostHasAlreadyBeenSearched_verifyId1IsFilteredOut() {
        val ghost = Item("id2", "google.com", tags = listOf("ghost"))
        val joyful = Item("id1", "google.com", tags = listOf("joyful"))
        val list = listOf(
                joyful,
                ghost
        )

        whenever(repository.loadItems()).thenReturn(Observable.just(list))

        testSubject.loadData("ghost")
        verify(view).displayItems(capture(captor))
        assertEquals(captor.value, listOf(ghost))
    }

    /**
     * This class forces all of our rxjava code to run on the same code so that it can be easily
     * unit tested.
     */
    class RxImmediateSchedulerRule : TestRule {

        override fun apply(base: Statement, d: Description): Statement {
            return object : Statement() {
                @Throws(Throwable::class)
                override fun evaluate() {
                    RxJavaPlugins.setIoSchedulerHandler { Schedulers.trampoline() }
                    RxJavaPlugins.setComputationSchedulerHandler { Schedulers.trampoline() }
                    RxJavaPlugins.setNewThreadSchedulerHandler { Schedulers.trampoline() }
                    RxAndroidPlugins.setInitMainThreadSchedulerHandler { Schedulers.trampoline() }

                    try {
                        base.evaluate()
                    } finally {
                        RxJavaPlugins.reset()
                        RxAndroidPlugins.reset()
                    }
                }
            }
        }

    }
}