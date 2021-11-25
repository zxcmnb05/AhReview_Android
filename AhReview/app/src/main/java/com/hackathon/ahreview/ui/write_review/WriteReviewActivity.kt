package com.hackathon.ahreview.ui.write_review

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
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
    }
}