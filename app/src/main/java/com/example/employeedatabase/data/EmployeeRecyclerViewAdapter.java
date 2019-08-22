package com.example.employeedatabase.data;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.employeedatabase.EditEmployee;
import com.example.employeedatabase.R;
import com.example.employeedatabase.ViewDetails;

import java.util.ArrayList;

public class EmployeeRecyclerViewAdapter extends RecyclerView.Adapter<EmployeeRecyclerViewAdapter.ViewHolder> {
     private ArrayList<Employee> employeeArrayList;
     private int whichActivity;


    public EmployeeRecyclerViewAdapter(ArrayList<Employee> employeeList, int activity) {
        this.employeeArrayList = employeeList;
        whichActivity = activity;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.employee_item2, parent, false);

        return new ViewHolder(itemView);
    }


    @Override
    public void onBindViewHolder(@NonNull EmployeeRecyclerViewAdapter.ViewHolder holder, int position) {
        Employee employeeBeingRendered = employeeArrayList.get(position);
        if(whichActivity == 0){
            holder.setEmployee(employeeBeingRendered);
        }
        else if(whichActivity == 1){

            holder.setEmployee(employeeBeingRendered);
        } else{

            holder.tvFirstName.setText(employeeBeingRendered.getFirstName());
            holder.tvLastName.setText(employeeBeingRendered.getLastName());
            holder.tvTaxId.setText(employeeBeingRendered.getTaxID());
            holder.setEmployee2(employeeBeingRendered);
            holder.setFirstName(employeeBeingRendered.getFirstName());



        }


    }

    @Override
    public int getItemCount() {
        return employeeArrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvFirstName;
        TextView tvLastName;
        TextView tvTaxId;

        Employee employee;
        String firstName;


        public ViewHolder(View itemView) {
            super(itemView);
            tvFirstName = itemView.findViewById(R.id.tvFirstName);
            tvLastName = itemView.findViewById(R.id.tvLastName);
            tvTaxId = itemView.findViewById(R.id.tvTaxId);

            if(whichActivity == 0) {
                itemView.setOnClickListener(

                        new View.OnClickListener() {


                            @Override
                            public void onClick(final View view) {

                                DialogInterface.OnClickListener dialogClickListener = new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        switch (which) {
                                            case DialogInterface.BUTTON_POSITIVE:
                                                Intent intent = new Intent(view.getContext(), ViewDetails.class);
                                                intent.putExtra("passedTaxId", tvTaxId.getText().toString());
                                                view.getContext().startActivity(intent);


                                                break;

                                            case DialogInterface.BUTTON_NEGATIVE:
                                                //No button clicked
                                                break;
                                        }
                                    }
                                };

                                AlertDialog.Builder builder = new AlertDialog.Builder(view.getContext());
                                builder.setMessage("View details?").setPositiveButton("Yes", dialogClickListener)
                                        .setNegativeButton("No", dialogClickListener).show();


                            }
                        });
            }
            else if (whichActivity == 1){
                itemView.setOnClickListener(
                        new View.OnClickListener() {
                            @Override
                            public void onClick(final View view) {

                                DialogInterface.OnClickListener dialogClickListener = new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        switch (which) {
                                            case DialogInterface.BUTTON_POSITIVE:
                                                EmployeeDatabaseHelper helper = new EmployeeDatabaseHelper(view.getContext());
                                                helper.deleteEmployee(tvTaxId.getText().toString());
                                                employeeArrayList = helper.getAllEmployees();
                                                notifyDataSetChanged();
                                                Toast.makeText(view.getContext(), "Employee Deleted", Toast.LENGTH_LONG).show();
                                                break;

                                            case DialogInterface.BUTTON_NEGATIVE:
                                                //No button clicked
                                                break;
                                        }
                                    }
                                };

                                AlertDialog.Builder builder = new AlertDialog.Builder(view.getContext());
                                builder.setMessage("Are you sure?").setPositiveButton("Yes", dialogClickListener)
                                        .setNegativeButton("No", dialogClickListener).show();


                            }
                        });


            }
            else itemView.setOnClickListener(
                        new View.OnClickListener() {
                            @Override
                            public void onClick(final View view) {

                                DialogInterface.OnClickListener dialogClickListener = new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        switch (which) {
                                            case DialogInterface.BUTTON_POSITIVE:
                                                Intent intent = new Intent(view.getContext(), EditEmployee.class);
                                                intent.putExtra("firstNameToEdit", tvFirstName.getText().toString());
                                                intent.putExtra("lastNameToEdit", tvLastName.getText().toString());
                                                intent.putExtra("taxId", tvTaxId.getText().toString());
                                                view.getContext().startActivity(intent);
                                                break;

                                            case DialogInterface.BUTTON_NEGATIVE:
                                                //No button clicked
                                                break;
                                        }
                                    }
                                };

                                AlertDialog.Builder builder = new AlertDialog.Builder(view.getContext());
                                builder.setMessage("Update Employee?").setPositiveButton("Yes", dialogClickListener)
                                        .setNegativeButton("No", dialogClickListener).show();


                            }
                        });


        }


        public void setEmployee(Employee employee) {
            tvFirstName.setText(employee.getFirstName());
            tvLastName.setText(employee.getLastName());
            tvTaxId.setText(employee.getTaxID());


        }

        void setEmployee2(Employee employee) {
            this.employee = employee;
        }


        public void setFirstName(String first){
            this.firstName = first;
        }


    }


}








