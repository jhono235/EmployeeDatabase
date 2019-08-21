package com.example.employeedatabase.data;

import android.content.ContentResolver;
import android.net.Uri;
import android.provider.BaseColumns;

import java.util.Locale;

public final class EmployeeDatabaseContract {



    // To prevent someone from accidentally instantiating the contract class,
    // give it an empty constructor.
    private EmployeeDatabaseContract() {
    }

    /**
     * The "Content authority" is a name for the entire content provider, similar to the
     * relationship between a domain name and its website.  A convenient string to use for the
     * content authority is the package name for the app, which is guaranteed to be unique on the
     * device.
     */
    public static final String CONTENT_AUTHORITY = "com.example.employeedatabase";

    /**
     * Use CONTENT_AUTHORITY to create the base of all URI's which apps will use to contact
     * the content provider.
     */

    public static final Uri BASE_CONTENT_URI = Uri.parse("content://" + CONTENT_AUTHORITY);

    /**
     * Possible path (appended to base content URI for possible URI's)
     * For instance, content://com.example.android.pets/pets/ is a valid path for
     * looking at pet data. content://com.example.android.pets/staff/ will fail,
     * as the ContentProvider hasn't been given any information on what to do with "staff".
     */
    public static final String PATH_EMPLOYEE = "Employee_table";

    /**
     * Inner class that defines constant values for the employee database table.
     * Each entry in the table represents a single employee.
     */

    public static final class EmployeeEntry implements BaseColumns {

        /**
         * The MIME type of the {@link #CONTENT_URI} for a list of employees.
         */

        public static final String CONTENT_LIST_TYPE =
                ContentResolver.CURSOR_DIR_BASE_TYPE + "/" + CONTENT_AUTHORITY + "/" + PATH_EMPLOYEE;

        /**
         * The MIME type of the {@link #CONTENT_URI} for a single employee.
         */
        public static final  String CONTENT_ITEM_TYPE =
                ContentResolver.CURSOR_ITEM_BASE_TYPE + "/" + CONTENT_AUTHORITY + "/" + PATH_EMPLOYEE;

        /** The content URI to access the employee data in the provider */
        public static final Uri CONTENT_URI = Uri.withAppendedPath(BASE_CONTENT_URI, PATH_EMPLOYEE);



        public static final String DATABASE_NAME = "employee_db";
        public static final int DATABASE_VERSION = 1;

        /** Name of database table for employees */
        public static final String TABLE_NAME = "Employee_table";
        public static final String COL_FIRST_NAME = "first";
        public static final String COL_LAST_NAME = "last";
        public static final String COL_STREET = "street";
        public static final String COL_CITY = "city";
        public static final String COL_STATE = "state";
        public static final String COL_ZIP = "zip";
        public static final String COL_TAXID = BaseColumns._ID;
        public static final String COL_POSITION = "position";
        public static final String COL_DEPARTMENT = "department";

        public static final String DROP_TABLE_QUERY = "DROP TABLE " + TABLE_NAME;//Delete the entire table
        public static final String SELECT_ALL_EMPLOYEES = "SELECT * FROM " + TABLE_NAME;

        public static String getCreateTableQuery() {
            return String.format(Locale.US, "CREATE TABLE %s( %s TEXT PRIMARY KEY, %s TEXT, %s TEXT, %s TEXT, %s TEXT, %s TEXT, %s TEXT, %s TEXT, %s TEXT)",
                    TABLE_NAME, COL_TAXID, COL_FIRST_NAME, COL_LAST_NAME, COL_STREET, COL_CITY, COL_STATE, COL_ZIP, COL_POSITION, COL_DEPARTMENT);
        }

        public static String getSelectEmployeeByDepartmentQuery(String department) {
            return String.format(Locale.US, "SELECT * FROM %s WHERE %s = \'%s\'", TABLE_NAME, COL_DEPARTMENT, department);
        }

        public static String getSelectEmployeeByTaxidQuery(String taxId) {
            return String.format(Locale.US, "SELECT * FROM %s WHERE %s = \'%s\'", TABLE_NAME, COL_TAXID, taxId);
        }

    }
}
