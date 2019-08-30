package com.example.fbdatabase;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
//import android.widget.Toolbar;

public class login extends AppCompatActivity {

    private Toolbar toolbar;
    private ProgressBar progressBar;
    private EditText uEmail;
    private EditText uPassword;
    private Button signIn;


    FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);


        toolbar = findViewById(R.id.toolbarId1);
        progressBar = findViewById(R.id.progressBarId1);
        uEmail = findViewById(R.id.userEmailId);
        uPassword = findViewById(R.id.userPasswordId);
        signIn = findViewById(R.id.btnSignInId);

        toolbar.setTitle(R.string.app_name);


        firebaseAuth = FirebaseAuth.getInstance();
        signIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                progressBar.setVisibility(View.VISIBLE);
                firebaseAuth.signInWithEmailAndPassword(uEmail.getText().toString(),uPassword.getText().toString())
                        .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        progressBar.setVisibility(View.GONE);
                        if (task.isSuccessful()){
                            if (firebaseAuth.getCurrentUser().isEmailVerified()) {
                                startActivity(new Intent(login.this,profile.class));
                                //finish();
                            }else {
                                Toast.makeText(login.this,"please verify ur email",Toast.LENGTH_LONG).show();
                            }

                        }else {

                            Toast.makeText(login.this,task.getException().getMessage(),Toast.LENGTH_LONG).show();

                        }
                    }
                });
            }
        });

    }
}
