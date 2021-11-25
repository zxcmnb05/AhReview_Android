package com.hackathon.ahreview.ui.writeReview

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.hackathon.ahreview.R
import com.hackathon.ahreview.databinding.ItemImageWriteReviewBinding
import java.io.File

class WriteReviewImageAdapter:
    RecyclerView.Adapter<WriteReviewImageAdapter.WritePostImageViewHolder>() {

    val imageList = MutableLiveData<ArrayList<File>>(arrayListOf())

    inner class WritePostImageViewHolder(val binding: ItemImageWriteReviewBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(item: File?) {
            Glide.with(binding.root)
                .load(item)
                .into(binding.ivWritePostItem)

            binding.btnDeleteImageItem.setOnClickListener {
                imageList.value?.remove(item)
                notifyListChanged()
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WritePostImageViewHolder {
        return WritePostImageViewHolder(
            DataBindingUtil.inflate(
                LayoutInflater.from(parent.context),
                R.layout.item_image_write_review,
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: WritePostImageViewHolder, position: Int) {
        holder.bind(imageList.value?.get(position))
    }

    override fun getItemCount(): Int {
        return imageList.value?.size ?: 0
    }

    fun notifyListChanged() {
        imageList.value = imageList.value
    }
}