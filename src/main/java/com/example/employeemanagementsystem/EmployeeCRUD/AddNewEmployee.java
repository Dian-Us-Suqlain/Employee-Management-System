package com.example.employeemanagementsystem.EmployeeCRUD;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.employeemanagementsystem.ModelClass.Employee;
import com.example.employeemanagementsystem.R;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.SimpleDateFormat;
import java.util.Date;

public class AddNewEmployee extends AppCompatActivity /*implements DatePickerFragment.DateDialogListener*/ {

    EditText etEmployeeID, etEmployeeJoiningDate, etEmployeeFullName, etEmployeeDesignation, etEmployeeMobileNo, etEmployeeWorkDetails, etEmployeeSalary;
    DatabaseReference mRef;  // Instance of Firebase Database for connection
    //private static final String DIALOG_DATE = "DialogDate";
    //ImageView calendarImage;
    //FragmentManager fragmentManager;
    //Fragment AddEmployeeFrag, EmployeeListFrag;
    //ImageView ivAddEmployee, ivUpdateEmployee;
    //Button btnAddEmployee;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_new_employee);

        getIntent();
        init();

        /*ivAddEmployee.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fragmentManager.beginTransaction().show(AddEmployeeFrag).hide(EmployeeListFrag).commit();
                btnAddEmployee.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        SaveEmployeeDetails();
                    }
                });
            }
        });

        ivUpdateEmployee.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fragmentManager.beginTransaction().hide(AddEmployeeFrag).show(EmployeeListFrag).commit();
            }
        });*/

        /*calendarImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentManager manager = getSupportFragmentManager();
                DatePickerFragment dialog = new DatePickerFragment();
                dialog.show(manager, DIALOG_DATE);
            }
        });*/
    }

    void init() {
        etEmployeeID = findViewById(R.id.et_employee_id);
        etEmployeeJoiningDate = findViewById(R.id.et_employee_joining_date);
        etEmployeeFullName = findViewById(R.id.et_employee_fullname);
        etEmployeeDesignation = findViewById(R.id.et_employee_designation);
        etEmployeeMobileNo = findViewById(R.id.et_employee_mobile);
        etEmployeeWorkDetails = findViewById(R.id.et_work_details);
        etEmployeeSalary = findViewById(R.id.et_employee_salary);

        //btnAddEmployee = findViewById(R.id.btn_save);

        //fragmentManager = getSupportFragmentManager();
        //AddEmployeeFrag = fragmentManager.findFragmentById(R.id.frag_add_employee);
        //EmployeeListFrag = fragmentManager.findFragmentById(R.id.frag_employee_list);

        mRef = FirebaseDatabase.getInstance().getReference("Employee");
    }

    public void SaveEmployeeDetails(View view) {
        String empID, empJoinDate, empFullName, empDesign, empMobNo, empWorkDet, empSalary, empAttendance;
        String id;

        empID =       etEmployeeID.getText().toString().trim();
        empJoinDate = etEmployeeJoiningDate.getText().toString().trim();
        empFullName = etEmployeeFullName.getText().toString().trim();
        empDesign =   etEmployeeDesignation.getText().toString().trim();
        empMobNo =    etEmployeeMobileNo.getText().toString().trim();
        empWorkDet =  etEmployeeWorkDetails.getText().toString().trim();
        empSalary =   etEmployeeSalary.getText().toString().trim();
        empAttendance = "";

        if (empID.isEmpty()) { Toast.makeText(this, "Enter Employee ID", Toast.LENGTH_SHORT).show(); }
        else if (empJoinDate.isEmpty()) { Toast.makeText(this, "Enter Joining Date", Toast.LENGTH_SHORT).show(); }
        else if (empFullName.isEmpty()) { Toast.makeText(this, "Enter Full Name ", Toast.LENGTH_SHORT).show(); }
        else if (empDesign.isEmpty()) { Toast.makeText(this, "Enter Designation", Toast.LENGTH_SHORT).show(); }
        else if (empMobNo.isEmpty()) { Toast.makeText(this, "Enter Mobile No", Toast.LENGTH_SHORT).show(); }
        else if (empSalary.isEmpty()) { Toast.makeText(this, "Enter Monthly Salary", Toast.LENGTH_SHORT).show(); }
        else {
            id = mRef.push().getKey();  // This will create a unique key in firebase every single time
            Employee employee = new Employee(empID, empJoinDate, empFullName, empDesign, empMobNo, empWorkDet, empSalary, empAttendance);
            assert id != null;
            mRef.child(id).setValue(employee);
            Toast.makeText(this, "Employee " + empFullName + " has been added to company", Toast.LENGTH_SHORT).show();
            finish();
        }

    /*public String formatDate(Date date) {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        String hireDate = sdf.format(date);
        return hireDate;
    }

    @Override
    public void onFinishDialog(Date date) {
        etEmployeeJoiningDate.setText(formatDate(date));
    }*/
    }
}
