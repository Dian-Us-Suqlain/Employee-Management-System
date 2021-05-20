package com.example.employeemanagementsystem.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.employeemanagementsystem.Adapter.EmployeeAdapter;
import com.example.employeemanagementsystem.Adapter.EmployeeAttendanceAdapter;
import com.example.employeemanagementsystem.ModelClass.Employee;
import com.example.employeemanagementsystem.R;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.FirebaseDatabase;

public class EmployeeAttendance extends AppCompatActivity {

    RecyclerView EmployeeRecyclerView;
    EmployeeAttendanceAdapter myAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_employee_attendance);

        EmployeeRecyclerView = findViewById(R.id.employee_recycler_view);

        EmployeeRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        FirebaseRecyclerOptions<Employee> options =
                new FirebaseRecyclerOptions.Builder<Employee>()
                        .setQuery(FirebaseDatabase.getInstance().getReference("Employee"), Employee.class)
                        .build();

        myAdapter = new EmployeeAttendanceAdapter(options, this);
        EmployeeRecyclerView.setAdapter(myAdapter);
    }

    /*public void EditEmployee(View v){
        //Toast.makeText(this, "Edit Employee", Toast.LENGTH_SHORT).show();
        setContentView(R.layout.update_employee);

    }

    public void DeleteEmployee(View v){
        Toast.makeText(this, "Delete Employee", Toast.LENGTH_SHORT).show();
    }*/

    @Override
    protected void onStart() {
        super.onStart();
        myAdapter.startListening();
    }

    @Override
    protected void onStop() {
        super.onStop();
        myAdapter.stopListening();
    }
}