package com.kevin.tech.kevinsummary.fragment;

import android.os.Bundle;
import android.preference.Preference;
import android.preference.PreferenceFragment;
import android.preference.SwitchPreference;
import android.support.annotation.Nullable;

import com.kevin.tech.kevinsummary.R;
import com.kevin.tech.kevinsummary.constants.Constants;
import com.kevin.tech.kevinsummary.uitls.SPUtil;

import skin.support.SkinCompatManager;

/**
 * Created by <a href="http://blog.csdn.net/student9128">Kevin</a> for Project KevinSummary on 2017/9/14.
 * <h3>Description:</h3>
 * <div>
 * <div/>
 */


public class SettingFragment extends PreferenceFragment implements Preference.OnPreferenceChangeListener {
    public static final String SWITCH_SKIN = "switchSkin";
    private SwitchPreference switchSkin;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addPreferencesFromResource(R.xml.setting_preferences);
        switchSkin = (SwitchPreference) findPreference(SWITCH_SKIN);
        Boolean skin = SPUtil.getBooleanSP(Constants.KEY_SKIN, getActivity());
        if (skin) {
            switchSkin.setChecked(true);
        } else {
            switchSkin.setChecked(false);
        }
        switchSkin.setOnPreferenceChangeListener(this);
    }

    @Override
    public boolean onPreferenceChange(Preference preference, Object newValue) {
        boolean value = (boolean) newValue;
        if (value) {
            SPUtil.setSP(Constants.KEY_SKIN, getActivity(), true);
            SkinCompatManager.getInstance().loadSkin("night", SkinCompatManager.SKIN_LOADER_STRATEGY_BUILD_IN);
        } else {
            SPUtil.setSP(Constants.KEY_SKIN, getActivity(), false);
            SkinCompatManager.getInstance().restoreDefaultTheme();
        }
        return true;
    }
}
