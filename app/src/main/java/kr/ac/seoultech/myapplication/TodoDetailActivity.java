package kr.ac.seoultech.myapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;

import kr.ac.seoultech.myapplication.model.Todo;

public class TodoDetailActivity extends AppCompatActivity {

    private EditText etTitle;
    private EditText etContent;
    private Todo todo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_todo_detail);

        etTitle = (EditText) findViewById(R.id.et_title);
        etContent = (EditText) findViewById(R.id.et_content);

        Intent intent = getIntent();
        todo = (Todo) intent.getSerializableExtra("todo");

        etTitle.setText(todo.getTitle());
        etContent.setText(todo.getContent());


    }
}
