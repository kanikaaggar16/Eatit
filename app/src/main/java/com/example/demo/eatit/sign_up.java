package com.example.demo.eatit;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.demo.eatit.Model_user.user;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class sign_up extends AppCompatActivity {
    private Button sign_up;
    private EditText number , name , password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_sign_up);
        number = (EditText)findViewById(R.id.ed_number);
        name = (EditText)findViewById(R.id.ed_name);
        password = (EditText)findViewById(R.id.ed_password);
        sign_up = (Button)findViewById(R.id.signup);
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        final DatabaseReference table = database.getReference("users");
        sign_up.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final ProgressDialog mdialog = new ProgressDialog(sign_up.this);
                mdialog.setMessage("Please wait....");
                mdialog.show();
                table.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        if(dataSnapshot.child(number.getText().toString()).exists())
                        {
                            mdialog.dismiss();
                            Toast.makeText(com.example.demo.eatit.sign_up.this,"Phone number already exists",Toast.LENGTH_SHORT).show();
                        }
                        else
                        {
                            mdialog.dismiss();
                            user user = new user(name.getText().toString(),password.getText().toString());
                            table.child(number.getText().toString()).setValue(user);
                            Toast.makeText(com.example.demo.eatit.sign_up.this,"Sign up succesfull",Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(sign_up.this,home.class);
                            startActivity(intent);

                            finish();
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });
            }
        });
    }
}
