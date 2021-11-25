package com.hackathon.ahreview.ui.findStore

import androidx.lifecycle.Observer
import com.hackathon.ahreview.databinding.ActivityFindStoreBinding
import com.hackathon.ahreview.ui.base.BaseActivity
import org.koin.androidx.viewmodel.ext.android.viewModel

class FindStoreActivity : BaseActivity<ActivityFindStoreBinding, FindStoreViewModel>() {
    override val viewModel: FindStoreViewModel by viewModel()

    override fun observerViewModel() {
        with(viewModel){
            backBtn.observe(this@FindStoreActivity, Observer {
                finish()
            })
        }
    }

}