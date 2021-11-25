package com.hackathon.ahreview.ui.main

import android.content.Intent
import android.media.AudioManager
import android.media.MediaPlayer
import androidx.lifecycle.Observer
import com.hackathon.ahreview.databinding.ActivityMainBinding
import com.hackathon.ahreview.ui.base.BaseActivity
import com.hackathon.ahreview.ui.findStore.FindStoreActivity
import com.hackathon.ahreview.utils.NaverAPITTS
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : BaseActivity<ActivityMainBinding, MainViewModel>() {
    override val viewModel: MainViewModel by viewModel()

    override fun observerViewModel() {
        with(viewModel) {
            findStoreBtn.observe(this@MainActivity, Observer {
                //val intent = Intent(this@MainActivity, TestActivity::class.java)
                val intent = Intent(this@MainActivity, FindStoreActivity::class.java)
                startActivity(intent)
            })
        }
    }
}