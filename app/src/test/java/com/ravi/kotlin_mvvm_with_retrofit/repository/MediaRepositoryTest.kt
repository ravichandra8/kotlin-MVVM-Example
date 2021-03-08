package com.ravi.kotlin_mvvm_with_retrofit.repository

import com.ravi.kotlin_mvvm_with_retrofit.MediaService
import com.ravi.kotlin_mvvm_with_retrofit.ui.photos.model.PhotoResponse
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Test

import org.junit.Assert.*
import org.junit.Before
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.doReturn
import org.mockito.junit.MockitoJUnitRunner
import retrofit2.Response

@RunWith(MockitoJUnitRunner::class)
class MediaRepositoryTest {

    @Mock
    private lateinit var photoResponse: PhotoResponse

    @Mock
    private lateinit var mediaService: MediaService

    private lateinit var mediaRepository:MediaRepository
    @Before
    fun setup(){
        mediaRepository = MediaRepository(mediaService)
    }
    @Test
    fun getPhotoApi() = runBlockingTest{
        
        val photoList = mutableListOf<PhotoResponse>()
        photoList.add(photoResponse)


        doReturn(photoList).`when`(mediaService).getPhotos()

        assertEquals(photoList,mediaRepository.getPhotoApi())
    }
}