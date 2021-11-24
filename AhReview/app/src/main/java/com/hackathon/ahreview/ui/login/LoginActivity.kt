package com.hackathon.ahreview.ui.login

import com.hackathon.ahreview.databinding.ActivityLoginBinding
import com.hackathon.ahreview.ui.base.BaseActivity
import org.koin.androidx.viewmodel.ext.android.viewModel

class LoginActivity : BaseActivity<ActivityLoginBinding,LoginViewModel>() {
    override val viewModel: LoginViewModel by viewModel()

    override fun observerViewModel() {

    }
}