package Data;

import java.util.Locale;

public class EmployeeDatabaseContract {
    public static final String DATABASE_NAME = "employee_db";
    public static final int DATABASE_VERSION = 1;
    public static final String TABLE_NAME = "Employee_table";
    public static final String COL_FIRST_NAME = "first";
    public static final String COL_LAST_NAME = "last";
    public static final String COL_STREET = "street";
    public static final String COL_CITY = "city";
    public static final String COL_STATE = "state";
    public static final String COL_ZIP = "zip";
    public static final String COL_TAXID = "tax_id";
    public static final String COL_POSITION = "position";
    public static final String COL_DEPARTMENT = "department";

    public static final String DROP_TABLE_QUERY = "DROP TABLE " + TABLE_NAME;//Delete the entire table
    public static final String SELECT_ALL_EMPLOYEES = "SELECT * FROM "+TABLE_NAME;

    public static String getCreateTableQuery() {
        return String.format(Locale.US, "CREATE TABLE %s( %s TEXT PRIMARY KEY, %s TEXT, %s TEXT, %s TEXT, %s TEXT, %s TEXT, %s TEXT, %s TEXT, %s TEXT)",
                TABLE_NAME, COL_TAXID, COL_FIRST_NAME, COL_LAST_NAME, COL_STREET, COL_CITY, COL_STATE, COL_ZIP, COL_POSITION, COL_DEPARTMENT);
    }

        public static String getSelectEmployeeByDepartmentQuery(String department){
            return String.format(Locale.US, "SELECT * FROM %s WHERE %s = \'%s\'", TABLE_NAME, COL_DEPARTMENT,department );
        }

}
