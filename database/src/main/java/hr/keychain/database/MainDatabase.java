package hr.keychain.database;

import com.raizlabs.android.dbflow.annotation.Database;

/**
 * Created by Saša Poslončec on 10.11.16..
 */
@Database(name = MainDatabase.NAME, version = MainDatabase.VERSION)
public class MainDatabase {

    public static final String NAME = "keychain";
    public static final int VERSION = 1;

}
