package com.hackathon.ahreview.ui.detailStore

import android.content.Intent
import androidx.lifecycle.Observer
import com.bumptech.glide.Glide
import com.hackathon.ahreview.data.model.response.Store
import com.hackathon.ahreview.databinding.ActivityDetailStoreBinding
import com.hackathon.ahreview.ui.base.BaseActivity
import com.hackathon.ahreview.ui.writeReview.WriteReviewActivity
import org.koin.androidx.viewmodel.ext.android.viewModel

class DetailStoreActivity : BaseActivity<ActivityDetailStoreBinding, DetailStoreViewModel>() {
    override val viewModel: DetailStoreViewModel by viewModel()

    lateinit var store: Store

    override fun observerViewModel() {
        getStoreData()
        with(viewModel) {
            onClickWriteReview.observe(this@DetailStoreActivity, Observer {
                val intent = Intent(this@DetailStoreActivity, WriteReviewActivity::class.java)
                startActivity(intent)
            })
            backBtn.observe(this@DetailStoreActivity, Observer {
                finish()
            })
        }
    }

    private fun getStoreData() {
        val name = intent.getStringExtra("storeName")
        val address = intent.getStringExtra("address")
        val ammount = intent.getIntExtra("ammount", 0)
        val score = intent.getIntExtra("score", 0)
        val url = intent.getStringExtra("url")

        mBinding.tvStoreDetailName.text = name
        mBinding.tvReviewNumber.text = "${ammount}개"
        mBinding.tvStarAverage.text = "${score}.0"
        mBinding.tvLocation1.text = address

        Glide.with(this)
            .load(url)
            .into(mBinding.storeImage)

        store = Store(name, address, ammount, score, url)
    }
}