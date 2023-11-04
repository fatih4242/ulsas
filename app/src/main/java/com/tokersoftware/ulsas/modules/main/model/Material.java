package com.tokersoftware.ulsas.modules.main.model;

import com.google.gson.annotations.SerializedName;

public class Material {

    @SerializedName("material_name")
    String materialName;
    @SerializedName("material_count")
    String materialCount;
    @SerializedName("material_serial_no")
    String materialSerialNo;

    public Material(String materialName, String materialCount, String materialSerialNo) {
        this.materialName = materialName;
        this.materialCount = materialCount;
        this.materialSerialNo = materialSerialNo;
    }

    public String getMaterialName() {
        return materialName;
    }

    public void setMaterialName(String materialName) {
        this.materialName = materialName;
    }

    public String getMaterialCount() {
        return materialCount;
    }

    public void setMaterialCount(String materialCount) {
        this.materialCount = materialCount;
    }

    public String getMaterialSerialNo() {
        return materialSerialNo;
    }

    public void setMaterialSerialNo(String materialSerialNo) {
        this.materialSerialNo = materialSerialNo;
    }
}
