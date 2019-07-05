package com.vinay.savers;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
//import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;
import com.google.firebase.auth.FirebaseAuthWeakPasswordException;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Register extends AppCompatActivity {

    private EditText inputEmail, inputPassword;
    private FirebaseAuth auth;
    private Button btnSignUp, btnLogin;
    private ProgressDialog PD;



    @Override    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        getSupportActionBar().hide();

        PD = new ProgressDialog(this);
        PD.setMessage("Loading...");
        PD.setCancelable(true);
        PD.setCanceledOnTouchOutside(false);

        auth = FirebaseAuth.getInstance();

        if (auth.getCurrentUser() != null) {
            startActivity(new Intent(Register.this, MainActivity.class));
            finish();
        }

        inputEmail = (EditText) findViewById(R.id.email);
        inputPassword = (EditText) findViewById(R.id.password);
        btnSignUp = (Button) findViewById(R.id.sign_up_button);
        btnLogin = (Button) findViewById(R.id.sign_in_button);

        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override            public void onClick(View view) {
                final String email = inputEmail.getText().toString();
                final String password = inputPassword.getText().toString();


                try {
                    if (password.length() > 0 && email.length() > 0) {
                        if(valid(email))
                        {
                            PD.show();
                            auth.createUserWithEmailAndPassword(email, password)
                                    .addOnCompleteListener(Register.this, new OnCompleteListener<AuthResult>() {
                                        @Override
                                        public void onComplete(@NonNull Task<AuthResult> task) {
                                            if (!task.isSuccessful()) {

                                                try
                                                {
                                                    throw task.getException();
                                                }
                                                catch (FirebaseAuthWeakPasswordException e)
                                                {
                                                    Toast.makeText(
                                                            Register.this,
                                                            "Weak Password",
                                                            Toast.LENGTH_LONG).show();
                                                }
                                                catch (FirebaseAuthInvalidCredentialsException e)
                                                {
                                                    Toast.makeText(
                                                            Register.this,
                                                            "Invalid Credentials",
                                                            Toast.LENGTH_LONG).show();
                                                }
                                                catch (FirebaseAuthUserCollisionException e)
                                                {
                                                    Toast.makeText(
                                                            Register.this,
                                                            "UserCollisionException",
                                                            Toast.LENGTH_LONG).show();
                                                }
                                                catch(Exception e)
                                                {
                                                    Toast.makeText(
                                                            Register.this,
                                                            "Fill All Fields",
                                                            Toast.LENGTH_LONG).show();
                                                }  } else {
                                                Intent intent = new Intent(Register.this, Number.class);
                                                startActivity(intent);
                                                finish();
                                            }
                                            PD.dismiss();
                                        }
                                    });
                        } else {
                            Toast.makeText(
                                    Register.this,
                                    "invalid email/Fill All Fields",
                                    Toast.LENGTH_LONG).show();
                        }}
                } catch (Exception e) {
                    Toast.makeText(Register.this, "invalid input", Toast.LENGTH_SHORT).show();
                    e.printStackTrace();
                }
            }
        });

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override            public void onClick(View view) {
                finish();
            }
        });


    }
    public boolean valid(String a)
    {
        final String EMAIL_PATTERN =
                "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
                        + "[A-Za-z]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
        Pattern pattern = Pattern.compile(EMAIL_PATTERN);
        Matcher matcher = pattern.matcher(a);
        if (matcher.find()) {
            String email = a.substring(matcher.start(), matcher.end());
        } else {
            // TODO handle condition when input doesn't have an email address
            Toast.makeText(
                    Register.this,
                    "Invalid Email",
                    Toast.LENGTH_LONG).show();
            //inputEmail.setError("Enter valid Email");
            return false;

        }
        return true;

    }
}

