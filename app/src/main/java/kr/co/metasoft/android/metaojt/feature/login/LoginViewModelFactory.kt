package kr.co.metasoft.android.metaojt.feature.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import kr.co.metasoft.android.metaojt.model.network.ApiRepository

class LoginViewModelFactory(
    private val repository: ApiRepository
    ): ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(LoginViewModel::class.java)) {
            return LoginViewModel(repository) as T
        }
        throw IllegalAccessException("Unknown ViewModel class")
    }
}