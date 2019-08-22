package com.example.employeedatabase;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import com.example.employeedatabase.data.EmployeeDatabaseHelper;
import com.example.employeedatabase.data.EmployeeRecyclerViewAdapter;

public class DeleteEmployee extends AppCompatActivity {

    RecyclerView rvDeleteList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete_employee);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);



        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        EmployeeDatabaseHelper employeeDatabaseHelper = new EmployeeDatabaseHelper(this);

        EmployeeRecyclerViewAdapter employeeRecyclerViewAdapter = new EmployeeRecyclerViewAdapter(employeeDatabaseHelper.getAllEmployees(), 1);

        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(this, ((LinearLayoutManager) layoutManager).getOrientation());

        rvDeleteList = findViewById(R.id.rvDelete);
        rvDeleteList.setLayoutManager(layoutManager);
        rvDeleteList.setAdapter(employeeRecyclerViewAdapter);
        rvDeleteList.addItemDecoration(dividerItemDecoration);


    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:

                Intent intent = new Intent(this, ListEmployees.class);
                startActivity(intent);
                finish();
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }


}
