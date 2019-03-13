//package com.school.foot_patroling.login;
//
//import android.app.ProgressDialog;
//import android.os.Bundle;
//import android.util.Log;
//
//import android.content.Intent;
//import android.widget.Button;
//import android.widget.EditText;
//import android.widget.Toast;
//
//import com.school.foot_patroling.BaseActivity;
//import com.school.foot_patroling.R;
//import com.school.foot_patroling.utils.Constants;
//import com.school.foot_patroling.utils.PreferenceHelper;
//
//import butterknife.BindView;
//import butterknife.ButterKnife;
//import butterknife.OnClick;
//
//
///**
// * Created by shyam on 2/19/2018.
// */
//public class LoginActivity extends BaseActivity {
//    private static final String TAG = "LoginActivity";
//    private static final int REQUEST_SIGNUP = 0;
//    private static final int REQUEST_FORGOT_PASSWORD = 1;
//
//    @BindView(R.id.input_username) EditText _emailText;
//    @BindView(R.id.input_password) EditText _passwordText;
//    @BindView(R.id.btn_signin) Button _loginButton;
//
//  //  @Inject
//  //  LoginApi loginApi;
//
//    @Override
//    public void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        activityComponent().inject(this);
//        PreferenceHelper preferenceHelper = PreferenceHelper.getPrefernceHelperInstace();
//        String loginToken = preferenceHelper.getString(LoginActivity.this, Constants.PREF_KEY_TOKEN, "");
//        if(loginToken.isEmpty()){
//            setContentView(R.layout.activity_login);
//            ButterKnife.bind(this);
//        }
//        else{
//          //  startActivity(new Intent(LoginActivity.this, NavigationDrawerActivity.class));
//          //  finish();
//        }
//    }
//    @OnClick(R.id.link_forgotpassword)
//    public void forgotPassword(){
//    //    Intent intent = new Intent(LoginActivity.this, ForgotPasswordActivity.class);
//    //    startActivityForResult(intent, REQUEST_FORGOT_PASSWORD);
//    }
//    @OnClick(R.id.btn_signin)
//    public void login() {
//        Log.d(TAG, "Login");
//
//        if (!validate()) {
//            onLoginFailed();
//            return;
//        }
//
//        _loginButton.setEnabled(false);
//
//        final ProgressDialog progressDialog = new ProgressDialog(LoginActivity.this,
//                R.style.AppTheme_Dark_Dialog);
//        progressDialog.setIndeterminate(true);
//        progressDialog.setMessage(getString(R.string.text_authenticating));
//        progressDialog.show();
//
//        String username = _emailText.getText().toString();
//        String password = _passwordText.getText().toString();
//
////        LoginRequest request = new LoginRequest();
////        request.setUsername(username);
////        request.setPassword(password);
////
////        loginApi.login(request)
////                .subscribeOn(Schedulers.io())
////                .observeOn(AndroidSchedulers.mainThread())
////                .subscribe(new Observer<LoginResponse>() {
////                    @Override
////                    public void onError(Throwable e) {
////                        progressDialog.dismiss();
////                        onLoginFailed();
////                    }
////
////                    @Override
////                    public void onComplete() {
////
////                    }
////
////                    @Override
////                    public void onSubscribe(Disposable d) {
////
////                    }
////
////                    @Override
////                    public void onNext(LoginResponse loginResponse){
////                        progressDialog.dismiss();
////                        _loginButton.setEnabled(true);
////                        if(loginResponse.getStatus().equals("200")) {
////                            onLoginSuccess(loginResponse);
////                        }
////                        else if(loginResponse.getStatus().equals("206")){
////                            //display error.
////                            new CustomAlertDialog().showAlert1(
////                                    LoginActivity.this,
////                                    R.string.text_login_failed,
////                                    loginResponse.getError(),
////                                    null);
////                        }
////
////                    }
////                });
//    }
//
//    @Override
//    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
//        if (requestCode == REQUEST_SIGNUP) {
//            if (resultCode == RESULT_OK) {
//
////                new CustomAlertDialog().showAlert1(
////                        LoginActivity.this,
////                        R.string.text_account_created,
////                        data.getExtras().getString(BUNDLE_KEY_OTP_VERIFIED_MESSAGE),
////                        null);
//                // TODO: Implement successful signup logic here
//                // By default we just finish the Activity and log them in automatically
//                // this.finish();
//            }
//        }
//        if (requestCode == REQUEST_FORGOT_PASSWORD) {
//            if (resultCode == RESULT_OK) {
////                if(data.getExtras().getBoolean(BUNDLE_KEY_RESET_PASSWORD_RESULT_FLAG, false)){
////                    //display alert reset password completed successfully
////                }
//            }
//        }
//    }
//
//    @Override
//    public void onBackPressed() {
//        // disable going back to the MainActivity
//        moveTaskToBack(true);
//    }
//
////    public void onLoginSuccess(LoginResponse loginResponse) {
////        _loginButton.setEnabled(true);
////        PreferenceHelper preferenceHelper = PreferenceHelper.getPrefernceHelperInstace();
////        preferenceHelper.setString(LoginActivity.this, Constants.PREF_KEY_LOGIN_ID, loginResponse.getId());
////        preferenceHelper.setString(LoginActivity.this, Constants.PREF_KEY_TOKEN, loginResponse.getToken());
////        startActivity(new Intent(LoginActivity.this, NavigationDrawerActivity.class));
////        finish();
////    }
//
//    public void onLoginFailed() {
//        Toast.makeText(getBaseContext(), "Login failed", Toast.LENGTH_LONG).show();
//        _loginButton.setEnabled(true);
//    }
//
//    public boolean validate() {
//        boolean valid = true;
//
//        String username = _emailText.getText().toString().trim();
//        String password = _passwordText.getText().toString().trim();
//       // if (mobileNumber.isEmpty() || mobileNumber.length() != 10) {
//        if(!android.util.Patterns.EMAIL_ADDRESS.matcher(username).matches()){
//            _emailText.setError("Enter a valid Email address");
//            valid = false;
//        } else {
//            _emailText.setError(null);
//        }
//
//        if (password.isEmpty() || password.length() < 4 || password.length() > 10) {
//            _passwordText.setError("between 4 and 10 alphanumeric characters");
//            valid = false;
//        } else {
//            _passwordText.setError(null);
//        }
//
//        return valid;
//    }
//}
