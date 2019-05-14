package ba.unsa.etf.rma.rma_t1_z1.Klase;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;

//funkcija za obradu web servisa koaj se odvija na zasebnoj niti, ne na glavnoj
public class PretragaMuzicara extends AsyncTask<String, Integer, Void>
{
    //ATRIBUTI
    ArrayList<Muzicar> rezultatPretrage = new ArrayList<>();
    private OnMuzicarSearchDone pozivatelj;

    Context kontekst;

    //INTERFEJS
    public interface OnMuzicarSearchDone
    {
        public void onDone( ArrayList<Muzicar> listaMuzicara);
    }

    //KONSTRUKTOR
    public PretragaMuzicara(OnMuzicarSearchDone p, Context context)
    {
        //"pozivatelj" se koristi kao referenca na objekat koji je koristen za kreiranje objekta tipa "PretragaMuzicar"
        pozivatelj = p;

        kontekst = context;
    }

    //METODE
    @Override
    protected Void doInBackground(String... params)
    {
        ArrayList<Muzicar> muzicari = new ArrayList<>();

        String query = null;

        try
        {
            // "URLEncoder" - klasa za kodiranje u HTML format.
            //Ova klasa sadrzi staticke metode za konvertovanje stringa u
            //"application/x-www-form-urlencoded" MIME format.
            // Drugi paramtera ove funkcije je specificiranje formata koijm se
            //kodirati, a prvi je string koji se kodira.
            query = URLEncoder.encode(params[0], "utf-8");
        }
        catch (UnsupportedEncodingException e)
        {
            e.printStackTrace();
        }

        String url1 = "https://api.spotify.com/v1/search?q="+query+"&type=artist";

        try
        {
            URL url = new URL(url1);

            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();

            //Tokens are data automatically generated by a Web server and passed as parameters in URLs Tokens are data automatically
            // generated by a Web server and passed as parameters in URLs

            //Token is a necessary step to call a protected API. The token needs to be used to access a Web API. The way to do it is by
            // setting the Authorization header to be "Bearer", followed by a space, followed by the access token.
            //poziv ima oblik: "conn.setRequestProperty("Authorization", "Bearer " + accessToken);"
            urlConnection.setRequestProperty("Authorization", "Bearer "
                    +"BQA4fxNCSKpXrSbQnrimH1ndEj5ImZPJO3LxqXFzOxDVpdMHZWKsuPkidiuk0svrphVJJQk27XiYEb1qdRWzWvGrYZ9sks5_0GumvyrNQ80mDC3pcrHe-1xMjijJEiH7piV6DKc7yCVPtW0DSpbm933pWs1xy2mCMA");
            //REZULTAT POZIVA WEB SERVISA JE INPUT STREAM
            InputStream in = new BufferedInputStream(urlConnection.getInputStream());

            //PROCITANI STRING JE U JSON-FORMATU
            String rezultat = convertStreamToString(in);
            //upisiUTxtFajl(rezultat);

            JSONObject jo = new JSONObject(rezultat);
            JSONObject nizMuzicara = jo.getJSONObject("artists");
            JSONArray items = nizMuzicara.getJSONArray("items");

            for (int i = 0; i < 5; i++)
            {
                JSONObject jedanMuzicar = items.getJSONObject(i);
                String name = jedanMuzicar.getString("name");
                String artist_ID = jedanMuzicar.getString("id");
                //Ovdje trebate dodati kreiranje objekta Muzičara i dodavanje u listu
                //Ovo uradite na sličan način kako ste radili i kada ste hardkodirali
                //podatke samo što sada koristite stvarne podatke

                JSONArray oSlici = jedanMuzicar.getJSONArray("images");
                String urlSlike = "https://f4.bcbits.com/img/a0252633309_10.jpg"; //ovo je slika "no photo"

                //provjera je neophodna jer za neke muzicare mozda nema slika, ali mora postojati dio "images"
                if(oSlici.length() > 0)
                {
                    JSONObject detaljiOSlici = oSlici.getJSONObject(0);
                    urlSlike = detaljiOSlici.getString("url");
                }

                //razdvajanje imena i prezimena
                String[] imeIPrezime = name.split(" ");

                Muzicar novi;
                if(imeIPrezime.length == 2)
                novi = new Muzicar(imeIPrezime[0], imeIPrezime[1],"POP");
                else
                    novi = new Muzicar(name, "","POP");

                novi.setUrlZaSliku(urlSlike);

                rezultatPretrage.add(novi);
            }
        }
        catch (MalformedURLException e)
        {
            e.printStackTrace();
            //ispisiPoruku("MalformedURLException", e.getMessage());
        }
        catch (IOException e)
        {
            e.printStackTrace();
            //ispisiPoruku("IOException", e.getMessage());
        }
        catch (JSONException e)
        {
            e.printStackTrace();
            //ispisiPoruku("JSONException", e.getMessage());
        }

        //DODAVANJE DETALJA O ALBUMU MUZICARA--------------------------------------------------------------------
        for(int i=0; i<rezultatPretrage.size(); i++)
        {
            String query2;

            try
            {
                query2 = URLEncoder.encode(rezultatPretrage.get(i).getIme()+rezultatPretrage.get(i).getPrezime(), "utf-8");
            }
            catch (UnsupportedEncodingException e)
            {
                e.printStackTrace();
            }

            String url2 = "https://api.spotify.com/v1/search?q="+query+"&type=artist,album";

            try
            {
                URL url2String = new URL(url2);

                HttpURLConnection urlConnection = (HttpURLConnection) url2String.openConnection();

                //Tokens are data automatically generated by a Web server and passed as parameters in URLs Tokens are data automatically
                // generated by a Web server and passed as parameters in URLs

                //Token is a necessary step to call a protected API. The token needs to be used to access a Web API. The way to do it is by
                // setting the Authorization header to be "Bearer", followed by a space, followed by the access token.
                //poziv ima oblik: "conn.setRequestProperty("Authorization", "Bearer " + accessToken);"
                urlConnection.setRequestProperty("Authorization", "Bearer "
                        +"BQA4fxNCSKpXrSbQnrimH1ndEj5ImZPJO3LxqXFzOxDVpdMHZWKsuPkidiuk0svrphVJJQk27XiYEb1qdRWzWvGrYZ9sks5_0GumvyrNQ80mDC3pcrHe-1xMjijJEiH7piV6DKc7yCVPtW0DSpbm933pWs1xy2mCMA");
                //REZULTAT POZIVA WEB SERVISA JE INPUT STREAM
                InputStream in = new BufferedInputStream(urlConnection.getInputStream());

                //PROCITANI STRING JE U JSON-FORMATU
                String rezultat = convertStreamToString(in);
                //upisiUTxtFajl(rezultat);

                JSONObject jo = new JSONObject(rezultat);
                JSONObject pocetniElement = jo.getJSONObject("albums");
                JSONArray nizAlbuma = pocetniElement.getJSONArray("items");

                for (int j = 0; j < 5; j++)
                {
                    JSONObject jedanAlbum = nizAlbuma.getJSONObject(j);
                    String imeAlbuma = jedanAlbum.getString("name");

                    rezultatPretrage.get(i).getAlbumi().add(imeAlbuma);
                }

                //rezultatPretrage.get(i).getAlbumi().add("nista");
            }
            catch (MalformedURLException e)
            {
                e.printStackTrace();
                //ispisiPoruku("MalformedURLException", e.getMessage());
            }
            catch (IOException e)
            {
                e.printStackTrace();
                //ispisiPoruku("IOException", e.getMessage());
            }
            catch (JSONException e)
            {
                e.printStackTrace();
                //ispisiPoruku("JSONException", e.getMessage());
            }
        }

        return null;
    }

