package com.trd.loginapp.homescreen

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import com.trd.loginapp.Constants.PHONE_NUMBER_KEY
import com.trd.loginapp.R
import com.trd.loginapp.databinding.ActivityLoginBinding
import com.trd.loginapp.states.LoginState
import com.trd.loginapp.states.LoginState.*
import com.trd.loginapp.utils.ToastUtils
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding
    private val viewModel: LoginViewModel by viewModels()

    @Inject
    lateinit var toastUtils: ToastUtils

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        initToolbar()
        handleLoginBtnClick()
        observeLoginState()
        setContentView(binding.root)
    }

    private fun initToolbar() {
        binding.appToolbar.title = getString(R.string.sign_in)
        binding.appToolbar.setTitleTextColor(resources.getColor(R.color.white, null))
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
            handleLoginState(state)
            handleProgressBar(state)
        }
    }

    private fun handleLoginState(state: LoginState?) {
        when (state) {
            LoginSuccess -> handleSuccessLoginState()
            NetworkFailure -> toastUtils.showNetworkErrorToast()
            UnknownFailure -> toastUtils.showUnknownErrorToast()
            EmptyPhoneNumber -> toastUtils.showEmptyPhoneErrorToast()
            EmptyPassword -> toastUtils.showEmptyPasswordErrorToast()
            InvalidPhoneNumber -> toastUtils.showInvalidPhoneNumberErrorToast()
            LoginError -> toastUtils.showLoginErrorToast()
            PhoneNumberTooShort -> toastUtils.showPhoneNumberTooShortErrorToast()
            MissedPlusSymbolError -> toastUtils.showMissedPlusSymbolErrorToast()
            null -> toastUtils.showUnknownErrorToast()
        }
    }

    private fun handleSuccessLoginState() {
        val intent = Intent(this, HomeScreen::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        Log.i(
            "***888",
            "observeLoginState: binding.phoneNumberEdt.text = ${binding.phoneNumberEdt.text}"
        )
        intent.putExtra(PHONE_NUMBER_KEY, binding.phoneNumberEdt.text.toString())
        startActivity(intent)
    }

    private fun handleProgressBar(state: LoginState) {
        val showProgress = when (state) {
            Loading -> true
            else -> false
        }
        binding.loginProgressBar.isVisible = showProgress
    }

}