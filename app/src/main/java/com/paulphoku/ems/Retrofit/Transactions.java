package com.paulphoku.ems.Retrofit;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Transactions {

    @SerializedName("t_id")
    @Expose
    private Integer tId;
    @SerializedName("usr_id")
    @Expose
    private String usrId;
    @SerializedName("t_type")
    @Expose
    private String tType;
    @SerializedName("t_desc")
    @Expose
    private String tDesc;
    @SerializedName("t_date")
    @Expose
    private String tDate;
    @SerializedName("t_amt")
    @Expose
    private Integer tAmt;
    @SerializedName("t_bal")
    @Expose
    private Integer tBal;

    public Integer getTId() {
        return tId;
    }

    public void setTId(Integer tId) {
        this.tId = tId;
    }

    public String getUsrId() {
        return usrId;
    }

    public void setUsrId(String usrId) {
        this.usrId = usrId;
    }

    public String getTType() {
        return tType;
    }

    public void setTType(String tType) {
        this.tType = tType;
    }

    public String getTDesc() {
        return tDesc;
    }

    public void setTDesc(String tDesc) {
        this.tDesc = tDesc;
    }

    public String getTDate() {
        return tDate;
    }

    public void setTDate(String tDate) {
        this.tDate = tDate;
    }

    public Integer getTAmt() {
        return tAmt;
    }

    public void setTAmt(Integer tAmt) {
        this.tAmt = tAmt;
    }

    public Integer getTBal() {
        return tBal;
    }

    public void setTBal(Integer tBal) {
        this.tBal = tBal;
    }

}