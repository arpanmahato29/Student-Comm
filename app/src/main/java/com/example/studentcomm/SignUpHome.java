package com.example.studentcomm;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.studentcomm.ClubHead.ClubheadSignup;
import com.example.studentcomm.Faculty.FacultySignUp;
import com.example.studentcomm.Student.StudentSignUp;

public class SignUpHome extends AppCompatActivity implements View.OnClickListener {

    private Button student,faculty,clubHead;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up_home);

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
            Intent intentStudent=new Intent(SignUpHome.this, StudentSignUp.class);
            startActivity(intentStudent);
        }
        if(v==faculty) {
            Intent intentFaculty=new Intent(SignUpHome.this, FacultySignUp.class);
            startActivity(intentFaculty);
        }
        if(v==clubHead) {
            Intent intentClubHead=new Intent(SignUpHome.this, ClubheadSignup.class);
            startActivity(intentClubHead);
        }

    }
}
