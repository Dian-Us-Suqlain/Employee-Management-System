package com.example.employeemanagementsystem.Adapter;

import android.content.Context;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
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

public class EmployeeAdapter extends FirebaseRecyclerAdapter<Employee, EmployeeAdapter.EmployeeViewHolder> {

    private Context context;

    public EmployeeAdapter(@NonNull FirebaseRecyclerOptions<Employee> options, Context context) {
        super(options);
        this.context = context;
    }

    @Override
    protected void onBindViewHolder(@NonNull EmployeeViewHolder holder, final int position, @NonNull final Employee model) {
        holder.tvEmpID.setText(model.getEmployeeID());
        holder.tvEmpName.setText(model.getEmployeeFullName());
        holder.tvEmpDesign.setText(model.getEmployeeDesignation());
        holder.tvEmpMobNo.setText(model.getEmployeeMobileNo());

        holder.imgEditEmployee.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final DialogPlus dialog = DialogPlus.newDialog(context)
                        .setMargin(10, 10, 10, 30)
                        .setGravity(Gravity.CENTER)
                        .setContentHolder(new ViewHolder(R.layout.update_employee))
                        .setOnItemClickListener(new OnItemClickListener() {
                            @Override
                            public void onItemClick(DialogPlus dialog, Object item, View view, int position) { }
                        })
                        .setExpanded(false)
                        .create();

                View view = dialog.getHolderView();

                final EditText etDialogEmpName =   view.findViewById(R.id.et_dialog_employee_name);
                final EditText etDialogEmpDesign = view.findViewById(R.id.et_dialog_employee_designation);
                final EditText etDialogWorkDet =   view.findViewById(R.id.et_dialog_work_details);
                final EditText etDialogSalary =    view.findViewById(R.id.et_dialog_salary);

                Button btnUpdate = view.findViewById(R.id.btn_update);

                etDialogEmpName.setText(model.getEmployeeFullName());
                etDialogEmpDesign.setText(model.getEmployeeDesignation());
                etDialogWorkDet.setText(model.getEmployeeWorkDetails());
                etDialogSalary.setText(model.getEmployeeSalary());

                btnUpdate.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Map<String, Object> map = new HashMap<>();
                        map.put("employeeFullName",    etDialogEmpName.getText().toString());
                        map.put("employeeDesignation", etDialogEmpDesign.getText().toString());
                        map.put("employeeWorkDetails", etDialogWorkDet.getText().toString());
                        map.put("employeeSalary",      etDialogSalary.getText().toString());

                        FirebaseDatabase.getInstance().getReference("Employee")
                                .child(getRef(position).getKey())
                                .updateChildren(map)
                                .addOnCompleteListener(new OnCompleteListener<Void>() {
                                    @Override
                                    public void onComplete(@NonNull Task<Void> task) {
                                        Toast.makeText(context, "Employee's data has been updated", Toast.LENGTH_SHORT).show();
                                        dialog.dismiss();
                                    }
                                });
                    }
                });

                dialog.show();
            }
        });

        holder.imgDeleteEmployee.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseDatabase.getInstance().getReference("Employee")
                        .child(getRef(position).getKey())
                        .removeValue()
                        .addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                Toast.makeText(context, "Employee has been removed", Toast.LENGTH_SHORT).show();
                            }
                        });
            }
        });
    }

    @NonNull
    @Override
    public EmployeeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.employee_list, parent, false);
        return new EmployeeViewHolder(view);
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

        TextView tvEmpID, tvEmpName, tvEmpDesign, tvEmpMobNo;
        ImageView imgEditEmployee, imgDeleteEmployee;

        EmployeeViewHolder(@NonNull View itemView) {
            super(itemView);

            tvEmpID =     itemView.findViewById(R.id.tv_employee_id);
            tvEmpName =   itemView.findViewById(R.id.tv_employee_name);
            tvEmpDesign = itemView.findViewById(R.id.tv_designation);
            tvEmpMobNo =  itemView.findViewById(R.id.tv_employee_mobile);

            imgEditEmployee =  itemView.findViewById(R.id.img_edit_employee);
            imgDeleteEmployee= itemView.findViewById(R.id.img_delete_employee);
        }
    }
}
