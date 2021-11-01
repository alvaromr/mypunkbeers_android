package com.alvaromr.mypunkbeers.domain.interactor

import com.alvaromr.mypunkbeers.domain.model.Beer
import com.alvaromr.mypunkbeers.domain.model.Resource
import com.alvaromr.mypunkbeers.domain.repository.BeerRepository
import com.alvaromr.mypunkbeers.fakeBeer
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
class GetBeerByIdTest {
    private lateinit var useCase: GetBeerById
    private lateinit var repository: BeerRepository


    @Before
    fun setUp() {
        repository = mock()
        useCase = GetBeerById(repository)
    }

    @Test
    fun testNotFound() = runBlockingTest {
        whenever(repository.getById(any())).thenReturn(flow {
            emit(null)
        })

        val res = mutableListOf<Resource<out Beer?>>()
        useCase(1) { res.add(it) }
        assertEquals(Resource.Loading, res[0])
        assertEquals(Resource.Success(null), res[1])
        assertEquals(2, res.size)
    }

    @Test
    fun testFound() = runBlockingTest {
        whenever(repository.getById(any())).thenReturn(flow {
            emit(fakeBeer)
        })

        val res = mutableListOf<Resource<out Beer?>>()
        useCase(fakeBeer.id) { resource ->
            res.add(resource)
        }
        assertEquals(Resource.Loading, res[0])
        assertEquals(Resource.Success(fakeBeer), res[1])
        assertEquals(2, res.size)
    }

    @Test
    fun testError() = runBlockingTest {
        whenever(repository.getById(any())).thenReturn(flow {
            throw RuntimeException()
        })

        val res = mutableListOf<Resource<out Beer?>>()
        useCase(1) { resource ->
            res.add(resource)
        }
        assertEquals(Resource.Loading, res[0])
        assertEquals(Resource.Error("not found error"), res[1])
        assertEquals(2, res.size)
    }
}