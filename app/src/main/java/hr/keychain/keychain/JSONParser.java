package hr.keychain.keychain;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;


public class JSONParser {

    public static ArrayList<Key> parseKljuc(String content){

    //Svi kljucevi (naziv, sirina, duzina)
    try{
        JSONArray jsonArray = new JSONArray(content);
        ArrayList<Key> kljucevi = new ArrayList<Key>();

        for (int i=0; i < jsonArray.length(); i++){
            JSONObject jsonobject = jsonArray.getJSONObject(i);
            Key k = new Key();
            k.setTitle(jsonobject.getString("naziv"));
            k.setLatitude(jsonobject.getDouble("longitude"));
            k.setLongitude(jsonobject.getDouble("latitude"));
            kljucevi.add(k);
        }
        return kljucevi;

    }catch(JSONException e){
        e.printStackTrace();
        return null;
    }
    }
}
