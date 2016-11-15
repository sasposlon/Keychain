package hr.keychain.webservice.requests;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Saša Poslončec on 15.11.16..
 */

public class RegistrationRequest {
    @SerializedName("service")
    private String service;
    @SerializedName("mail")
    public String mail;
    @SerializedName("password")
    public String password;

    public RegistrationRequest(String mail, String password) {
        this.service = "registration";
        this.mail = mail;
        this.password = password;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
