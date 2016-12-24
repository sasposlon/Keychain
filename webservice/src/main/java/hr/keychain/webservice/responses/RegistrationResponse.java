package hr.keychain.webservice.responses;

import hr.keychain.webservice.helper.UsersItems;

/**
 * Created by root on 24.12.16..
 */

public class RegistrationResponse extends WebServiceResponse{

    public UsersItems items;

    public UsersItems getItems() {
        return items;
    }

    public void setItems(UsersItems items) {
        this.items = items;
    }
}
