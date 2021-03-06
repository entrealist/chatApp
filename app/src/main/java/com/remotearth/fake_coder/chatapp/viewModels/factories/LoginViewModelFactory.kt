package com.remotearth.fake_coder.chatapp.viewModels.factories

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.remotearth.fake_coder.chatapp.contracts.LoginView
import com.remotearth.fake_coder.chatapp.services.FireBaseAuthService
import com.remotearth.fake_coder.chatapp.services.FireBaseRealTimeDataBaseService
import com.remotearth.fake_coder.chatapp.services.FireBaseTokenService
import com.remotearth.fake_coder.chatapp.viewModels.LoginViewModel

class LoginViewModelFactory(
    private val fireBaseAuthService: FireBaseAuthService,
    private val fireBaseTokenService: FireBaseTokenService,
    private val fireBaseRealTimeDataBaseService: FireBaseRealTimeDataBaseService,
    private val loginView: LoginView
): ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(LoginViewModel::class.java)) {
            return LoginViewModel(fireBaseAuthService, fireBaseTokenService, fireBaseRealTimeDataBaseService, loginView) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}