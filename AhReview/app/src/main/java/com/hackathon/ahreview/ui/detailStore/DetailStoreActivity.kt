package com.hackathon.ahreview.ui.detailStore

import android.content.Intent
import com.hackathon.ahreview.databinding.ActivityDetailStoreBinding
import com.hackathon.ahreview.ui.base.BaseActivity
import com.hackathon.ahreview.ui.writeReview.WriteReviewActivity
import org.koin.androidx.viewmodel.ext.android.viewModel

class DetailStoreActivity : BaseActivity<ActivityDetailStoreBinding, DetailStoreViewModel>() {
    override val viewModel: DetailStoreViewModel by viewModel()

    override fun observerViewModel() {


        with(viewModel){
            onClickWriteReview.observe(this@DetailStoreActivity, {
                val intent = Intent(this@DetailStoreActivity, WriteReviewActivity::class.java)
                startActivity(intent)
            })
        }
    }
}