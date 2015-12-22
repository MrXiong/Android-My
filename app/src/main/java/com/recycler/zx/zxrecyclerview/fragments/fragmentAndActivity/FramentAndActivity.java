package com.recycler.zx.zxrecyclerview.fragments.fragmentAndActivity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.recycler.zx.zxrecyclerview.R;

public class FramentAndActivity extends AppCompatActivity implements MenuFragment.MyMenuListener {

    private MenuFragment menuFragment;
    private MainFragment mainFragment;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_frament_and);
        menuFragment = (MenuFragment) getFragmentManager().findFragmentById(R.id.menu_fragment);
        mainFragment = (MainFragment) getFragmentManager().findFragmentById(R.id.main_frament);
    }

    @Override
    public void changeValue(String value) {
        mainFragment.setTvValue(value);

    }
}
