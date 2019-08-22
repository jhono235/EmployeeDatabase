package com.example.employeedatabase.data;

import android.content.Context;
import android.os.AsyncTask;

public class BackgroundTask extends AsyncTask<String, Void, String>{
    private final Context ctx;


    BackgroundTask(Context ctx){
        this.ctx = ctx;
    }

    @Override
    protected void onPreExecute(){
        super.onPreExecute();
    }


    @Override
    protected String doInBackground(String... params) {

        String method = params[0];
        EmployeeDatabaseHelper employeeDatabaseHelper = new EmployeeDatabaseHelper(ctx);
        if(method.equals("get_info")){


        }
        return null;
    }
}
