package com.hackathon.ahreview.ui.findStore

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.hackathon.ahreview.R
import com.hackathon.ahreview.data.model.response.Store
import com.hackathon.ahreview.databinding.StoreItemBinding
import com.hackathon.ahreview.ui.detailStore.DetailStoreActivity

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
            binding.storeTvAverage.text = "${store.starScore.toString()}.0"
            binding.storeTvStoreReview.text = "리뷰 ${store.reviewAmount.toString()}개"
            binding.tvLocation.text = store.address
            binding.storeTvStoreName.text = store.name

            Glide.with(context)
                .load(store.url)
                .into(binding.storeImage)

            binding.storeItemView.setOnClickListener {
                val intent = Intent(context.applicationContext, DetailStoreActivity::class.java)
                intent.putExtra("storeName", store.name)
                intent.putExtra("address", store.address)
                intent.putExtra("ammount", store.reviewAmount)
                intent.putExtra("score", store.starScore)
                intent.putExtra("url", store.url)

                context.startActivity(intent)
            }
        }
    }

}