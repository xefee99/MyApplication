package kr.ac.seoultech.myapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;

import kr.ac.seoultech.myapplication.model.Todo;

public class TodoDetailActivity extends AppCompatActivity {

    private EditText etTitle;
    private EditText etContent;
    private Todo todo;
    private int position;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_todo_detail);

        etTitle = (EditText) findViewById(R.id.et_title);
        etContent = (EditText) findViewById(R.id.et_content);

        Intent intent = getIntent();
        todo = (Todo) intent.getSerializableExtra("todo");
        position = intent.getIntExtra("position", -1);

        etTitle.setText(todo.getTitle());
        etContent.setText(todo.getContent());

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_todo_detail, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_save : {
                String title = etTitle.getText().toString();
                String content = etContent.getText().toString();

                todo.setTitle(title);
                todo.setContent(content);

                Intent intent = new Intent();
                intent.putExtra("todo", todo);
                intent.putExtra("position", position);

                setResult(RESULT_OK, intent);
                finish();

                return true;
            }
        }
        return false;
    }


}
