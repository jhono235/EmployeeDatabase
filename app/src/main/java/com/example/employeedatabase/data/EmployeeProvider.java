package com.example.employeedatabase.data;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;

import static com.example.employeedatabase.data.EmployeeDatabaseContract.EmployeeEntry.TABLE_NAME;

public class EmployeeProvider extends ContentProvider {

    /** URI matcher code for the content URI for the employee table */
    private static final int EMPLOYEE = 100;

    /** URI matcher code for the content URI for a single employee in the pets table */
    private static final int EMPLOYEE_ID = 101;

    /**
     * UriMatcher object to match a content URI to a corresponding code.
     * The input passed into the constructor represents the code to return for the root URI.
     * It's common to use NO_MATCH as the input for this case.
     */
    private static final UriMatcher sUriMatcher = new UriMatcher(UriMatcher.NO_MATCH);

    private EmployeeDatabaseHelper mEmployeeDbHelper;

    @Override
    public boolean onCreate() {
        mEmployeeDbHelper = new EmployeeDatabaseHelper(getContext());
        return false;
    }

    @Override
    public Cursor query(Uri uri, String[] strings, String s, String[] strings1, String s1) {

        SQLiteDatabase database = mEmployeeDbHelper.getReadableDatabase();

        //cursor holds the results of the query
        Cursor cursor = null;

        int match = sUriMatcher.match(uri);
        switch (match){
            case EMPLOYEE:
                cursor = database.query(TABLE_NAME, strings, s, strings1,null, null, s1  );

                break;

            case EMPLOYEE_ID:

                s = EmployeeDatabaseContract.EmployeeEntry._ID + "=?";

                strings1 = new String[] { String.valueOf(ContentUris.parseId(uri)) };


                cursor = database.query(TABLE_NAME, strings, s, strings1, null, null, s1);

                break;

                default:

                    throw new IllegalArgumentException("Cannot query unknown URI " + uri);


        }
        // Set notification URI on the Cursor,
        // so we know what content URI the Cursor was created for.
        // If the data at this URI changes, then we know we need to update the Cursor.
        cursor.setNotificationUri(getContext().getContentResolver(), uri);

        return cursor;
    }

    @Override
    public String getType(Uri uri) {
        return null;
    }

    @Override
    public Uri insert(Uri uri, ContentValues contentValues) {
        return null;
    }

    @Override
    public int delete(Uri uri, String s, String[] strings) {
        return 0;
    }

    @Override
    public int update(Uri uri, ContentValues contentValues, String s, String[] strings) {
        return 0;
    }
}
