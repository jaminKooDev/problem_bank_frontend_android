package kr.co.metasoft.android.metaojt.feature.dashboard

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import kr.co.metasoft.android.metaojt.model.network.ApiRepository

class DashboardViewModelFactory(
    private val repository: ApiRepository
): ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(DashboardViewModel::class.java)) {
            return DashboardViewModel(repository) as T
        }
        throw IllegalAccessException("Unknown ViewModel class")
    }
}