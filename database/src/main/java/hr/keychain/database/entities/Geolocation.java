package hr.keychain.database.entities;

import com.raizlabs.android.dbflow.annotation.Column;
import com.raizlabs.android.dbflow.annotation.PrimaryKey;
import com.raizlabs.android.dbflow.annotation.Table;
import com.raizlabs.android.dbflow.structure.BaseModel;

import hr.keychain.database.MainDatabase;

/**
 * Created by Saša Poslončec on 12.11.16..
 */
@Table(database = MainDatabase.class)
public class Geolocation extends BaseModel{

    @PrimaryKey
    @Column String id_geolocation;

    @Column String address;
    @Column double longitude;
    @Column double latitude;

    public Geolocation(String id_geolocation, String address, double longitude, double latitude) {
        this.id_geolocation = id_geolocation;
        this.address = address;
        this.longitude = longitude;
        this.latitude = latitude;
    }

    public Geolocation() {
    }

    public String getId_geolocation() {
        return id_geolocation;
    }

    public void setId_geolocation(String id_geolocation) {
        this.id_geolocation = id_geolocation;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }
}
