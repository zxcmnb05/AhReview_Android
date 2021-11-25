package com.hackathon.ahreview.ui.detailStore

import android.content.Context
import android.os.Build
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.hackathon.ahreview.R
import com.hackathon.ahreview.data.model.response.Store
import com.hackathon.ahreview.data.model.response.StoreReview
import com.hackathon.ahreview.databinding.DetailReviewItemBinding
import com.hackathon.ahreview.utils.NaverAPITTS

class StoreReviewAdapter: RecyclerView.Adapter<StoreReviewAdapter.ViewHolder>() {

    lateinit var binding: DetailReviewItemBinding
    lateinit var items: List<StoreReview>
    lateinit var context: Context
    lateinit var store: Store

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        binding = DataBindingUtil.inflate(LayoutInflater.from(parent.context),
            R.layout.detail_review_item,
            parent,
            false)

        return ViewHolder(binding)
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(items[position], context)
    }

    override fun getItemCount(): Int {
        return items.size
    }
    inner class ViewHolder(binding: DetailReviewItemBinding) : RecyclerView.ViewHolder(binding.root) {
        @RequiresApi(Build.VERSION_CODES.O)
        fun bind(storeReview: StoreReview, context: Context) {
            Glide.with(context)
                .load(storeReview.urlList[0])
                .into(binding.userImage)
            if(storeReview.username == null){
                binding.reviewDName.text = "익명"
            } else {
                binding.reviewDName.text = storeReview.username
            }
            binding.reviewDContent.text = storeReview.review
            binding.imageBtnSpeaker2.setOnClickListener {
                NaverAPITTS().getTTS(storeReview.review)
            }

            binding.replyImage
            Glide.with(context)
                .load(store.url)
                .into(binding.replyImage)

            binding.replyName.text = store.name
            binding.replyContent.text = storeReview.answer
            binding.imageBtnSpeaker3.setOnClickListener {
                NaverAPITTS().getTTS(storeReview.answer)
            }
        }
    }

    fun starShow(cnt: Int){
        when(cnt){
            0->{
                binding.detailImageStar1.setImageResource(R.drawable.ic_off_star)
                binding.detailImageStar2.setImageResource(R.drawable.ic_off_star)
                binding.detailImageStar3.setImageResource(R.drawable.ic_off_star)
                binding.detailImageStar4.setImageResource(R.drawable.ic_off_star)
                binding.detailImageStar5.setImageResource(R.drawable.ic_off_star)
            }
            1->{
                binding.detailImageStar1.setImageResource(R.drawable.ic_on_star)
                binding.detailImageStar2.setImageResource(R.drawable.ic_off_star)
                binding.detailImageStar3.setImageResource(R.drawable.ic_off_star)
                binding.detailImageStar4.setImageResource(R.drawable.ic_off_star)
                binding.detailImageStar5.setImageResource(R.drawable.ic_off_star)
            }
            2->{
                binding.detailImageStar1.setImageResource(R.drawable.ic_on_star)
                binding.detailImageStar2.setImageResource(R.drawable.ic_on_star)
                binding.detailImageStar3.setImageResource(R.drawable.ic_off_star)
                binding.detailImageStar4.setImageResource(R.drawable.ic_off_star)
                binding.detailImageStar5.setImageResource(R.drawable.ic_off_star)
            }
            3->{
                binding.detailImageStar1.setImageResource(R.drawable.ic_on_star)
                binding.detailImageStar2.setImageResource(R.drawable.ic_on_star)
                binding.detailImageStar3.setImageResource(R.drawable.ic_on_star)
                binding.detailImageStar4.setImageResource(R.drawable.ic_off_star)
                binding.detailImageStar5.setImageResource(R.drawable.ic_off_star)
            }
            4->{
                binding.detailImageStar1.setImageResource(R.drawable.ic_on_star)
                binding.detailImageStar2.setImageResource(R.drawable.ic_on_star)
                binding.detailImageStar3.setImageResource(R.drawable.ic_on_star)
                binding.detailImageStar4.setImageResource(R.drawable.ic_on_star)
                binding.detailImageStar5.setImageResource(R.drawable.ic_off_star)
            }
            5->{
                binding.detailImageStar1.setImageResource(R.drawable.ic_on_star)
                binding.detailImageStar2.setImageResource(R.drawable.ic_on_star)
                binding.detailImageStar3.setImageResource(R.drawable.ic_on_star)
                binding.detailImageStar4.setImageResource(R.drawable.ic_on_star)
                binding.detailImageStar5.setImageResource(R.drawable.ic_on_star)
            }
        }
    }
}