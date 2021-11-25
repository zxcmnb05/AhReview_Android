package com.hackathon.ahreview.ui.main

import androidx.lifecycle.Observer
import com.bumptech.glide.Glide
import com.hackathon.ahreview.data.util.SharedPreferenceManager
import com.hackathon.ahreview.databinding.ActivityMainBinding
import com.hackathon.ahreview.ui.base.BaseActivity
import com.hackathon.ahreview.ui.findStore.FindStoreActivity
import kr.hs.dgsw.smartschool.morammoram.presentation.extension.shortToast
import kr.hs.dgsw.smartschool.morammoram.presentation.extension.startActivity
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : BaseActivity<ActivityMainBinding, MainViewModel>() {
    override val viewModel: MainViewModel by viewModel()

    override fun observerViewModel() {
        userInit()

        with(viewModel) {
            findStoreBtn.observe(this@MainActivity, Observer {
                this@MainActivity.startActivity(FindStoreActivity::class.java)
            })

            getUserInfoSuccess.observe(this@MainActivity, Observer {
                mBinding.userName.text = it.name
                Glide.with(this@MainActivity)
                    .load(mBinding.userImage)
                    .into(mBinding.userImage)

                val mainAdapter = MainAdapter()
                mainAdapter.context = this@MainActivity
                mainAdapter.items = it.reviewInfoList

                mBinding.myReviewRecycler.adapter = mainAdapter
            })

            getUserInfoError.observe(this@MainActivity, Observer {
                shortToast("유저 정보를 받아오지 못했습니다")
            })
        }
    }

    private fun userInit() {
        val token = SharedPreferenceManager.getToken(applicationContext)

        if (token != null) {
            viewModel.getUserInfo("Bearer $token")
        } else {
            shortToast("토큰이 존재하지 않습니다.")
        }
    }
}