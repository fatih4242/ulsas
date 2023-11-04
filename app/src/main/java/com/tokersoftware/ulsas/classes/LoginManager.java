package com.tokersoftware.ulsas.classes;

import com.tokersoftware.ulsas.interfaces.API;
import com.tokersoftware.ulsas.interfaces.ResponseI;
import com.tokersoftware.ulsas.model.ErrorMessage;
import com.tokersoftware.ulsas.network.NetworkConstant;

import java.util.HashMap;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginManager {


    public static void signIn(String email, String pass, ResponseI responseI){

        API retrofit = NetworkConstant.getClient().create(API.class);
        Call<ErrorMessage> call = retrofit.Login(email, pass);

        call.enqueue(new Callback<ErrorMessage>() {
            @Override
            public void onResponse(Call<ErrorMessage> call, Response<ErrorMessage> response) {
                if (response.isSuccessful()){
                    responseI.responseFromDB(response.body());
                } else {
                    responseI.responseFromDB(new ErrorMessage(1, response.message()));
                }
            }

            @Override
            public void onFailure(Call<ErrorMessage> call, Throwable t) {
                responseI.responseFromDB(new ErrorMessage(1, t.getLocalizedMessage()));
            }
        });
    }
}
