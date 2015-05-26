package galaxy.pegazus.bienetre;

import android.app.ListActivity;
import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.SimpleCursorAdapter;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import galaxy.pegazus.bienetre.data.LogDayDBHelper;
import galaxy.pegazus.bienetre.data.LogDay;


public class DisplayLogs extends ListActivity {

    public final static String EXTRA_MSG_ID = "galaxy.pegazus.bienetre.EXTRA_MSG_ID";

    private Cursor selectedItems=null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_logs);
        LogDayDBHelper db = new LogDayDBHelper(getApplicationContext());
        selectedItems = db.showAllcursor();

        Log.i("Dev","List des jours !!!");

        SimpleCursorAdapter simpleCursorAdapter =new SimpleCursorAdapter(getApplicationContext(),R.layout.day_log,selectedItems,
                new String[] {LogDay.LogDayEntry.COLUMN_NAME_DATE,LogDay.LogDayEntry._ID},new int[] { R.id.dayText },0);
        Log.i("Dev", "après set list !!!");
        setListAdapter(simpleCursorAdapter);




    }

    @Override
    protected  void onListItemClick(ListView l, View v, int position, long id){
        if(selectedItems !=null){
            Toast.makeText(getApplicationContext(),
                    "Click ListItem Number " + position+ " idclick="+id + " positiocursor="+selectedItems.getPosition()+ "  value="+selectedItems.getString(0), Toast.LENGTH_LONG )
                    .show();


            Log.i("Dev", "Click ListItem Number " + position+ " idclick="+id + " positiocursor="+selectedItems.getPosition()+ "  value="+selectedItems.getString(0)+ "  date="+selectedItems.getString(1));
            Intent intent = new Intent(this, SaveDay.class);
            intent.putExtra(EXTRA_MSG_ID, selectedItems.getString(0));
            startActivity(intent);




        }else{
            Toast.makeText(getApplicationContext(),
                    "Click ListItem Number " + position+ " idclick="+id, Toast.LENGTH_LONG )
                    .show();
        }
    }



}
