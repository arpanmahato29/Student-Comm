package com.example.studentcomm;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button student,faculty,clubHead;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        student=findViewById(R.id.button_student);
        faculty=findViewById(R.id.button_faculty);
        clubHead=findViewById(R.id.button_clubhead);

        student.setOnClickListener(this);
        faculty.setOnClickListener(this);
        clubHead.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {

        if(v==student) {
            Intent intentStudent=new Intent(MainActivity.this,Login.class);
            startActivity(intentStudent);
        }
        if(v==faculty) {
            Intent intentStudent=new Intent(MainActivity.this,Login.class);
            startActivity(intentStudent);
        }
        if(v==clubHead) {
            Intent intentStudent=new Intent(MainActivity.this,Login.class);
            startActivity(intentStudent);
        }
    }
}