    //aplikacija se zamrszne kada se ova metoda pozove??????????????????????????????????????
    private void ispisiPoruku(String imeIzuzetka, String porukaIzuzetka)
    {
        AlertDialog alertDialog = new AlertDialog.Builder(kontekst).create();
        alertDialog.setTitle("IZUZETAK tipa: "+ imeIzuzetka);
        alertDialog.setMessage("e.getMessage(): "+ porukaIzuzetka);

        alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which)
                    {
                        dialog.dismiss();
                    }
                });

        alertDialog.show();
    }

    /*private void upisiUTxtFajl(String sadrzaj, Context c)
    {
        try
        {
            OutputStreamWriter outputStreamWriter = new OutputStreamWriter(c.openFileOutput("config.txt", Context.MODE_PRIVATE));
            outputStreamWriter.write(sadrzaj);
            outputStreamWriter.close();
        }
        catch (IOException e)
        {
            Log.e("Exception", "File write failed: " + e.toString());
        }

    }*/

    public String convertStreamToString(InputStream is)
    {
        //BufferedReader je klasa wrapper za oboje "InputStreamReader/FileReader". KOristeci bufffer(spremink), efikasnije odradjuje citanje bajta(tj. charova, jer je jedan char jena bajt)
        BufferedReader reader = new BufferedReader(new InputStreamReader(is));

        //String is immutable, if you try to alter their values, another object gets created, whereas StringBuffer and StringBuilder are mutable so they can change their values.
        // Thread-Safety Difference: The difference between StringBuffer and StringBuilder is that StringBuffer is thread-safe.
        StringBuilder sb = new StringBuilder();
        String line = null;
        try
        {
            while ((line = reader.readLine()) != null)
                sb.append(line + "\n");
        }
        catch (IOException e)
        {
            e.getStackTrace();
        }
        finally
        {
            try
            {
                is.close();
            }
            catch (IOException e)
            {
                e.getStackTrace();
            }
        }
        return sb.toString();
    }

    protected void onPostExecute(Void aVoid)
    {
        super.onPostExecute(aVoid);

        pozivatelj.onDone(rezultatPretrage);
    }
}

