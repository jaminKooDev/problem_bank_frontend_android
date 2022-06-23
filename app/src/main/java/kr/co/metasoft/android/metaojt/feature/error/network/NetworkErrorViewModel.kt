package kr.co.metasoft.android.metaojt.feature.error.network

import android.app.Application
import android.content.Intent
import android.net.Uri
import android.view.View
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kr.co.metasoft.android.metaojt.global.Event
import kr.co.metasoft.android.metaojt.global.Preferences
import kr.co.metasoft.android.metaojt.model.network.ApiRepository

class NetworkErrorViewModel(
    private val repository: ApiRepository,
    application: Application
): AndroidViewModel(application) {

    private val _context = getApplication<Application>().applicationContext
    private val prefs = Preferences(_context)

    private val _navigationToDashboardEvent = MutableLiveData<Event<Unit>>()
    val navigationToDashboardEvent: LiveData<Event<Unit>> = _navigationToDashboardEvent
    private val _toWebEvent = MutableLiveData<Event<Unit>>()
    val toWebEvent: LiveData<Event<Unit>> = _toWebEvent

    fun onToWebBtnClick(view: View?) {
        _toWebEvent.value = Event(Unit)
    }

    fun onNetworkConnected() {
        _navigationToDashboardEvent.value = Event(Unit)
    }
}