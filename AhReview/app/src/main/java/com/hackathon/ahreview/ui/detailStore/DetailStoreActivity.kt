package com.hackathon.ahreview.ui.detailStore

import androidx.lifecycle.Observer
import com.hackathon.ahreview.databinding.ActivityDetailStoreBinding
import com.hackathon.ahreview.ui.base.BaseActivity
import org.koin.androidx.viewmodel.ext.android.viewModel

class DetailStoreActivity : BaseActivity<ActivityDetailStoreBinding, DetailStoreViewModel>() {
    override val viewModel: DetailStoreViewModel by viewModel()

    override fun observerViewModel() {
        with(viewModel){
            backBtn.observe(this@DetailStoreActivity, Observer {
                finish()
            })
        }
    }
}