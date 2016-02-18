package com.utilities.dhananjayan.utilitiesclasses;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;


public class MainActivity extends ActionBarActivity implements View.OnClickListener {

    String xx;
    String istablet;
    Button btn1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
       xx = String.valueOf(CommonUtils.getSdkVersion());
       istablet = String.valueOf(CommonUtils.isTablet(this));
        CommonUtils.makeToast(this, xx);
        CommonUtils.makeToast(this, istablet);
        FileUtils.writeFileInternalStorage("Dhananjahy ankushrao",this,"PersonalDetails.txt");
        CommonUtils.makeToast(this, FileUtils.readFileInternalStorage("PersonalDetails.txt", this));
        btn1 = (Button) findViewById(R.id.button1);
        btn1.setOnClickListener(this);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
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

    @Override
    public void onClick(View v) {
        NotificationHelperUtils.fireNotification("Test","TEst",this,1,null);
    }
}
