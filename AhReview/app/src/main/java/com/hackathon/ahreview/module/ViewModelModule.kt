package com.hackathon.ahreview.module

import com.hackathon.ahreview.ui.detailStore.DetailStoreViewModel
import com.hackathon.ahreview.ui.findStore.FindStoreViewModel
import com.hackathon.ahreview.ui.login.LoginViewModel
import com.hackathon.ahreview.ui.main.MainViewModel
import com.hackathon.ahreview.ui.test.TestViewModel
import com.hackathon.ahreview.ui.writeReview.WriteReviewViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val ViewModelModule = module {
    viewModel { MainViewModel(get()) }
    viewModel { LoginViewModel(get()) }
    viewModel { FindStoreViewModel(get()) }
    viewModel { DetailStoreViewModel(get()) }
    viewModel { TestViewModel(get()) }
    viewModel { WriteReviewViewModel(get(), get()) }
}