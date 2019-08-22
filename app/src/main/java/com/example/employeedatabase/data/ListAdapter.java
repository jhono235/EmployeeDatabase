package com.example.employeedatabase.data;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.employeedatabase.R;

import java.util.ArrayList;
import java.util.List;

public class ListAdapter extends ArrayAdapter {

    List list=new ArrayList();
    public ListAdapter(Context context, int resource) {
        super(context, resource);
    }

    static class LayoutHandler{
        TextView firstName, lastName, street, city, state, zip, position, department, taxId;



    }

    @Override
    public void add(Object object) {
        super.add(object);
        list.add(object);
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View row= convertView;
        LayoutHandler layoutHandler;
        if (row==null){
            LayoutInflater layoutInflater=(LayoutInflater)this.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            row = layoutInflater.inflate(R.layout.display_employee_row,parent,false);
            layoutHandler = new LayoutHandler();
            layoutHandler.firstName = row.findViewById(R.id.tvFirstNameDetails);
            layoutHandler.lastName = row.findViewById(R.id.tvLastNameDetails);
            layoutHandler.street = row.findViewById(R.id.tvStreetNameDetails);
            layoutHandler.city = row.findViewById(R.id.tvCityNameDetails);
            layoutHandler.state = row.findViewById(R.id.tvStateNameDetails);
            layoutHandler.zip = row.findViewById(R.id.tvZipNameDetails);
            layoutHandler.department = row.findViewById(R.id.tvDepartmentDetails);
            layoutHandler.position = row.findViewById(R.id.tvPositionDetails);
            layoutHandler.taxId = row.findViewById(R.id.tvTaxIdDetails);
            row.setTag(layoutHandler);

            //Log.d("TAG", layoutHandler.city.getText().toString());



        }else{
            layoutHandler = (LayoutHandler)row.getTag();

        }

        Employee employee = (Employee)this.getItem(position);
        layoutHandler.firstName.setText(employee.getFirstName());
        layoutHandler.lastName.setText(employee.getLastName());
        layoutHandler.street.setText(employee.getStreetAddress());
        layoutHandler.city.setText(employee.getCity());
        layoutHandler.state.setText(employee.getState());
        layoutHandler.zip.setText(employee.getZip());
        layoutHandler.position.setText(employee.getPosition());
        layoutHandler.department.setText(employee.getDepartment());
        layoutHandler.taxId.setText(employee.getTaxID());




        return row;

    }
}
