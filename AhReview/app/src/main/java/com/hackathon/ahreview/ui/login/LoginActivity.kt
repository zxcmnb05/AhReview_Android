package com.hackathon.ahreview.ui.login

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.util.Log
import androidx.lifecycle.Observer
import com.hackathon.ahreview.R
import com.hackathon.ahreview.data.util.SharedPreferenceManager
import com.hackathon.ahreview.databinding.ActivityLoginBinding
import com.hackathon.ahreview.ui.base.BaseActivity
import com.hackathon.ahreview.ui.main.MainActivity
import com.nhn.android.naverlogin.OAuthLogin
import com.nhn.android.naverlogin.OAuthLoginHandler
import kr.hs.dgsw.smartschool.morammoram.presentation.extension.shortToast
import org.koin.androidx.viewmodel.ext.android.viewModel

class LoginActivity : BaseActivity<ActivityLoginBinding, LoginViewModel>() {
    override val viewModel: LoginViewModel by viewModel()

    lateinit var mContext: Context

    override fun observerViewModel() {
        mContext = this

        mOAuthLoginInstance = OAuthLogin.getInstance()
        mOAuthLoginInstance.init(
            mContext,
            resources.getString(R.string.naver_client_id),
            resources.getString(R.string.naver_client_secret),
            resources.getString(R.string.naver_client_name)
        )

        mBinding.naverLoginBtn.setOAuthLoginHandler(mOAuthLoginHandler)

        with(viewModel){
            loginSuccess.observe(this@LoginActivity, Observer {
                SharedPreferenceManager.setToken(this@LoginActivity, it.accessToken)

                val intent = Intent(mContext, MainActivity::class.java)
                startActivity(intent)
                finish()
            })

            loginError.observe(this@LoginActivity, Observer {
                shortToast("토큰을 전달받지 못했습니다. 다시 시도해주세요")
            })
        }
    }

    private val mOAuthLoginHandler: OAuthLoginHandler = @SuppressLint("HandlerLeak")
    object : OAuthLoginHandler() {
        override fun run(success: Boolean) {
            if (success) {
                SharedPreferenceManager.setToken(this@LoginActivity, mOAuthLoginInstance.getAccessToken(mContext))
                Log.e("accessToken : ", mOAuthLoginInstance.getAccessToken(mContext))
                Log.e("refreshToken  : ", mOAuthLoginInstance.getRefreshToken(mContext))
                Log.e("expiresAt  : ", mOAuthLoginInstance.getExpiresAt(mContext).toString())
                Log.e("tokenType  : ", mOAuthLoginInstance.getTokenType(mContext))

                viewModel.login(mOAuthLoginInstance.getAccessToken(mContext))
            } else {
                val errorCode: String = mOAuthLoginInstance.getLastErrorCode(mContext).code
                val errorDesc = mOAuthLoginInstance.getLastErrorDesc(mContext)

                shortToast(
                    "errorCode:" + errorCode
                            + ", errorDesc:" + errorDesc
                )
            }

        }
    }
}