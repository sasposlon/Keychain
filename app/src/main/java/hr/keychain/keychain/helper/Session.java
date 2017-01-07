package hr.keychain.keychain.helper;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

/**
 * Created by root on 27.12.16..
 */

public class Session {

    private SharedPreferences preferences;

    public Session(Context context) {
        preferences = PreferenceManager.getDefaultSharedPreferences(context);
    }

    public void setUsername(String username) {
        preferences.edit().putString("username", username).apply();
    }

    public String getUsername() {
        String username = preferences.getString("username", "");
        return username;
    }
}
