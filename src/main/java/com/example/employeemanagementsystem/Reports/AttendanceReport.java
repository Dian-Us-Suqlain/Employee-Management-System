package com.example.employeemanagementsystem.Reports;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.employeemanagementsystem.ModelClass.Employee;
import com.example.employeemanagementsystem.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class AttendanceReport extends AppCompatActivity {

    ListView attendanceList;
    DatabaseReference databaseReference;
    ArrayList<String> myArrayList;
    ArrayAdapter<String> myAdapter;
    Employee employee;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_attendance_report);

        employee = new Employee();

        attendanceList = findViewById(R.id.list_attendance);
        databaseReference = FirebaseDatabase.getInstance().getReference("Employee");

        myArrayList = new ArrayList<>();
        myAdapter = new ArrayAdapter<String>(this, R.layout.employee_report, R.id.tv_attendance_report, myArrayList);

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot dataSnapshot: snapshot.getChildren()){
                    employee = dataSnapshot.getValue(Employee.class);
                    myArrayList.add("Employee Name: " + employee.getEmployeeFullName() +
                            "\n" + "Employee ID: " + employee.getEmployeeID() +
                            "\n" + "Attendance: " + employee.getEmployeeAttendance());
                }
                attendanceList.setAdapter(myAdapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}