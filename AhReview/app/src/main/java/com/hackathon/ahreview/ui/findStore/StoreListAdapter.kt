package com.hackathon.ahreview.ui.findStore

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.hackathon.ahreview.R
import com.hackathon.ahreview.data.model.response.Store
import com.hackathon.ahreview.databinding.StoreItemBinding

class StoreListAdapter : RecyclerView.Adapter<StoreListAdapter.ViewHolder>() {
    lateinit var binding: StoreItemBinding
    lateinit var items: List<Store>
    lateinit var context: Context

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StoreListAdapter.ViewHolder {
        binding = DataBindingUtil.inflate(LayoutInflater.from(parent.context),
            R.layout.store_item,
            parent,
            false)

        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: StoreListAdapter.ViewHolder, position: Int) {
        holder.bind(items[position], context)
    }

    override fun getItemCount(): Int {
        return items.size
    }

    inner class ViewHolder(binding: StoreItemBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(store: Store, context: Context) {
            binding.storeTvAverage.text = store.starScore.toString()
            binding.storeTvStoreReview.text = store.reviewAmount.toString()
            binding.tvLocation.text = store.address
        }
    }

}