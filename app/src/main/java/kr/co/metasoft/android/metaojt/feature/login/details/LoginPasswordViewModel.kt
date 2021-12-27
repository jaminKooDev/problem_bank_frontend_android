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
        scope.launch(Dispatchers.Main) {
            _isBtnLoading.postValue(true)
            try {
                // loginId 와 pwText 는 검증로직이 존제하기 때문에 값이 null 이 될 수 없음을 보장함.
                val response = repository.postLogin(loginId.value!!, pwText.value!!)
                token.value = response.headers().get("authorization")?.replace("Bearer ", "") ?: return@launch

                // StatusCode 가 200이면 토큰이 유효함
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
                Toast.makeText(_context, "로그인 처리 중 오류가 발생하였습니다.", Toast.LENGTH_LONG).show()
                e.printStackTrace()
            }
            _isBtnLoading.postValue(false)
        }
    }

    fun onBackBtnClick(view: View?) {
        Log.d(TAG, "Back버튼이 클릭되었습니다.")
        _navigationToBackEvent.value = Event(Unit)
    }

}