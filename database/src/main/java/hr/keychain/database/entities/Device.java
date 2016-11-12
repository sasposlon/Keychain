package hr.keychain.database.entities;

import com.raizlabs.android.dbflow.annotation.Column;
import com.raizlabs.android.dbflow.annotation.PrimaryKey;
import com.raizlabs.android.dbflow.annotation.Table;
import com.raizlabs.android.dbflow.structure.BaseModel;

import java.util.Date;

import hr.keychain.database.MainDatabase;

/**
 * Created by Saša Poslončec on 12.11.16..
 */
@Table(database = MainDatabase.class)
public class Device extends BaseModel {

    @PrimaryKey
    @Column String serial_number;

    @Column String manufacturer;
    @Column String model;
    @Column Date last_synced;
    @Column Date last_modified;

    public Device(String serial_number, String manufacturer, String model, Date last_synced, Date last_modified) {
        this.serial_number = serial_number;
        this.manufacturer = manufacturer;
        this.model = model;
        this.last_synced = last_synced;
        this.last_modified = last_modified;
    }

    public Device() {

    }

    public String getSerial_number() {
        return serial_number;
    }

    public void setSerial_number(String serial_number) {
        this.serial_number = serial_number;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public Date getLast_synced() {
        return last_synced;
    }

    public void setLast_synced(Date last_synced) {
        this.last_synced = last_synced;
    }

    public Date getLast_modified() {
        return last_modified;
    }

    public void setLast_modified(Date last_modified) {
        this.last_modified = last_modified;
    }
}
