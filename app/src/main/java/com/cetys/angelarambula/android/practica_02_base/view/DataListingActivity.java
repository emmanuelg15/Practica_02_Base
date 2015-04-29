package com.cetys.angelarambula.android.practica_02_base.view;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.cetys.angelarambula.android.practica_01_base.R;
import com.cetys.angelarambula.android.practica_02_base.controller.CoachesAdapter;
import com.cetys.angelarambula.android.practica_02_base.model.Coach;
import com.cetys.angelarambula.android.practica_02_base.utils.ParserUtils;

import java.util.ArrayList;


public class DataListingActivity extends Activity {

    ListView lstView = null;
    CoachesAdapter adapter = null;
    ArrayList<Coach> theCoaches = new ArrayList<Coach>();
    ParserUtils parserUtils;
    ProgressDialog progress;
    GetCoaches task = null;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data_listing);
        lstView = (ListView) findViewById(R.id.lstList);
        lstView.setOnItemClickListener(new AdapterView.OnItemClickListener()
        {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l)
            {
                Coach coach = (Coach)adapter.getItem(i);
                Intent intent = new Intent(DataListingActivity.this, CoachDetails.class);
                Bundle extras = new Bundle();
                extras.putSerializable("coach",coach);
                intent.putExtras(extras);
                startActivity(intent);
            }
        });
        adapter = new CoachesAdapter(this);
        lstView.setAdapter(adapter);
        start();
    }

    private void start() {
        // instantiate a new async task
        task = new GetCoaches(this);
        task.execute();
    }

    public void ShowProcessDialog()
    {
        progress = ProgressDialog.show(this, this.getString(R.string.wait),
                this.getString(R.string.data, true));
    }
    public void Dismiss()
    {
        progress.dismiss();
    }

    private  class GetCoaches extends AsyncTask<Void, Integer, ArrayList<Coach>> {
        public DataListingActivity activity;
        public GetCoaches(DataListingActivity a)
        {
            this.activity = a;
        }

        @Override
        protected void onPreExecute()
        {
            ShowProcessDialog();
            lstView = (ListView) findViewById(R.id.lstList);
            adapter = new CoachesAdapter(this.activity);
            lstView.setAdapter(adapter);
            parserUtils = new ParserUtils();
            super.onPreExecute();
        }

        @Override
        protected ArrayList<Coach> doInBackground(Void... params)
        {
            try
            {
                theCoaches = parserUtils.getCoaches();
                return  theCoaches;
            }
            catch (Exception ex)
            {
                System.out.println(ex);
            }
            return  null;
        }

        @Override
        protected void onPostExecute(ArrayList<Coach> theCoaches) {
            super.onPostExecute(theCoaches);
            for (Coach coach : theCoaches) {
                adapter.add(coach);
            }
            adapter.notifyDataSetChanged();
            Dismiss();
        }
    }
}
