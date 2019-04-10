package ba.unsa.etf.rma.rma_t1_z1.Fragmenti;

import android.content.Context;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import ba.unsa.etf.rma.rma_t1_z1.Klase.Muzicar;
import ba.unsa.etf.rma.rma_t1_z1.R;

public class FragmentDetaljiOMuzicaru extends Fragment
{
    //ATRIBUTI
    private Muzicar muzicar;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        View iv= inflater.inflate(R.layout.detalji_fragment, container, false);

        if( getArguments()!= null && getArguments().containsKey("muzicar"))
        {
            muzicar=getArguments().getParcelable("muzicar");
            TextView ime = (TextView) iv.findViewById(R.id.mprIme);
            TextView prezime = (TextView) iv.findViewById(R.id.mprPrezime);
            TextView zanr = (TextView) iv.findViewById(R.id.mprZanr);
            ImageView slika = (ImageView) iv.findViewById(R.id.mprSlika);

            //Postavljanje i ostalih vrijednosti na isti način
            ime.setText(muzicar.getIme());
            prezime.setText(muzicar.getPrezime());
            zanr.setText(muzicar.getZanr());

            //POSTAVLJANJE SLIKE U ZAVISNOSTI OD ELEMENTA
            String imeSlike = muzicar.getZanr().toLowerCase().trim();
            slika.setImageResource( getImageId(this.getContext(), imeSlike));
        }
        return iv;
    }

    public static int getImageId(Context context, String imageName)
    {
        return context.getResources().getIdentifier("drawable/" + imageName, null, context.getPackageName());
    }

}
