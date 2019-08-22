package com.example.employeedatabase.workerthreads;


import android.content.Context;
import android.os.AsyncTask;

import com.example.employeedatabase.ListEmployees;
import com.example.employeedatabase.data.Employee;
import com.example.employeedatabase.data.EmployeeDatabaseHelper;

import java.util.List;

public  class GetEmployees extends AsyncTask<String, Void, List<Employee>> {
    EmployeeListener employeeListener;
    public interface EmployeeListener{
        void onEmployeesRecevied(List<Employee> employees);
    }

    EmployeeDatabaseHelper employeeDatabaseHelper;

    public GetEmployees(Context context) {
employeeListener = (EmployeeListener) context;
        employeeDatabaseHelper = new EmployeeDatabaseHelper(context);
    }


    @Override
    protected List<Employee> doInBackground(String... strings) {
        employeeDatabaseHelper.getEmployeeByDepartment(strings[0]);

        return employeeDatabaseHelper.getEmployeeByDepartment(strings[0]);
    }


    @Override
    protected void onPostExecute(List<Employee> employees) {
        super.onPostExecute(employees);
        employeeListener.onEmployeesRecevied(employees);
    }
}
