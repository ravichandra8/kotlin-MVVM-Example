package com.ravi.kotlin_mvvm_with_retrofit.ui.photos.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ravi.kotlin_mvvm_with_retrofit.databinding.PhotoItemBinding
import com.ravi.kotlin_mvvm_with_retrofit.ui.photos.SelectedCallBack
import com.ravi.kotlin_mvvm_with_retrofit.ui.photos.model.PhotoResponse
import okhttp3.internal.notify


class PhotoAdapter(private var photoList: MutableList<PhotoResponse>,private val selectedCallBack: SelectedCallBack) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

     var photoList1 : MutableList<PhotoResponse> = photoList

    fun setData(photoList1 : MutableList<PhotoResponse>){
        this.photoList1=photoList1
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val itemBinding: PhotoItemBinding = PhotoItemBinding
                .inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewItem(itemBinding,selectedCallBack)
    }

    override fun getItemCount(): Int {
        return photoList.size
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is ViewItem) {
            holder.bind(position,photoList1)
        }
    }


    class ViewItem(itemBinding: PhotoItemBinding,private val selectedCallBack: SelectedCallBack) : RecyclerView.ViewHolder(itemBinding.root) {

        private val itemBinding: PhotoItemBinding = itemBinding

        fun bind(position: Int, photoList1: MutableList<PhotoResponse>) {

            var photoRes: PhotoResponse = photoList1[position]
            itemBinding.title.text = photoList1[position].title

            itemBinding.checkbox.isChecked = photoList1[position].isChecked
            Log.d("pos","pos"+position+"-->"+photoList1[position].isChecked)

            itemBinding.checkbox.id = position
            itemBinding.checkbox.setOnClickListener {
                Log.d("context", ""+ it.id)
                selectedCallBack.onCheckboxSelected(it.id, itemBinding.checkbox.isChecked)

            }
        }

    }

}