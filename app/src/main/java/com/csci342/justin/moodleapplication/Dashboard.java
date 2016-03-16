package com.csci342.justin.moodleapplication;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;

import layout.EditPersonalDetails;
import layout.ViewUniversityInfo;

public class Dashboard extends Activity{

    FragmentManager fm = getFragmentManager();
    Fragment frag;
    FrameLayout tabs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        frag = new EditPersonalDetails();
        FragmentTransaction ft = fm.beginTransaction();

        tabs = (FrameLayout) findViewById(R.id.D_tabview_framelayout);
        tabs.removeAllViews();
        ft.replace(R.id.D_tabview_framelayout, frag).commit();
    }

    public void toEditDetails(View v)
    {
            frag = new EditPersonalDetails();
            FragmentTransaction ft = fm.beginTransaction();
            tabs.removeAllViews();
            ft.replace(R.id.D_tabview_framelayout, frag).commit();
    }

    public void toViewUniInfo(View v)
    {
            frag = new ViewUniversityInfo();
            FragmentTransaction ft = fm.beginTransaction();
            tabs.removeAllViews();
            ft.replace(R.id.D_tabview_framelayout, frag).commit();
    }

}
