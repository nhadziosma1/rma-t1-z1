package ba.unsa.etf.rma.rma_t1_z1.Fragmenti;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import ba.unsa.etf.rma.rma_t1_z1.Adapteri.AlbumAdapter;
import ba.unsa.etf.rma.rma_t1_z1.Klase.Muzicar;
import ba.unsa.etf.rma.rma_t1_z1.Klase.PretragaSlike;
import ba.unsa.etf.rma.rma_t1_z1.R;

public class FragmentDetaljiOMuzicaru extends Fragment
{
    //ATRIBUTI
    private Muzicar muzicar;
    private TextView ime;
    private TextView prezime;
    private TextView zanr;
    private ImageView slika;
    private Button podijeli;

    private ListView lvAlbumi;
    private AlbumAdapter adapterAlbuma;
    private View headerView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        View iv= inflater.inflate(R.layout.detalji_fragment, container, false);

        if( getArguments()!= null && getArguments().containsKey("muzicar"))
        {
            muzicar=getArguments().getParcelable("muzicar");
            ime = (TextView) iv.findViewById(R.id.mprIme);
            prezime = (TextView) iv.findViewById(R.id.mprPrezime);
            zanr = (TextView) iv.findViewById(R.id.mprZanr);
            slika = (ImageView) iv.findViewById(R.id.mprSlika);
            podijeli = (Button) iv.findViewById(R.id.mprPodijeli);
            lvAlbumi = (ListView) iv.findViewById(R.id.lvAlbumi);

            //Postavljanje i ostalih vrijednosti na isti način
            ime.setText(muzicar.getIme());
            prezime.setText(muzicar.getPrezime());
            zanr.setText(muzicar.getZanr());

            zanr.setBackgroundColor(Color.RED);

            headerView = getLayoutInflater().inflate(R.layout.footer, null);

            TextView naslovHeadera;
            if(headerView instanceof TextView)
            {
                naslovHeadera = (TextView) headerView;
                naslovHeadera.setText("ALBUMI:");
                lvAlbumi.addHeaderView(headerView);
                adapterAlbuma = new AlbumAdapter(this.getContext(), R.layout.adapter_album, muzicar.getAlbumi());
                lvAlbumi.setAdapter(adapterAlbuma);

                lvAlbumi.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id)
                    {
                        //AKO SLIKNE NA NEKI OD ALBUMA, TREBA DA MU SE OTOVRI SPOTIFY STRANICA TA TAJ ALBUM
                    }
                });
            }




            //POSTAVLJANJE SLIKE U ZAVISNOSTI OD ELEMENTA
            //String imeSlike = muzicar.getZanr().toLowerCase().trim();
            //slika.setImageResource(getImageId(this.getContext(), imeSlike));

            /*URL myUrl = null;
            try
            {
                myUrl = new URL(muzicar.getUrlZaSliku());
                InputStream inputStream = (InputStream)myUrl.getContent();
                Drawable drawable = Drawable.createFromStream(inputStream, null);
                slika.setImageDrawable(drawable);
            }
            catch (MalformedURLException e)
            {
                e.printStackTrace();
            }
            catch (IOException e)
            {
                e.printStackTrace();
            }*/

            /*try
            {
                Bitmap bitmap = BitmapFactory.decodeStream((InputStream)new URL(muzicar.getUrlZaSliku()).getContent());
                slika.setImageBitmap(bitmap);
            }
            catch (MalformedURLException e)
            {
                e.printStackTrace();
            }
            catch (IOException e)
            {
                e.printStackTrace();
            }*/
            //loadImageFromURL(muzicar.getUrlZaSliku(), slika);

            new PretragaSlike(slika).execute(muzicar.getUrlZaSliku());

            podijeli.setOnClickListener(new View.OnClickListener()
            {
                @Override
                public void onClick(View v)
                {
                    Intent otvoriFB = new Intent();
                    otvoriFB.setAction(Intent.ACTION_SEND);
                    otvoriFB.putExtra(Intent.EXTRA_TEXT,"POSLANO PREKO INTENTA");
                    otvoriFB.setType("text/plain");

                    if(otvoriFB.resolveActivity(getActivity().getPackageManager() ) != null)
                    {
                        startActivity(otvoriFB.createChooser(otvoriFB, "Share"));
                    }
                }
            });
        }
        return iv;
    }

    public static int getImageId(Context context, String imageName)
    {
        return context.getResources().getIdentifier("drawable/" + imageName, null, context.getPackageName());
    }

    //nETWORK ON MAIN THREAD EXCEPTION!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
    /*public boolean loadImageFromURL(String fileUrl, ImageView iv)
    {
        try
        {

            URL myFileUrl = new URL (fileUrl);
            HttpURLConnection conn = (HttpURLConnection) myFileUrl.openConnection();
            conn.setDoInput(true);
            conn.connect();

            InputStream is = conn.getInputStream();
            iv.setImageBitmap(BitmapFactory.decodeStream(is));

            return true;

        }
        catch (MalformedURLException e)
        {
            e.printStackTrace();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

        return false;
    }*/
}
