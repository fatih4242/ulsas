package com.tokersoftware.ulsas.interfaces;

import com.tokersoftware.ulsas.model.ErrorMessage;
import com.tokersoftware.ulsas.modules.main.model.Material;
import com.tokersoftware.ulsas.modules.main.model.Report;

import org.json.JSONArray;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface API {


    @GET("api/login.php")
    Call<ErrorMessage> Login(@Query("email") String email, @Query("password") String password);

    @FormUrlEncoded
    @POST("api/addReport.php")
    Call<ErrorMessage> AddReport(
            @Field("serviced_name") String servicedName,
            @Field("serviced_address") String servicedAddress,
            @Field("serviced_tel_fax") String servicedTelFax,
            @Field("reported_malfunction") String reportedMalFunction,

            @Field("service_request_name_surname") String serviceRequestNameSurname,
            @Field("service_request_guarantee") String serviceRequestGuarantee,
            @Field("service_request_out_of_warranty") String serviceRequestOutOfWarranty,
            @Field("service_request_capacity") String serviceRequestCapacity,
            @Field("service_request_serial_no") String serviceRequestSerialNo,
            @Field("service_request_brand") String serviceRequestBrand,
            @Field("service_request_weighing_machine") String serviceRequestWeighingMachine,
            @Field("service_request_lc_type") String serviceRequestLCType,
            @Field("service_request_indicator_type") String serviceRequestIndicatorType,
            @Field("service_request_power_source") String serviceRequestPowerSource,

            @Field("stamp_control_is_the_calibration_lock_closed") String stampControlIsTheCalibrationLockClosed,
            @Field("stamp_control_sticker_exists") String stampControlStickerExists,
            @Field("stamp_control_is_box_indicator_stamped") String stampControlIsBoxIndicatorStamped,
            @Field("stamp_control_is_loadcell_connector_stamped") String stampControlIsLoadcellConnectorStamped,
            @Field("stamp_status_last") String stampStatusLast,
            @Field("stamp_status_future") String stampStatusFuture,

            @Field("service_maintenance_personnel") String serviceMaintenancePersonnel,
            @Field("service_start_date_time") String serviceStartDateTime,
            @Field("service_end_date_time") String serviceEndDateTime,

            @Field("transactions_made_in_the_service") String transactionsMadeInTheService,
            @Field("replaced_materials") String replacedMaterials,

            @Field("delivery_name") String deliveryName,
            @Field("delivery_date") String deliveryDate,
            @Field("delivery_signature") String deliverySignature,
            @Field("delivery_images_before") String deliveryImagesBefore,
            @Field("delivery_images_after") String deliveryImagesAfter
    );
}
