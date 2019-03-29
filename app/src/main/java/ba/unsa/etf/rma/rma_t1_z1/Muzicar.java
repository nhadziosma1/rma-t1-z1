package ba.unsa.etf.rma.rma_t1_z1;

import java.util.ArrayList;

public class Muzicar
{
    public enum Zanr
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
    }

    //ATRIBUTI
    private String ime, prezime, cv;
    private Zanr zanr;
    private ArrayList<String> najpoznatijePjesem = new ArrayList<>();

    //KONSTRUKTORI
    public Muzicar(String ime, String prezime, Zanr zanr, String cv)
    {
        this.ime = ime;
        this.prezime = prezime;
        this.zanr = zanr;
        this.cv = cv;
    }

    //GETTERI I SETTERI
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

    public Zanr getZanr() {
        return zanr;
    }

    public void setZanr(Zanr zanr) {
        this.zanr = zanr;
    }

    public ArrayList<String> getNajpoznatijePjesem() {
        return najpoznatijePjesem;
    }

    public void setNajpoznatijePjesem(ArrayList<String> najpoznatijePjesem) {
        this.najpoznatijePjesem = najpoznatijePjesem;
    }



}
