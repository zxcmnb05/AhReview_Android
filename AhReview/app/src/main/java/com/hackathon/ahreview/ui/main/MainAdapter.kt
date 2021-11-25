package com.hackathon.ahreview.ui.main

import android.content.Context
import android.os.Build
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.hackathon.ahreview.R
import com.hackathon.ahreview.data.model.response.ReviewInfo
import com.hackathon.ahreview.databinding.MyReviewItemBinding
import com.hackathon.ahreview.utils.NaverAPITTS

class MainAdapter: RecyclerView.Adapter<MainAdapter.ViewHolder>() {
    lateinit var binding: MyReviewItemBinding
    lateinit var items: List<ReviewInfo>
    lateinit var context: Context

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainAdapter.ViewHolder {
        binding = DataBindingUtil.inflate(LayoutInflater.from(parent.context),
            R.layout.my_review_item,
            parent,
            false)

        return ViewHolder(binding)
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onBindViewHolder(holder: MainAdapter.ViewHolder, position: Int) {
        holder.bind(context, items[position])
    }

    override fun getItemCount(): Int {
        return items.size
    }

    inner class ViewHolder(binding: MyReviewItemBinding) : RecyclerView.ViewHolder(binding.root) {
        @RequiresApi(Build.VERSION_CODES.O)
        fun bind(context: Context, item: ReviewInfo){
            //binding.tvStoreName.text = item.
            setStar(item.starScore)
            binding.tvContent.text = item.review

            binding.imageBtnSpeaker1.setOnClickListener {
                NaverAPITTS().getTTS(item.review)
            }
        }
    }

    fun setStar(score: Int){
        when(score){
            0->{
                binding.starImage1.setImageResource(R.drawable.ic_off_star)
                binding.starImage2.setImageResource(R.drawable.ic_off_star)
                binding.starImage3.setImageResource(R.drawable.ic_off_star)
                binding.starImage4.setImageResource(R.drawable.ic_off_star)
                binding.starImage5.setImageResource(R.drawable.ic_off_star)
            }
            1->{
                binding.starImage1.setImageResource(R.drawable.ic_on_star)
                binding.starImage2.setImageResource(R.drawable.ic_off_star)
                binding.starImage3.setImageResource(R.drawable.ic_off_star)
                binding.starImage4.setImageResource(R.drawable.ic_off_star)
                binding.starImage5.setImageResource(R.drawable.ic_off_star)
            }
            2->{
                binding.starImage1.setImageResource(R.drawable.ic_on_star)
                binding.starImage2.setImageResource(R.drawable.ic_on_star)
                binding.starImage3.setImageResource(R.drawable.ic_off_star)
                binding.starImage4.setImageResource(R.drawable.ic_off_star)
                binding.starImage5.setImageResource(R.drawable.ic_off_star)
            }
            3->{
                binding.starImage1.setImageResource(R.drawable.ic_on_star)
                binding.starImage2.setImageResource(R.drawable.ic_on_star)
                binding.starImage3.setImageResource(R.drawable.ic_on_star)
                binding.starImage4.setImageResource(R.drawable.ic_off_star)
                binding.starImage5.setImageResource(R.drawable.ic_off_star)
            }
            4->{
                binding.starImage1.setImageResource(R.drawable.ic_on_star)
                binding.starImage2.setImageResource(R.drawable.ic_on_star)
                binding.starImage3.setImageResource(R.drawable.ic_on_star)
                binding.starImage4.setImageResource(R.drawable.ic_on_star)
                binding.starImage5.setImageResource(R.drawable.ic_off_star)
            }
            5->{
                binding.starImage1.setImageResource(R.drawable.ic_on_star)
                binding.starImage2.setImageResource(R.drawable.ic_on_star)
                binding.starImage3.setImageResource(R.drawable.ic_on_star)
                binding.starImage4.setImageResource(R.drawable.ic_on_star)
                binding.starImage5.setImageResource(R.drawable.ic_on_star)
            }
        }
    }
}