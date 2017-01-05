package hr.keychain.keychain;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import hr.keychain.keychain.classes.Coordinates;
import hr.keychain.keychain.classes.Key;


public class JSONParser {

    public static ArrayList<Coordinates> parseLock(String content){

    //Svi kljucevi (naziv, sirina, duzina)  --> za ispis kljuceva koji su dostupni za koristenje u Lock/Unlock
    try{
        JSONArray jsonArray = new JSONArray(content);
        ArrayList<Coordinates> kljucevi = new ArrayList<Coordinates>();

        for (int i=0; i < jsonArray.length(); i++){
            JSONObject jsonobject = jsonArray.getJSONObject(i);
            Coordinates k = new Coordinates();
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

    public static List<String> parseAllLocks(String content){

        //Svi kljucevi (naziv) --> za ispis u fragmentu AllKeys
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

    public static ArrayList<Key> parseEditKey(String content){

        //Svi kljucevi (naziv, opis, hash)  --> za uredivanje kljuca(EDIT)
        try{
            JSONArray jsonArray = new JSONArray(content);
            ArrayList<Key> kljucevi = new ArrayList<Key>();

            for (int i=0; i < jsonArray.length(); i++){
                JSONObject jsonobject = jsonArray.getJSONObject(i);
                Key k = new Key();
                k.setTitle(jsonobject.getString("naziv"));
                k.setDescription(jsonobject.getString("opis"));
                k.setHash(jsonobject.getString("hash"));
                kljucevi.add(k);
            }
            return kljucevi;

        }catch(JSONException e){
            e.printStackTrace();
            return null;
        }
    }
}
