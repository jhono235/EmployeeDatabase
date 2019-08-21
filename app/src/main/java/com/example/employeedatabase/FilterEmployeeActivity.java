package com.example.employeedatabase;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.example.employeedatabase.data.Employee;
import com.example.employeedatabase.data.EmployeeDatabaseHelper;

public class FilterEmployeeActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    Spinner spinnerDepartment;
    String itemStr;
    String TAG;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_filter_employee);




        spinnerDepartment = findViewById(R.id.spinnerDepartment);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.departments,android.R.layout.simple_spinner_item);
adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
spinnerDepartment.setAdapter(adapter);

spinnerDepartment.setOnItemSelectedListener(this);



    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long l) {
        itemStr = parent.getItemAtPosition(position).toString();

    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }




    public void onClick(View view) {

        if (spinnerDepartment.getSelectedItem() == null) {
            return;
        }
        itemStr = spinnerDepartment.getSelectedItem().toString();

    }

    public void goToList(View view){
        Intent intent = new Intent(this, ListEmployees.class);
        intent.putExtra("data", itemStr);


        startActivity(intent);
    }


}
