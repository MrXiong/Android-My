package com.recycler.zx.zxrecyclerview.fragments.preference;

import android.os.Bundle;
import android.preference.PreferenceFragment;

import com.recycler.zx.zxrecyclerview.R;

public class PrefsFragment extends PreferenceFragment {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Load the preferences from an XML resource
        addPreferencesFromResource(R.xml.my_preference);
    }
}