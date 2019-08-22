package com.example.employeedatabase;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.employeedatabase.data.Employee;
import com.example.employeedatabase.data.EmployeeDatabaseHelper;
import com.example.employeedatabase.data.EmployeeRecyclerViewAdapter;

public class EditEmployee extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    EditText etFirstName, etLastName, etTaxId, etStreetAddress, etCity, etState, etZip, etPosition, etDepartment;

    Spinner spinnerDepartment;

    //string for spinner item
    String itemStr;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_employee);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Intent intent = getIntent();
        String strFirstName = intent.getStringExtra("firstNameToEdit");
        String strLastName = intent.getStringExtra("lastNameToEdit");
        String strTaxId = intent.getStringExtra("taxId");

        etFirstName = findViewById(R.id.etFirstNameEdit);
        etLastName = findViewById(R.id.etLastNameEdit);
        etTaxId = findViewById(R.id.etTaxIdEdit);
        etStreetAddress = findViewById(R.id.etStreetEdit);
        etCity = findViewById(R.id.etCityEdit);
        etState = findViewById(R.id.etStateEdit);
        etZip = findViewById(R.id.etZipEdit);
        etPosition = findViewById(R.id.etPositionEdit);
        spinnerDepartment = findViewById(R.id.spinnerDepartmentEdit);


        etFirstName.setText(strFirstName);
        etLastName.setText(strLastName);
        etTaxId.setText(strTaxId);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.departments,android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerDepartment.setAdapter(adapter);

        spinnerDepartment.setOnItemSelectedListener(this);

        if(savedInstanceState != null){
            etFirstName.setText(savedInstanceState.getString("firstName"));
            etLastName.setText(savedInstanceState.getString("lastName"));
            etStreetAddress.setText(savedInstanceState.getString("street"));
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

    public void onClickEdit(View view) {

        if (spinnerDepartment.getSelectedItem() == null) {
            return;
        }
        itemStr = spinnerDepartment.getSelectedItem().toString();

        switch (view.getId()) {

            case R.id.btnInsertEdit:
                final String firstName = etFirstName.getText().toString();
                final String lastName = etLastName.getText().toString();
                final String taxId = etTaxId.getText().toString();
                final String streetAddress = etStreetAddress.getText().toString();
                final String city = etCity.getText().toString();
                final String state = etState.getText().toString();
                final String zip = etZip.getText().toString();
                final String position = etPosition.getText().toString();
                final String department = itemStr;






                Employee employeeToUpdate = new Employee(firstName, lastName,streetAddress, city, state, zip,taxId, position, department);

                EmployeeDatabaseHelper helper = new EmployeeDatabaseHelper(this);
                helper.updateEmployeeIntoDatabase(employeeToUpdate);



                Toast.makeText(view.getContext(), "Updated" + employeeToUpdate.getFirstName(), Toast.LENGTH_LONG).show();


                break;


        }
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

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString("firstName", etFirstName.getText().toString());
        outState.putString("lastName", etLastName.getText().toString());
        outState.putString("street", etStreetAddress.getText().toString());
        outState.putString("city", etCity.getText().toString());
        outState.putString("state", etState.getText().toString());
        outState.putString("zip", etZip.getText().toString());
        outState.putString("taxId", etTaxId.getText().toString());
        outState.putString("position", etPosition.getText().toString());


    }
}

