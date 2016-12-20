package hr.keychain.keychain.klase;

/**
 * Created by anto_ on 20.12.2016..
 */

public class Izbornik {
    private String text;
    private int image;

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public Izbornik(){}

    public Izbornik(String naziv, int image) {
        this.text = naziv;
        this.image = image;

    }

    public String getNaziv() {
        return text;
    }

    public void setNaziv(String naziv) {
        this.text = naziv;
    }
}
