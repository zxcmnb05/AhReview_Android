package com.hackathon.ahreview.ui.detailStore

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.hackathon.ahreview.R
import com.hackathon.ahreview.data.model.response.Store
import com.hackathon.ahreview.databinding.DetailReviewItemBinding

class StoreReviewAdapter: RecyclerView.Adapter<StoreReviewAdapter.ViewHolder>() {

    lateinit var binding: DetailReviewItemBinding
    lateinit var items: List<Store>
    lateinit var context: Context

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        binding = DataBindingUtil.inflate(LayoutInflater.from(parent.context),
            R.layout.detail_review_item,
            parent,
            false)

        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind()
    }

    override fun getItemCount(): Int {
        return items.size
    }
    inner class ViewHolder(binding: DetailReviewItemBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(){

        }
    }

}