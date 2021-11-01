package com.alvaromr.mypunkbeers.domain.interactor

import com.alvaromr.mypunkbeers.domain.model.BeerList
import com.alvaromr.mypunkbeers.domain.model.Resource
import com.alvaromr.mypunkbeers.domain.repository.BeerRepository
import com.alvaromr.mypunkbeers.fakeBeers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.mockito.kotlin.any
import org.mockito.kotlin.mock
import org.mockito.kotlin.whenever

@ExperimentalCoroutinesApi
class SearchBeersTest {
    private lateinit var useCase: SearchBeers
    private lateinit var repository: BeerRepository


    @Before
    fun setUp() {
        repository = mock()
        useCase = SearchBeers(repository)
    }

    @Test
    fun testList() = runBlockingTest {
        val fakeList = BeerList(fakeBeers, true)
        whenever(repository.searchByName(any(), any())).thenReturn(flow {
            emit(fakeList)
        })

        val res = mutableListOf<Resource<out BeerList>>()
        useCase("foo", 1) { res.add(it) }
        assertEquals(Resource.Loading, res[0])
        assertEquals(Resource.Success(fakeList), res[1])
        assertEquals(2, res.size)
    }

    @Test
    fun testError() = runBlockingTest {
        whenever(repository.searchByName(any(), any())).thenReturn(flow {
            throw RuntimeException()
        })

        val res = mutableListOf<Resource<out BeerList>>()
        useCase("foo", 1) { res.add(it) }
        assertEquals(Resource.Loading, res[0])
        assertEquals(Resource.Error("search error"), res[1])
        assertEquals(2, res.size)
    }
}