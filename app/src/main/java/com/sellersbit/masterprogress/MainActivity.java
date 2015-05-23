package com.sellersbit.masterprogress;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBar;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.os.Build;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.zip.Inflater;


public class MainActivity extends ActionBarActivity {
    private static final String TAG = "ActionBarActivity";

    private int savedInt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.container, new PlaceholderFragment())
                    .commit();
        }
        LayoutInflater inflater = getLayoutInflater();
        //View rootView = inflater.inflate(R.layout.skill_list_fragment, this);
        String[] listcontents = new String[] {"one", "two", "three", "4", "t5e", "thr5ee", "thr7ee", "thr9wee"};
        //ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this, R.layout.skill_list_item, listcontents);
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(getApplicationContext(), R.layout.skill_list_item, listcontents);
        ListView listView = (ListView)findViewById(R.id.list);

        ArrayList<Skill> skillArrayList = new ArrayList<>();
        skillArrayList.add(new Skill("Android Dev"));
        skillArrayList.add(new Skill("Guitar"));
        skillArrayList.add(new Skill("Swimming"));
        SkillAdapter skillAdapter = new SkillAdapter(getApplicationContext(), this, skillArrayList);

        listView.setAdapter(skillAdapter);
        listView.setDividerHeight(0);
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
        switch (id){
            case R.id.action_settings:
                return true;
            case R.id.action_enterTime:
                Log.d(TAG, "EnterTime");
                //Show enterTime dialog
                //Dialog dialog = createEnterTimeDialog();
                //dialog.show();
                return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void showEnterTimeDialog(Skill skill){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        LayoutInflater inflater = getLayoutInflater();


        final View view = inflater.inflate(R.layout.alert_enter_time, null);
        final TextView textView_time = (TextView)view.findViewById(R.id.enterTime_time);
        TextView title = (TextView)view.findViewById(R.id.alertEnterTime_skillTitle);
        title.setText(skill.getTitle());
        final int[] timeSetInDialog = {0};
        Button button_h = (Button)view.findViewById(R.id.enterTime_button_h);
        button_h.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "Button_H: onClick" + savedInt);
                timeSetInDialog[0] += 60;
                Log.d(TAG, "timesSetInDialog: " + timeSetInDialog[0]);
                textView_time.setText(String.valueOf(timeSetInDialog[0]));
                saveInt(timeSetInDialog[0]);
            }
        });
        Log.d(TAG, "POST timesSetInDialog: " + timeSetInDialog[0]);
        int time = Integer.valueOf(textView_time.getText().toString());

        builder.setView(view)
                .setPositiveButton(R.string.alert_enterTime_PositiveButton, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        int result = Integer.valueOf(textView_time.getText().toString());
                        ((TextView)findViewById(R.id.textView)).setText(String.valueOf(savedInt));
                        Log.d(TAG, "EnterTime: Confirm");
                    }
                })
                .setNegativeButton(R.string.alert_enterTime_NegativeButton, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Log.d(TAG, "EnterTime: Cancel");
                    }
                });


        Log.d(TAG, "Post: " + savedInt);
        builder.create().show();
    }


    private void saveInt(int a){
        savedInt = a;
    }


    /**
     * A placeholder fragment containing a simple view.
     */
    public static class PlaceholderFragment extends Fragment {

        public PlaceholderFragment() {
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_main, container, false);
            return rootView;
        }
    }

    public void hello(){

    }
}
