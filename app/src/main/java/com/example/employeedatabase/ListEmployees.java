package com.example.employeedatabase;

import android.content.Intent;
import android.os.Bundle;

import com.example.employeedatabase.data.Employee;
import com.example.employeedatabase.workerthreads.GetEmployees;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import android.view.View;

import androidx.core.view.GravityCompat;
import androidx.appcompat.app.ActionBarDrawerToggle;

import android.view.MenuItem;

import com.google.android.material.navigation.NavigationView;

import androidx.drawerlayout.widget.DrawerLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.Menu;

import com.example.employeedatabase.data.EmployeeDatabaseHelper;
import com.example.employeedatabase.data.EmployeeRecyclerViewAdapter;

import java.util.ArrayList;
import java.util.List;

public class ListEmployees extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener, GetEmployees.EmployeeListener {



    RecyclerView rvEmployeeList;




    String stringDept;

    @Override
    protected void onCreate(Bundle savedInstanceState) {



        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_employees);

        Intent intent = getIntent();
         stringDept = intent.getStringExtra("data");





        rvEmployeeList = findViewById(R.id.rvListEmployees);

        //EmployeeRecyclerViewAdapter employeeRecyclerViewAdapter = new EmployeeRecyclerViewAdapter(employeeDatabaseHelper.getAllEmployees(), 2);



        //GetEmployees getEmployees = new GetEmployees(this);


        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(this, ((LinearLayoutManager) layoutManager).getOrientation());

        rvEmployeeList.setLayoutManager(layoutManager);

        rvEmployeeList.addItemDecoration(dividerItemDecoration);


        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        navigationView.setNavigationItemSelectedListener(this);

        new GetEmployees(this).execute(stringDept);


    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.list_employees, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.navNewEmployee) {
            Intent intent = new Intent(this, NewEmployee.class);
            startActivity(intent);
        } else if (id == R.id.navDeleteEmployee) {
            Intent intent = new Intent(this, DeleteEmployee.class);
            startActivity(intent);

        } else if (id == R.id.navUpdateEmployee) {
            Intent intent = new Intent(this, UpdateEmployee.class);
            startActivity(intent);

        } else if (id == R.id.navFilterEmployee) {
            Intent intent = new Intent(this, FilterEmployeeActivity.class);
            startActivity(intent);

        }

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    protected void onResume() {
        super.onResume();

    }

    @Override
    public void onEmployeesRecevied(List<Employee> employees) {
rvEmployeeList.setAdapter(new EmployeeRecyclerViewAdapter((ArrayList<Employee>) employees, 0));


    }
}

