package com.ravi.kotlin_mvvm_with_retrofit.ui.photos.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ravi.kotlin_mvvm_with_retrofit.databinding.PhotoItemBinding
import com.ravi.kotlin_mvvm_with_retrofit.ui.photos.model.PhotoResponse


class PhotoAdapter(private var photoList: List<PhotoResponse>) : RecyclerView.Adapter<RecyclerView.ViewHolder>(){



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val itemBinding:PhotoItemBinding = PhotoItemBinding
            .inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewItem(itemBinding,photoList)
    }

    override fun getItemCount(): Int {
       return photoList.size
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is ViewItem) {
            holder.bind(position)
        }
    }


    class ViewItem(itemBinding: PhotoItemBinding,private val data: List<PhotoResponse>) : RecyclerView.ViewHolder(itemBinding.root){

        private val itemBinding: PhotoItemBinding = itemBinding

       fun bind(position: Int) {
        itemBinding.title.text = data[position].title
      }

    }

}