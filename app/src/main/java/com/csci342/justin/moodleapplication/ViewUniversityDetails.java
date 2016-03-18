package com.csci342.justin.moodleapplication;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

public class ViewUniversityDetails extends Activity {

    Intent previous;
    String authority;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_university_details);

        previous = getIntent();
        authority = previous.getStringExtra("Authority");
    }
}
