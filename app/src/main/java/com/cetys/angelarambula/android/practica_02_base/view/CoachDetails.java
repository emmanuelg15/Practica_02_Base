package com.cetys.angelarambula.android.practica_02_base.view;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import com.cetys.angelarambula.android.practica_01_base.R;
import com.cetys.angelarambula.android.practica_02_base.model.Coach;


public class CoachDetails extends ActionBarActivity {

    Coach theCoach;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.row_list);
        Intent intent = getIntent();
        Bundle b = getIntent().getExtras();
        if(b != null)
            theCoach = (Coach) b.getSerializable("coach");
        TextView txtID = (TextView) this.findViewById(R.id.txtRowID);
        TextView txtName = (TextView) this.findViewById(R.id.txtRowName);
        TextView txtTeam = (TextView) this.findViewById(R.id.txtRowTeam);

        txtID.setText(String.valueOf(theCoach.getnID()));
        txtName.setText(theCoach.getsName());
        txtTeam.setText(theCoach.getsTeam());
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_row_list, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
