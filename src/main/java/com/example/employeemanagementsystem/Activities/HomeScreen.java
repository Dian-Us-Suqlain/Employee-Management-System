package com.example.employeemanagementsystem.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.employeemanagementsystem.EmployeeCRUD.AddNewEmployee;
import com.example.employeemanagementsystem.R;
import com.example.employeemanagementsystem.EmployeeCRUD.ViewEmployees;
import com.example.employeemanagementsystem.Reports.AttendanceReport;
import com.example.employeemanagementsystem.Reports.SummaryReport;

public class HomeScreen extends AppCompatActivity {

    ImageView ivAddEmployee, ivEmployeeList, ivMarkAttendance, ivAttendanceReport, ivSummary;
    TextView tvCompanyName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_screen);

        getIntent();

        init();

        ivAddEmployee.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(HomeScreen.this, AddNewEmployee.class));
            }
        });

        ivEmployeeList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(HomeScreen.this, ViewEmployees.class));
            }
        });

        ivMarkAttendance.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(HomeScreen.this, EmployeeAttendance.class));
            }
        });

        ivAttendanceReport.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(HomeScreen.this, AttendanceReport.class));
            }
        });

        ivSummary.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(HomeScreen.this, SummaryReport.class));
            }
        });
    }

    void init(){
        tvCompanyName =      findViewById(R.id.tv_company_name);
        ivAddEmployee =      findViewById(R.id.iv_add_employee);
        ivEmployeeList =     findViewById(R.id.iv_employee_list);
        ivMarkAttendance =   findViewById(R.id.iv_mark_attendance);
        ivAttendanceReport = findViewById(R.id.iv_attendance_report);
        ivSummary =          findViewById(R.id.iv_summary);
    }
}
