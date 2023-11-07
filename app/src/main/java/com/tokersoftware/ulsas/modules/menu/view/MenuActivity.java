package com.tokersoftware.ulsas.modules.menu.view;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import com.tokersoftware.ulsas.R;
import com.tokersoftware.ulsas.interfaces.ResponseI;
import com.tokersoftware.ulsas.model.ErrorMessage;
import com.tokersoftware.ulsas.modules.login.view.LoginActivity;
import com.tokersoftware.ulsas.modules.main.view.MainActivity;
import com.tokersoftware.ulsas.modules.menu.viewmodel.MenuViewModel;

public class MenuActivity extends AppCompatActivity {

    //ViewModel
    MenuViewModel viewModel;

    //Activity
    Activity activity = MenuActivity.this;

    //View
    Button addReportBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        init();
    }

    private void checkIfIsLogged(){
        //Check for if the email and password is correct
        viewModel.isSigned(new ResponseI() {
            @Override
            public void responseFromDB(ErrorMessage errorMessage) {
                if (errorMessage.getError() == 1){
                    startActivity(new Intent(activity, LoginActivity.class));
                    finish();
                }
            }
        });
    }

    private void init(){

        //ViewModel
        viewModel = new MenuViewModel(this);

        checkIfIsLogged();


        //View
        addReportBtn = findViewById(R.id.addReportBtn);
        addReportBtn.setOnClickListener(v -> {
            startActivity(new Intent(MenuActivity.this, MainActivity.class));
        });

    }
}