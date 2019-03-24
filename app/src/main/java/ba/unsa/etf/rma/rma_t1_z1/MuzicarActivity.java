package ba.unsa.etf.rma.rma_t1_z1;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class MuzicarActivity extends AppCompatActivity
{

    private ImageView mprSlika;
    private TextView mprIme;
    private TextView mprPrezime;
    private TextView mprZanr;
    private ArrayList<String> mprLista = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_muzicar);
        mprIme = (TextView) findViewById(R.id.mprIme);
        mprPrezime = (TextView) findViewById(R.id.mprPrezime);
        mprZanr = (TextView) findViewById(R.id.mprZanr);
        mprSlika = (ImageView) findViewById(R.id.mprSlika);
    }
}
