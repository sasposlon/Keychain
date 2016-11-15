package hr.keychain.webservice.responses;

/**
 * Created by Saša Poslončec on 15.11.16..
 */

public class WebServiceResponse {

    public int code;
    public String type;
    public String message;
    public String items;

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

    public String getItems() { return items; }

    public void setItems(String items) {
        this.items = items;
    }
}
