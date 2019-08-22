package com.example.employeedatabase;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.TextView;

import com.example.employeedatabase.data.Employee;
import com.example.employeedatabase.data.EmployeeDatabaseHelper;
import com.example.employeedatabase.data.ListAdapter;

public class ViewDetails extends AppCompatActivity {
    SQLiteDatabase sqLiteDatabase;
    EmployeeDatabaseHelper employeeDatabaseHelper;
    Cursor cursor;
    TextView tvFirstName;

    ListView listView;
    ListAdapter listAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_details);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Intent intent = getIntent();
        String passedTaxid = intent.getStringExtra("passedTaxId");

        tvFirstName = findViewById(R.id.tvFirstNameDetails);
        listView =findViewById(R.id.lvDisplayListView);

        employeeDatabaseHelper = new EmployeeDatabaseHelper(getApplicationContext());
        sqLiteDatabase = employeeDatabaseHelper.getReadableDatabase();
        cursor=employeeDatabaseHelper.getEmployeeDetails(passedTaxid);
        listAdapter = new ListAdapter(getApplicationContext(),R.layout.display_employee_row);
        listView.setAdapter(listAdapter);

        if(cursor.moveToFirst()){


            do{
                String firstName, lastName, street, city, state, zip, position, department, taxid;
                firstName= cursor.getString(0);
                lastName = cursor.getString(1);
                street = cursor.getString(2);
                city = cursor.getString(3);
                state = cursor.getString(4);
                zip = cursor.getString(5);
                position = cursor.getString(6);
                department = cursor.getString(7);
                taxid = cursor.getString(8);

                Employee employee = new Employee(
                        firstName,
                        lastName,
                        street,
                        city,
                        state,
                        zip,
                        position,
                        department,
                        taxid);
                listAdapter.add(employee);




            }
                while(cursor.moveToNext());
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



    }

