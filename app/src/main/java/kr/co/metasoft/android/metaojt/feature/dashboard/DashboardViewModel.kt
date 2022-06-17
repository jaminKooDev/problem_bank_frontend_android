package kr.co.metasoft.android.metaojt.feature.dashboard

import android.app.Application
import android.content.Context
import android.util.Log
import android.view.View
import android.webkit.WebChromeClient
import android.webkit.WebSettings
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.preference.Preference
import androidx.preference.PreferenceManager
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kr.co.metasoft.android.metaojt.global.Event
import kr.co.metasoft.android.metaojt.global.NetworkConnection
import kr.co.metasoft.android.metaojt.global.Preferences
import kr.co.metasoft.android.metaojt.model.network.ApiRepository
import java.lang.Exception
import kotlin.coroutines.CoroutineContext

class DashboardViewModel(
    private val repository: ApiRepository,
    application: Application
): AndroidViewModel(application) {

    private val _context = getApplication<Application>().applicationContext
    private val prefs = Preferences(_context)

    private val _navigationToLoginEvent = MutableLiveData<Event<Unit>>()
    val navigationToLoginEvent: LiveData<Event<Unit>> = _navigationToLoginEvent

    private val _navigationBackEvent = MutableLiveData<Event<Unit>>()
    val navigationBackEvent: LiveData<Event<Unit>> = _navigationBackEvent

    private val _navigationNetworkErrorEvent = MutableLiveData<Event<Unit>>()
    val navigationNetworkErrorEvent: LiveData<Event<Unit>> = _navigationNetworkErrorEvent

    private val parentJob = Job()
    private val coroutineContext : CoroutineContext get() = parentJob + Dispatchers.Default
    private val scope = CoroutineScope(coroutineContext)

    fun getContext(): Context? {
        return _context;
    }

    fun chkTokenValidation(token: String?) {
        val sharedPreferences = PreferenceManager.getDefaultSharedPreferences(_context)
        val autoLogin = sharedPreferences.getBoolean("auto_login", true)

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

    fun onNetworkError() {
        _navigationNetworkErrorEvent.value = Event(Unit)
    }

    fun onBackBtnClick(view: View?) {
        _navigationBackEvent.value = Event(Unit)
    }

    fun logout(token: String?) {
        scope.launch {
            try {
                prefs.token = null
                repository.postLogout(token!!)

            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

}