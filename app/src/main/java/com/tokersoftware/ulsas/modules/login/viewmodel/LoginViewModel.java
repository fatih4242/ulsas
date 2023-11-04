package com.tokersoftware.ulsas.modules.login.viewmodel;

import android.content.Context;
import android.widget.Toast;

import com.tokersoftware.ulsas.classes.LoginManager;
import com.tokersoftware.ulsas.interfaces.API;
import com.tokersoftware.ulsas.interfaces.ResponseI;
import com.tokersoftware.ulsas.model.ErrorMessage;
import com.tokersoftware.ulsas.network.NetworkConstant;

import java.util.HashMap;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginViewModel {

    public void signIn(String email, String pass, ResponseI responseI){
        LoginManager.signIn(email, pass, responseI);
    }
}
