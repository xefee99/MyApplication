package kr.ac.seoultech.myapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class SimpleListActivity extends AppCompatActivity {

    private ListView listView;
    private ArrayAdapter<String> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_simple_list);

        listView = (ListView) findViewById(R.id.list_view);

        List<String> items = findList();
        adapter = new ArrayAdapter<String>(
                this,
                android.R.layout.simple_list_item_1,
                items);

        listView.setAdapter(adapter);

    }

    private List<String> findList() {
        List<String> list = new ArrayList<>();
        list.add("Seoul");
        list.add("Busan");
        list.add("Incheon");
        for (int i=1; i<40; i++) {
            list.add("Data " +i);
        }
        return list;
    }


}
