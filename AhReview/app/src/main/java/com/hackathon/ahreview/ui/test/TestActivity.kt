package com.hackathon.ahreview.ui.test

import androidx.lifecycle.Observer
import com.hackathon.ahreview.R
import com.hackathon.ahreview.databinding.ActivityTestBinding
import com.hackathon.ahreview.ui.base.BaseActivity
import org.koin.androidx.viewmodel.ext.android.viewModel

class TestActivity : BaseActivity<ActivityTestBinding, TestViewModel>() {
    override val viewModel: TestViewModel by viewModel()

    override fun observerViewModel() {
        with(viewModel) {
            btn.observe(this@TestActivity, Observer {
                if (input.value != null) {
                    viewModel.check(
                        resources.getString(R.string.clova_client_id),
                        resources.getString(R.string.clova_client_secret),
                        resources.getString(R.string.json_type),
                        viewModel.input.value!!
                    )
                }
            })
        }
    }
}