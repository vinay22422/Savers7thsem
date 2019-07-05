package com.vinay.savers;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import com.vinay.savers.Model.contact;



public class MainActivity extends AppCompatActivity {
    Button btnSignOut;
    FirebaseAuth auth;
    FirebaseUser user;
    ProgressDialog PD;
    TextView num,email,name1,age1;
    private DatabaseReference dbref;
    String phnum,name,age;
    String vall;



    @Override    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        auth = FirebaseAuth.getInstance();
        user = auth.getCurrentUser();

        PD = new ProgressDialog(this);
        PD.setMessage("Loading...");
        PD.setCancelable(true);
        PD.setCanceledOnTouchOutside(false);


        num=findViewById(R.id.numm);
        email=findViewById(R.id.eemail);
        name1=findViewById(R.id.name);
        age1=findViewById(R.id.age);
        dbref= FirebaseDatabase.getInstance().getReference("Number");
        if(user!=null)
        {
            email.setText(user.getEmail().toString().trim());
            dbref.child(user.getUid().toString().trim());
            dbref.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    for(DataSnapshot msg : dataSnapshot.getChildren())
                    {
                        if(msg.getKey().equalsIgnoreCase(user.getUid().toString())) {
                            phnum = (String) msg.child("number").getValue(String.class);
                            name=(String)msg.child("name").getValue(String.class);
                            age = (String) msg.child("age").getValue(String.class);

                        }

                    }
                    num.setText(phnum);
                    name1.setText(name);
                    age1.setText(age);

                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }
            });

        }
        name1.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                contact con=new contact();
                showUpdateName(con.getId(),con.getName());
                return false;
            }
        } );
        age1.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                contact con=new contact();
                showUpdateAge(con.getId(),con.getAge());
                return false;
            }
        } );
        num.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                contact con=new contact();
                showUpdateNumber(con.getId(),con.getNumber());
                return false;
            }
        } );




    }

    public String getPhnum() {
        return phnum;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.edit:
                auth.signOut();
                //startActivity(new Intent(MainActivity.this, Login.class));
                startActivity(new Intent(MainActivity.this, Login.class));
                finish();


                FirebaseAuth.AuthStateListener authListener = new FirebaseAuth.AuthStateListener() {
                    @Override
                    public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                        FirebaseUser user1 = firebaseAuth.getCurrentUser();
                        if (user1 == null) {
                            startActivity(new Intent(MainActivity.this, Login.class));
                            finish();
                        }
                    }
                };
                break;
            case R.id.search:
                startActivity(new Intent(getApplicationContext(), ForgetPassword.class).putExtra("Mode", 1));
                break;
            case R.id.add:
                startActivity(new Intent(getApplicationContext(), ForgetPassword.class).putExtra("Mode", 2));
                break;
            case R.id.delete:
                startActivity(new Intent(getApplicationContext(), ForgetPassword.class).putExtra("Mode", 3));
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override    protected void onResume() {
        if (auth.getCurrentUser() == null) {
            startActivity(new Intent(MainActivity.this, Login.class));
            finish();
        }
        super.onResume();
    }

    private void showUpdateName(final String artistId, final String artistName) {

        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(this);
        LayoutInflater inflater = getLayoutInflater();
        final View dialogView = inflater.inflate(R.layout.update_dialog, null);
        dialogBuilder.setView(dialogView);
        final EditText etArtistName = dialogView.findViewById(R.id.editTextName);
        Button btnUpdate = dialogView.findViewById(R.id.buttonUpdateArtist);
        dialogBuilder.setTitle(artistName);

        final AlertDialog alert = dialogBuilder.create();
        alert.show();


        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String name = etArtistName.getText().toString().trim();
                if(isName(name)) {
                    if (user != null) {
                        vall = user.getUid();
                    }
                    String id = dbref.push().getKey();
                    contact num = new contact(id, name, phnum, age);
                    dbref.child(vall).setValue(num);
                    startActivity(new Intent(MainActivity.this, Navigation.class));
                    alert.dismiss();
                }
                else
                {
                    Toast.makeText(getApplicationContext(),"Please enter valid  name",Toast.LENGTH_LONG).show();
                }

            }


        });
    }

    private void showUpdateAge(final String artistId, final String Age) {

        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(this);
        LayoutInflater inflater = getLayoutInflater();
        final View dialogView = inflater.inflate(R.layout.update_dialog, null);
        dialogBuilder.setView(dialogView);
        final EditText etArtistName = dialogView.findViewById(R.id.editTextName);
        Button btnUpdate = dialogView.findViewById(R.id.buttonUpdateArtist);
        dialogBuilder.setTitle(Age);

        final AlertDialog alert = dialogBuilder.create();
        alert.show();


        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String aget= etArtistName.getText().toString().trim();
                if(isAge(aget)) {
                    if (user != null) {
                        vall = user.getUid();
                    }
                    String id = dbref.push().getKey();
                    contact num = new contact(id, name, aget, phnum);
                    dbref.child(vall).setValue(num);
                    startActivity(new Intent(MainActivity.this, Navigation.class));
                    alert.dismiss();

                }
                else
                {
                    Toast.makeText(getApplicationContext(),"Please enter valid  age",Toast.LENGTH_LONG).show();
                }
            }


        });
    }
    private void showUpdateNumber(final String artistId, final String Number) {

        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(this);
        LayoutInflater inflater = getLayoutInflater();
        final View dialogView = inflater.inflate(R.layout.update_dialog, null);
        dialogBuilder.setView(dialogView);
        final EditText etArtistName = dialogView.findViewById(R.id.editTextName);
        Button btnUpdate = dialogView.findViewById(R.id.buttonUpdateArtist);
        dialogBuilder.setTitle(Number);

        final AlertDialog alert = dialogBuilder.create();
        alert.show();


        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String numbb= etArtistName.getText().toString().trim();
                if(isValid(numbb)) {
                    if (user != null) {
                        vall = user.getUid();
                    }
                    String id = dbref.push().getKey();
                    contact num = new contact(id, name, age, numbb);
                    dbref.child(vall).setValue(num);
                    startActivity(new Intent(MainActivity.this, Navigation.class));
                    alert.dismiss();
                }
                else
                {
                    Toast.makeText(getApplicationContext(),"Please enter valid number",Toast.LENGTH_LONG).show();
                }

            }


        });
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
    boolean isName(String age) {
        String NAME_PATTERN = "^[a-zA-Z ]{5,30}$";
        Pattern pattern = Pattern.compile(NAME_PATTERN);
        Matcher matcher = pattern.matcher(age);
        return matcher.matches();
    }


}