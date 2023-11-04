package com.tokersoftware.ulsas.modules.login.view;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.tokersoftware.ulsas.R;
import com.tokersoftware.ulsas.classes.LocalDataManager;
import com.tokersoftware.ulsas.classes.NetworkManager;
import com.tokersoftware.ulsas.classes.ProgressDialogManager;
import com.tokersoftware.ulsas.interfaces.ResponseI;
import com.tokersoftware.ulsas.model.ErrorMessage;
import com.tokersoftware.ulsas.modules.login.viewmodel.LoginViewModel;
import com.tokersoftware.ulsas.modules.main.view.MainActivity;

public class LoginActivity extends AppCompatActivity {

    //View
    TextInputLayout emailInputLayout, passInputLayout;
    TextInputEditText emailInputEditText, passInputEditText;
    Button loginBtn;

    //ViewModel
    LoginViewModel loginViewModel;

    //Activity
    Activity activity;

    //SharedPreferences
    LocalDataManager localDataManager;

    //ProgressDialog
    ProgressDialogManager progressDialogManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        init();
    }

    private void init(){
        //Activity
        activity = LoginActivity.this;

        //ViewModel
        loginViewModel = new LoginViewModel();

        //SharedPreferences
        localDataManager = new LocalDataManager(this);

        //ProgressDialog
        progressDialogManager = new ProgressDialogManager(this);

        //View
        emailInputLayout = findViewById(R.id.emailInputLayout);
        passInputLayout = findViewById(R.id.passInputLayout);
        emailInputEditText = findViewById(R.id.emailInputEditText);
        passInputEditText = findViewById(R.id.passInputEditText);

        loginBtn = findViewById(R.id.loginBtn);
        loginBtn.setOnClickListener( v -> {
            if (NetworkManager.isConnectedToNetwork(activity)){
                login();
            } else Toast.makeText(activity, "Lütfen İnternet Bağlantınızı Kontrol Ediniz", Toast.LENGTH_SHORT).show();

        } );
    }

    private void login(){
        progressDialogManager.showProgressDialog("Lütfen Bekleyiniz", "Bilgileriniz Kontrol Ediliyor...");
        String email = emailInputEditText.getText().toString();
        String pass = passInputEditText.getText().toString();

        loginViewModel.signIn(email, pass, new ResponseI() {
            @Override
            public void responseFromDB(ErrorMessage errorMessage) {
                progressDialogManager.dismissProgressDialog();
                if (errorMessage.getError() == 1){
                    showError();
                    Toast.makeText(activity, errorMessage.getMessage(), Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(activity, errorMessage.getMessage(), Toast.LENGTH_SHORT).show();

                    saveLoginData(email, pass);

                    startActivity(new Intent(activity, MainActivity.class));
                    finish();
                }
            }
        });
    }

    private void saveLoginData(String email, String pass) {
        localDataManager.setSharedPreference(LocalDataManager.keys.email.getKey(), email);
        localDataManager.setSharedPreference(LocalDataManager.keys.password.getKey(), pass);
    }

    private void showError(){
        emailInputLayout.setErrorEnabled(true);
        passInputLayout.setErrorEnabled(true);
        emailInputLayout.setFocusable(true);
    }
}