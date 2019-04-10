package com.school.foot_patroling;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.school.foot_patroling.register.RegisterActivity;
import com.school.foot_patroling.utils.PreferenceHelper;

import static com.school.foot_patroling.utils.Constants.BUNDLE_KEY_LAST_SYNC_DATE;


public class MainActivity extends AppCompatActivity {
    PreferenceHelper preferenceHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        preferenceHelper = PreferenceHelper.getPrefernceHelperInstace();
        String lastSyncDate = preferenceHelper.getString(MainActivity.this, BUNDLE_KEY_LAST_SYNC_DATE, "");
        if(lastSyncDate.equals("")){
            new android.os.Handler().postDelayed(
                    new Runnable() {
                        public void run() {
                            startActivity(new Intent(MainActivity.this, RegisterActivity.class));
                            finish();
                        }
                    }, 3000);
        }
        else{
            new android.os.Handler().postDelayed(
                    new Runnable() {
                        public void run() {
                            startActivity(new Intent(MainActivity.this, NavigationDrawerActivity.class));
                            finish();
                        }
                    }, 3000);
        }

    }
}
