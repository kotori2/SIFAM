package com.caraxian.sifam;

import android.content.Context;
import android.os.Bundle;
import android.util.TypedValue;
import android.util.Log;

import androidx.appcompat.view.ContextThemeWrapper;
import androidx.preference.CheckBoxPreference;
import androidx.preference.PreferenceFragmentCompat;
import androidx.preference.PreferenceScreen;

public class AppPrefFragment extends PreferenceFragmentCompat {
    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        Log.d("SIFAM", "onCreate");

        Context activityContext = getActivity();

        TypedValue themeTypedValue = new TypedValue();
        activityContext.getTheme().resolveAttribute(R.attr.preferenceTheme, themeTypedValue, true);
        ContextThemeWrapper contextThemeWrapper = new ContextThemeWrapper(activityContext, themeTypedValue.resourceId);
        PreferenceScreen serverSetting = (PreferenceScreen)findPreference("screen_server_setting");
        if(serverSetting == null){
            //当不是服务器列表那一屏的时候不要往里面加
            return;
        }

        for (Server s : SIFAM.serverList) {
            String extraMessage = "";
            if (!s.installed) {
                extraMessage = "\n" + getString(R.string.summary_not_installed);
            }
            addCheckbox(contextThemeWrapper, serverSetting, s.name, s.code, getString(R.string.summary_server_on) + extraMessage, getString(R.string.summary_server_off) + extraMessage, s.installed);
        }

    }

    @Override
    public void onCreatePreferences(Bundle bundle, String rootKey){
        if(getArguments() != null){
            String key = getArguments().getString("rootKey");
            setPreferencesFromResource(R.xml.preference_setting, key);
        }else{
            setPreferencesFromResource(R.xml.preference_setting, rootKey);
        }
    }

    @Override
    public void onNavigateToScreen(PreferenceScreen preferenceScreen){
        AppPrefFragment applicationPreferencesFragment = new AppPrefFragment();
        Bundle args = new Bundle();
        args.putString("rootKey", preferenceScreen.getKey());
        applicationPreferencesFragment.setArguments(args);
        getFragmentManager()
                .beginTransaction()
                .replace(getId(), applicationPreferencesFragment)
                .addToBackStack(null)
                .commit();
    }

    private void addCheckbox(ContextThemeWrapper contextThemeWrapper, PreferenceScreen serverSetting,
                             String title, String key, String summaryOn, String summaryOff, boolean is_installed){

        CheckBoxPreference checkBoxPreference = new CheckBoxPreference(contextThemeWrapper);
        checkBoxPreference.setTitle(title);
        checkBoxPreference.setKey(key);
        checkBoxPreference.setSummaryOn(summaryOn);
        checkBoxPreference.setSummaryOff(summaryOff);
        if(!is_installed) {
            checkBoxPreference.setChecked(false);
            checkBoxPreference.setEnabled(false);
        }
        serverSetting.addPreference(checkBoxPreference);

    }
}
