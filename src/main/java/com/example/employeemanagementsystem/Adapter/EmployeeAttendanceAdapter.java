package com.example.employeemanagementsystem.Adapter;

import android.content.Context;
import android.provider.MediaStore;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.employeemanagementsystem.ModelClass.Employee;
import com.example.employeemanagementsystem.R;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.FirebaseDatabase;
import com.orhanobut.dialogplus.DialogPlus;
import com.orhanobut.dialogplus.OnItemClickListener;
import com.orhanobut.dialogplus.ViewHolder;

import java.util.HashMap;
import java.util.Map;

public class EmployeeAttendanceAdapter extends FirebaseRecyclerAdapter<Employee, EmployeeAttendanceAdapter.EmployeeViewHolder> {

    private Context context;

    public EmployeeAttendanceAdapter(@NonNull FirebaseRecyclerOptions<Employee> options, Context context) {
        super(options);
        this.context = context;
    }

    @Override
    protected void onBindViewHolder(@NonNull EmployeeViewHolder holder, final int position, @NonNull final Employee model) {
        holder.tvEmpID.setText(model.getEmployeeID());
        holder.tvEmpName.setText(model.getEmployeeFullName());
        holder.tvAttendance.setText(model.getEmployeeAttendance());

        holder.imgEditEmployee.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final DialogPlus dialog = DialogPlus.newDialog(context)
                        .setMargin(10, 10, 10, 30)
                        .setGravity(Gravity.CENTER)
                        .setContentHolder(new ViewHolder(R.layout.dialog_employee_attendance))
                        .setOnItemClickListener(new OnItemClickListener() {
                            @Override
                            public void onItemClick(DialogPlus dialog, Object item, View view, int position) { }
                        })
                        .setExpanded(false)
                        .create();

                View view = dialog.getHolderView();

                final TextView tvEmployeeName = view.findViewById(R.id.tv_employee_name);
                final TextView tvEmployeeID = view.findViewById(R.id.tv_employee_id);
                final EditText etAttendance = view.findViewById(R.id.et_attendance);

                Button btnUpdate = view.findViewById(R.id.btn_update);

                tvEmployeeName.setText(model.getEmployeeFullName());
                tvEmployeeID.setText(model.getEmployeeID());
                etAttendance.setText(model.getEmployeeAttendance());

                btnUpdate.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Map<String, Object> map = new HashMap<>();
                        map.put("employeeID",    tvEmployeeID.getText().toString());
                        map.put("employeeFullName", tvEmployeeName.getText().toString());
                        map.put("employeeAttendance", etAttendance.getText().toString());

                        FirebaseDatabase.getInstance().getReference("Employee")
                                .child(getRef(position).getKey())
                                .updateChildren(map)
                                .addOnCompleteListener(new OnCompleteListener<Void>() {
                                    @Override
                                    public void onComplete(@NonNull Task<Void> task) {
                                        Toast.makeText(context, "Employee's attendance has been updated", Toast.LENGTH_SHORT).show();
                                        dialog.dismiss();
                                    }
                                });
                    }
                });

                dialog.show();
            }
        });
    }

    @NonNull
    @Override
    public EmployeeAttendanceAdapter.EmployeeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.attendance_list, parent, false);
        return new EmployeeAttendanceAdapter.EmployeeViewHolder(view);
    }

    /*@Override
    protected void onBindViewHolder(@NonNull EmployeeViewHolder holder, int position, @NonNull Employee model) {
        holder.tvEmpID.setText("Emp ID: " + model.getEmployeeID());
        holder.tvEmpName.setText("" + model.getEmployeeFullName());
        holder.tvEmpDesign.setText("" + model.getEmployeeDesignation());
        holder.tvEmpMobNo.setText("" + model.getEmployeeMobileNo());
    }

    @NonNull
    @Override
    public EmployeeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.employee_list, parent, false);
        return new EmployeeViewHolder(view);
    }*/

    static class EmployeeViewHolder extends RecyclerView.ViewHolder{

        TextView tvEmpID, tvEmpName, tvAttendance;
        ImageView imgEditEmployee;

        EmployeeViewHolder(@NonNull View itemView) {
            super(itemView);

            tvEmpID =     itemView.findViewById(R.id.tv_employee_id);
            tvEmpName =   itemView.findViewById(R.id.tv_employee_name);
            tvAttendance = itemView.findViewById(R.id.tv_attendance);

            imgEditEmployee =  itemView.findViewById(R.id.img_emp_logo);
        }
    }
}