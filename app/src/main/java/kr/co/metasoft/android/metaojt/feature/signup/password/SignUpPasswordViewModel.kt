package kr.co.metasoft.android.metaojt.feature.signup.password

import android.util.Log
import android.view.View
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kr.co.metasoft.android.metaojt.global.Event
import kr.co.metasoft.android.metaojt.global.ValidationPattern
import kr.co.metasoft.android.metaojt.model.UserModel

class SignUpPasswordViewModel : ViewModel() {

    lateinit var userModel: UserModel

    private val _navigationToSignUpNameEvent = MutableLiveData<Event<Unit>>()
    val navigationToSignUpNameEvent: LiveData<Event<Unit>> = _navigationToSignUpNameEvent

    private val _navigationToBackEvent = MutableLiveData<Event<Unit>>()
    val navigationToBackEvent: LiveData<Event<Unit>> = _navigationToBackEvent

    val pwText = MutableLiveData<String>()

    val pwChkText = MutableLiveData<String>()

    private val _isPwValidation = MutableLiveData<Boolean>()
    val isPwValidation: LiveData<Boolean> = _isPwValidation

    private val _isPwChkValidation = MutableLiveData<Boolean>()
    val isPwChkValidation: LiveData<Boolean> = _isPwChkValidation

    private val _pwValidationText = MutableLiveData<String>()
    val pwValidationText: LiveData<String> = _pwValidationText
    fun setPwValidationText(value: String) {
        _pwValidationText.value = value
    }

    private val _pwChkValidationText = MutableLiveData<String>()
    val pwChkValidationText: LiveData<String> = _pwChkValidationText
    fun setPwChkValidationText(value: String) {
        _pwChkValidationText.value = value
    }
//    private val _pwValidationText = MutableLiveData<String>()
//    val pwValidationText: LiveData<String> = _pwValidationText
//
//    private val _pwChkValidationText = MutableLiveData<String>()
//    val pwChkValidationText: LiveData<String> = _pwChkValidationText

    fun onBackBtnClick(view: View?) {
        _navigationToBackEvent.value = Event(Unit)
    }

    fun onContinueBtnClick(view: View?) {
        _navigationToSignUpNameEvent.value = Event(Unit)
    }

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

    fun chkPwChkValidation(value: String?) : String {
        Log.d("test", "value: $value, pwValidationText: ${pwValidationText.value}")
        if (value == null || value == "") {
            _isPwChkValidation.value = false
            return "비밀번호를 입력해주세요."
        } else if (value.length < 8) {
            _isPwChkValidation.value = false
            return "비밀번호는 8자 이상입니다."
        } else if (value.length > 20) {
            _isPwChkValidation.value = false
            return "비밀번호는 20자를 넘길 수 없습니다."
        } else if (Regex(ValidationPattern.PASSWORD_PATTERN).matches(value)) {
            _isPwChkValidation.value = false
            return "비밀번호는 대소문자/숫자/특수문자만 입력할 수 있습니다."
        } else if (pwText.value != value) {
            _isPwChkValidation.value = false
            return "비밀번호가 일치하지 않습니다."
        }
        _isPwChkValidation.value = true
        return "비밀번호가 일치합니다."
    }

}