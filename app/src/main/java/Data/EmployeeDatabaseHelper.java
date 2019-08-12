package Data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

import static Data.EmployeeDatabaseContract.COL_CITY;
import static Data.EmployeeDatabaseContract.COL_DEPARTMENT;
import static Data.EmployeeDatabaseContract.COL_FIRST_NAME;
import static Data.EmployeeDatabaseContract.COL_LAST_NAME;
import static Data.EmployeeDatabaseContract.COL_POSITION;
import static Data.EmployeeDatabaseContract.COL_STATE;
import static Data.EmployeeDatabaseContract.COL_STREET;
import static Data.EmployeeDatabaseContract.COL_TAXID;
import static Data.EmployeeDatabaseContract.COL_ZIP;
import static Data.EmployeeDatabaseContract.DATABASE_NAME;
import static Data.EmployeeDatabaseContract.DATABASE_VERSION;
import static Data.EmployeeDatabaseContract.SELECT_ALL_EMPLOYEES;
import static Data.EmployeeDatabaseContract.TABLE_NAME;

public class EmployeeDatabaseHelper extends SQLiteOpenHelper {
    public EmployeeDatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(EmployeeDatabaseContract.getCreateTableQuery());


    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL(EmployeeDatabaseContract.DROP_TABLE_QUERY);
        onCreate(sqLiteDatabase);

    }

    //get all items from database
    public ArrayList<Employee> getAllEmployees(){
        SQLiteDatabase readableDataBase = this.getReadableDatabase();
        ArrayList<Employee> returnEmployeeList = new ArrayList<>();

        Cursor cursor = readableDataBase.rawQuery(SELECT_ALL_EMPLOYEES, null);

        if (cursor.moveToFirst()){
            final String taxID = cursor.getString(cursor.getColumnIndex(COL_TAXID));
            final String firstName = cursor.getString(cursor.getColumnIndex(COL_FIRST_NAME));
            final String lastName = cursor.getString(cursor.getColumnIndex(COL_LAST_NAME));
            final String street = cursor.getString(cursor.getColumnIndex(COL_STREET));
            final String city = cursor.getString(cursor.getColumnIndex(COL_CITY));
            final String state = cursor.getString(cursor.getColumnIndex(COL_STATE));
            final String zip = cursor.getString(cursor.getColumnIndex(COL_ZIP));
            final String position = cursor.getString(cursor.getColumnIndex(COL_POSITION));
            final String department = cursor.getString(cursor.getColumnIndex(COL_DEPARTMENT));
            Employee currentEmployee = new Employee(firstName, lastName, street, city, state, zip, taxID, position, department);

        }
        cursor.close();
        return returnEmployeeList;
    }

    public Employee getEmployeeByDepartment(String departmentToQuery){
        SQLiteDatabase readableDataBase = this.getReadableDatabase();
        Employee returnEmployee = null;

        Cursor cursor = readableDataBase.rawQuery(EmployeeDatabaseContract.getSelectEmployeeByDepartmentQuery(departmentToQuery), null);

        if(cursor.moveToFirst()){
            final String taxID = cursor.getString(cursor.getColumnIndex(COL_TAXID));
            final String firstName = cursor.getString(cursor.getColumnIndex(COL_FIRST_NAME));
            final String lastName = cursor.getString(cursor.getColumnIndex(COL_LAST_NAME));
            final String street = cursor.getString(cursor.getColumnIndex(COL_STREET));
            final String city = cursor.getString(cursor.getColumnIndex(COL_CITY));
            final String state = cursor.getString(cursor.getColumnIndex(COL_STATE));
            final String zip = cursor.getString(cursor.getColumnIndex(COL_ZIP));
            final String position = cursor.getString(cursor.getColumnIndex(COL_POSITION));
            final String department = cursor.getString(cursor.getColumnIndex(COL_DEPARTMENT));
            returnEmployee = new Employee(firstName, lastName, street, city, state, zip, position, department, taxID);

        }
        cursor.close();
        return returnEmployee;
    }

    public void insertEmployeeIntoDatabase(Employee employeeToInsert){
        SQLiteDatabase database = this.getWritableDatabase();

        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_TAXID, employeeToInsert.getTaxID());
        contentValues.put(COL_FIRST_NAME, employeeToInsert.getFirstName());
        contentValues.put(COL_LAST_NAME, employeeToInsert.getLastName());
        contentValues.put(COL_STREET, employeeToInsert.getStreetAddress());
        contentValues.put(COL_CITY, employeeToInsert.getCity());
        contentValues.put(COL_STATE, employeeToInsert.getState());
        contentValues.put(COL_ZIP, employeeToInsert.getZip());
        contentValues.put(COL_POSITION, employeeToInsert.getPosition());
        contentValues.put(COL_DEPARTMENT, employeeToInsert.getDepartment());

        database.insert(TABLE_NAME, null, contentValues);
    }

    public void deleteEmployee(String taxIdToDelete){
        SQLiteDatabase database = this.getWritableDatabase();
        database.delete(TABLE_NAME, COL_TAXID + " = ?", new String[]{taxIdToDelete});
    }

    public void updateEmployeeIntoDatabase(Employee employeeToUpdate){
        SQLiteDatabase database = this.getWritableDatabase();

        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_TAXID, employeeToUpdate.getTaxID());
        contentValues.put(COL_FIRST_NAME, employeeToUpdate.getFirstName());
        contentValues.put(COL_LAST_NAME, employeeToUpdate.getLastName());
        contentValues.put(COL_STREET, employeeToUpdate.getStreetAddress());
        contentValues.put(COL_CITY, employeeToUpdate.getCity());
        contentValues.put(COL_STATE, employeeToUpdate.getState());
        contentValues.put(COL_ZIP, employeeToUpdate.getZip());
        contentValues.put(COL_POSITION, employeeToUpdate.getPosition());
        contentValues.put(COL_DEPARTMENT, employeeToUpdate.getDepartment());

        database.update(TABLE_NAME, contentValues, COL_TAXID + " = ?", new String[] {employeeToUpdate.getTaxID()});

    }
}