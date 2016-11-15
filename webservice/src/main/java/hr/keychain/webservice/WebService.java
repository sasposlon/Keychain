package hr.keychain.webservice;

import hr.keychain.webservice.requests.RegistrationRequest;
import hr.keychain.webservice.responses.WebServiceResponse;
import retrofit.Call;
import retrofit.http.Field;
import retrofit.http.FormUrlEncoded;
import retrofit.http.Headers;
import retrofit.http.POST;

/**
 * Created by Saša Poslončec on 15.11.16..
 */

public interface WebService {

    @FormUrlEncoded
    @Headers("Accept: application/json")
    @POST("RESTController")
    Call<WebServiceResponse> registration(@Field("service") String service, @Field("mail") String mail, @Field("password") String password);

}