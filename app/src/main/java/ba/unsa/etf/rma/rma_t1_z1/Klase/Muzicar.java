package ba.unsa.etf.rma.rma_t1_z1.Klase;

import android.os.Parcel;
import android.os.Parcelable;
import android.widget.ImageView;

import java.io.Serializable;
import java.util.ArrayList;

public class Muzicar implements Parcelable
{
    /*public enum Zanr implements Serializable
    {
        POP("pop"), FOLK("folk"), ROCK("rock"), RAP("rap"), JAZZ("jazz");

        private String imeZanra;

        private Zanr(String s)  //kostruktor enuma ne moze primati enum
        {
            imeZanra = s;
        }

        public String getImeZanra()
        {
            return imeZanra;
        }
    }*/

    //ATRIBUTI
    //private Zanr zanr;
    private String ime, prezime, cv;
    private String zanr;
    private ArrayList<String> najpoznatijePjesem = new ArrayList<>();

    private String urlZaSliku = "https://f4.bcbits.com/img/a0252633309_10.jpg";

    public String getUrlZaSliku() {
        return urlZaSliku;
    }

    public void setUrlZaSliku(String urlZaSliku) {
        this.urlZaSliku = urlZaSliku;
    }


    // I korak za Parcel interfejs
    // This is where you will write your member variables in Parcel. Here you
    // can write in any order. It is not necessary to write all members in Parcel.
    @Override
    public void writeToParcel(Parcel dest, int flags)
    {
        dest.writeString(this.ime);
        dest.writeString(this.prezime);
        dest.writeString(this.cv);
        dest.writeString(this.zanr);

        // "dest.writeTypedList" za listu objekata klasa koje smo sami pisali
        dest.writeList(this.najpoznatijePjesem);
    }

    //KONSTRUKTORI
    // II korak za Parcel interfejs
    // In constructor you will read the variables from Parcel. Make sure to
    // read them in the same sequence in which you have written them in Parcel.
    public Muzicar(Parcel in)
    {
        ime = in.readString();
        prezime = in.readString();
        cv = in.readString();

        //http://void-developer.blogspot.com/2013/06/write-read-parcelable-array-in.html
        //https://www.codota.com/web/assistant/code/rs/5c7cb4daac38dc0001e4288e#L1105
        //prosljeduje je se ClassLoader paramatar, ili null
        najpoznatijePjesem = in.readArrayList(null);
    }

    public Muzicar(String ime, String prezime, String zanr, String cv)
    {
        this.ime = ime;
        this.prezime = prezime;
        this.zanr = zanr;
        this.cv = cv;
    }

    public Muzicar(String ime, String prezime, String zanr)
    {
        this.ime = ime;
        this.prezime = prezime;
        this.zanr = zanr;
        this.cv = cv;
    }

    //GETTERI I SETTERI ( III korak za Parcel interfejs)
    public String getIme() {
        return ime;
    }

    public void setIme(String ime) {
        this.ime = ime;
    }

    public String getPrezime() {
        return prezime;
    }

    public void setPrezime(String prezime) {
        this.prezime = prezime;
    }

    public String getCv() {
        return cv;
    }

    public void setCv(String cv) {
        this.cv = cv;
    }

    /*public Zanr getZanr() {
        return zanr;
    }

    public void setZanr(Zanr zanr) {
        this.zanr = zanr;
    }*/

    public String getZanr() {
        return zanr;
    }

    public void setZanr(String zanr) {
        this.zanr = zanr;
    }

    public ArrayList<String> getNajpoznatijePjesem() {
        return najpoznatijePjesem;
    }

    public void setNajpoznatijePjesem(ArrayList<String> najpoznatijePjesem) {
        this.najpoznatijePjesem = najpoznatijePjesem;
    }

    // IV korak za Parcel interfejs
    public static final Creator<Muzicar> CREATOR = new Creator<Muzicar>() {

        @Override
        public Muzicar createFromParcel(Parcel source)
        {
            return new Muzicar(source);
        }

        @Override
        public Muzicar[] newArray(int size)
        {
            return new Muzicar[size];
        }
    };

    // V korak za Parcel interfejs
    @Override
    public int describeContents()
    {
        return 0;
    }
}
