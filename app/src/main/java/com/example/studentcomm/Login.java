package com.example.studentcomm;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class Login extends AppCompatActivity implements View.OnClickListener {

    private EditText email,password;
    private Button login,signup;
    private FirebaseAuth firebaseAuth;
    private String strEmail,strPassowrd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        email=findViewById(R.id.edit_email);
        password=findViewById(R.id.edit_password);
        login=findViewById(R.id.button_login);
        signup=findViewById(R.id.button_signup);

        firebaseAuth=FirebaseAuth.getInstance();

        login.setOnClickListener(this);
        signup.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

        if(v==login) {
            loginMethod();
        }
        if(v==signup) {
            Intent intentSignup=new Intent(Login.this,SignUpHome.class);
            startActivity(intentSignup);
        }
    }

    private void loginMethod() {

        strEmail=email.getText().toString();
        strPassowrd=password.getText().toString();

        if(TextUtils.isEmpty(strEmail)){
            email.setError("Required");
        }
        if(TextUtils.isEmpty(strPassowrd)){
            password.setError("Required");
        }else {
            firebaseAuth.signInWithEmailAndPassword(strEmail,strPassowrd)
                    .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                Toast.makeText(Login.this,task.getException().getMessage(), Toast.LENGTH_LONG).show();

                            } else {
                                Toast.makeText(Login.this,task.getException().getMessage(),Toast.LENGTH_LONG).show();
                            }

                        }
                    });
        }
    }
}
