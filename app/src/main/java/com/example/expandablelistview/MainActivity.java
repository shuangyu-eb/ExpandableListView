package com.example.expandablelistview;

import android.util.Log;
import android.view.View;
import android.widget.ExpandableListView;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private SelectorView selectorView;
    List<String> list;
    private TextView show;

    private static final String TAG = "MainActivity";

    public final ArrayList<String> unpaidList = new ArrayList<String>();
    ArrayList<String> animalNames = new ArrayList<>();



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        show = (TextView) findViewById(R.id.select_year);

        list = new ArrayList<>();

        String testResponse = "{\n" +
                "\t\"1111\": \"check1\",\n" +
                "\t\"2222\": \"check2\",\n" +
                "\t\"3333\": \"check3\",\n" +
                "\t\"4444\": \"check4\",\n" +
                "\t\"5555\": \"check5\",\n" +
                "\t\"6666\": \"check6\",\n" +
                "\t\"7777\": \"check7\",\n" +
                "\t\"8888\": \"check8\"\n" +
                "}";
        JSONObject json = null;
        try {
            json = new JSONObject(testResponse);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        list = new ArrayList<>();

        //                          JSONArray array = json.getJSONArray("Pendings");
        Iterator<String> keys = json.keys();
        int checkCount = 1;
        while (keys.hasNext()) {
            String key = keys.next();
            String ISV = "test ISV";
            unpaidList.add(key);
            animalNames.add("Check "+ checkCount++);
        }



        selectorView = findViewById(R.id.selector);

        for (int i = 0; i < 10; i++) {
            list.add(i+"月");
        }

        selectorView.setAdapter(adapter);

        selectorView.setOnItemCheckListener(new SelectorView.OnItemCheckListener() {
            @Override
            public void onItemChecked(int position) {
                Log.i(TAG, "onItemChecked: "+position);
                show.setText(unpaidList.get(position));
            }

            @Override
            public void onScrolled(int position) {
                Log.i(TAG, "onScrolled: "+position);
            }
        });
    }

    public void left(View view){
        selectorView.left();
    }

    public void right(View view){
        selectorView.right();
    }

    SelectorView.SeletcorAdapter adapter = new SelectorView.SeletcorAdapter(){
        @Override
        public int getItemCount() {
            return unpaidList.size();
        }

        @Override
        public void setView(View view, int position) {
            ((TextView)view).setText(unpaidList.get(position));
        }


    };
}
