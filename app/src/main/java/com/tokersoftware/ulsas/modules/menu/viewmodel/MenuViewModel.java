package com.tokersoftware.ulsas.modules.menu.viewmodel;

import android.content.Context;

import com.tokersoftware.ulsas.classes.LocalDataManager;
import com.tokersoftware.ulsas.classes.LoginManager;
import com.tokersoftware.ulsas.interfaces.ResponseI;

public class MenuViewModel {

    //SharedPreferences
    private LocalDataManager localDataManager;

    public MenuViewModel(Context c){
        localDataManager = new LocalDataManager(c);
    }

    public void isSigned(ResponseI responseI){
        String emailFromDB = localDataManager.getSharedPreference(LocalDataManager.keys.email.getKey());
        String passFromDB = localDataManager.getSharedPreference(LocalDataManager.keys.password.getKey());

        LoginManager.signIn(emailFromDB, passFromDB, responseI);
    }
}
