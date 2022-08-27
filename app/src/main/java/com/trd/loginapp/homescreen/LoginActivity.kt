package com.trd.loginapp.homescreen

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import com.trd.loginapp.Constants.PHONE_NUMBER_KEY
import com.trd.loginapp.R
import com.trd.loginapp.api.LoginData
import com.trd.loginapp.databinding.ActivityLoginBinding
import com.trd.loginapp.states.LoginState.*
import com.trd.loginapp.utils.ToastUtils
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class LoginActivity : AppCompatActivity() {

    @Inject
    lateinit var toastUtils: ToastUtils

    private lateinit var binding: ActivityLoginBinding
    private val viewModel: LoginViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        handleLoginBtnClick()
        observeLoginState()
        setContentView(binding.root)
    }

    private fun handleLoginBtnClick() {
        binding.loginBtn.setOnClickListener {
            val phoneNumber = binding.phoneNumberEdt.text.toString()
            val password = binding.passwordEdt.text.toString()
            viewModel.login(phoneNumber, password)
        }
    }


    private fun observeLoginState() {
        viewModel.loginStateLiveData.observe(this) { state ->
            when (state) {
                LoginSuccess -> {
                    val intent = Intent(this, HomeScreen::class.java)
                    intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                    Log.i(
                        "***888",
                        "observeLoginState: binding.phoneNumberEdt.text = ${binding.phoneNumberEdt.text}"
                    )
                    intent.putExtra(PHONE_NUMBER_KEY, binding.phoneNumberEdt.text.toString())
                    startActivity(intent)
                }
                InvalidCredentials -> {
                    toastUtils.showToast(getString(R.string.invalid_login_credentials))
                }
                NetworkFailure -> {
                    toastUtils.showNetworkErrorToast()
                }
                UnknownFailure -> {
                    toastUtils.showUnknownErrorToast()
                }
                EmptyPhoneCode -> {
                    toastUtils.showToast("EmptyPhoneCode")
                }
                EmptyPhoneNumber -> {
                    toastUtils.showToast("EmptyPhoneNumber")
                }
                EmptyPassword -> {
                    toastUtils.showToast("EmptyPassword")
                }
                InvalidPhoneCode -> {
                    toastUtils.showToast("InvalidPhoneCode")
                }
                InvalidPhoneNumber -> {
                    toastUtils.showToast("InvalidPhoneNumber")
                }
                LoginError -> {
                    toastUtils.showToast("LoginError")
                }
                PhoneNumberToShort -> {
                    toastUtils.showToast("PhoneNumberToShort")
                }
                PhoneCodeMissedPlusSymbol -> {
                    toastUtils.showToast("PhoneCodeMissedPlusSymbol")
                }
            }
        }
    }

}