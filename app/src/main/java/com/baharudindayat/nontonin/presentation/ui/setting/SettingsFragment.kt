package com.baharudindayat.nontonin.presentation.ui.setting

import android.content.Intent
import android.os.Bundle
import android.provider.Settings
import androidx.appcompat.app.AppCompatDelegate
import androidx.preference.PreferenceFragmentCompat
import com.baharudindayat.nontonin.R

class SettingsFragment : PreferenceFragmentCompat() {
        override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
            setPreferencesFromResource(R.xml.root_preferences, rootKey)

            val theme = findPreference<androidx.preference.ListPreference>(getString(R.string.pref_key_dark))
            theme?.setOnPreferenceChangeListener { _, newValue ->
                when(newValue){
                    "auto" -> updateTheme(AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM)
                    "on" -> updateTheme(AppCompatDelegate.MODE_NIGHT_YES)
                    "off" -> updateTheme(AppCompatDelegate.MODE_NIGHT_NO)
                }
                true
            }

            val language = findPreference<androidx.preference.Preference>(getString(R.string.pref_key_language))
            language?.onPreferenceClickListener = androidx.preference.Preference.OnPreferenceClickListener {
                val intent = Intent(Settings.ACTION_LOCALE_SETTINGS)
                startActivity(intent)
                true
            }

        }
        private fun updateTheme(mode: Int): Boolean {
            AppCompatDelegate.setDefaultNightMode(mode)
            requireActivity().recreate()
            return true
        }
}