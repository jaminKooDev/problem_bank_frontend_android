package kr.co.metasoft.android.metaojt.feature.signup.phonenum

import android.annotation.SuppressLint
import android.app.Application
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kr.co.metasoft.android.metaojt.global.Event
import kr.co.metasoft.android.metaojt.global.Preferences
import kr.co.metasoft.android.metaojt.global.ValidationPattern
import kr.co.metasoft.android.metaojt.model.PersonModel
import kr.co.metasoft.android.metaojt.model.UserModel
import kr.co.metasoft.android.metaojt.model.network.ApiRepository
import kr.co.metasoft.android.metaojt.model.network.UserPhoneNumAuthModel
import kotlin.coroutines.CoroutineContext

class SignUpPhoneNumViewModel(
    private val repository: ApiRepository,
    application: Application
) : AndroidViewModel(application) {

    @SuppressLint("StaticFieldLeak")
    private val _context = getApplication<Application>().applicationContext

    lateinit var userModel: UserModel

    private val parentJob = Job()
    private val coroutineContext : CoroutineContext get() = parentJob + Dispatchers.Default
    private val scope = CoroutineScope(coroutineContext)

    private val _navigationToSignUpPhoneNumAuthEvent = MutableLiveData<Event<Unit>>()
    val navigationToSignUpPhoneNumAuthEvent: LiveData<Event<Unit>> = _navigationToSignUpPhoneNumAuthEvent
    private val _navigationToSignUpLoginEvent = MutableLiveData<Event<Unit>>()
    val navigationToSignUpLoginEvent: LiveData<Event<Unit>> = _navigationToSignUpLoginEvent
    private val _navigationToBackEvent = MutableLiveData<Event<Unit>>()
    val navigationToBackEvent: LiveData<Event<Unit>> = _navigationToBackEvent

    val phoneNumText = MutableLiveData<String>()
    val phoneNumAuthText = MutableLiveData<String>()

    private val _isPhoneNumValidation = MutableLiveData<Boolean>()
    val isPhoneNumValidation: LiveData<Boolean> = _isPhoneNumValidation
    private val _isPhoneNumAuthValidation = MutableLiveData<Boolean>()
    val isPhoneNumAuthValidation: LiveData<Boolean> = _isPhoneNumAuthValidation

    private val _phoneNumValidationText = MutableLiveData<String>()
    private val _phoneNumAuthValidationText = MutableLiveData<String>()

    val phoneNumValidationText: LiveData<String> = _phoneNumValidationText
    fun setPhoneNumValidationText(value: String) {
        _phoneNumValidationText.value = value
    }
    val phoneNumAuthValidationText: LiveData<String> = _phoneNumAuthValidationText
    fun setPhoneNumAuthValidationText(value: String) {
        _phoneNumAuthValidationText.value = value
    }


    fun onBackBtnClick(view: View) {
        _navigationToBackEvent.value = Event(Unit)
    }

    fun onPhoneNumContinueBtnClick(view: View) {
        if(_isPhoneNumValidation.value == true) {
            scope.launch(Dispatchers.Main) {
                val userPhoneNumAuthModel = UserPhoneNumAuthModel(phoneNum = phoneNumText.value!!)

                val response = repository.postAuth(userPhoneNumAuthModel)
                if (response.isSuccessful) {
                    userModel.authId = response.body()!!.id
                    _navigationToSignUpPhoneNumAuthEvent.value = Event(Unit)
                }
            }
        }
    }

    fun onPhoneNumAuthContinueBtnClick(view: View) {
        if(_isPhoneNumAuthValidation.value == true) {
            scope.launch(Dispatchers.Main) {
                val userPhoneNumAuthModel = UserPhoneNumAuthModel(
                    id = userModel.authId!!,
                    phoneNum = userModel.phoneNum!!,
                    authToken = phoneNumAuthText.value!!)
                Log.d("qwe", userPhoneNumAuthModel.toString())
                val response = repository.postAuthVerify(userPhoneNumAuthModel)
                Log.d("qwe", response.message())
                Log.d("qwe", response.body().toString())

                if (response.body()!!.statusCode == 1L) {
                    // 회원가입 코드
                    val personModel = PersonModel(userModel.name!!)
                    Log.d("personModel", personModel.toString())
                    Log.d("userModel", userModel.toString())
                    val response2 = repository.postSignUp(userModel, personModel)
                    Log.d("qwe", response2.toString())
                    if (response2.isSuccessful)
                        _navigationToSignUpLoginEvent.value = Event(Unit)
                } else {
                    Toast.makeText(_context, response.body()!!.responseMessage, Toast.LENGTH_LONG).show()
                }
            }
        }
    }

    fun chkPhoneNumValidation(value: String?) : String{
        if (value == null || value == "") {
            _isPhoneNumValidation.value = false
            return "핸드폰 번호를 입력해주세요."
        } else if (value.length < 9) {
            _isPhoneNumValidation.value = false
            return "핸드폰 번호는 10자 이상입니다."
        } else if (value.length > 12) {
            _isPhoneNumValidation.value = false
            return "핸드폰 번호는 12자를 넘길 수 없습니다."
        } else if (!Regex(ValidationPattern.PHONE_NUM_PATTERN).matches(value)) {
            _isPhoneNumValidation.value = false
            return "핸드폰 번호는 숫자만 입력이 가능합니다."
        }
        _isPhoneNumValidation.value = true
        return "올바른 형식입니다 :)"
    }

    fun chkPhoneNumAuthValidation(value: String?) : String{
        if (value == null || value == "") {
            _isPhoneNumAuthValidation.value = false
            return "인증번호를 입력해주세요."
        } else if (value.length != 6) {
            _isPhoneNumAuthValidation.value = false
            return "인증번호는 6자 입니다."
        } else if (Regex(ValidationPattern.ONLY_NUMBER_PATTERN).matches(value)) {
            _isPhoneNumAuthValidation.value = false
            return "인증번호는 숫자만 입력이 가능합니다."
        }
        _isPhoneNumAuthValidation.value = true
        return "올바른 형식입니다 :)"
    }
}