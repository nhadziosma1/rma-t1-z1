package ba.unsa.etf.rma.rma_t1_z1.Klase;

public class Album
{
    //ATRIBUTI
    private String imeAlbuma;
    private String urlZaAlbum;

    //KONSTRUKTRORI
    public Album(String imeAlbuma, String urlZaAlbum)
    {
        this.imeAlbuma = imeAlbuma;
        this.urlZaAlbum = urlZaAlbum;
    }

    public Album()
    {}

    //GETTERI I SETTERI
    public String getImeAlbuma() {
        return imeAlbuma;
    }

    public void setImeAlbuma(String imeAlbuma) {
        this.imeAlbuma = imeAlbuma;
    }

    public String getUrlZaAlbum() {
        return urlZaAlbum;
    }

    public void setUrlZaAlbum(String urlZaAlbum) {
        this.urlZaAlbum = urlZaAlbum;
    }
}
