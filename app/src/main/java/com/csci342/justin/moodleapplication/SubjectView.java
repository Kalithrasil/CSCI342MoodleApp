package com.csci342.justin.moodleapplication;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class SubjectView extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_subject_view);

        Intent intent = getIntent();
        String subject = intent.getStringExtra("Subject Name");

        TextView temp = (TextView) findViewById(R.id.SV_subjectname_textview);
        temp.setText(subject);
    }
}
