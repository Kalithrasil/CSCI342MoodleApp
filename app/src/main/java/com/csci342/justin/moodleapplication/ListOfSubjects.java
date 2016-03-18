package com.csci342.justin.moodleapplication;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;

import java.util.ArrayList;

public class ListOfSubjects extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_of_subjects);

        Spinner spinner = (Spinner) findViewById(R.id.LOS_yearselection_spinner);

        String[] years = new String[] {"2016", "2015", "2014"};

        final ArrayAdapter spinner_adapter = new ArrayAdapter(this, android.R.layout.simple_spinner_item, years);

        spinner_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinner.setAdapter(spinner_adapter);

        final ListView listview = (ListView) findViewById(R.id.LOS_subjectlist_listview);

        //receive info from online database to fill the listview
            //and set the year (default)
        String[] values = new String[] {"CSCI015", "CSCI103", "CSCI131", "CSCI114", "CSCI124", "CSCI203", "CSCI235"};
        final ArrayList<String> list = new ArrayList<String>();
        for(int i= 0; i < values.length; ++i)
        {
            list.add(values[i]);
        }

        final ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, list);
        listview.setAdapter(adapter);

        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, final View view,
                                    int position, long id) {
                String x = (String) adapter.getItem(position);

                Intent i = new Intent(ListOfSubjects.this, SubjectView.class);
                i.putExtra("Subject Name", x);
                startActivity(i);
            }

        });
    }

}
