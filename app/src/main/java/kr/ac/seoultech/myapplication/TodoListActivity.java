package kr.ac.seoultech.myapplication;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import kr.ac.seoultech.myapplication.adapter.TodoAdapter;
import kr.ac.seoultech.myapplication.model.Todo;

public class TodoListActivity extends AppCompatActivity
                                implements View.OnClickListener,
                                    AdapterView.OnItemClickListener{

    private final static int REQUEST_CODE_ADD = 1;
    private final static int REQUEST_CODE_DETAIL = 2;


    private ListView listView;
    private TodoAdapter adapter;
    private EditText etTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_todo_list);

        listView = (ListView) findViewById(R.id.list_view);
        etTitle = (EditText) findViewById(R.id.et_title);

        findViewById(R.id.btn_save).setOnClickListener(this);

        List<Todo> items = findTodoList();
        adapter = new TodoAdapter(this, R.layout.list_item_todo, items);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(this);

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
        if (resultCode != Activity.RESULT_OK)  return;

        if (requestCode == REQUEST_CODE_ADD) {
            Todo todo = (Todo) data.getSerializableExtra("todo");
            adapter.addItem(0, todo);
        }
        else if (requestCode == REQUEST_CODE_DETAIL) {
            Todo todo = (Todo) data.getSerializableExtra("todo");
            int position = data.getIntExtra("position", -1);

            adapter.setItem(position, todo);

        }


    }

    private List<Todo> findTodoList() {
        List<Todo> list = new ArrayList<>();
        for (int i=0; i<40; i++) {
            list.add(new Todo(0L, "제목" + i, "내용" +i, new Date()));
        }

        return list;
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_save : {
                String title = etTitle.getText().toString();
                Todo todo = new Todo(0L, title, "", new Date());
                adapter.addItem(0, todo);

                etTitle.setText("");
                hideKeyboard(etTitle);
                break;
            }
        }
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

        Todo todo = (Todo) adapter.getItem(position);
        Intent intent = new Intent(this, TodoDetailActivity.class);
        intent.putExtra("todo", todo);
        intent.putExtra("position", position);

        startActivityForResult(intent, REQUEST_CODE_DETAIL);

    }




    private void hideKeyboard(EditText editText){
        InputMethodManager imm =
                (InputMethodManager)getSystemService(INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(editText.getWindowToken(), 0);
    }




}
