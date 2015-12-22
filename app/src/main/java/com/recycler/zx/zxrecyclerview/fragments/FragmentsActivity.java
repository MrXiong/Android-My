package com.recycler.zx.zxrecyclerview.fragments;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.recycler.zx.zxrecyclerview.R;

public class FragmentsActivity extends AppCompatActivity {


    TitleFragment titleFragment;
    ContentFragment contentFragment;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fragments);
        titleFragment = (TitleFragment) getFragmentManager().findFragmentById(R.id.fragment_title);
        contentFragment = (ContentFragment) getFragmentManager().findFragmentById(R.id.fragment_content);
    }
}
