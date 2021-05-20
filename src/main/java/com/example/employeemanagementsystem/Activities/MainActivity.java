package com.example.employeemanagementsystem.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.example.employeemanagementsystem.ModelClass.Admin;
import com.example.employeemanagementsystem.R;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MainActivity extends AppCompatActivity {

    EditText etCompanyName, etDesignation, etUsername, etMobileNo, etEmail;
    //DatabaseReference mRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getIntent();
        init();
    }

    void init() {
        etCompanyName = findViewById(R.id.et_company_name);
        etDesignation = findViewById(R.id.et_designation);
        etUsername =    findViewById(R.id.et_username);
        etMobileNo =    findViewById(R.id.et_mobile_no);
        etEmail =       findViewById(R.id.et_email);

        //mRef = FirebaseDatabase.getInstance().getReference("Admin");
    }

    public void SaveButton(View v) {
        /*String compName, adminDesig, username, adminMobNo, adminEmail;
        compName =   etCompanyName.getText().toString().trim();
        adminDesig = etDesignation.getText().toString().trim();
        username =   etUsername.getText().toString().trim();
        adminMobNo = etMobileNo.getText().toString().trim();
        adminEmail = etEmail.getText().toString().trim();

        String id;
        id = mRef.push().getKey();

        Admin admin = new Admin(compName, adminDesig, username, adminMobNo, adminEmail);
        assert id != null;
        mRef.child(id).setValue(admin);*/

        Intent send = new Intent(MainActivity.this, HomeScreen.class);

        startActivity(send);
        finish();
    }
}
