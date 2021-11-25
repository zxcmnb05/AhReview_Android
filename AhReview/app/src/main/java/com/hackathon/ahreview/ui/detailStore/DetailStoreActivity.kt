package com.hackathon.ahreview.ui.detailStore

import android.content.Intent
import android.content.res.Resources
import androidx.appcompat.widget.ThemeUtils
import androidx.lifecycle.Observer
import com.bumptech.glide.Glide
import com.hackathon.ahreview.R
import com.hackathon.ahreview.data.model.response.Store
import com.hackathon.ahreview.data.util.SharedPreferenceManager
import com.hackathon.ahreview.databinding.ActivityDetailStoreBinding
import com.hackathon.ahreview.ui.base.BaseActivity
import com.hackathon.ahreview.ui.writeReview.WriteReviewActivity
import kr.hs.dgsw.smartschool.morammoram.presentation.extension.shortToast
import org.koin.androidx.viewmodel.ext.android.viewModel

class DetailStoreActivity : BaseActivity<ActivityDetailStoreBinding, DetailStoreViewModel>() {
    override val viewModel: DetailStoreViewModel by viewModel()

    lateinit var store: Store

    override fun onResume() {
        super.onResume()
        getReviewList(1)
    }

    override fun observerViewModel() {

        getStoreData()
        with(viewModel) {
            onClickWriteReview.observe(this@DetailStoreActivity, Observer {
                val intent = Intent(this@DetailStoreActivity, WriteReviewActivity::class.java)
                intent.putExtra("address", store.address)
                intent.putExtra("name", store.name)
                intent.putExtra("count", store.reviewAmount)
                intent.putExtra("score", store.starScore)
                intent.putExtra("address", store.address)
                intent.putExtra("url", store.url)

                startActivity(intent)
            })
            backBtn.observe(this@DetailStoreActivity, Observer {
                finish()
            })

            getListSuccess.observe(this@DetailStoreActivity, Observer {
                val adapter = StoreReviewAdapter()
                adapter.context = this@DetailStoreActivity
                adapter.items = it
                adapter.store = store
                adapter.notifyDataSetChanged()
                mBinding.storeReviewRecycler.adapter = adapter
            })

            getListError.observe(this@DetailStoreActivity, Observer {
                shortToast("리스트를 받아오지 못했습니다.")
            })

            recentBtn.observe(this@DetailStoreActivity, Observer {
                getReviewList(1)

                btnInit()
                mBinding.btnRecent.background = resources.getDrawable(R.drawable.activation_button_background)
                mBinding.btnRecent.setTextColor(resources.getColor(R.color.white))

            })

            positiveBtn.observe(this@DetailStoreActivity, Observer {
                getReviewList(2)
                btnInit()
                mBinding.btnPositive.background = resources.getDrawable(R.drawable.activation_button_background)
                mBinding.btnPositive.setTextColor(resources.getColor(R.color.white))
            })

            negativeBtn.observe(this@DetailStoreActivity, Observer {
                getReviewList(3)
                btnInit()
                mBinding.btnNegative.background = resources.getDrawable(R.drawable.activation_button_background)
                mBinding.btnNegative.setTextColor(resources.getColor(R.color.white))
            })
        }
    }

    fun btnInit(){
        mBinding.btnRecent.background = resources.getDrawable(R.drawable.disabled_button_background)
        mBinding.btnPositive.background = resources.getDrawable(R.drawable.disabled_button_background)
        mBinding.btnNegative.background = resources.getDrawable(R.drawable.disabled_button_background)

        mBinding.btnRecent.setTextColor(resources.getColor(R.color.black))
        mBinding.btnPositive.setTextColor(resources.getColor(R.color.black))
        mBinding.btnNegative.setTextColor(resources.getColor(R.color.black))

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

        store = Store(address, name, ammount, score, url)
        getReviewList(1)
    }

    fun getReviewList(filter: Int){
        val token = SharedPreferenceManager.getToken(this)

        if(token != null){
            viewModel.getReviewList("Bearer $token", filter, store.address)
        } else {
            shortToast("토큰이 존재하지 않습니다.")
        }
    }
}