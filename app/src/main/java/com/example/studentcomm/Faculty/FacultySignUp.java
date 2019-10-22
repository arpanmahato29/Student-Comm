package com.example.studentcomm.Faculty;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.studentcomm.R;
import com.example.studentcomm.Users;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class FacultySignUp extends AppCompatActivity implements View.OnClickListener {
    private EditText name, email, password, confirmPassword;
    private Spinner college, branch, post;
    private Button signup;
    private FirebaseAuth firebaseAuth;
    private FirebaseDatabase firebaseDatabase;
    private DatabaseReference faculty;
    private String strName, strEmail, strPassword, strConfirmPassword, strCollege, strBranch, strPost;
    private String[] spinCOllege, spinBranch, spinPost;
    Users users;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_faculty_sign_up);
        users = new Users();

        name = findViewById(R.id.edit_name);
        email = findViewById(R.id.edit_email);
        password = findViewById(R.id.edit_password);
        confirmPassword = findViewById(R.id.edit_confirm_password);

        college = findViewById(R.id.spinner_college);
        branch = findViewById(R.id.spinner_branch);
        post = findViewById(R.id.spinner_post);

        signup = findViewById(R.id.button_signup);

        spinCOllege = new String[]{"C V Raman", "IIT Mumbai", "IIT Delhi"};
        ArrayAdapter adapterCollege = new ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, spinCOllege);
        college.setAdapter(adapterCollege);
        college.setPrompt("College");
        college.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                strCollege = spinCOllege[position];
                Toast.makeText(FacultySignUp.this, strCollege, Toast.LENGTH_SHORT).show();

            }
        });

        spinBranch = new String[]{"CSE", "IT", "EE", "ETC", "EEE", "MECH", "CHEM", "CIVIL"};
        ArrayAdapter adapterBranch = new ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, spinBranch);
        branch.setAdapter(adapterBranch);
        branch.setPrompt("Branch");
        branch.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                strBranch = spinBranch[position];
                Toast.makeText(FacultySignUp.this, strBranch, Toast.LENGTH_SHORT).show();
            }
        });

        spinPost = new String[]{"Principal", "Vice Pricipal", "Professor", "Assistant Prof", "Lab Assistant"};
        ArrayAdapter adapterPost = new ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, spinPost);
        post.setAdapter(adapterPost);
        post.setPrompt("Post");
        post.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                strPost = spinPost[position];
                Toast.makeText(FacultySignUp.this, strPost, Toast.LENGTH_SHORT).show();
            }
        });

        firebaseAuth = FirebaseAuth.getInstance();
        firebaseDatabase = FirebaseDatabase.getInstance();
        faculty = firebaseDatabase.getReference("Faculty");

        signup.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {

        if (v == signup) {
            signupMethod();
        }
    }

    private void signupMethod() {
        strName = name.getText().toString();
        strEmail = email.getText().toString();
        strPassword = password.getText().toString();
        strConfirmPassword = confirmPassword.getText().toString();
        users.setName(strName);
        users.setEmail(strEmail);
        users.setCollege(strCollege);
        users.setBranch(strBranch);
        users.setPost(strPost);

        if (TextUtils.isEmpty(strName)) {
            name.setError("Required");
        }
        if (TextUtils.isEmpty(strEmail)) {
            email.setError("Required");
        }
        if (TextUtils.isEmpty(strPassword)) {
            password.setError("Required");
        }
        if (TextUtils.isEmpty(strConfirmPassword)) {
            confirmPassword.setError("Required");
        } else {
            if (strPassword.equals(strConfirmPassword)) {

                firebaseAuth.signInWithEmailAndPassword(strEmail, strPassword)
                        .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {
                                    Toast.makeText(FacultySignUp.this, task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                                    faculty.child(strName).setValue(users);
                                }
                                else{
                                    Toast.makeText(FacultySignUp.this,task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                                }

                            }
                        });


            }
        }
    }
}