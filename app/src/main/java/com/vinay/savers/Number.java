package com.vinay.savers;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.vinay.savers.Model.contact;

public class Number extends AppCompatActivity {

    EditText etNum,etAge,etName;
     String vall;
    Button btnAdd;

    FirebaseAuth auth;
    FirebaseUser user;

    //db refrence

    DatabaseReference dbNum;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_number);

        dbNum = FirebaseDatabase.getInstance().getReference("Number");

        //initialize UI elements
        etNum = findViewById(R.id.num);
        etName = findViewById(R.id.name);
        etAge = findViewById(R.id.age);

        btnAdd = findViewById(R.id.num_button);

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                add();

            }
        });

        auth=FirebaseAuth.getInstance();
        user=auth.getCurrentUser();

    }
    public void  add(){
        String Num=etNum.getText().toString().trim();
        String Age=etAge.getText().toString().trim();
        String Name=etName.getText().toString().trim();

        if(isValid(Num)&&isAge(Age)&&isName(Name))
        {
            if(user!=null)
            {
                vall=user.getUid();
            }
            String id=dbNum.push().getKey();
            contact num=new contact(id,Name,Age,Num);
            dbNum.child(vall).setValue(num);
            startActivity(new Intent(Number.this,Navigation.class));
           // Toast.makeText(getApplicationContext(),"added",Toast.LENGTH_LONG).show();
        }
        else
            {
            Toast.makeText(getApplicationContext(),"Please enter valid details",Toast.LENGTH_LONG).show();
        }
         etNum.setText(null);
    }
    boolean isValid(String phonenumber) {
        String PHONE_PATTERN = "^[789]\\d{9}$";
        Pattern pattern = Pattern.compile(PHONE_PATTERN);
        Matcher matcher = pattern.matcher(phonenumber);
        return matcher.matches();
    }
    boolean isAge(String age) {
        String AGE_PATTERN = "^[0123456789][0123456789]$";
        Pattern pattern = Pattern.compile(AGE_PATTERN);
        Matcher matcher = pattern.matcher(age);
        return matcher.matches();
    }
    boolean isName(String name) {
        String NAME_PATTERN = "^[a-zA-Z ]{5,30}$";
        Pattern pattern = Pattern.compile(NAME_PATTERN);
        Matcher matcher = pattern.matcher(name);
        return matcher.matches();
    }

}