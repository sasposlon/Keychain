package hr.keychain.webservice;

import com.squareup.okhttp.OkHttpClient;

import hr.keychain.webservice.responses.WebServiceResponse;
import retrofit.Call;
import retrofit.Callback;
import retrofit.GsonConverterFactory;
import retrofit.Response;
import retrofit.Retrofit;

/**
 * Created by Saša Poslončec on 15.11.16..
 */

public class WebServiceCaller {
    Retrofit retrofit;
    private final String baseUrl = "http://arka.foi.hr/WebDiP/2014_projekti/WebDiP2014x054/WS/";

    public WebServiceCaller() {
        OkHttpClient client = new OkHttpClient();

        this.retrofit = new Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .build();
    }
    /*
    public String registration (final String service, String mail, String password){
        WebService serviceCaller = retrofit.create(WebService.class);
        Call<WebServiceResponse> call = serviceCaller.registration(service, mail, password);
        if(call != null){
            call.enqueue(new Callback<WebServiceResponse>() {
                @Override
                public void onResponse(Response<WebServiceResponse> returnedResponse, Retrofit retrofit) {
                    try{
                        if(returnedResponse.isSuccess()){
                            if(service == "registration"){
                                System.out.println(returnedResponse.body().getCode());
                            }
                            else{
                                System.out.println("Unknown operation");
                            }
                        }
                    } catch (Exception ex){
                        ex.printStackTrace();
                    }
                }

                @Override
                public void onFailure(Throwable t) {
                    t.printStackTrace();
                }
            });
        }
        return status;
    }*/


}
