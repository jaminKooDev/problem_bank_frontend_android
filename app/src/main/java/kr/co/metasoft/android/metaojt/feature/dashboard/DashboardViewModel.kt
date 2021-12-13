package kr.co.metasoft.android.metaojt.feature.dashboard

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kr.co.metasoft.android.metaojt.global.Event
import kr.co.metasoft.android.metaojt.model.network.ApiRepository
import kotlin.coroutines.CoroutineContext

class DashboardViewModel(
    private val repository: ApiRepository
): ViewModel() {

    private val _navigationToLoginEvent = MutableLiveData<Event<Unit>>()
    val navigationToLoginEvent: LiveData<Event<Unit>> = _navigationToLoginEvent

    private val parentJob = Job()
    private val coroutineContext : CoroutineContext get() = parentJob + Dispatchers.Default
    private val scope = CoroutineScope(coroutineContext)

    fun chkTokenValidation(token: String?) {
        if (token == null)  _navigationToLoginEvent.value = Event(Unit)
        else {
            scope.launch {
                val response = repository.getTokenValidation(token)
                if (response.code() != 200) {
                    _navigationToLoginEvent.postValue(Event(Unit))
                }
            }
        }
    }

}