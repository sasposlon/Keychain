package hr.keychain.webservice.responses;

import org.json.JSONArray;

/**
 * Created by Saša Poslončec on 15.11.16..
 */

public class WebServiceResponse {

    public int code;
    public String type;
    public String message;
    public JSONArray items;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public JSONArray getItems() {
        return items;
    }

    public void setItems(JSONArray items) {
        this.items = items;
    }
}
