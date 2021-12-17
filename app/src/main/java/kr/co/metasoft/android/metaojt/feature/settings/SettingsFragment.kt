package kr.co.metasoft.android.metaojt.feature.settings

import android.content.SharedPreferences
import android.os.Bundle
import androidx.preference.Preference
import androidx.preference.PreferenceFragmentCompat
import kr.co.metasoft.android.metaojt.R

class SettingsFragment :PreferenceFragmentCompat() {

    lateinit var prefs : SharedPreferences

    var autoLoginPreference: Preference? = null
    var pushNotificationPreference: Preference? = null

    override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
        setPreferencesFromResource(R.xml.basic_preferences, rootKey)

        if (rootKey == null) {
            autoLoginPreference = findPreference("auto_login")
            pushNotificationPreference = findPreference("push_notification")

        }
    }


}