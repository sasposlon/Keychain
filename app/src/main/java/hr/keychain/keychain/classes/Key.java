package hr.keychain.keychain.classes;

/**
 * Created by Ines on 5.1.2017..
 */

public class Key {

    private String title;
    private String description;
    private String hash;

    public Key() {
    }

    public Key(String title, String description, String hash) {
        this.title = title;
        this.description = description;
        this.hash = hash;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getHash() {
        return hash;
    }

    public void setHash(String hash) {
        this.hash = hash;
    }
}