package hr.keychain.database.entities;

import com.raizlabs.android.dbflow.annotation.Column;
import com.raizlabs.android.dbflow.annotation.ForeignKey;
import com.raizlabs.android.dbflow.annotation.PrimaryKey;
import com.raizlabs.android.dbflow.annotation.Table;
import com.raizlabs.android.dbflow.structure.BaseModel;

import hr.keychain.database.MainDatabase;

/**
 * Created by Saša Poslončec on 12.11.16..
 */
@Table(database = MainDatabase.class)
public class Key extends BaseModel {

    @PrimaryKey
    @Column String id_key;

    @Column String key_hash;

    @Column
    @ForeignKey(tableClass = Geolocation.class)
    String geolocation;

    @Column
    @ForeignKey(tableClass = Wifi.class)
    String wifi;

    public Key() {
    }

    public Key(String id_key, String key_hash, String geolocation, String wifi) {
        this.id_key = id_key;
        this.key_hash = key_hash;
        this.geolocation = geolocation;
        this.wifi = wifi;
    }

    public String getId_key() {
        return id_key;
    }

    public void setId_key(String id_key) {
        this.id_key = id_key;
    }

    public String getKey_hash() {
        return key_hash;
    }

    public void setKey_hash(String key_hash) {
        this.key_hash = key_hash;
    }

    public String getGeolocation() {
        return geolocation;
    }

    public void setGeolocation(String geolocation) {
        this.geolocation = geolocation;
    }

    public String getWifi() {
        return wifi;
    }

    public void setWifi(String wifi) {
        this.wifi = wifi;
    }
}
