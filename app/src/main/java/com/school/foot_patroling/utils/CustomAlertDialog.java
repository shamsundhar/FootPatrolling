package com.school.foot_patroling.utils;


import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;

/**
 * Created by shyam on 4/7/2018.
 */

public class CustomAlertDialog {
    AlertDialog alertDialog;

    public void showAlert2(Context context, int titleResId, String message, final Callback callback) {
        alertDialog = new AlertDialog.Builder(context)
                .setTitle(context.getResources().getString(titleResId))
                .setMessage(message)
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        callback.onSucess(0);
                    }
                }).setNegativeButton("No", new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        callback.onSucess(-1);
                    }
                }).show();
    }
    public void dismissAlert(){
        if(alertDialog != null){
            alertDialog.dismiss();
        }
    }
    public void showAlert1(Context context, int titleResId, String message, final Callback callback){
        new AlertDialog.Builder(context)
                .setTitle(context.getResources().getString(titleResId))
                .setMessage(message)
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dismissAlert();
                        if(callback != null){
                            callback.onSucess(0);
                        }

                    }
                }).show();
    }
    public void showAlert1(Context context, int titleResId, String message){
        showAlert1(context, titleResId, message, null);
    }
    public interface Callback {

        public void onSucess(int t);

    }

}