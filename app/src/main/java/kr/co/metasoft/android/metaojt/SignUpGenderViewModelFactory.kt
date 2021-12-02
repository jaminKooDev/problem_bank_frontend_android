package kr.co.metasoft.android.metaojt

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import kr.co.metasoft.android.metaojt.api.ApiRepository

class SignUpGenderViewModelFactory(
    private val repository: ApiRepository
): ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(SignUpGenderViewModel::class.java)) {
            return SignUpGenderViewModel(repository) as T
        }
        throw IllegalAccessException("Unknown ViewModel class")
    }
}