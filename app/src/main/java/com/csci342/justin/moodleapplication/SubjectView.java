package com.csci342.justin.moodleapplication;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;

import layout.UploadAssignment;
import layout.ViewGrades;
import layout.ViewResources;

public class SubjectView extends Activity {

    Intent previous;
    String authority;

    FragmentManager fm = getFragmentManager();
    Fragment frag;
    FrameLayout tabs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_subject_view);

        previous = getIntent();
        authority = previous.getStringExtra("Authority");
        String subject = previous.getStringExtra("Subject Name");

        TextView temp = (TextView) findViewById(R.id.SV_subjectname_textview);
        temp.setText(subject);

        if(authority.equals("Teacher"))
        {
            frag = new ViewResources();
            FragmentTransaction ft = fm.beginTransaction();

            tabs = (FrameLayout) findViewById(R.id.SV_tabview_framelayout);
            tabs.removeAllViews();
            ft.replace(R.id.SV_tabview_framelayout, frag).commit();
        }
        else
        {
            frag = new ViewResources();
            FragmentTransaction ft = fm.beginTransaction();

            tabs = (FrameLayout) findViewById(R.id.SV_tabview_framelayout);
            tabs.removeAllViews();
            ft.replace(R.id.SV_tabview_framelayout, frag).commit();
        }
    }

    public void switchToViewResources(View v)
    {
        frag = new ViewResources();
        FragmentTransaction ft = fm.beginTransaction();
        tabs.removeAllViews();
        ft.replace(R.id.SV_tabview_framelayout, frag).commit();
    }

    public void switchToViewGrades(View v)
    {
        frag = new ViewGrades();
        FragmentTransaction ft = fm.beginTransaction();
        tabs.removeAllViews();
        ft.replace(R.id.SV_tabview_framelayout, frag).commit();
    }

    public void switchToUploadAssignment(View v)
    {
        frag = new UploadAssignment();
        FragmentTransaction ft = fm.beginTransaction();
        tabs.removeAllViews();
        ft.replace(R.id.SV_tabview_framelayout, frag).commit();
    }
}
