package com.trd.loginapp.homescreen

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.trd.loginapp.database.UserItem
import com.trd.loginapp.databinding.FragmentProfileBinding
import com.trd.loginapp.states.LoadingState.*
import com.trd.loginapp.utils.ToastUtils
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject
import kotlin.properties.Delegates


@AndroidEntryPoint
class ProfileFragment : Fragment() {
    private var binding by Delegates.notNull<FragmentProfileBinding>()

    private val viewModel: ProfileViewModel by viewModels()

    private val args: ProfileFragmentArgs by navArgs()

    @Inject
    lateinit var toastUtils: ToastUtils

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = initBinding(inflater, container)
        loadUserData()
        observeDataLoading()
        return binding.root
    }

    private fun observeDataLoading() {
        viewModel.userLoadingStateLiveData.observe(viewLifecycleOwner) { loadingState ->
            when (loadingState) {
                is LoadingError -> {}
                is DataLoading -> {}
                is LoadingSuccess -> {
                    setProfileData(loadingState.userItem)
                }
            }
        }
    }

    private fun loadUserData() {
        args.userPhoneNumber?.let { viewModel.loadUserData(it) }
            ?: toastUtils.showToast("Profile was loaded with error. Please try again")
    }

    private fun initBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentProfileBinding {
        return FragmentProfileBinding.inflate(inflater, container, false)
    }


    private fun setProfileData(userData: UserItem) {
        binding.surnameTv.text = userData.surname
        binding.nameTv.text = userData.name
        binding.phoneNumberTv.text = userData.phoneNumber
    }


}