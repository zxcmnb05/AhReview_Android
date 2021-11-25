package com.hackathon.ahreview.ui.findStore

import androidx.lifecycle.Observer
import com.hackathon.ahreview.data.util.SharedPreferenceManager
import com.hackathon.ahreview.databinding.ActivityFindStoreBinding
import com.hackathon.ahreview.ui.base.BaseActivity
import kr.hs.dgsw.smartschool.morammoram.presentation.extension.shortToast
import org.koin.androidx.viewmodel.ext.android.viewModel

class FindStoreActivity : BaseActivity<ActivityFindStoreBinding, FindStoreViewModel>() {
    override val viewModel: FindStoreViewModel by viewModel()

    override fun observerViewModel() {
        getStoreList()

        with(viewModel) {
            backBtn.observe(this@FindStoreActivity, Observer {
                finish()
            })

            getStoreListSuccess.observe(this@FindStoreActivity, Observer {
                val storeListAdapter = StoreListAdapter()
                storeListAdapter.context = this@FindStoreActivity
                storeListAdapter.items = it

                mBinding.storeRecycler.adapter = storeListAdapter
            })

            getStoreListError.observe(this@FindStoreActivity, Observer {
                shortToast("가게 정보를 가져오지 못했습니다.")
            })
        }
    }

    private fun getStoreList() {
        val token = SharedPreferenceManager.getToken(applicationContext)

        if (token != null) {
            viewModel.getStoreList("Bearer $token")
        } else {
            shortToast("토큰이 존재하지 않습니다.")
        }
    }

}