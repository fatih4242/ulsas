package com.tokersoftware.ulsas.classes;

import android.app.ProgressDialog;
import android.content.Context;

public class ProgressDialogManager {

    Context context;
    ProgressDialog progressDialog;

    public ProgressDialogManager(Context c){
        this.context = c;
    }

    public void showProgressDialog(String title, String message){
        if(isShowingProgressDialog())
            progressDialog.dismiss();

        progressDialog = new ProgressDialog(context);
        progressDialog.setCancelable(false);
        progressDialog.setTitle(title);
        progressDialog.setMessage(message);
        progressDialog.show();
    }

    public boolean isShowingProgressDialog(){
        if (progressDialog == null)
            return false;
        else return progressDialog.isShowing();
    }

    public void dismissProgressDialog(){
        if (isShowingProgressDialog())
            progressDialog.dismiss();
    }
}
