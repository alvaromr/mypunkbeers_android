package com.alvaromr.mypunkbeers.domain.repository

import com.alvaromr.mypunkbeers.data.local.BeersLocalDataSource
import com.alvaromr.mypunkbeers.data.remote.BeersRemoteDataSource
import com.alvaromr.mypunkbeers.domain.model.BeerList
import com.alvaromr.mypunkbeers.fakeBeer
import com.alvaromr.mypunkbeers.fakeBeers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.mockito.kotlin.*

@ExperimentalCoroutinesApi
class BeerRepositoryTest {
    private lateinit var repository: BeerRepository
    private lateinit var beersRemoteDataSource: BeersRemoteDataSource
    private lateinit var beersLocalDataSource: BeersLocalDataSource


    @Before
    fun setUp() {
        beersRemoteDataSource = mock()
        beersLocalDataSource = mock()
        repository = BeerRepository(beersRemoteDataSource, beersLocalDataSource)
    }

    @Test
    fun testSearchByName() = runBlockingTest {
        val fakeList = BeerList(fakeBeers, true)
        whenever(beersRemoteDataSource.searchByName(any(), any())).thenReturn(fakeList)

        val res = repository.searchByName("foo", 1).toList()
        assertEquals(fakeList, res[0])
        assertEquals(1, res.size)
        verify(beersLocalDataSource, times(1)).save(fakeList.beers)
    }

    @Test
    fun testGetById() = runBlockingTest {
        whenever(beersLocalDataSource.getById(any())).thenReturn(fakeBeer)

        val res = repository.getById(fakeBeer.id).toList()
        assertEquals(fakeBeer, res[0])
        assertEquals(1, res.size)
    }
}