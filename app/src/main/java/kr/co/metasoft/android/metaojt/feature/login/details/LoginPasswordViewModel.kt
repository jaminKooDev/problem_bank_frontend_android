package kr.co.metasoft.android.metaojt.feature.login.details

import android.annotation.SuppressLint
import android.app.Application
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kr.co.metasoft.android.metaojt.global.Event
import kr.co.metasoft.android.metaojt.global.Preferences
import kr.co.metasoft.android.metaojt.global.ValidationPattern
import kr.co.metasoft.android.metaojt.model.network.ApiRepository
import kr.co.metasoft.android.metaojt.util.JWTUtils
import org.json.JSONObject
import java.lang.Exception
import kotlin.coroutines.CoroutineContext

class LoginPasswordViewModel(
    private val repository: ApiRepository,
    application: Application
): AndroidViewModel(application) {

    @SuppressLint("StaticFieldLeak")
    private val _context = getApplication<Application>().applicationContext

    private val prefs = Preferences(_context)

    private val TAG = "lpvm"

    val loginId = MutableLiveData<String>()
    val pwText = MutableLiveData<String>()

    private val token = MutableLiveData<String>()

    private val _pwValidationText = MutableLiveData<String>()
    val pwValidationText: LiveData<String> = _pwValidationText
    fun setPwValidationText(value: String) {
        _pwValidationText.value = value
    }

    private val _isPwValidation = MutableLiveData<Boolean>()
    val isPwValidation: LiveData<Boolean> = _isPwValidation

    private val parentJob = Job()
    private val coroutineContext : CoroutineContext get() = parentJob + Dispatchers.Default
    private val scope = CoroutineScope(coroutineContext)

    private val _navigationToBackEvent = MutableLiveData<Event<Unit>>()
    val navigationToBackEvent: LiveData<Event<Unit>> = _navigationToBackEvent

    private val _navigationToDashboardEvent = MutableLiveData<Event<Unit>>()
    val navigationToDashboardEvent: LiveData<Event<Unit>> = _navigationToDashboardEvent

    private val _isBtnLoading = MutableLiveData<Boolean>()
    val isBtnLoading: LiveData<Boolean> = _isBtnLoading

    fun chkPwValidation(value: String?) : String{
        if (value == null || value == "") {
            _isPwValidation.value = false
            return "??????????????? ??????????????????."
        } else if (value.length < 8) {
            _isPwValidation.value = false
            return "??????????????? 8??? ???????????????."
        } else if (value.length > 20) {
            _isPwValidation.value = false
            return "??????????????? 20?????? ?????? ??? ????????????."
        } else if (Regex(ValidationPattern.PASSWORD_PATTERN).matches(value)) {
            _isPwValidation.value = false
            return "??????????????? ????????????/??????/??????????????? ????????? ??? ????????????."
        }
        _isPwValidation.value = true
        return "????????? ??????????????? :)"
    }

    fun onLoginBtnClick(view: View?) {
        scope.launch(Dispatchers.Main) {
            _isBtnLoading.postValue(true)
            try {
                // loginId ??? pwText ??? ??????????????? ???????????? ????????? ?????? null ??? ??? ??? ????????? ?????????.
                val response = repository.postLogin(loginId.value!!, pwText.value!!)
                token.value = response.headers().get("authorization")?.replace("Bearer ", "") ?: return@launch

                // StatusCode ??? 200?????? ????????? ?????????
                if (repository.getTokenValidation(token.value!!).code() == 200) {
                    prefs.token = token.value!!
                    prefs.username = loginId.value!!
                    JWTUtils.decoded(token.value!!)
                    _isBtnLoading.postValue(false)
//                    val jsonObject = JSONObject("Berear " + token.value!!)
//                    Log.d("lpvm", "$jsonObject")
                    _navigationToDashboardEvent.value = Event(Unit)
                }
            } catch (e: Exception) {
                Toast.makeText(_context, "????????? ?????? ??? ????????? ?????????????????????.", Toast.LENGTH_LONG).show()
                e.printStackTrace()
            }
            _isBtnLoading.postValue(false)
        }
    }

    fun onBackBtnClick(view: View?) {
        Log.d(TAG, "Back????????? ?????????????????????.")
        _navigationToBackEvent.value = Event(Unit)
    }

}