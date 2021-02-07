package com.ravi.kotlin_mvvm_with_retrofit.ui.photos

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.ViewModelProviders
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.ravi.kotlin_mvvm_with_retrofit.databinding.ActivityMainBinding
import com.ravi.kotlin_mvvm_with_retrofit.repository.MediaRepository
import com.ravi.kotlin_mvvm_with_retrofit.ui.ViewModelproviderFactory
import com.ravi.kotlin_mvvm_with_retrofit.ui.photos.adapter.PhotoAdapter
import com.ravi.kotlin_mvvm_with_retrofit.ui.photos.viewmodel.PhotoViewModel
import com.ravi.kotlin_mvvm_with_retrofit.utils.Resource
import kotlinx.coroutines.flow.collect

class MainActivity : AppCompatActivity() {
    private lateinit var photoViewModel: PhotoViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val activityMainBinding:ActivityMainBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(activityMainBinding.root)

        val mediaRepository:MediaRepository = MediaRepository()
        val viewModelFactory = ViewModelproviderFactory(PhotoViewModel(mediaRepository))

         photoViewModel = ViewModelProviders.of(this, viewModelFactory).get(PhotoViewModel::class.java)

        val linearLayoutManager = LinearLayoutManager(this)
        activityMainBinding.photoRecyclerView.layoutManager = linearLayoutManager

        activityMainBinding.photoRecyclerView.addItemDecoration(
            DividerItemDecoration(
                this,
                LinearLayoutManager.HORIZONTAL
            )
        )

        lifecycleScope.launchWhenStarted {
            photoViewModel.getPhotoInformation()

            photoViewModel.photosMutableLiveData.collect {
                when(it){
                    is Resource.Success -> {
                            val recyclerViewAdapter: PhotoAdapter = PhotoAdapter(it.photoList)
                            activityMainBinding.photoRecyclerView.adapter = recyclerViewAdapter
                    }
                }
            }
        }


    }
}