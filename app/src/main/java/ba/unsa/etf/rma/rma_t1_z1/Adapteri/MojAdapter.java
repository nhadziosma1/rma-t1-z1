package ba.unsa.etf.rma.rma_t1_z1.Adapteri;

import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import ba.unsa.etf.rma.rma_t1_z1.Klase.Muzicar;
import ba.unsa.etf.rma.rma_t1_z1.R;

public class MojAdapter extends BaseAdapter implements View.OnClickListener
{
    /*********** Declare Used Variables *********/
    private Activity activity;
    private ArrayList data;
    private static LayoutInflater inflater = null;
    public Resources res;
    Muzicar muzicar = null;
    int i=0;

    /*************  CustomAdapter Constructor *****************/
    public MojAdapter(Activity a, ArrayList d,Resources resLocal)
    {

        /********** Take passed values **********/
        activity = a;
        data = d;
        res = resLocal;

        /***********  Layout inflator to call external xml layout () ***********/
        inflater = ( LayoutInflater )activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    /******** What is the size of Passed Arraylist Size ************/
    public int getCount()
    {
        if(data.size()<=0)
            return 1;

        return data.size();
    }

    public Object getItem(int position) {
        return position;
    }

    public long getItemId(int position) {
        return position;
    }

    /********* Create a holder Class to contain inflated xml file elements *********/
    public static class ViewHolder
    {
        public TextView zanr;
        public TextView ime;
        public TextView prezime;
        public ImageView slika;
    }

    /****** Depends upon data size called for each row , Create each ListView row *****/
    public View getView(int position, View convertView, ViewGroup parent)
    {
        View vi = convertView;
        ViewHolder holder;

        if(convertView == null)
        {

            /****** Inflate tabitem.xml file for each row ( Defined below ) *******/
            vi = inflater.inflate(R.layout.moj_izgled, null);

            /****** View Holder Object to contain tabitem.xml file elements ******/

            holder = new ViewHolder();
            holder.zanr = (TextView) vi.findViewById(R.id.zanr);
            holder.ime = (TextView)vi.findViewById(R.id.ime);
            holder.prezime = (TextView)vi.findViewById(R.id.prezime);

            holder.slika=(ImageView)vi.findViewById(R.id.slika);

            /************  Set holder with LayoutInflater ************/
            vi.setTag( holder );
        }
        else
            holder=(ViewHolder) vi.getTag();

        if(data.size()<=0)
        {
            holder.zanr.setText("No Data");
            holder.ime.setText("No Data");
            holder.prezime.setText("No Data");
        }
        else
        {
            /***** Get each Model object from Arraylist ********/
            muzicar = null;
            muzicar = ( Muzicar ) data.get( position );

            /************  Set Model values in Holder elements ***********/
            holder.zanr.setText( muzicar.getZanr() );
            holder.ime.setText( muzicar.getIme() );
            holder.prezime.setText( muzicar.getPrezime() );

            holder.slika.setImageResource(
                                            //ime paketa
                   res.getIdentifier("ba.unsa.etf.rma.rma_t1_z1:drawable/"+muzicar.getZanr().toLowerCase(),null,null));

            /******** Set Item Click Listner for LayoutInflater for each row *******/
            //vi.setOnClickListener(new OnItemClickListener( position ));
        }
        return vi;
    }

    @Override
    public void onClick(View v)
    {
        Log.v("CustomAdapter", "=====Row button clicked=====");
    }

    /********* Called when Item click in ListView ************/
    /*private class OnItemClickListener  implements View.OnClickListener
    {
        private int mPosition;

        OnItemClickListener(int position)
        {
            mPosition = position;
        }

        @Override
        public void onClick(View arg0)
        {
            MainActivity prozor = (MainActivity) activity;

            //  Call  onItemClick Method inside CustomListViewAndroidExample Class ( See Below )
            prozor.onItemClick(mPosition);
        }
    }*/
}

