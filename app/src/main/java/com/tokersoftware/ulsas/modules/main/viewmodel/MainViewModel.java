package com.tokersoftware.ulsas.modules.main.viewmodel;

import android.content.Context;

import com.tokersoftware.ulsas.classes.LocalDataManager;
import com.tokersoftware.ulsas.classes.LoginManager;
import com.tokersoftware.ulsas.interfaces.API;
import com.tokersoftware.ulsas.interfaces.ResponseI;
import com.tokersoftware.ulsas.model.ErrorMessage;
import com.tokersoftware.ulsas.modules.main.model.Report;
import com.tokersoftware.ulsas.network.NetworkConstant;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainViewModel {


    public void uploadReport(Report report, ResponseI responseI){
        API retrofit = NetworkConstant.getClient().create(API.class);

        Call<ErrorMessage> call = retrofit.AddReport(
                report.getServiced_name(),
                report.getServiced_address(),
                report.getServiced_tel_fax(),
                report.getReported_mal_function(),
                report.getService_request_name_surname(),
                report.getService_request_guarantee(),
                report.getService_request_out_of_warranty(),
                report.getService_request_capacity(),
                report.getService_request_serial_no(),
                report.getService_request_brand(),
                report.getService_request_weighing_machine(),
                report.getService_request_lc_type(),
                report.getService_request_indicator_type(),
                report.getService_request_power_source(),
                report.getStamp_control_is_the_calibration_lock_closed(),
                report.getSticker_before(),
                report.getSticker_after(),
                report.getStamp_control_is_box_indicator_stamped(),
                report.getStamp_control_is_loadcell_connector_stamped(),
                report.getStamp_status_last(),
                report.getStamp_status_future(),
                report.getService_maintenance_personnel(),
                report.getService_start_date_time(),
                report.getService_end_date_time(),
                report.getTransactionsMadeInTheService(),
                report.getReplaced_materials(),
                report.getDelivery_name(),
                report.getDelivery_date(),
                report.getDelivery_signature(),
                report.getDelivery_images_before(),
                report.getDelivery_images_after()
        );


        call.enqueue(new Callback<ErrorMessage>() {
            @Override
            public void onResponse(Call<ErrorMessage> call, Response<ErrorMessage> response) {
                if (response.isSuccessful()){
                    responseI.responseFromDB(response.body());
                } else {
                    responseI.responseFromDB(new ErrorMessage( 1, response.message()));
                }
            }

            @Override
            public void onFailure(Call<ErrorMessage> call, Throwable t) {
                responseI.responseFromDB(new ErrorMessage(1, t.getLocalizedMessage()));
            }
        });

    }

}
