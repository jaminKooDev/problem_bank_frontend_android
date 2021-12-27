package kr.co.metasoft.android.metaojt.feature.menulist

//import android.app.Application
//import android.util.Log
//import android.view.View
//import androidx.lifecycle.AndroidViewModel
//import androidx.lifecycle.LiveData
//import androidx.lifecycle.MutableLiveData
//import androidx.lifecycle.ViewModel
//import com.google.gson.Gson
//import com.google.gson.JsonObject
//import kotlinx.coroutines.CoroutineScope
//import kotlinx.coroutines.Dispatchers
//import kotlinx.coroutines.Job
//import kotlinx.coroutines.launch
//import kr.co.metasoft.android.metaojt.global.Event
//import kr.co.metasoft.android.metaojt.global.Preferences
//import kr.co.metasoft.android.metaojt.model.network.ApiRepository
//import kr.co.metasoft.android.metaojt.model.network.CodeModel
//import kr.co.metasoft.android.metaojt.model.network.TreeMenu
//import kr.co.metasoft.android.metaojt.model.network.TreeMenuModel
//import kr.co.metasoft.android.metaojt.util.JWTUtils
//import org.json.JSONArray
//import org.json.JSONObject
//import java.lang.Exception
//import kotlin.coroutines.CoroutineContext
//
//class MenuListViewModel(
//    private val repository: ApiRepository,
//    application: Application
//): AndroidViewModel(application) {
//
//    private val _context = getApplication<Application>().applicationContext
//
//    private lateinit var topMenuCode: Number
//    private val _navigationToBackEvent = MutableLiveData<Event<Unit>>()
//    val navigationToBackEvent: LiveData<Event<Unit>> = _navigationToBackEvent
//
//    private lateinit var treeMenuList: ArrayList<TreeMenuModel>
//
//    private val prefs = Preferences(_context)
//
//    private val parentJob = Job()
//    private val coroutineContext : CoroutineContext get() = parentJob + Dispatchers.Default
//    private val scope = CoroutineScope(coroutineContext)
//
//    fun initTopMenuCode() {
//        scope.launch {
//            try {
//                val response = repository.getCode(32)
//                if (response.code() == 200) {
//                    val codeModel: CodeModel = response.body() as CodeModel
//                    topMenuCode = codeModel.value?.toInt() ?: 20
//                }
//
//            } catch (e: Exception) { e.printStackTrace() }
//        }
//    }
//
//    fun initTreeMenuList() {
//        val token = prefs.token
//        val payload = JWTUtils.getPayload(token!!)
//        val jsonObject = JSONObject(payload).getString("treeMenuList")
//        val data = Gson()
//        Log.d("mlvmtest", "${data}")
//
//    }
//
//    fun onBackBtnClick(view: View?) {
//        _navigationToBackEvent.value = Event(Unit)
//    }
//
//}