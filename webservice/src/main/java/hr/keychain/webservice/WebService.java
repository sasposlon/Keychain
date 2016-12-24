package hr.keychain.webservice;

import hr.keychain.webservice.responses.RegistrationResponse;
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
    @POST("RESTController.php")
    Call<RegistrationResponse> registration(@Field("service") String service, @Field("mail") String mail, @Field("password") String password);

    @FormUrlEncoded
    @Headers("Accept: application/json")
    @POST("RESTController.php")
    Call<RegistrationResponse> login(@Field("service") String service, @Field("mail") String mail, @Field("password") String password);

}
