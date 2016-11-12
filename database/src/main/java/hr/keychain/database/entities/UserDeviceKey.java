package hr.keychain.database.entities;

import com.raizlabs.android.dbflow.annotation.Column;
import com.raizlabs.android.dbflow.annotation.ForeignKey;
import com.raizlabs.android.dbflow.annotation.PrimaryKey;
import com.raizlabs.android.dbflow.annotation.Table;
import com.raizlabs.android.dbflow.structure.BaseModel;

import java.util.Date;

import hr.keychain.database.MainDatabase;

/**
 * Created by Saša Poslončec on 12.11.16..
 */
@Table(database = MainDatabase.class)
public class UserDeviceKey extends BaseModel {

    @PrimaryKey
    @Column
    @ForeignKey(tableClass = User.class)
    String user;

    @PrimaryKey
    @Column
    @ForeignKey(tableClass = Device.class)
    String device;

    @PrimaryKey
    @Column
    @ForeignKey(tableClass = Key.class)
    String key;

    @Column Date date_added;

    public UserDeviceKey() {
    }

    public UserDeviceKey(String user, String device, String key, Date date_added) {
        this.user = user;
        this.device = device;
        this.key = key;
        this.date_added = date_added;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getDevice() {
        return device;
    }

    public void setDevice(String device) {
        this.device = device;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public Date getDate_added() {
        return date_added;
    }

    public void setDate_added(Date date_added) {
        this.date_added = date_added;
    }
}
