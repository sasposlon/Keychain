package hr.keychain.keychain;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;


public class JSONParser {

    public static List<String> parseKljuc(String content){

    //Svi kljucevi samo nazivi
    try{
        JSONArray jsonArray = new JSONArray(content);
        List<String> kljucevi = new ArrayList<String>();

        for (int i=0; i < jsonArray.length(); i++){
            JSONObject jsonobject = jsonArray.getJSONObject(i);
            String naziv = jsonobject.getString("naziv");
            kljucevi.add(naziv);
        }
        return kljucevi;

    }catch(JSONException e){
        e.printStackTrace();
        return null;
    }
    }
}
