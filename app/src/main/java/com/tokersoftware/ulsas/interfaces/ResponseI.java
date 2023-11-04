package com.tokersoftware.ulsas.interfaces;

import com.tokersoftware.ulsas.model.ErrorMessage;

public interface ResponseI {
    void responseFromDB(ErrorMessage errorMessage);
}
