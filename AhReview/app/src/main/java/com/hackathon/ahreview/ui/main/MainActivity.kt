package com.hackathon.ahreview.ui.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.hackathon.ahreview.R
import com.hackathon.ahreview.databinding.ActivityMainBinding
import com.hackathon.ahreview.ui.base.BaseActivity
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : BaseActivity<ActivityMainBinding, MainViewModel>() {
    override val viewModel: MainViewModel by viewModel()

    override fun observerViewModel() {

    }
}