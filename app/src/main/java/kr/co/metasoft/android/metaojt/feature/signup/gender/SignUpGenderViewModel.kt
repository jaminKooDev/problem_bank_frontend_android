package kr.co.metasoft.android.metaojt.feature.signup.gender

import android.view.View
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kr.co.metasoft.android.metaojt.global.Event
import kr.co.metasoft.android.metaojt.model.network.ApiRepository
import kr.co.metasoft.android.metaojt.model.PersonModel
import kr.co.metasoft.android.metaojt.model.UserModel
import kotlin.coroutines.CoroutineContext

enum class GenderType {
    MAN, WOMAN, OTHER
}

class SignUpGenderViewModel(
    private val repository: ApiRepository
) : ViewModel() {

    lateinit var userModel: UserModel

    private val _navigationToLoginEvent = MutableLiveData<Event<Unit>>()
    val navigationToLoginEvent: LiveData<Event<Unit>> = _navigationToLoginEvent

    private val _navigationToBackEvent = MutableLiveData<Event<Unit>>()
    val navigationToBackEvent: LiveData<Event<Unit>> = _navigationToBackEvent

    private val _genderEnum = MutableLiveData<GenderType>()
    val genderEnum: LiveData<GenderType> = _genderEnum

    private val parentJob = Job()
    private val coroutineContext : CoroutineContext get() = parentJob + Dispatchers.Default
    private val scope = CoroutineScope(coroutineContext)

    private val _isBtnLoading = MutableLiveData<Boolean>()
    val isBtnLoading: LiveData<Boolean> = _isBtnLoading

    fun onBackBtnClick(view: View) {
        _navigationToBackEvent.value = Event(Unit)
    }

    fun onContinueBtnClick(view: View) {
        if (_genderEnum.value != null) {
            _isBtnLoading.postValue(true)
            when (_genderEnum.value) {
                GenderType.MAN -> userModel.gender = "M"
                GenderType.WOMAN -> userModel.gender = "F"
                GenderType.OTHER -> userModel.gender = "E"
            }
            scope.launch(Dispatchers.Main) {
                val personModel = PersonModel(userModel.name!!)

                val response = repository.postSignUp(userModel, personModel)
                _isBtnLoading.postValue(false)
                if (response.isSuccessful)
                    _navigationToLoginEvent.value = Event(Unit)
            }
        }


        _navigationToLoginEvent.value = Event(Unit)
    }

    fun onManBtnClick(view: View) {
        _genderEnum.value = GenderType.MAN
    }

    fun onFemaleBtnClick(view: View) {
        _genderEnum.value = GenderType.WOMAN
    }

    fun onOtherBtnClick(view: View) {
        _genderEnum.value = GenderType.OTHER
    }

}