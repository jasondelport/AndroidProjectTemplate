package com.jasondelport.notes.ui.activity;

import android.app.Fragment;
import android.os.Bundle;

import com.jasondelport.notes.Constants;
import com.jasondelport.notes.ui.fragment.PercentFragment;


public class PercentActivity extends BaseActivity {

    private Fragment fragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (savedInstanceState == null) {
            fragment = PercentFragment.newInstance();
            fragment.setArguments(getIntent().getExtras());
            getFragmentManager().beginTransaction().add(android.R.id.content, fragment, Constants.PERCENT_FRAGMENT_TAG).commit();
        } else {
            fragment = getFragmentManager().getFragment(savedInstanceState, "fragment");
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        getFragmentManager().putFragment(outState, "fragment", fragment);
    }
}
