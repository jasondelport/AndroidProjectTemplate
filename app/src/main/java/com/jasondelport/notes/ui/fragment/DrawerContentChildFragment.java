package com.jasondelport.notes.ui.fragment;

import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import com.jasondelport.notes.Constants;
import com.jasondelport.notes.R;
import com.jasondelport.notes.listener.OnNavigationEventListener;
import com.jasondelport.notes.ui.activity.DrawerActivity;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;


public class DrawerContentChildFragment extends BaseFragment implements OnNavigationEventListener {

    @Bind(R.id.text)
    TextView text;
    private String page;
    private boolean isDifferent;
    public DrawerContentChildFragment() {

    }

    public static DrawerContentChildFragment newInstance(String page) {
        DrawerContentChildFragment fragment = new DrawerContentChildFragment();
        Bundle args = new Bundle();
        args.putString("page", page);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setRetainInstance(false);
        this.setHasOptionsMenu(false);
        this.page = getArguments().getString("page");
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_drawer_content_child, container, false);
        ButterKnife.bind(this, view);

        text.setText(this.page);

        return view;
    }

    @OnClick(R.id.button)
    void loadPage() {
        ((DrawerActivity) getParentFragment().getActivity()).loadContent(Constants.FRAGMENT_CONTENT_3);
    }

    @OnClick(R.id.checkbox)
    void changeBoolean(View view) {
        if (((CheckBox) view).isChecked()) {
            this.isDifferent = true;
            ((DrawerContentParentFragment) getParentFragment()).setOnNavigationEventListener(this);
        } else {
            this.isDifferent = false;
            ((DrawerContentParentFragment) getParentFragment()).setOnNavigationEventListener(null);
        }
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (isVisibleToUser) {
            if (getView() != null && getParentFragment() != null) {
                if (((CheckBox) getView().findViewById(R.id.checkbox)).isChecked()) {
                    ((DrawerContentParentFragment) getParentFragment()).setOnNavigationEventListener(this);
                } else {
                    ((DrawerContentParentFragment) getParentFragment()).setOnNavigationEventListener(null);
                }
            }
        } else {
            if (this.isDifferent) {
                Snackbar.make(getView(), "Fragment hidden", Snackbar.LENGTH_LONG).show();
            }
        }
    }

    @Override
    public void onHomePressed() {
        Snackbar.make(getView(), "Home Intercepted", Snackbar.LENGTH_LONG).show();
    }

    @Override
    public void onBackPressed() {
        Snackbar.make(getView(), "Back Intercepted", Snackbar.LENGTH_LONG).show();
    }
}
