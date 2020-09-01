package com.paulphoku.ems.Retrofit;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class User {

    @SerializedName("usr_id")
    @Expose
    private Integer usrId;
    @SerializedName("usr_unique_id")
    @Expose
    private String usrUniqueId;
    @SerializedName("usr_salt")
    @Expose
    private String usrSalt;
    @SerializedName("usr_created_at")
    @Expose
    private String usrCreatedAt;
    @SerializedName("usr_updated_at")
    @Expose
    private String usrUpdatedAt;
    @SerializedName("usr_fname")
    @Expose
    private String usrFname;
    @SerializedName("usr_lname")
    @Expose
    private String usrLname;
    @SerializedName("usr_email")
    @Expose
    private String usrEmail;
    @SerializedName("usr_encrypted_password")
    @Expose
    private String usrEncryptedPassword;

    public Integer getUsrId() {
        return usrId;
    }

    public void setUsrId(Integer usrId) {
        this.usrId = usrId;
    }

    public String getUsrUniqueId() {
        return usrUniqueId;
    }

    public void setUsrUniqueId(String usrUniqueId) {
        this.usrUniqueId = usrUniqueId;
    }

    public String getUsrSalt() {
        return usrSalt;
    }

    public void setUsrSalt(String usrSalt) {
        this.usrSalt = usrSalt;
    }

    public String getUsrCreatedAt() {
        return usrCreatedAt;
    }

    public void setUsrCreatedAt(String usrCreatedAt) {
        this.usrCreatedAt = usrCreatedAt;
    }

    public String getUsrUpdatedAt() {
        return usrUpdatedAt;
    }

    public void setUsrUpdatedAt(String usrUpdatedAt) {
        this.usrUpdatedAt = usrUpdatedAt;
    }

    public String getUsrFname() {
        return usrFname;
    }

    public void setUsrFname(String usrFname) {
        this.usrFname = usrFname;
    }

    public String getUsrLname() {
        return usrLname;
    }

    public void setUsrLname(String usrLname) {
        this.usrLname = usrLname;
    }

    public String getUsrEmail() {
        return usrEmail;
    }

    public void setUsrEmail(String usrEmail) {
        this.usrEmail = usrEmail;
    }

    public String getUsrEncryptedPassword() {
        return usrEncryptedPassword;
    }

    public void setUsrEncryptedPassword(String usrEncryptedPassword) {
        this.usrEncryptedPassword = usrEncryptedPassword;
    }

}