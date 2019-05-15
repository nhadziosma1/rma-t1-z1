package ba.unsa.etf.rma.rma_t1_z1.Adapteri;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import ba.unsa.etf.rma.rma_t1_z1.Klase.Album;
import ba.unsa.etf.rma.rma_t1_z1.R;

public class AlbumAdapter extends ArrayAdapter<Album>
{
    private Context context;
    private ArrayList<Album> listaAlbuma;

    public AlbumAdapter(Context context, int textViewResourceId, ArrayList<Album> values)
    {
        super(context, textViewResourceId, values);

        this.context = context;
        this.listaAlbuma = values;
    }

    public int getCount(){
        return listaAlbuma.size();
    }

    public Album getItem(int position){
        return listaAlbuma.get(position);
    }

    public long getItemId(int position){
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent)
    {
        final Album album = getItem(position);

        if(convertView == null)
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.adapter_album, parent, false);

        TextView tvNazivAlbuma = (TextView) convertView.findViewById(R.id.naslovAlbuma);
        tvNazivAlbuma.setText(album.getImeAlbuma());

        return convertView;
    }

}

