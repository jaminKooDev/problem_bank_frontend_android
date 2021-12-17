package kr.co.metasoft.android.metaojt.feature.login.details

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
import java.lang.Exception
import kotlin.coroutines.CoroutineContext

class LoginPasswordViewModel(
    private val repository: ApiRepository,
    application: Application
): AndroidViewModel(application) {

    private val _context = getApplication<Application>().applicationContext

    val TAG = "lpvm"

    val loginId = MutableLiveData<String>()
    val pwText = MutableLiveData<String>()

    val token = MutableLiveData<String>()

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
            return "비밀번호를 입력해주세요."
        } else if (value.length < 8) {
            _isPwValidation.value = false
            return "비밀번호는 8자 이상입니다."
        } else if (value.length > 20) {
            _isPwValidation.value = false
            return "비밀번호는 20자를 넘길 수 없습니다."
        } else if (Regex(ValidationPattern.PASSWORD_PATTERN).matches(value)) {
            _isPwValidation.value = false
            return "비밀번호는 대소문자/숫자/특수문자만 입력할 수 있습니다."
        }
        _isPwValidation.value = true
        return "올바른 형식입니다 :)"
    }

    fun onLoginBtnClick(view: View?) {
        Log.d(TAG, "로그인버튼이 클릭되었습니다.")
        Toast.makeText(_context, "로그인버튼이 클릭되었습니다.", Toast.LENGTH_LONG).show()

        scope.launch(Dispatchers.Main) {
            try {
                _isBtnLoading.postValue(true)
                val response = repository.postLogin(loginId.value ?: "", pwText.value ?: "")
                Log.d(TAG, "${response.headers().get("authorization")}")
                token.value = response.headers().get("authorization")
                val tokenValidation = repository.getTokenValidation(token.value ?: "")
                _isBtnLoading.postValue(false)
                if (tokenValidation.code() == 200 && token.value != null) {
                    Preferences.setToken(_context, token.value!!)
                    Preferences.setUsername(_context, loginId.value!!)
                    _navigationToDashboardEvent.value = Event(Unit)
                } else {
                    Toast.makeText(_context, "로그인 처리 중 오류가 발생하였습니다.", Toast.LENGTH_LONG).show()
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
//        _navigationToDashboardEvent.value = Event(Unit)
    }

    fun onBackBtnClick(view: View?) {
        Log.d(TAG, "Back버튼이 클릭되었습니다.")
        _navigationToBackEvent.value = Event(Unit)
    }

}