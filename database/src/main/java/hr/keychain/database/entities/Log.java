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
public class Log extends BaseModel {

    @PrimaryKey(autoincrement = true)
    @Column int id_log;

    @Column String action;

    @Column
    @ForeignKey(tableClass = User.class)
    int user;

    public Log() {
    }

    public int getId_log() {
        return id_log;
    }

    public void setId_log(int id_log) {
        this.id_log = id_log;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public int getUser() {
        return user;
    }

    public void setUser(int user) {
        this.user = user;
    }
}
