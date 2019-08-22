package com.example.employeedatabase;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.nfc.Tag;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.employeedatabase.data.Employee;
import com.example.employeedatabase.data.EmployeeDatabaseHelper;

public class NewEmployee extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    EditText etFirstName, etLastName, etStreet, etCity, etState, etZip, etTaxId, etPosition, etDepartment;

    Spinner spnrDept;

    String TAG;



    //string for spinner item
    String itemStr;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_employee);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        etFirstName = findViewById(R.id.etFirstName);
        etLastName = findViewById(R.id.etLastName);
        etStreet = findViewById(R.id.etStreet);
        etCity = findViewById(R.id.etCity);
        etState = findViewById(R.id.etState);
        etZip = findViewById(R.id.etZip);
        etTaxId = findViewById(R.id.etTaxId);
        etPosition = findViewById(R.id.etPositionNew);
        spnrDept = findViewById(R.id.spinnerDepartmentNew);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.departments,android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spnrDept.setAdapter(adapter);

        spnrDept.setOnItemSelectedListener(this);


        if(savedInstanceState != null){
            etFirstName.setText(savedInstanceState.getString("firstName"));
            etLastName.setText(savedInstanceState.getString("lastName"));
            etStreet.setText(savedInstanceState.getString("street"));
            etCity.setText(savedInstanceState.getString("city"));
            etState.setText(savedInstanceState.getString("state"));
            etZip.setText(savedInstanceState.getString("zip"));
            etTaxId.setText(savedInstanceState.getString("taxId"));
            etPosition.setText(savedInstanceState.getString("position"));

        }


    }


    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long l) {
        itemStr = parent.getItemAtPosition(position).toString();

    }

 @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }






    public void onClick(final View view) {

        if (spnrDept.getSelectedItem() == null) {
            return;
        }
        itemStr = spnrDept.getSelectedItem().toString();

        switch(view.getId()) {
            case R.id.btnInsert:
                final String firstName = etFirstName.getText().toString();
                final String lastName = etLastName.getText().toString();
                final String street = etStreet.getText().toString();
                final String city = etCity.getText().toString();
                final String state = etState.getText().toString();
                final String zip = etZip.getText().toString();
                final String taxId = etTaxId.getText().toString();
                final String position = etPosition.getText().toString();
                final String department = itemStr;


                //insert
                Thread thread = new Thread(
                        new Runnable() {
                            @Override
                            public void run() {
                                if (!(firstName.isEmpty()) || lastName.isEmpty()) {
                                    Employee employeeToInsert = new Employee(firstName, lastName, street, city, state, zip, taxId, position, department);
                                    EmployeeDatabaseHelper dbHelper = new EmployeeDatabaseHelper(view.getContext());
                                    dbHelper.insertEmployeeIntoDatabase(employeeToInsert);


                                }
                            }


                            //Log.d(TAG, ""+employeeToInsert);
                        });

                thread.start();
                Toast.makeText(view.getContext(), "Added", Toast.LENGTH_SHORT).show();

                break;



            case R.id.btnGoToMain:
                startActivity(new Intent(this, ListEmployees.class));

        }

        }


    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString("firstName", etFirstName.getText().toString());
        outState.putString("lastName", etLastName.getText().toString());
        outState.putString("street", etStreet.getText().toString());
        outState.putString("city", etCity.getText().toString());
        outState.putString("state", etState.getText().toString());
        outState.putString("zip", etZip.getText().toString());
        outState.putString("taxId", etTaxId.getText().toString());
        outState.putString("position", etPosition.getText().toString());


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
