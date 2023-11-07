package com.tokersoftware.ulsas.modules.main.model;

import com.google.gson.annotations.SerializedName;

import org.json.JSONArray;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class Report {

    @SerializedName("serviced_name")
    String serviced_name;
    @SerializedName("serviced_address")
    String serviced_address;

    @SerializedName("serviced_tel_fax")
    String serviced_tel_fax;
    @SerializedName("reported_mal_function")
    String reported_mal_function;
    @SerializedName("service_request_name_surname")
    String service_request_name_surname;
    @SerializedName("service_request_guarantee")
    String service_request_guarantee;
    @SerializedName("service_request_out_of_warranty")
    String service_request_out_of_warranty;
    @SerializedName("service_request_capacity")
    String service_request_capacity;
    @SerializedName("service_request_serial_no")
    String service_request_serial_no;
    @SerializedName("service_request_brand")
    String service_request_brand;
    @SerializedName("service_request_weighing_machine")
    String service_request_weighing_machine;
    @SerializedName("service_request_lc_type")
    String service_request_lc_type;
    @SerializedName("service_request_indicator_type")
    String service_request_indicator_type;
    @SerializedName("service_request_power_source")
    String service_request_power_source;

    @SerializedName("stamp_control_is_the_calibration_lock_closed")
    String stamp_control_is_the_calibration_lock_closed;
    @SerializedName("sticker_before")
    String sticker_before;
    @SerializedName("sticker_after")
    String sticker_after;
    @SerializedName("stamp_control_is_box_indicator_stamped")
    String stamp_control_is_box_indicator_stamped;
    @SerializedName("stamp_control_is_loadcell_connector_stamped")
    String stamp_control_is_loadcell_connector_stamped;
    @SerializedName("stamp_status_last")
    String stamp_status_last;
    @SerializedName("stamp_status_future")
    String stamp_status_future;

    @SerializedName("service_maintenance_personnel")
    String service_maintenance_personnel;
    @SerializedName("service_start_date_time")
    String service_start_date_time;
    @SerializedName("service_end_date_time")
    String service_end_date_time;

    @SerializedName("transactions_made_in_the_service")
    String transactions_made_in_the_service;
    @SerializedName("replaced_materials")
    String replaced_materials;
    @SerializedName("delivery_name")
    String delivery_name;
    @SerializedName("delivery_date")
    String delivery_date;
    @SerializedName("delivery_signature")
    String delivery_signature;
    @SerializedName("delivery_images_before")
    String delivery_images_before;
    @SerializedName("delivery_images_after")
    String delivery_images_after;


    public Report(String serviced_name, String serviced_address, String serviced_tel_fax, String reported_mal_function, String service_request_name_surname, String service_request_guarantee, String service_request_out_of_warranty, String service_request_capacity, String service_request_serial_no, String service_request_brand, String service_request_weighing_machine, String service_request_lc_type, String service_request_indicator_type, String service_request_power_source, String stamp_control_is_the_calibration_lock_closed, String sticker_before, String sticker_after, String stamp_control_is_box_indicator_stamped, String stamp_control_is_loadcell_connector_stamped, String stamp_status_last, String stamp_status_future, String service_maintenance_personnel, String service_start_date_time, String service_end_date_time, String transactionsMadeInTheService, String replaced_materials, String delivery_name, String delivery_date, String delivery_signature, String delivery_images_before, String delivery_images_after) {
        this.serviced_name = serviced_name;
        this.serviced_address = serviced_address;
        this.serviced_tel_fax = serviced_tel_fax;
        this.reported_mal_function = reported_mal_function;
        this.service_request_name_surname = service_request_name_surname;
        this.service_request_guarantee = service_request_guarantee;
        this.service_request_out_of_warranty = service_request_out_of_warranty;
        this.service_request_capacity = service_request_capacity;
        this.service_request_serial_no = service_request_serial_no;
        this.service_request_brand = service_request_brand;
        this.service_request_weighing_machine = service_request_weighing_machine;
        this.service_request_lc_type = service_request_lc_type;
        this.service_request_indicator_type = service_request_indicator_type;
        this.service_request_power_source = service_request_power_source;
        this.stamp_control_is_the_calibration_lock_closed = stamp_control_is_the_calibration_lock_closed;
        this.sticker_before = sticker_before;
        this.sticker_after = sticker_after;
        this.stamp_control_is_box_indicator_stamped = stamp_control_is_box_indicator_stamped;
        this.stamp_control_is_loadcell_connector_stamped = stamp_control_is_loadcell_connector_stamped;
        this.stamp_status_last = stamp_status_last;
        this.stamp_status_future = stamp_status_future;
        this.service_maintenance_personnel = service_maintenance_personnel;
        this.service_start_date_time = service_start_date_time;
        this.service_end_date_time = service_end_date_time;
        this.transactions_made_in_the_service = transactionsMadeInTheService;
        this.replaced_materials = replaced_materials;
        this.delivery_name = delivery_name;
        this.delivery_date = delivery_date;
        this.delivery_signature = delivery_signature;
        this.delivery_images_before = delivery_images_before;
        this.delivery_images_after = delivery_images_after;
    }

    public String getSticker_before() {
        return sticker_before;
    }

    public void setSticker_before(String sticker_before) {
        this.sticker_before = sticker_before;
    }

    public String getSticker_after() {
        return sticker_after;
    }

    public void setSticker_after(String sticker_after) {
        this.sticker_after = sticker_after;
    }

    public String getServiced_name() {
        return serviced_name;
    }

    public void setServiced_name(String serviced_name) {
        this.serviced_name = serviced_name;
    }

    public String getServiced_address() {
        return serviced_address;
    }

    public void setServiced_address(String serviced_address) {
        this.serviced_address = serviced_address;
    }

    public String getServiced_tel_fax() {
        return serviced_tel_fax;
    }

    public void setServiced_tel_fax(String serviced_tel_fax) {
        this.serviced_tel_fax = serviced_tel_fax;
    }

    public String getReported_mal_function() {
        return reported_mal_function;
    }

    public void setReported_mal_function(String reported_mal_function) {
        this.reported_mal_function = reported_mal_function;
    }

    public String getService_request_name_surname() {
        return service_request_name_surname;
    }

    public void setService_request_name_surname(String service_request_name_surname) {
        this.service_request_name_surname = service_request_name_surname;
    }

    public String getService_request_guarantee() {
        return service_request_guarantee;
    }

    public void setService_request_guarantee(String service_request_guarantee) {
        this.service_request_guarantee = service_request_guarantee;
    }

    public String getService_request_out_of_warranty() {
        return service_request_out_of_warranty;
    }

    public void setService_request_out_of_warranty(String service_request_out_of_warranty) {
        this.service_request_out_of_warranty = service_request_out_of_warranty;
    }

    public String getService_request_capacity() {
        return service_request_capacity;
    }

    public void setService_request_capacity(String service_request_capacity) {
        this.service_request_capacity = service_request_capacity;
    }

    public String getService_request_serial_no() {
        return service_request_serial_no;
    }

    public void setService_request_serial_no(String service_request_serial_no) {
        this.service_request_serial_no = service_request_serial_no;
    }

    public String getService_request_brand() {
        return service_request_brand;
    }

    public void setService_request_brand(String service_request_brand) {
        this.service_request_brand = service_request_brand;
    }

    public String getService_request_weighing_machine() {
        return service_request_weighing_machine;
    }

    public void setService_request_weighing_machine(String service_request_weighing_machine) {
        this.service_request_weighing_machine = service_request_weighing_machine;
    }

    public String getService_request_lc_type() {
        return service_request_lc_type;
    }

    public void setService_request_lc_type(String service_request_lc_type) {
        this.service_request_lc_type = service_request_lc_type;
    }

    public String getService_request_indicator_type() {
        return service_request_indicator_type;
    }

    public void setService_request_indicator_type(String service_request_indicator_type) {
        this.service_request_indicator_type = service_request_indicator_type;
    }

    public String getService_request_power_source() {
        return service_request_power_source;
    }

    public void setService_request_power_source(String service_request_power_source) {
        this.service_request_power_source = service_request_power_source;
    }

    public String getStamp_control_is_the_calibration_lock_closed() {
        return stamp_control_is_the_calibration_lock_closed;
    }

    public void setStamp_control_is_the_calibration_lock_closed(String stamp_control_is_the_calibration_lock_closed) {
        this.stamp_control_is_the_calibration_lock_closed = stamp_control_is_the_calibration_lock_closed;
    }



    public String getStamp_control_is_box_indicator_stamped() {
        return stamp_control_is_box_indicator_stamped;
    }

    public void setStamp_control_is_box_indicator_stamped(String stamp_control_is_box_indicator_stamped) {
        this.stamp_control_is_box_indicator_stamped = stamp_control_is_box_indicator_stamped;
    }

    public String getStamp_control_is_loadcell_connector_stamped() {
        return stamp_control_is_loadcell_connector_stamped;
    }

    public void setStamp_control_is_loadcell_connector_stamped(String stamp_control_is_loadcell_connector_stamped) {
        this.stamp_control_is_loadcell_connector_stamped = stamp_control_is_loadcell_connector_stamped;
    }

    public String getStamp_status_last() {
        return stamp_status_last;
    }

    public void setStamp_status_last(String stamp_status_last) {
        this.stamp_status_last = stamp_status_last;
    }

    public String getStamp_status_future() {
        return stamp_status_future;
    }

    public void setStamp_status_future(String stamp_status_future) {
        this.stamp_status_future = stamp_status_future;
    }

    public String getService_maintenance_personnel() {
        return service_maintenance_personnel;
    }

    public void setService_maintenance_personnel(String service_maintenance_personnel) {
        this.service_maintenance_personnel = service_maintenance_personnel;
    }

    public String getService_start_date_time() {
        return service_start_date_time;
    }

    public void setService_start_date_time(String service_start_date_time) {
        this.service_start_date_time = service_start_date_time;
    }

    public String getService_end_date_time() {
        return service_end_date_time;
    }

    public void setService_end_date_time(String service_end_date_time) {
        this.service_end_date_time = service_end_date_time;
    }

    public String getTransactionsMadeInTheService() {
        return transactions_made_in_the_service;
    }

    public void setTransactionsMadeInTheService(String transactionsMadeInTheService) {
        this.transactions_made_in_the_service = transactionsMadeInTheService;
    }

    public String getReplaced_materials() {
        return replaced_materials;
    }

    public void setReplaced_materials(String replaced_materials) {
        this.replaced_materials = replaced_materials;
    }

    public String getDelivery_name() {
        return delivery_name;
    }

    public void setDelivery_name(String delivery_name) {
        this.delivery_name = delivery_name;
    }

    public String getDelivery_date() {
        return delivery_date;
    }

    public void setDelivery_date(String delivery_date) {
        this.delivery_date = delivery_date;
    }

    public String getDelivery_signature() {
        return delivery_signature;
    }

    public void setDelivery_signature(String delivery_signature) {
        this.delivery_signature = delivery_signature;
    }

    public String getDelivery_images_before() {
        return delivery_images_before;
    }

    public void setDelivery_images_before(String delivery_images_before) {
        this.delivery_images_before = delivery_images_before;
    }

    public String getDelivery_images_after() {
        return delivery_images_after;
    }

    public void setDelivery_images_after(String delivery_images_after) {
        this.delivery_images_after = delivery_images_after;
    }
}
