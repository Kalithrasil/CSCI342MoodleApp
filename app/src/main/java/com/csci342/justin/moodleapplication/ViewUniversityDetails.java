package com.csci342.justin.moodleapplication;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;

import layout.ViewCalendar;

public class ViewUniversityDetails extends Activity {

    Intent previous;
    String authority;

    FragmentManager fm = getFragmentManager();
    Fragment frag;
    FrameLayout tabs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_university_details);

        previous = getIntent();
        authority = previous.getStringExtra("Authority");

        frag = new ViewCalendar();
        FragmentTransaction ft = fm.beginTransaction();

        tabs = (FrameLayout) findViewById(R.id.VUD_tabsview_framelayout);
        tabs.removeAllViews();
        ft.replace(R.id.VUD_tabsview_framelayout, frag).commit();

    }

    public void switchToViewCalendar(View v)
    {
        frag = new EditPersonalDetails();
        FragmentTransaction ft = fm.beginTransaction();
        tabs.removeAllViews();
        ft.replace(R.id.D_tabview_framelayout, frag).commit();
    }

    public void switchToViewContactUs(View v)
    {

    }

    public void switchToViewPolicies(View v)
    {

    }
}
