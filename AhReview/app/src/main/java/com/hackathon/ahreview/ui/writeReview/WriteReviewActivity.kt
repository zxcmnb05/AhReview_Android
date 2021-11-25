package com.hackathon.ahreview.ui.writeReview

import android.widget.RadioButton
import android.widget.RatingBar
import com.hackathon.ahreview.databinding.ActivityWriteReviewBinding
import com.hackathon.ahreview.ui.base.BaseActivity
import org.koin.androidx.viewmodel.ext.android.viewModel

class WriteReviewActivity : BaseActivity<ActivityWriteReviewBinding, WriteReviewViewModel>() {
    override val viewModel: WriteReviewViewModel by viewModel()

    override fun observerViewModel() {
        mBinding.ratingBar.onRatingBarChangeListener =
            RatingBar.OnRatingBarChangeListener { _, rating, _ ->
                viewModel.rating.value = rating
            }

        with(viewModel) {
            onClickedAnonymous.observe(this@WriteReviewActivity, {
                anonymous.value = anonymous.value != true
            })
        }
    }
}