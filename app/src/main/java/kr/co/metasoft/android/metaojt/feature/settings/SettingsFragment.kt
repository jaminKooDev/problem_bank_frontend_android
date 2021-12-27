package kr.co.metasoft.android.metaojt.feature.settings

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import androidx.activity.OnBackPressedCallback
import androidx.navigation.fragment.findNavController
import androidx.preference.Preference
import androidx.preference.PreferenceFragmentCompat
import kr.co.metasoft.android.metaojt.R

class SettingsFragment :PreferenceFragmentCompat() {

    lateinit var prefs : SharedPreferences
    private lateinit var callback: OnBackPressedCallback

    var autoLoginPreference: Preference? = null
    var pushNotificationPreference: Preference? = null

    override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
        setPreferencesFromResource(R.xml.basic_preferences, rootKey)

        if (rootKey == null) {
            autoLoginPreference = findPreference("auto_login")
            pushNotificationPreference = findPreference("push_notification")

        }
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        callback = object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                Log.d("back", "preessed)_")
                findNavController().popBackStack()
            }
        }
        requireActivity().onBackPressedDispatcher.addCallback(this, callback)
    }

    override fun onDetach() {
        super.onDetach()
        callback.remove()
    }
}