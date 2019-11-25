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

import com.example.demo.eatit.Model_user.common.common;
import com.example.demo.eatit.Model_user.user;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class sign_in extends AppCompatActivity {

   // @Override
    EditText number , password;
    Button signin;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_sign_in);
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        final DatabaseReference table = database.getReference("users");
        signin = (Button)findViewById(R.id.btn_signin);
        number = (EditText)findViewById(R.id.number);
        password = (EditText)findViewById(R.id.password);
        signin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final ProgressDialog mdialog = new ProgressDialog(sign_in.this);
                mdialog.setMessage("Please wait....");
                mdialog.show();
                table.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        if (dataSnapshot.child(number.getText().toString()).exists()) {
                            mdialog.dismiss();
                            user user = dataSnapshot.child(number.getText().toString()).getValue(user.class);
                            if (user.getPassword().equals(password.getText().toString())) {
                                Intent intent = new Intent(sign_in.this,home.class);
                                //startActivity(intent);
                                common.current_user = user;
                                startActivity(intent);
                                finish();
                                Toast.makeText(sign_in.this, "Sign in succesfull", Toast.LENGTH_SHORT).show();
                            } else {
                                Toast.makeText(sign_in.this, "Sign in error", Toast.LENGTH_SHORT).show();
                            }
                        } else {
                            Toast.makeText(sign_in.this,"User does not exist",Toast.LENGTH_SHORT).show();

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
