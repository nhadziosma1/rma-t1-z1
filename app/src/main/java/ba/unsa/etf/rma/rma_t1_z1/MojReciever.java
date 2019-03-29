package ba.unsa.etf.rma.rma_t1_z1;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.widget.Toast;

public class MojReciever extends BroadcastReceiver
{
    @Override
    public void onReceive(Context context, Intent intent)
    {
        if(Intent.ACTION_BOOT_COMPLETED.equals(intent.getAction()))
        {
            Toast.makeText(context, "zavrseno pokretanje", Toast.LENGTH_SHORT).show();
        }
        else if(ConnectivityManager.CONNECTIVITY_ACTION.equals(intent.getAction()))
        {
            //drugi parametar je defaultna vrijednost ako prvi parametar, koji predstavlja predefinisanu konstantu, ne
            // vrati nista
            boolean konstantaNemaInterneta = intent.getBooleanExtra(ConnectivityManager.EXTRA_NO_CONNECTIVITY,false);

            if(konstantaNemaInterneta == true)
            Toast.makeText(context, "uredjaj iskljucen sa interneta", Toast.LENGTH_SHORT).show();
        }
    }
}
