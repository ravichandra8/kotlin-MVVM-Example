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
import com.ravi.kotlin_mvvm_with_retrofit.ui.photos.model.PhotoResponse
import com.ravi.kotlin_mvvm_with_retrofit.ui.photos.viewmodel.PhotoViewModel
import com.ravi.kotlin_mvvm_with_retrofit.utils.Resource
import kotlinx.coroutines.flow.collect

class MainActivity : AppCompatActivity(),SelectedCallBack {
    private lateinit var photoViewModel: PhotoViewModel
    private lateinit var photoList: MutableList<PhotoResponse>
    private lateinit var recyclerViewAdapter: PhotoAdapter
    private lateinit var activityMainBinding:ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
         activityMainBinding = ActivityMainBinding.inflate(layoutInflater)
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
                            photoList = it.photoList;
                             recyclerViewAdapter = PhotoAdapter(it.photoList,this@MainActivity)
                            activityMainBinding.photoRecyclerView.adapter = recyclerViewAdapter

                    }
                }
            }
        }


    }

    override fun onCheckboxSelected(pos: Int, checked: Boolean) {
        photoList[pos].isChecked = checked
        recyclerViewAdapter.setData(photoList)

    }
}