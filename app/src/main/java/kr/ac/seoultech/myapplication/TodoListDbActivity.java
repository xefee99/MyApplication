package kr.ac.seoultech.myapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import kr.ac.seoultech.myapplication.adapter.TodoAdapter;
import kr.ac.seoultech.myapplication.dao.TodoDao;
import kr.ac.seoultech.myapplication.helper.TodoDatabaseHelper;
import kr.ac.seoultech.myapplication.model.Todo;

public class TodoListDbActivity extends AppCompatActivity {

    private final static int REQUEST_CODE_ADD = 1;
    private final static int REQUEST_CODE_DETAIL = 2;

    private ListView listView;
    private TodoAdapter adapter;

    private TodoDatabaseHelper helper;
    private TodoDao todoDao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_todo_list_db);

        listView = (ListView) findViewById(R.id.list_view);
        adapter = new TodoAdapter(this, R.layout.list_item_todo, new ArrayList<Todo>());
        listView.setAdapter(adapter);

        this.helper  = new TodoDatabaseHelper(this);
        todoDao = new TodoDao(this.helper);

        reload();
    }

    @Override
    protected void onDestroy() {
        todoDao.close();
        super.onDestroy();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_todo_list, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_add : {
                Intent intent = new Intent(this, TodoAddActivity.class);
                startActivityForResult(intent, REQUEST_CODE_ADD);
                return true;
            }
        }
        return false;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode != RESULT_OK) return;

        if (requestCode == REQUEST_CODE_ADD) {
            Todo todo = (Todo) data.getSerializableExtra("todo");
            todoDao.insert(todo);
            reload();
        }

    }


    private void reload() {
        List<Todo> list = todoDao.selectList();
        adapter.setItems(list);
    }


}
