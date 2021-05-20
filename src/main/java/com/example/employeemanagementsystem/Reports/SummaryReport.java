package com.example.employeemanagementsystem.Reports;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.employeemanagementsystem.ModelClass.Employee;
import com.example.employeemanagementsystem.R;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class SummaryReport extends AppCompatActivity {

    ListView summaryList;
    DatabaseReference databaseReference;
    ArrayList<String> myArrayList;
    ArrayAdapter<String> myAdapter;
    Employee employee;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_summary_report);

        employee = new Employee();

        summaryList = findViewById(R.id.list_attendance);
        databaseReference = FirebaseDatabase.getInstance().getReference("Employee");

        myArrayList = new ArrayList<>();
        myAdapter = new ArrayAdapter<String>(this, R.layout.employee_summary, R.id.tv_summary_report, myArrayList);

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot dataSnapshot: snapshot.getChildren()){
                    employee = dataSnapshot.getValue(Employee.class);
                    assert employee != null;
                    myArrayList.add("Employee Name: " + employee.getEmployeeFullName() +
                            "\n" + "Employee ID: " + employee.getEmployeeID() +
                            "\n" + "Mobile No: " + employee.getEmployeeMobileNo() +
                            "\n" + "Designation: " + employee.getEmployeeDesignation() +
                            "\n" + "Joining Date: " + employee.getEmployeeJoiningDate() +
                            "\n" + "Work Details: " + employee.getEmployeeWorkDetails() +
                            "\n" + "Salary: " + employee.getEmployeeSalary()+
                            "\n" + "Attendance: " + employee.getEmployeeAttendance());
                }
                summaryList.setAdapter(myAdapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}