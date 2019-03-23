package ba.unsa.etf.rma.rma_t1_z1;

public class Muzicar
{
    //ATRIBUTI
    private String ime, prezime, zanr, cv;

    //KONSTRUKTORI
    public Muzicar(String ime, String prezime, String zanr, String cv) {
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

    public String getZanr() {
        return zanr;
    }

    public void setZanr(String zanr) {
        this.zanr = zanr;
    }

    public String getCv() {
        return cv;
    }

    public void setCv(String cv) {
        this.cv = cv;
    }
}
